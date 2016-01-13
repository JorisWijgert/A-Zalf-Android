package groep4.a_zalf.Collection;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;

import groep4.a_zalf.Database.DbHandler;

/**
 * Created by brunodelsing on 12/11/15.
 */
public class Ziekenhuis {

    private ArrayList<Arts> artsen;
    private ArrayList<Patient> patienten;
    private Context context;
    DbHandler handler;

    public Ziekenhuis(boolean fillDatabase, Context context) {
        this.artsen = new ArrayList<>();
        this.patienten = new ArrayList<>();

        //database stuff
        this.context = context;
        this.handler = new DbHandler(context, null, null, 1);
          fillDatabase();
        if (fillDatabase && handler.isEmpty("patienten")) {
            fillDatabase();
        }
    }

    private void fillDatabase() {
        handler.add(new Patient("Bruno Delsing", "123", 0001));
        handler.add(new Patient("Pieter Wels", "123", 0002));
        handler.add(new Patient("Henk Janssen", "123", 0003));
        handler.add(new Patient("Jac Mensens", "123", 74523));
        handler.add(new Patient("Frans Diedericks", "123", 74663));
        handler.add(new Patient("Julia Davids", "123", 71123));
        handler.add(new Patient("Robbert Janse", "123", 92113));
        handler.add(new Patient("Francien Mensens", "123", 76166));
    }

    public boolean inloggen(String patientNr, String wachtwoord) {
        return handler.findPatientBy(patientNr, wachtwoord);
    }
}
