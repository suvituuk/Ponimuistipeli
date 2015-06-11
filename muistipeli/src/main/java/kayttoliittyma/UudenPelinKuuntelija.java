package kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 *
 * ActionListener, joka avaa käyttöliittymän, jossa valitaan ja aloitetaan peli.
 */
public class UudenPelinKuuntelija implements ActionListener {
    private final Kayttoliittyma kl;
    
    public UudenPelinKuuntelija(Kayttoliittyma kl) {
        this.kl = kl;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Pelinkaynnistin kaynnistin = new Pelinkaynnistin();
            SwingUtilities.invokeLater(kaynnistin);
            kl.getFrame().dispose();
        } catch (IOException ex) {
            Logger.getLogger(UudenPelinKuuntelija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
