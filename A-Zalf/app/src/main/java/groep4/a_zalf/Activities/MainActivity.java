package groep4.a_zalf.Activities;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import groep4.a_zalf.Collection.Patient;
import groep4.a_zalf.Collection.Ziekenhuis;
import groep4.a_zalf.Database.DbHandler;
import groep4.a_zalf.R;

public class MainActivity extends AppCompatActivity {

    private EditText etPatientNr, etWachtwoord;
    private Button btInloggen;
    private Ziekenhuis ziekenhuis;
    private Toolbar tbInloggen;

    private DbHandler handler;
    private SharedPreferences preferences;

    //Socktes
    private DataInputStream dataInputStream = null;
    private Socket socket;

    private String messageFromServer = "leeg";
    private int patientNrFromServer = 0;

    private final String IPADDRESS = "145.93.129.129";
    private final int PORT = 8888;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tbInloggen = (Toolbar) findViewById(R.id.toolbar);
        this.setSupportActionBar(tbInloggen);
        getSupportActionBar().setTitle("A-Zalf");

        handler = new DbHandler(getApplicationContext(), null, null, 1);
        preferences = getSharedPreferences("patient", Context.MODE_PRIVATE);

        ziekenhuis = new Ziekenhuis(true, getApplicationContext());

        initializeUIComponents();
        inloggen();
        socketListener();
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

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("patientKey", etPatientNr.getText().toString());
                    editor.commit();

                    final Intent afsprakenActivity = new Intent(getApplicationContext(), Afspraken.class);
                    startActivity(afsprakenActivity);
                } else {
                    System.out.println("patientNr of wachtwoord is fout.");
                }
            }
        });

    }

    private void socketListener() {
        new AsyncTask<Void, Void, Void>() {
            boolean loop = true;

            @Override
            protected Void doInBackground(Void... params) {
                while(loop) {

                    dataInputStream = null;

                    try {
                        socket = new Socket(IPADDRESS, PORT);
                        dataInputStream = new DataInputStream(socket.getInputStream());
                        //messageFromServer = dataInputStream.readUTF();
                        //System.out.println(messageFromServer);

                        messageFromServer = dataInputStream.readUTF();

                        if (messageFromServer.matches("[0-9]+")) {
                            patientNrFromServer = Integer.valueOf(messageFromServer);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    createNotification(patientNrFromServer, "binnenkomen");
                                }
                            });
                        } else {
                            String[] diagnoseStr = messageFromServer.split("@");

                            handler.addDiagnose(diagnoseStr[0], diagnoseStr[1], diagnoseStr[2], patientNrFromServer);
                            System.out.println("Diagnose toegevoegd.");

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    createNotification(patientNrFromServer, "niet binnenkomen");
                                }
                            });
                        }

                        //patientNrFromServer = dataInputStream.readInt();

                    } catch (UnknownHostException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } finally {
                        if (socket != null) {
                            try {
                                socket.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        if (dataInputStream != null) {
                            try {
                                dataInputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }
                return null;
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void createNotification(int patientNr, String binnekomen) {
        Patient patient = handler.findPatientBy(String.valueOf(patientNr));

        Vibrator v = (Vibrator) this.getApplicationContext().getSystemService(getApplicationContext().VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        v.vibrate(500);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        v.vibrate(500);

        final Intent afsprakenActivity = new Intent(getApplicationContext(), Afspraken.class);
        final Intent diagnoseActivity = new Intent(getApplicationContext(), Diagnose.class);
        Notification noti;

        if (binnekomen.equals("binnenkomen")) {
            PendingIntent pIntent = PendingIntent.getActivity(this, 0, afsprakenActivity, 0);
            noti = new Notification.Builder(this)
                    .setCategory(Notification.CATEGORY_PROMO)
                    .setTicker("De Dermatoloog verwacht u.")
                    .setContentTitle("Welkom, " + patient.getNaam())
                    .setContentText("U wordt verwacht in kamer 3.23.")
                    .setSmallIcon(R.drawable.artsgroot)
                    .setPriority(Notification.PRIORITY_HIGH)
                    .setContentIntent(pIntent).getNotification();
        } else {
            PendingIntent pIntent = PendingIntent.getActivity(this, 0, diagnoseActivity, 0);
            noti = new Notification.Builder(this)
                    .setCategory(Notification.CATEGORY_PROMO)
                    .setTicker("De dermatoloog heeft u een diagnose gestuurd")
                    .setContentTitle("Diagnose")
                    .setContentText("De dermatoloog heeft u een diagnoseartsgroot.png gestuurd")
                    .setSmallIcon(R.drawable.artsgroot)
                    .setPriority(Notification.PRIORITY_HIGH)
                    .setContentIntent(pIntent).getNotification();
        }

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, noti);
    }

    private void initializeUIComponents() {
        etPatientNr = (EditText) findViewById(R.id.etPatientNr);
        etWachtwoord = (EditText) findViewById(R.id.etWachtwoord);
        btInloggen = (Button) findViewById(R.id.btInloggen);
    }
}
