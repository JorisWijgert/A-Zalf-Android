package groep4.a_zalf.Activities;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import groep4.a_zalf.Collection.Arts;
import groep4.a_zalf.R;

public class AfspraakMakenArts extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lvAfspraken;
    private List<Arts> artsen;
    private FloatingActionButton faButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afspraken);
        faButton  = (FloatingActionButton) findViewById(R.id.fab);
        artsen = new ArrayList<>();
        faButton.setVisibility(View.INVISIBLE);
        artsen.add(new Arts("Bianca"));
        artsen.add(new Arts("Friso"));
        artsen.add(new Arts("Philips"));
        artsen.add(new Arts("Theodore"));
        artsen.add(new Arts("Trudy"));

        AfspraakArtsListAdapter ala = new AfspraakArtsListAdapter(getApplicationContext(), artsen);

        lvAfspraken = (ListView) findViewById(R.id.listViewAfspraken);
        lvAfspraken.setAdapter(ala);
        lvAfspraken.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
