package groep4.a_zalf.Collection;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by brunodelsing on 12/10/15.
 */
public class Patient {

    private String naam;
    private String wachtwoord;
    private Date geboorteDatum;
    private int patientNr;
    private boolean aanwezig;

    private ArrayList<Afspraak> agenda;
    private Diagnose diagnose;

    public Patient(String naam, Date geboorteDatum, String wachtwoord, int patientNr) {
        this.naam = naam;
        this.wachtwoord = wachtwoord;
        this.patientNr = patientNr;
        this.geboorteDatum = geboorteDatum;
        this.aanwezig = false;
        this.agenda = new ArrayList();
    }

    public Patient(String naam, String wachtwoord, int patientNr) {
        this.naam = naam;
        this.wachtwoord = wachtwoord;
        this.patientNr = patientNr;
        this.geboorteDatum = new Date();
        this.aanwezig = false;
        this.agenda = new ArrayList();
    }

    public void setAanwezig() {
        this.aanwezig = true;
    }

    public boolean isAanwezig() {
        return aanwezig;
    }

    public void addAfspraak(Calendar tijdstip, Calendar tijdsduur, Arts arts, Informatie informatie) {
        agenda.add(new Afspraak(tijdstip, tijdsduur, arts, this, informatie));
    }

    public void geef(Diagnose diagnose) {
        this.diagnose = diagnose;
    }

    public int getPatientNr() { return this.patientNr; }

    public String getWachtwoord() {return this.wachtwoord; }

    public String getNaam() {return this.naam; }

    public Date getGeboorteDatum() {return this.geboorteDatum; }
}
