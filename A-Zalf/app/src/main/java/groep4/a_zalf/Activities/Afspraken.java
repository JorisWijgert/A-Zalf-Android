package groep4.a_zalf.Activities;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import groep4.a_zalf.Collection.*;
import groep4.a_zalf.Database.DbHandler;
import groep4.a_zalf.R;
import groep4.a_zalf.Protocol.IBeacon;
import groep4.a_zalf.Protocol.IBeaconListener;
import groep4.a_zalf.Protocol.IBeaconProtocol;
import groep4.a_zalf.Protocol.StringUtilities;
import groep4.a_zalf.Protocol.Utils;

public class Afspraken extends AppCompatActivity implements IBeaconListener, AdapterView.OnItemClickListener {


    private static final int REQUEST_BLUETOOTH_ENABLE = 1;

    private static IBeacon beacon;
    private static IBeaconProtocol ibp;

    public List<Afspraak> afspraken;

    private ListView lvAfspraken;

    Activity context = this;

    //Sockets
    private final String IPADDRESS = "145.93.129.129";
    private final int PORT = 8887;

    private DataOutputStream dataOutputStream = null;
    private Socket socket;

    private DbHandler handler;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afspraken);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        afspraken = new ArrayList();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                final Intent afspraakMakenActivity = new Intent(getApplicationContext(), AfspraakMaken.class);
                afspraakMakenActivity.putExtra("vraagNummer", 1);
                startActivity(afspraakMakenActivity);
            }
        });

        setUpIBeacon();
        makeAfspraken();
        AfspraakListAdapter ala = new AfspraakListAdapter(getApplicationContext(), afspraken);

        lvAfspraken = (ListView) findViewById(R.id.listViewAfspraken);
        lvAfspraken.setAdapter(ala);
        lvAfspraken.setOnItemClickListener(this);
        startSearch();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AfspraakListAdapter ala = (AfspraakListAdapter) parent.getAdapter();
        Afspraak afspraak = ala.getItem(position);
        Calendar tijdBegin = afspraak.getTijdstip();
        Calendar tijdsduur = afspraak.getTijdsduur();
        Calendar tijdEind = tijdBegin;
        tijdEind.add(Calendar.MINUTE, tijdsduur.get(Calendar.MINUTE));
        if (tijdEind.before(Calendar.getInstance())){
            Intent intent = new Intent(this, Diagnose.class);

            startActivity(intent);
        }else{
            Snackbar.make(view, "De afspraak heeft nog niet plaatsgevonden.", Snackbar.LENGTH_LONG)
                      .setAction("Action", null).show();
        }
        //Navigate to the page from the game.
//        Intent intent = new Intent(this, GameActivity.class);
//        intent.putExtra("Game", game);
//        intent.putExtra("User", text);
//        startActivity(intent);
        //makeAfspraken();
    }

    private void setUpIBeacon() {
        ibp = IBeaconProtocol.getInstance(this);
        ibp.setListener(this);
    }

    private void makeAfspraken() {
        Arts a = new Arts("Theodore", "12");
        Arts a2 = new Arts("Friso", "13");
        Patient p = new Patient("Harrie", "001", 12);
        Informatie i = new Informatie("Er zitten vreemde plekken op mijn been");
        Calendar startDate0 = Calendar.getInstance();
        startDate0.add(Calendar.DAY_OF_MONTH, -1);
        Calendar startDate1 = Calendar.getInstance();
        startDate1.add(Calendar.MINUTE, 15);
        Calendar timeLength = Calendar.getInstance();
        SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss");
        try {
            timeLength.setTime(sdf1.parse("00:30:00"));
            afspraken.add(new Afspraak(startDate0, timeLength, a2, p, i));
            afspraken.add(new Afspraak(startDate1, timeLength, a, p, i));

            Calendar startDate2 = Calendar.getInstance();
            startDate2.add(Calendar.HOUR, 2);
            afspraken.add(new Afspraak(startDate2, timeLength, a, p, i));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onStop() {
        ibp.stopScan();
        super.onStop();
    }

    /**
     * Call this when the phone needs to search for iBeacons.
     */
    public void startSearch() {
        scanBeacons();
    }

    private void scanBeacons() {
        Log.i(Utils.LOG_TAG, "Scanning");

        if (!IBeaconProtocol.configureBluetoothAdapter(this)) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_BLUETOOTH_ENABLE);
        } else {
            if (ibp.isScanning()) {
                ibp.stopScan();
            }
            ibp.reset();
            ibp.startScan();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_BLUETOOTH_ENABLE) {
            if (resultCode == Activity.RESULT_OK) {
                scanBeacons();
            }
        }
    }

    @Override
    public void enterRegion(IBeacon ibeacon) {

    }

    @Override
    public void exitRegion(IBeacon ibeacon) {

    }

    /**
     * The point where an action can be performed when the iBeacon is found
     * our iBeacon has the following uuid:
     * "F7826DA6-4FA2-4E98-8024-BC5B71E0893E"
     *
     * @param ibeacon The iBeacon found
     */
    @Override
    public void beaconFound(IBeacon ibeacon) {
        beacon = ibeacon;
        //TODO: THINGS WHEN BEACON FOUND
        if (beacon.getUuidHexStringDashed().equals("F7826DA6-4FA2-4E98-8024-BC5B71E0893E")) {

            preferences = getSharedPreferences("patient", Context.MODE_PRIVATE);
            String patientNr = preferences.getString("patientKey", null);
            handler = new DbHandler(getApplicationContext(), null, null, 1);

            Patient patient = handler.findPatientBy(patientNr);

            socketSender(patient.getNaam());

            final Intent suggestiesActivity = new Intent(getApplicationContext(), Suggesties.class);
            startActivity(suggestiesActivity);
        }

    }

    /**
     * Do things when the search has started or ended
     *
     * @param state The state, either <code>SEARCH_STARTED</code>, <code>SEARCH_END_EMPTY</code> or <code>SEARCH_END_SUCCESS</code>
     */
    @Override
    public void searchState(int state) {
        if (state == IBeaconProtocol.SEARCH_STARTED) {
        } else if (state == IBeaconProtocol.SEARCH_END_EMPTY) {
            startSearch();
        }
    }

    /**
     * When something went wrong
     *
     * @param status The error status code
     */
    @Override
    public void operationError(int status) {

        Toast.makeText(context, "Bluetooth error: " + status, Toast.LENGTH_SHORT).show();
    }


    private void socketSender(final String naam) {
        new AsyncTask<Void, Void, Void>() {
            boolean loop = true;

            @Override
            protected Void doInBackground(Void... params) {
                while(loop) {

                    dataOutputStream = null;

                    try {
                        socket = new Socket(IPADDRESS, PORT);
                        dataOutputStream = new DataOutputStream(socket.getOutputStream());

                        dataOutputStream.writeUTF(naam);

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

                        if (dataOutputStream != null) {
                            try {
                                dataOutputStream.close();
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


}
