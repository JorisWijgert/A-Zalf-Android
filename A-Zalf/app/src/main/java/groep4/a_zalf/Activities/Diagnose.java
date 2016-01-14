package groep4.a_zalf.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import groep4.a_zalf.Database.DbHandler;
import groep4.a_zalf.R;

public class Diagnose extends AppCompatActivity {

    private Toolbar tbDiagnose;
    private TextView tvDiagnose, tvPrescriptie;
    private ImageView ivQRcode;

    private DbHandler handler;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnose);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        handler = new DbHandler(getApplicationContext(), null, null, 1);
        preferences = getSharedPreferences("patient", Context.MODE_PRIVATE);

        initializeUIComponents();

        String patientNr = preferences.getString("patientKey", null);
        System.out.println("patientnr: " + patientNr);

        groep4.a_zalf.Collection.Diagnose diagnose = handler.findDiagnoseBy(patientNr);

        //groep4.a_zalf.Collection.Diagnose diagnose = new groep4.a_zalf.Collection.Diagnose("Na direct contact met de brandharen kan binnen 8 uur een rode pijnlijke huiduitslag met hevige jeuk ontstaan, die zich kenmerkt door bultjes, pukkeltjes of met vochtgevulde blaasjes die kunnen gaan ontsteken. Vaak ontstaan deze reacties op de onbedekte huid, maar door versleping met bijvoorbeeld zweet kan dit ook plaatsvinden op de bedekte huid.", "Prednison. \n\nDrie maal daags om de 2 uur innemen.");

        tbDiagnose = (Toolbar) findViewById(R.id.toolbar);
        this.setSupportActionBar(tbDiagnose);
        getSupportActionBar().setTitle("A-Zalf");

        tvDiagnose.setText(diagnose.getBeschrijving());
        tvPrescriptie.setText(diagnose.getPrescriptie() + "\n\n" + diagnose.getInname());
    }

    private void initializeUIComponents() {
        tvDiagnose = (TextView) findViewById(R.id.tvDiagnose);
        tvPrescriptie = (TextView) findViewById(R.id.tvPrescriptie);
        ivQRcode = (ImageView) findViewById(R.id.ivQRcode);
    }
}
