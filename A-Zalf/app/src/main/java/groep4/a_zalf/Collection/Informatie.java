package groep4.a_zalf.Collection;

import android.media.Image;

/**
 * Created by brunodelsing on 12/10/15.
 */
public class Informatie {

    private String beschrijving;
    private Image fotoVeraf;
    private Image fotoDichtbij;
    private String opmerkingen;

    public Informatie(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public Informatie(String beschrijving, Image fotoVeraf, Image fotoDichtbij) {
        this.beschrijving = beschrijving;
        this.fotoVeraf = fotoVeraf;
        this.fotoDichtbij = fotoDichtbij;
    }

    public Informatie(String beschrijving, Image fotoVeraf, Image fotoDichtbij, String opmerkingen) {
        this.beschrijving = beschrijving;
        this.fotoVeraf = fotoVeraf;
        this.fotoDichtbij = fotoDichtbij;
        this.opmerkingen = opmerkingen;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public Image getFotoDichtbij() {
        return fotoDichtbij;
    }

    public String getOpmerkingen() {
        return opmerkingen;
    }
}
