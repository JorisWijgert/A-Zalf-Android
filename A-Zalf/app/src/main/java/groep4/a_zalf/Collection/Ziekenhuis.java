package groep4.a_zalf.Collection;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by brunodelsing on 12/11/15.
 */
public class Ziekenhuis {

    private ArrayList<Arts> artsen;
    private ArrayList<Patient> patienten;

    public Ziekenhuis() {
        this.artsen = new ArrayList<>();
        this.patienten = new ArrayList<>();

        fillWithDummyData();
    }

    private void fillWithDummyData() {
        patienten.add(new Patient("Bruno Delsing", new Date(), "123", 737312));
        patienten.add(new Patient("Ramon Janssen", new Date(), "123", 1234567));
    }

}
