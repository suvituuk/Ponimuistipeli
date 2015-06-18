package muistipeli.kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import muistipeli.logiikka.Pelaaja;
import muistipeli.logiikka.Tulostenkasittelija;

/**
 *
 * ActionListener, joka näyttää pelaajan omat tulokset.
 */
public class OmienTulostenKuuntelija implements ActionListener {
    private final Tulokset tulokset;
    
    /**
     * 
     * @param tulokset Tulokset-käyttöliittymä, jossa tulokset näytetään ja josta nimimerkki haetaan.
     */
    public OmienTulostenKuuntelija(Tulokset tulokset) {
        this.tulokset = tulokset;
    }

    /**
     * Näyttää pelaajan omat tulokset.
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Tulostenkasittelija tk = new Tulostenkasittelija();
            tulokset.getTulokset().setText(tk.omatTulokset(new Pelaaja(tulokset.getNimi().getText())));
        } catch (IOException ex) {
            System.out.println("Tuloksia ei voitu näyttää.");
        }
    }
    
}
