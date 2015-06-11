package kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 *
 * ActionListener, joka tallentaa pelin tuloksen.
 */
public class TuloksenLisaysKuuntelija implements ActionListener {
    Kayttoliittyma kl;
    
    public TuloksenLisaysKuuntelija(Kayttoliittyma kl) throws IOException {
        this.kl = kl;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            kl.getPeli().tallennaTulos();
            kl.getTuloksenLisays().setEnabled(false);
        } catch (IOException ex) {
            System.out.println("Tuloksen tallentaminen ei onnistunut!");
        }
    }
    
}
