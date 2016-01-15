package groep4.a_zalf.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import groep4.a_zalf.Collection.Afspraak;
import groep4.a_zalf.Collection.Arts;
import groep4.a_zalf.Collection.Tijd;
import groep4.a_zalf.R;

public class AfspraakMakenArtsTijd extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private Spinner spDag;
    private TextView tvTijd;
    private ListView lvArtsenTijd;
    private List<Tijd> artsenTijd;

    public static Activity tijdActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afspraak_maken_arts_tijd);
        artsenTijd = new ArrayList<>();
        tijdActivity = this;
        artsenTijd.add(new Tijd("10:00 - 10:30"));
        artsenTijd.add(new Tijd("11:00 - 11:30"));
        artsenTijd.add(new Tijd("13:00 - 13:30"));
        artsenTijd.add(new Tijd("13:30 - 14:00"));
        artsenTijd.add(new Tijd("15:30 - 16:00"));

        initializeUIComponents();
        AfspraakArtsTijdListAdapter aala = new AfspraakArtsTijdListAdapter(getApplicationContext(), artsenTijd);
        lvArtsenTijd.setAdapter(aala);
        lvArtsenTijd.setOnItemClickListener(this);

        spDag.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (!selectedItemView.toString().equals("Kies hier uw datum")) {
                    spDag.setVisibility(View.INVISIBLE);
                    //tvTijd.setVisibility(View.VISIBLE);
                    lvArtsenTijd.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

    }


    private void initializeUIComponents() {
        lvArtsenTijd = (ListView) findViewById(R.id.lvTijden);
        spDag = (Spinner) findViewById(R.id.spDag);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AfspraakArtsTijdListAdapter ala = (AfspraakArtsTijdListAdapter) parent.getAdapter();
        Tijd tijd = ala.getItem(position);
        String arts = (String) getIntent().getExtras().getString("Arts");
        Intent intent = new Intent(this, PhotoActivity.class);
        intent.putExtra("Tijd", tijd.getTijd());
        intent.putExtra("Arts", arts);
        intent.putExtra("vraagNummer", 1);
        startActivity(intent);
    }
}
