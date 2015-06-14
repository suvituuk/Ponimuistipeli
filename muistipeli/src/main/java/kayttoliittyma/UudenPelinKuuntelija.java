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
    private Lopetus lopetus;
    
    public UudenPelinKuuntelija(Kayttoliittyma kl) {
        this.kl = kl;
        this.lopetus = null;
    }
    
    public UudenPelinKuuntelija(Kayttoliittyma kl, Lopetus lopetus) {
        this.kl = kl;
        this.lopetus = lopetus;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Pelinkaynnistin kaynnistin = new Pelinkaynnistin();
            SwingUtilities.invokeLater(kaynnistin);
            kl.getFrame().dispose();
            try {
                lopetus.getFrame().dispose();
            } catch (Exception ex) {
                //Lopetus-käyttöliittymää ei ole
            }
        } catch (IOException ex) {
            Logger.getLogger(UudenPelinKuuntelija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
