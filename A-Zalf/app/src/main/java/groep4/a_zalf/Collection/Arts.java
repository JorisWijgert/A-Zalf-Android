package groep4.a_zalf.Collection;

import java.util.ArrayList;

/**
 * Created by brunodelsing on 12/10/15.
 */
public class Arts {

    private String naam, kamerNr;

    private ArrayList<Afspraak> agenda;

    public Arts(String naam, String kamerNr) {
        this.naam = naam;
        this.kamerNr = kamerNr;
        this.agenda = new ArrayList();
    }

    public void add(Afspraak afspraak) {
        agenda.add(afspraak);

    }
}
