package groep4.a_zalf.Collection;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by brunodelsing on 12/10/15.
 */
public class Diagnose {

    private String beschrijving, qrCode, prescriptie;

    private ArrayList<Date> reminders;

    public Diagnose(String beschrijving, String qrCode) {
        this.beschrijving = beschrijving;
        this.qrCode = qrCode;
        this.reminders = null;
    }

    public Diagnose(String beschrijving, String qrCode, ArrayList<Date> reminders) {
        this.beschrijving = beschrijving;
        this.qrCode = qrCode;
        this.reminders = reminders;
    }

    public Diagnose(String beschrijving, String qrCode, String prescriptie) {
        this.beschrijving = beschrijving;
        this.qrCode = qrCode;
        this.prescriptie = prescriptie;
    }

    public String getPrescriptie() { return this.prescriptie; }

    public String getBeschrijving() {
        return beschrijving;
    }

    public String getQrCode() {
        return qrCode;
    }

    public ArrayList<Date> getReminders() {
        return reminders;
    }
}
