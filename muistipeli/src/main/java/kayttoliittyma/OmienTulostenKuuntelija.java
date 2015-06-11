package kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import muistipeli.muistipeli.Pelaaja;
import muistipeli.muistipeli.Tulostenkasittelija;

/**
 *
 * ActionListener, joka näyttää pelaajan omat tulokset.
 */
public class OmienTulostenKuuntelija implements ActionListener {
    private final Tulokset tulokset;
    
    public OmienTulostenKuuntelija(Tulokset tulokset) {
        this.tulokset = tulokset;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Tulostenkasittelija tk = new Tulostenkasittelija();
            tulokset.getTulokset().setText(tk.omatTulokset(new Pelaaja(tulokset.getNimi().getText())));
        } catch (IOException ex) {
            Logger.getLogger(OmienTulostenKuuntelija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
