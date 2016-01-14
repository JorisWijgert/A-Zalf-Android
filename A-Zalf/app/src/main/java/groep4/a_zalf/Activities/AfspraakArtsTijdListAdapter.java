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
import groep4.a_zalf.Collection.Tijd;
import groep4.a_zalf.R;

/**
 * Created by jvdwi on 8-1-2016.
 */
public class AfspraakArtsTijdListAdapter extends ArrayAdapter<Tijd> {
    private Context context;
    private List<Tijd> tijden;

    public AfspraakArtsTijdListAdapter(Context context, List<Tijd> tijden) {
        super(context, R.layout.activity_afspraken, tijden);
        this.tijden = tijden;
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Tijd reqTijd = tijden.get(position);
        View afspraakView = convertView;

        if (afspraakView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            afspraakView = inflater.inflate(R.layout.afspraak_list_item, null);
        }

        TextView datum = (TextView) afspraakView.findViewById(R.id.tvTijden);
        TextView tijden = (TextView) afspraakView.findViewById(R.id.tvTijden);

        String tijd = reqTijd.getTijd();
        tijden.setText(tijd);

        return afspraakView;
    }

}
