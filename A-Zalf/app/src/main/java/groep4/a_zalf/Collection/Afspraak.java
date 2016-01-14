package groep4.a_zalf.Collection;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by brunodelsing on 12/10/15.
 */
public class Afspraak {

    private Calendar tijdstip, tijdsduur;

    private Diagnose diagnose;
    private Informatie informatie;

    private Arts arts;
    private Patient patient;

    public Afspraak(Calendar tijdstip, Calendar tijdsduur, Arts arts, Patient patient, Informatie informatie) {
        this.tijdstip = tijdstip;
        this.tijdsduur = tijdsduur;
        this.arts = arts;
        this.patient = patient;
        this.informatie = informatie;
        this.diagnose = null;
    }

    public void setDiagnose(Diagnose diagnose) {
        this.diagnose = diagnose;
    }

    public Arts getArts(){return arts;}

    public Calendar getTijdstip(){
        return tijdstip;
    }

    public Calendar getTijdsduur(){
        return tijdsduur;
    }

    public String getBeginDateString(){
        String returnString = "Vanaf: ";
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        if (tijdstip != null){
            returnString += sdf.format(tijdstip.getTime());
        }
        return returnString;
    }

    public String getEndDateString(){
        Calendar returnCal = tijdstip;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        returnCal.add(Calendar.MINUTE, tijdsduur.get(Calendar.MINUTE));
        String returnString = "Tot: ";
        if (returnCal != null){
            returnString += sdf.format(returnCal.getTime());
        }
        return returnString;
    }
}
