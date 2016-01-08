package groep4.a_zalf.Protocol;

/**
 * Created by Stan on 3-1-2016.
 */
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import groep4.a_zalf.Activities.Suggesties;
import groep4.a_zalf.R;

public class ApplicationAdapter extends ArrayAdapter<ApplicationInfo> {
    private List<ApplicationInfo> appsList;
    private List<ApplicationInfo> originalList;
    private Suggesties activity;
    private PackageManager packageManager;


    public ApplicationAdapter(Suggesties activity, int textViewResourceId, List<ApplicationInfo> appsList) {
        super(activity, textViewResourceId, appsList);
        this.activity = activity;
        this.appsList = appsList;
        this.originalList = appsList;
        packageManager = activity.getPackageManager();
    }

    @Override
    public int getCount() {
        return appsList.size();
    }

    @Override
    public ApplicationInfo getItem(int position) {
        return appsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return appsList.indexOf(getItem(position));
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        // If holder not exist then locate all view from UI file.
        if (convertView == null) {
            // inflate UI from XML file
            convertView = inflater.inflate(R.layout.item_listviewsuggestie, parent, false);
            // get all UI view
            holder = new ViewHolder(convertView);
            // set tag for holder
            convertView.setTag(holder);
        } else {
            // if holder created, get tag from view
            holder = (ViewHolder) convertView.getTag();
        }

        //setting data to views
        holder.appName.setText(getItem(position).loadLabel(packageManager)); //get app name
        //holder.appPackage.setText(getItem(position).packageName); //get app package
        if((getItem(position).loadLabel(packageManager).toString().toUpperCase())
                .contains("Enqu\u00EAte".toString().toUpperCase())) {
            holder.appPackage.setText("Help het AZM door deze enqu\u00EAte in te vullen.");
            holder.icon.setImageDrawable(this.getContext().getResources().getDrawable(R.drawable.checkicon));
        } else if((getItem(position).loadLabel(packageManager).toString().toUpperCase())
                .contains("Youtube".toString().toUpperCase())) {
            holder.appPackage.setText("1 video");
            holder.icon.setImageDrawable(getContext().getPackageManager().getApplicationIcon(getItem(1)));
        } else if((getItem(position).loadLabel(packageManager).toString().toUpperCase())
                .contains("NOS".toString().toUpperCase())) {
            holder.appPackage.setText("3 nieuwsberichten");
            holder.icon.setImageDrawable(getContext().getPackageManager().getApplicationIcon(getItem(3)));
        } else if((getItem(position).loadLabel(packageManager).toString().toUpperCase())
                .contains("Facebook".toString().toUpperCase())) {
            holder.appPackage.setText("2 meldingen");
            holder.icon.setImageDrawable(getContext().getPackageManager().getApplicationIcon(getItem(2)));
        }




        //set on click event for each item view
        convertView.setOnClickListener(onClickListener(position));

        return convertView;
    }

    private View.OnClickListener onClickListener(final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplicationInfo app = appsList.get(position);
                try {
                    Intent intent = packageManager.getLaunchIntentForPackage(app.packageName);
                    if((app.loadLabel(packageManager).toString().toUpperCase())
                            .contains("enqu\u00EAte".toString().toUpperCase()))
                    {
                        Uri uri = Uri.parse("http://www.survio.com/survey/d/M3U9E2S1T9I3N9G1W");
                        Intent i = new Intent(Intent.ACTION_VIEW,uri);
                        activity.startActivity(i);
                        return;
                    }
                    activity.startActivity(intent);
                    if (null != intent) {
                        activity.startActivity(intent);
                    }
                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private class ViewHolder {
        private ImageView icon;
        private TextView appName;
        private TextView appPackage;

        public ViewHolder(View v) {
            icon = (ImageView) v.findViewById(R.id.icon);
            appName = (TextView) v.findViewById(R.id.name);
            appPackage = (TextView) v.findViewById(R.id.app_package);
        }
    }
    private class AppsFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                ArrayList<ApplicationInfo> filterList = new ArrayList<ApplicationInfo>();
                for (int i = 0; i < originalList.size(); i++) {
                    if ((originalList.get(i).loadLabel(packageManager).toString().toUpperCase())
                            .contains(constraint.toString().toUpperCase())) {

                        ApplicationInfo applicationInfo = originalList.get(i);
                        filterList.add(applicationInfo);
                    }
                }

                results.count = filterList.size();
                results.values = filterList;

            } else {
                results.count = originalList.size();
                results.values = originalList;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            appsList = (ArrayList<ApplicationInfo>) results.values;
            notifyDataSetChanged();

            if (appsList.size() == originalList.size()) {
                activity.updateUILayout("All apps (" + appsList.size() + ")");
            } else {
                activity.updateUILayout("Filtered apps (" + appsList.size() + ")");
            }
        }
    }
}