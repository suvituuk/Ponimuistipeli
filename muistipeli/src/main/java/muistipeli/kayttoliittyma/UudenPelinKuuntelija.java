package muistipeli.kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.SwingUtilities;

/**
 *
 * ActionListener, joka avaa käyttöliittymän, jossa valitaan ja aloitetaan peli.
 */
public class UudenPelinKuuntelija implements ActionListener {

    private final Kayttoliittyma kl;
    private final Lopetus lopetus;

    /**
     *
     * @param kl Kayttoliittyma, joka suljetaan.
     */
    public UudenPelinKuuntelija(Kayttoliittyma kl) {
        this.kl = kl;
        this.lopetus = null;
    }

    /**
     *
     * @param kl Kayttoliittyma, joka suljetaan.
     * @param lopetus Lopetus, joka suljetaan.
     */
    public UudenPelinKuuntelija(Kayttoliittyma kl, Lopetus lopetus) {
        this.kl = kl;
        this.lopetus = lopetus;
    }

    /**
     * Avaa Pelinkaynnistimen ja sulkee Kayttoliittyman ja tarvittaessa
     * Lopetuksen.
     *
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Pelinkaynnistin kaynnistin = new Pelinkaynnistin();
            SwingUtilities.invokeLater(kaynnistin);
            kl.getFrame().dispose();
            if (lopetus != null) {
                lopetus.getFrame().dispose();
            }
        } catch (IOException ex) {
            System.out.println("Käynnistintä ei voitu avata.");
        }
    }

}
