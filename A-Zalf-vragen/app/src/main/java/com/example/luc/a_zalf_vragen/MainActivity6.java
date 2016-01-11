package com.example.luc.a_zalf_vragen;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Random;

public class MainActivity6 extends Activity {

    private String[] aandoening = new String[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);


        aandoening[0] = "U heeft mogelijk Juveniel xanthogranuloom";
        aandoening[1] = "U heeft mogelijk Erythromelanosis interfollicularis colli";
        aandoening[2] = "U heeft mogelijk Herpes genitalis";
        aandoening[3] = "U heeft mogelijk PUPPP (Zwangerschapsjeuk)";
        aandoening[4] = "U heeft mogelijk Balanitis plasmocellularis";
        aandoening[5] = "U heeft mogelijk Chronische Discoïde Lupus Erythematosus (CDLE)";

        TextView txAandoening = (TextView) findViewById(R.id.txAandoening);

        Random r = new Random();
        txAandoening.setText(aandoening[r.nextInt(5) + 1].toString());
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
}
