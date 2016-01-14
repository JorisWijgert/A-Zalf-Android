package groep4.a_zalf.Activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import groep4.a_zalf.Collection.Afspraak;
import groep4.a_zalf.Collection.Arts;
import groep4.a_zalf.R;

/**
 * Created by jvdwi on 8-1-2016.
 */
public class AfspraakArtsListAdapter extends ArrayAdapter<Arts> {
    private Context context;
    private List<Arts> artsen;

    public AfspraakArtsListAdapter(Context context, List<Arts> artsen) {
        super(context, R.layout.activity_afspraken, artsen);
        this.artsen = artsen;
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Arts reqArts = artsen.get(position);
        View afspraakView = convertView;

        if (afspraakView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            afspraakView = inflater.inflate(R.layout.afspraak_list_item, null);
        }


        ImageView icon = (ImageView) afspraakView.findViewById(R.id.ivIcon);
        TextView arts = (TextView) afspraakView.findViewById(R.id.tvTijden);

        String artsNaam = reqArts.getNaam();
        arts.setText("Dermatoloog " + artsNaam);
        switch (artsNaam){
            case "Bianca":
                icon.setImageDrawable(this.getContext().getResources().getDrawable(R.drawable.artsbianca));
                break;
            case "Friso":
                icon.setImageDrawable(this.getContext().getResources().getDrawable(R.drawable.artsfriso));
                break;
            case "Philips":
                icon.setImageDrawable(this.getContext().getResources().getDrawable(R.drawable.artsphilips));
                break;
            case "Theodore":
                icon.setImageDrawable(this.getContext().getResources().getDrawable(R.drawable.artstheodore));
                break;
            default:
                icon.setImageDrawable(this.getContext().getResources().getDrawable(R.drawable.artstrudy));
                break;
        }

        return afspraakView;
    }

}
