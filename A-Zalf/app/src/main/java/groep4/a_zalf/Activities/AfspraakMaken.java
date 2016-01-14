package groep4.a_zalf.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import groep4.a_zalf.R;

public class AfspraakMaken extends AppCompatActivity {

    Button btAntwoord1, btAntwoord2, btAntwoord3;
    ProgressBar pbAfspraak;
    TextView tvVraag;
    private int vraagNummer;
    public String a1, a2, a3, a4, a5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afspraak_maken);

        initializeUIComponents();

        vraagNummer = (int) getIntent().getExtras().getInt("vraagNummer");

        switch (vraagNummer)
        {
            case 1:
                tvVraag.setText("Waar zit de aandoening ?");
                btAntwoord1.setText("Hoofd");
                btAntwoord2.setText("Romp");
                btAntwoord3.setText("Benen");
                pbAfspraak.setProgress(0);


                btAntwoord1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Intent afspraakMakenActivity = new Intent(getApplicationContext(), AfspraakMaken.class);

                        afspraakMakenActivity.putExtra("Antwoord1", btAntwoord1.getText());
                        String arts = (String) getIntent().getExtras().getString("Arts");
                        String tijd = (String) getIntent().getExtras().getString("Tijd");
                        afspraakMakenActivity.putExtra("Tijd", tijd);
                        afspraakMakenActivity.putExtra("Arts", arts);
                        vraagNummer++;
                        afspraakMakenActivity.putExtra("vraagNummer", vraagNummer);
                        startActivity(afspraakMakenActivity);
                    }
                });

                btAntwoord2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Intent afspraakMakenActivity = new Intent(getApplicationContext(), AfspraakMaken.class);


                        String arts = (String) getIntent().getExtras().getString("Arts");
                        String tijd = (String) getIntent().getExtras().getString("Tijd");
                        afspraakMakenActivity.putExtra("Tijd", tijd);
                        afspraakMakenActivity.putExtra("Arts", arts);
                        afspraakMakenActivity.putExtra("Antwoord1", btAntwoord2.getText());
                        vraagNummer++;
                        afspraakMakenActivity.putExtra("vraagNummer", vraagNummer);
                        startActivity(afspraakMakenActivity);
                    }
                });

                btAntwoord3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Intent afspraakMakenActivity = new Intent(getApplicationContext(), AfspraakMaken.class);

                        String arts = (String) getIntent().getExtras().getString("Arts");
                        String tijd = (String) getIntent().getExtras().getString("Tijd");
                        afspraakMakenActivity.putExtra("Tijd", tijd);
                        afspraakMakenActivity.putExtra("Arts", arts);
                        afspraakMakenActivity.putExtra("Antwoord1", btAntwoord3.getText());
                        vraagNummer++;
                        afspraakMakenActivity.putExtra("vraagNummer", vraagNummer);
                        startActivity(afspraakMakenActivity);
                    }
                });
                break;
            case 2:
                tvVraag.setText("Hoe ziet de aandoening eruit ?");
                btAntwoord1.setText("Glad");
                btAntwoord2.setText("Hobbeltjes");
                btAntwoord3.setText("._.");
                a1 = (String) getIntent().getExtras().getString("Antwoord1");
                btAntwoord3.setText(a1);
                pbAfspraak.setProgress(20);

                btAntwoord1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Intent afspraakMakenActivity = new Intent(getApplicationContext(), AfspraakMaken.class);

                        String arts = (String) getIntent().getExtras().getString("Arts");
                        String tijd = (String) getIntent().getExtras().getString("Tijd");
                        afspraakMakenActivity.putExtra("Tijd", tijd);
                        afspraakMakenActivity.putExtra("Arts", arts);
                        afspraakMakenActivity.putExtra("Antwoord1", a1);
                        afspraakMakenActivity.putExtra("Antwoord2", btAntwoord1.getText());
                        vraagNummer++;
                        afspraakMakenActivity.putExtra("vraagNummer", vraagNummer);
                        startActivity(afspraakMakenActivity);
                    }
                });

                btAntwoord2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Intent afspraakMakenActivity = new Intent(getApplicationContext(), AfspraakMaken.class);

                        String arts = (String) getIntent().getExtras().getString("Arts");
                        String tijd = (String) getIntent().getExtras().getString("Tijd");
                        afspraakMakenActivity.putExtra("Tijd", tijd);
                        afspraakMakenActivity.putExtra("Arts", arts);
                        afspraakMakenActivity.putExtra("Antwoord1", a1);
                        afspraakMakenActivity.putExtra("Antwoord2", btAntwoord2.getText());
                        vraagNummer++;
                        afspraakMakenActivity.putExtra("vraagNummer", vraagNummer);
                        startActivity(afspraakMakenActivity);
                    }
                });

                btAntwoord3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Intent afspraakMakenActivity = new Intent(getApplicationContext(), AfspraakMaken.class);

                        String arts = (String) getIntent().getExtras().getString("Arts");
                        String tijd = (String) getIntent().getExtras().getString("Tijd");
                        afspraakMakenActivity.putExtra("Tijd", tijd);
                        afspraakMakenActivity.putExtra("Arts", arts);
                        afspraakMakenActivity.putExtra("Antwoord1", a1);
                        afspraakMakenActivity.putExtra("Antwoord2", btAntwoord3.getText());
                        vraagNummer++;
                        afspraakMakenActivity.putExtra("vraagNummer", vraagNummer);
                        startActivity(afspraakMakenActivity);
                    }
                });
                break;
            case 3:
                a1 = (String) getIntent().getExtras().getString("Antwoord1");
                a2 = (String) getIntent().getExtras().getString("Antwoord2");

                tvVraag.setText("Dit is vraag 3");
                btAntwoord1.setText(a1);
                btAntwoord2.setText(a2);
                btAntwoord3.setText("X3");
                pbAfspraak.setProgress(40);

                btAntwoord1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Intent afspraakMakenActivity = new Intent(getApplicationContext(), AfspraakMaken.class);

                        String arts = (String) getIntent().getExtras().getString("Arts");
                        String tijd = (String) getIntent().getExtras().getString("Tijd");
                        afspraakMakenActivity.putExtra("Tijd", tijd);
                        afspraakMakenActivity.putExtra("Arts", arts);
                        afspraakMakenActivity.putExtra("Antwoord1", a1);
                        afspraakMakenActivity.putExtra("Antwoord2", a2);
                        afspraakMakenActivity.putExtra("Antwoord3", btAntwoord1.getText());
                        vraagNummer++;
                        afspraakMakenActivity.putExtra("vraagNummer", vraagNummer);
                        startActivity(afspraakMakenActivity);
                    }
                });

                btAntwoord2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Intent afspraakMakenActivity = new Intent(getApplicationContext(), AfspraakMaken.class);

                        String arts = (String) getIntent().getExtras().getString("Arts");
                        String tijd = (String) getIntent().getExtras().getString("Tijd");
                        afspraakMakenActivity.putExtra("Tijd", tijd);
                        afspraakMakenActivity.putExtra("Arts", arts);
                        afspraakMakenActivity.putExtra("Antwoord1", a1);
                        afspraakMakenActivity.putExtra("Antwoord2", a2);
                        afspraakMakenActivity.putExtra("Antwoord3", btAntwoord2.getText());
                        vraagNummer++;
                        afspraakMakenActivity.putExtra("vraagNummer", vraagNummer);
                        startActivity(afspraakMakenActivity);
                    }
                });

                btAntwoord3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Intent afspraakMakenActivity = new Intent(getApplicationContext(), AfspraakMaken.class);

                        String arts = (String) getIntent().getExtras().getString("Arts");
                        String tijd = (String) getIntent().getExtras().getString("Tijd");
                        afspraakMakenActivity.putExtra("Tijd", tijd);
                        afspraakMakenActivity.putExtra("Arts", arts);
                        afspraakMakenActivity.putExtra("Antwoord1", a1);
                        afspraakMakenActivity.putExtra("Antwoord2", a2);
                        afspraakMakenActivity.putExtra("Antwoord3", btAntwoord3.getText());
                        vraagNummer++;
                        afspraakMakenActivity.putExtra("vraagNummer", vraagNummer);
                        startActivity(afspraakMakenActivity);
                    }
                });

                break;
            case 4:
                a1 = (String) getIntent().getExtras().getString("Antwoord1");
                a2 = (String) getIntent().getExtras().getString("Antwoord2");
                a3 = (String) getIntent().getExtras().getString("Antwoord3");

                tvVraag.setText("Dit is vraag 4");
                btAntwoord1.setText(a1);
                btAntwoord2.setText(a2);
                btAntwoord3.setText(a3);
                pbAfspraak.setProgress(60);

                btAntwoord1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Intent afspraakMakenActivity = new Intent(getApplicationContext(), AfspraakMaken.class);

                        String arts = (String) getIntent().getExtras().getString("Arts");
                        String tijd = (String) getIntent().getExtras().getString("Tijd");
                        afspraakMakenActivity.putExtra("Tijd", tijd);
                        afspraakMakenActivity.putExtra("Arts", arts);
                        afspraakMakenActivity.putExtra("Antwoord1", a1);
                        afspraakMakenActivity.putExtra("Antwoord2", a2);
                        afspraakMakenActivity.putExtra("Antwoord3", a3);
                        afspraakMakenActivity.putExtra("Antwoord4", btAntwoord1.getText());
                        vraagNummer++;
                        afspraakMakenActivity.putExtra("vraagNummer", vraagNummer);
                        startActivity(afspraakMakenActivity);
                    }
                });

                btAntwoord2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Intent afspraakMakenActivity = new Intent(getApplicationContext(), AfspraakMaken.class);

                        String arts = (String) getIntent().getExtras().getString("Arts");
                        String tijd = (String) getIntent().getExtras().getString("Tijd");
                        afspraakMakenActivity.putExtra("Tijd", tijd);
                        afspraakMakenActivity.putExtra("Arts", arts);
                        afspraakMakenActivity.putExtra("Antwoord1", a1);
                        afspraakMakenActivity.putExtra("Antwoord2", a2);
                        afspraakMakenActivity.putExtra("Antwoord3", a3);
                        afspraakMakenActivity.putExtra("Antwoord4", btAntwoord2.getText());
                        vraagNummer++;
                        afspraakMakenActivity.putExtra("vraagNummer", vraagNummer);
                        startActivity(afspraakMakenActivity);
                    }
                });

                btAntwoord3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Intent afspraakMakenActivity = new Intent(getApplicationContext(), AfspraakMaken.class);

                        String arts = (String) getIntent().getExtras().getString("Arts");
                        String tijd = (String) getIntent().getExtras().getString("Tijd");
                        afspraakMakenActivity.putExtra("Tijd", tijd);
                        afspraakMakenActivity.putExtra("Arts", arts);
                        afspraakMakenActivity.putExtra("Antwoord1", a1);
                        afspraakMakenActivity.putExtra("Antwoord2", a2);
                        afspraakMakenActivity.putExtra("Antwoord3", a3);
                        afspraakMakenActivity.putExtra("Antwoord4", btAntwoord3.getText());
                        vraagNummer++;
                        afspraakMakenActivity.putExtra("vraagNummer", vraagNummer);
                        startActivity(afspraakMakenActivity);
                    }
                });

                break;
            case 5:
                a1 = (String) getIntent().getExtras().getString("Antwoord1");
                a2 = (String) getIntent().getExtras().getString("Antwoord2");
                a3 = (String) getIntent().getExtras().getString("Antwoord3");
                a4 = (String) getIntent().getExtras().getString("Antwoord4");

                tvVraag.setText("Dit is vraag 5");
                btAntwoord1.setText("ABC 5.1");
                btAntwoord2.setText("ABC 5.2");
                btAntwoord3.setText("ABC 5.3");
                pbAfspraak.setProgress(80);

                btAntwoord1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Intent afspraakMakenActivity = new Intent(getApplicationContext(), AfspraakMaken.class);

                        String arts = (String) getIntent().getExtras().getString("Arts");
                        String tijd = (String) getIntent().getExtras().getString("Tijd");
                        afspraakMakenActivity.putExtra("Tijd", tijd);
                        afspraakMakenActivity.putExtra("Arts", arts);
                        afspraakMakenActivity.putExtra("Antwoord1", a1);
                        afspraakMakenActivity.putExtra("Antwoord2", a2);
                        afspraakMakenActivity.putExtra("Antwoord3", a3);
                        afspraakMakenActivity.putExtra("Antwoord4", a4);
                        afspraakMakenActivity.putExtra("Antwoord5", btAntwoord1.getText());
                        vraagNummer++;
                        afspraakMakenActivity.putExtra("vraagNummer", vraagNummer);
                        startActivity(afspraakMakenActivity);
                    }
                });

                btAntwoord2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Intent afspraakMakenActivity = new Intent(getApplicationContext(), AfspraakMaken.class);

                        String arts = (String) getIntent().getExtras().getString("Arts");
                        String tijd = (String) getIntent().getExtras().getString("Tijd");
                        afspraakMakenActivity.putExtra("Tijd", tijd);
                        afspraakMakenActivity.putExtra("Arts", arts);
                        afspraakMakenActivity.putExtra("Antwoord1", a1);
                        afspraakMakenActivity.putExtra("Antwoord2", a2);
                        afspraakMakenActivity.putExtra("Antwoord3", a3);
                        afspraakMakenActivity.putExtra("Antwoord4", a4);
                        afspraakMakenActivity.putExtra("Antwoord5", btAntwoord2.getText());
                        vraagNummer++;
                        afspraakMakenActivity.putExtra("vraagNummer", vraagNummer);
                        startActivity(afspraakMakenActivity);
                    }
                });

                btAntwoord3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Intent afspraakMakenActivity = new Intent(getApplicationContext(), AfspraakMaken.class);

                        String arts = (String) getIntent().getExtras().getString("Arts");
                        String tijd = (String) getIntent().getExtras().getString("Tijd");
                        afspraakMakenActivity.putExtra("Tijd", tijd);
                        afspraakMakenActivity.putExtra("Arts", arts);
                        afspraakMakenActivity.putExtra("Antwoord1", a1);
                        afspraakMakenActivity.putExtra("Antwoord2", a2);
                        afspraakMakenActivity.putExtra("Antwoord3", a3);
                        afspraakMakenActivity.putExtra("Antwoord4", a4);
                        afspraakMakenActivity.putExtra("Antwoord5", btAntwoord3.getText());
                        vraagNummer++;
                        afspraakMakenActivity.putExtra("vraagNummer", vraagNummer);
                        startActivity(afspraakMakenActivity);
                    }
                });

                break;
            case 6:
                //De afspraak moet hier afgerond worden.
                a1 = (String) getIntent().getExtras().getString("Antwoord1");
                a2 = (String) getIntent().getExtras().getString("Antwoord2");
                a3 = (String) getIntent().getExtras().getString("Antwoord3");
                a4 = (String) getIntent().getExtras().getString("Antwoord4");
                a5 = (String) getIntent().getExtras().getString("Antwoord5");

                String arts = (String) getIntent().getExtras().getString("Arts");
                String tijd = (String) getIntent().getExtras().getString("Tijd");

                pbAfspraak.setProgress(100);
                tvVraag.setText("De volgende gegevens zijn doorgegeven: " + "\n" + "Arts: " + arts + "\n" + "op  " + tijd + "\n\n" + a1 + "\n\n" + a2 + "\n\n" + a3 + "\n\n" + a4 + "\n\n" + a5);
                btAntwoord1.setText("Afspraak afronden");
                btAntwoord2.setVisibility(View.INVISIBLE);
                btAntwoord3.setVisibility(View.INVISIBLE);
                btAntwoord1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Intent afspraakMakenActivity = new Intent(getApplicationContext(), Afspraken.class);
                        String arts = (String) getIntent().getExtras().getString("Arts");
                        afspraakMakenActivity.putExtra("Arts", arts);
                        String tijd = (String) getIntent().getExtras().getString("Tijd");
                        afspraakMakenActivity.putExtra("Tijd", tijd);
                        startActivity(afspraakMakenActivity);
                    }
                });
                break;
            default:
                tvVraag.setText("Waar zit de aandoening ?");
                btAntwoord1.setText("Hoofd");
                btAntwoord2.setText("Romp");
                btAntwoord3.setText("Benen");
                pbAfspraak.setProgress(0);
                btAntwoord1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Intent afspraakMakenActivity = new Intent(getApplicationContext(), Afspraken.class);

                        afspraakMakenActivity.putExtra("Antwoord1", btAntwoord1.getText());
                        vraagNummer++;
                        afspraakMakenActivity.putExtra("vraagNummer", vraagNummer);
                        startActivity(afspraakMakenActivity);
                    }
                });

                btAntwoord2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Intent afspraakMakenActivity = new Intent(getApplicationContext(), Afspraken.class);

                        afspraakMakenActivity.putExtra("Antwoord1", btAntwoord2.getText());
                        vraagNummer++;
                        afspraakMakenActivity.putExtra("vraagNummer", vraagNummer);
                        startActivity(afspraakMakenActivity);
                    }
                });

                btAntwoord3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Intent afspraakMakenActivity = new Intent(getApplicationContext(), Afspraken.class);

                        afspraakMakenActivity.putExtra("Antwoord1", btAntwoord3.getText());
                        vraagNummer++;
                        afspraakMakenActivity.putExtra("vraagNummer", vraagNummer);
                        startActivity(afspraakMakenActivity);
                    }
                });
                break;
        }

    }

    private void initializeUIComponents() {
        btAntwoord1 = (Button) findViewById(R.id.btAntwoord1);
        btAntwoord2 = (Button) findViewById(R.id.btAntwoord2);
        btAntwoord3 = (Button) findViewById(R.id.btAntwoord3);
        pbAfspraak = (ProgressBar) findViewById(R.id.pbAfspraak);
        tvVraag = (TextView) findViewById(R.id.tvVraag);
    }
}
