package groep4.a_zalf.Activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import groep4.a_zalf.Collection.Afspraak;
import groep4.a_zalf.R;

/**
 * Created by jvdwi on 8-1-2016.
 */
public class AfspraakListAdapter extends ArrayAdapter<Afspraak> {
    private Context context;
    private List<Afspraak> afspraken;

    public AfspraakListAdapter(Context context, List<Afspraak> afspraken) {
        super(context, R.layout.activity_afspraken, afspraken);
        this.afspraken = afspraken;
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Afspraak reqAfspraak = afspraken.get(position);
        View afspraakView = convertView;

        if (afspraakView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            afspraakView = inflater.inflate(R.layout.afspraak_list_item, null);
        }


        TextView datum = (TextView) afspraakView.findViewById(R.id.tvDatum);
        TextView tijden = (TextView) afspraakView.findViewById(R.id.tvTijden);
        TextView arts = (TextView) afspraakView.findViewById(R.id.tvArts);
        Calendar tijdstip = reqAfspraak.getTijdstip();
        Calendar tijdsduur = reqAfspraak.getTijdsduur();
        tijdstip.add(Calendar.MINUTE, -25);
        String beginString = "";
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
        if (tijdstip != null) {
            beginString += sdf1.format(tijdstip.getTime());
        }
        datum.setText(beginString);

        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
        Calendar returnCal = tijdstip;
        String endString = "";
        if (returnCal != null) {
            endString += sdf2.format(tijdstip.getTime()) + " - ";
            returnCal.add(Calendar.MINUTE, tijdsduur.get(Calendar.MINUTE));
            endString += sdf2.format(returnCal.getTime());
        }
        tijden.setText(endString);
        arts.setText("Arts: Theodore");
//
//        beginDate.setText(reqAfspraak.getBeginDateString());
//
//
//        endDate.setText(reqAfspraak.getEndDateString());

        return afspraakView;
    }

}
