package groep4.a_zalf.Activities;

import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import groep4.a_zalf.R;

public class Diagnose extends AppCompatActivity {

    private TextView tvDiagnose, tvPrescriptie;
    private ImageView ivQRcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnose);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initializeUIComponents();

        groep4.a_zalf.Collection.Diagnose diagnose = new groep4.a_zalf.Collection.Diagnose("Eczeem", "", "Prednison");

        tvDiagnose.setText(diagnose.getBeschrijving());
        tvPrescriptie.setText(diagnose.getPrescriptie());
    }

    private void initializeUIComponents() {
        tvDiagnose = (TextView) findViewById(R.id.tvDiagnose);
        tvPrescriptie = (TextView) findViewById(R.id.tvPrescriptie);
        ivQRcode = (ImageView) findViewById(R.id.ivQRcode);
    }
}
