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


        TextView beginDate = (TextView) afspraakView.findViewById(R.id.textViewBeginDate);
        TextView endDate = (TextView) afspraakView.findViewById(R.id.textViewEndDate);
        Calendar tijdstip = reqAfspraak.getTijdstip();
        Calendar tijdsduur = reqAfspraak.getTijdsduur();
        tijdstip.add(Calendar.MINUTE, -25);
        String beginString = "Vanaf: ";
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        if (tijdstip != null) {
            beginString += sdf.format(tijdstip.getTime());
        }
        beginDate.setText(beginString);

        Calendar returnCal = tijdstip;
        returnCal.add(Calendar.MINUTE, tijdsduur.get(Calendar.MINUTE));
        String endString = "Tot: ";
        if (returnCal != null) {
            endString += sdf.format(returnCal.getTime());
        }
        endDate.setText(endString);
//
//        beginDate.setText(reqAfspraak.getBeginDateString());
//
//
//        endDate.setText(reqAfspraak.getEndDateString());

        return afspraakView;
    }

}
