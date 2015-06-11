package kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import muistipeli.muistipeli.Tulostenkasittelija;

/**
 *
 * ActionListener, joka näyttää parhaat tulokset.
 */
public class TopTulostenKuuntelija implements ActionListener {
    private final Tulokset tulokset;
    
    public TopTulostenKuuntelija(Tulokset tulokset) {
        this.tulokset = tulokset;
    }

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