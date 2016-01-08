package groep4.a_zalf.Protocol;

/**
 * Created by Stan on 3-1-2016.
 */
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import groep4.a_zalf.Activities.Suggesties;

public class GetAllAppsTask extends AsyncTask<Void, Void, List<ApplicationInfo>> {

    private Suggesties activity;
    private List<ApplicationInfo> apps;
    private PackageManager packageManager;

    public GetAllAppsTask(Suggesties activity, List<ApplicationInfo> apps, PackageManager pm) {
        this.activity = activity;
        this.apps = apps;
        this.packageManager = pm;
    }

    @Override
    protected List<ApplicationInfo> doInBackground(Void... params) {
        apps = checkForLaunchIntent(packageManager.getInstalledApplications(PackageManager.GET_META_DATA));
        return apps;
    }

    private List<ApplicationInfo> checkForLaunchIntent(List<ApplicationInfo> list) {
        ArrayList<ApplicationInfo> applist = new ArrayList<>();
        ArrayList<ApplicationInfo> filterList = new ArrayList<ApplicationInfo>();
        for (ApplicationInfo applicationInfo : list) {
            try {
                if (packageManager.getLaunchIntentForPackage(applicationInfo.packageName) != null) {
                    applist.add(applicationInfo);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        //Comment this part if u want to find all the Apps installed, u can filter them by search after.
        ApplicationInfo newApplicationInfo = new ApplicationInfo();
        newApplicationInfo.name = "Enqu\u00EAte";

        filterList.add(newApplicationInfo);
        for (int i = 0; i < applist.size(); i++) {
            if ((applist.get(i).loadLabel(packageManager).toString().toUpperCase())
                    .contains("Youtube".toString().toUpperCase()) && !(applist.get(i).loadLabel(packageManager).toString().toUpperCase())
                    .contains("Live".toString().toUpperCase()) ) {

                ApplicationInfo applicationInfo = applist.get(i);
                filterList.add(applicationInfo);
            } else if ((applist.get(i).loadLabel(packageManager).toString().toUpperCase())
                    .contains("Facebook".toString().toUpperCase())) {

                ApplicationInfo applicationInfo = applist.get(i);
                filterList.add(applicationInfo);
            } else if ((applist.get(i).loadLabel(packageManager).toString().toUpperCase())
                    .contains("NOS".toString().toUpperCase())) {

                ApplicationInfo applicationInfo = applist.get(i);
                filterList.add(applicationInfo);
            }
        }

        applist.clear();
        for(ApplicationInfo applicationInfo : filterList)
        {
            applist.add(applicationInfo);
        }
        //Comment above.

        return applist;
    }

    @Override
    protected void onPostExecute(List<ApplicationInfo> list) {
        super.onPostExecute(list);
        activity.callBackDataFromAsynctask(list);
    }
}