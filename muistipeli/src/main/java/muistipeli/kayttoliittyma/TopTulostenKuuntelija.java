package muistipeli.kayttoliittyma;

import java.awt.event.*;
import java.io.IOException;
import java.util.logging.*;
import muistipeli.logiikka.Tulostenkasittelija;

/**
 *
 * ActionListener, joka näyttää parhaat tulokset.
 */
public class TopTulostenKuuntelija implements ActionListener {
    private final Tulokset tulokset;
    
    /**
     * 
     * @param tulokset Tulokset-käyttöliittymä, jossa tulokset näytetään.
     */
    public TopTulostenKuuntelija(Tulokset tulokset) {
        this.tulokset = tulokset;
    }

    /**
     * Näyttää parhaat 10 tulosta Tulokset-käyttöliittymässä.
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Tulostenkasittelija tk = new Tulostenkasittelija();
            tulokset.getTulokset().setText(tk.top(10));
        } catch (IOException ex) {
            Logger.getLogger(TopTulostenKuuntelija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
