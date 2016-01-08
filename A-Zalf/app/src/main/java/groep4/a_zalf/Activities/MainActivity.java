package groep4.a_zalf.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import groep4.a_zalf.Collection.Ziekenhuis;
import groep4.a_zalf.R;

public class MainActivity extends AppCompatActivity {

    private EditText etPatientNr, etWachtwoord;
    private Button btInloggen;
    private Ziekenhuis ziekenhuis;
    private Toolbar tbInloggen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tbInloggen = (Toolbar) findViewById(R.id.toolbar);
        this.setSupportActionBar(tbInloggen);
        getSupportActionBar().setTitle("A-Zalf");

        ziekenhuis = new Ziekenhuis(true, getApplicationContext());

        initializeUIComponents();
        inloggen();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void inloggen() {
        btInloggen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ziekenhuis.inloggen(etPatientNr.getText().toString(), etWachtwoord.getText().toString())) {
                    final Intent afsprakenActivity = new Intent(getApplicationContext(), Afspraken.class);
                    startActivity(afsprakenActivity);
                } else {
                    System.out.println("patientNr of wachtwoord is fout.");
                }
            }
        });

    }

    private void initializeUIComponents() {
        etPatientNr = (EditText) findViewById(R.id.etPatientNr);
        etWachtwoord = (EditText) findViewById(R.id.etWachtwoord);
        btInloggen = (Button) findViewById(R.id.btInloggen);
    }
}
