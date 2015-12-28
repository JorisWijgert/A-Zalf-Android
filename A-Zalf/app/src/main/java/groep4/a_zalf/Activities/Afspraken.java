package groep4.a_zalf.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import groep4.a_zalf.R;

public class Afspraken extends AppCompatActivity {

    private Button btDiagnose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afspraken);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        InitializeUIComponents();

        btDiagnose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent diagnoseActivity = new Intent(getApplicationContext(), Diagnose.class);
                startActivity(diagnoseActivity);
            }
        });
    }

    private void InitializeUIComponents() {
        btDiagnose = (Button) findViewById(R.id.btDiagnose);
    }

}
