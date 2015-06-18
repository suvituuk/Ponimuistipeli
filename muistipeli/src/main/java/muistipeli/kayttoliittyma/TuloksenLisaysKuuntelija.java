package muistipeli.kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.SwingUtilities;

/**
 *
 * ActionListener, joka tallentaa pelin tuloksen.
 */
public class TuloksenLisaysKuuntelija implements ActionListener {
    Kayttoliittyma kl;
    Lopetus lopetus;
    
    /**
     * 
     * @param kl Kayttoliittyma, johon liittyvän pelin tulos tallennetaan.
     * @param lopetus Lopetus, josta painiketta painetaan ja joka suljetaan.
     */
    public TuloksenLisaysKuuntelija(Kayttoliittyma kl, Lopetus lopetus) {
        this.kl = kl;
        this.lopetus = lopetus;
    }

    private void avaaTulokset() {
        Tulokset tulokset = new Tulokset(kl.getPeli().getPelaaja1().getNimi());
        SwingUtilities.invokeLater(tulokset);
    }
    
    private void sulje() {
        lopetus.getFrame().dispose();
    }
    
    /**
     * Tallentaa pelin tuloksen ja avaa Tulokset-käyttöliittymän. Sulkee Lopetus-käyttöliittymän.
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            kl.getPeli().tallennaTulos();
        } catch (IOException ex) {
            System.out.println("Tuloksen tallentaminen ei onnistunut.");
        }
        avaaTulokset();
        sulje();
    }
    
}
