package muistipeli.kayttoliittyma;

import java.awt.event.*;
import muistipeli.logiikka.Poyta;

/**
 * ActionListener, joka asettaa ruudukon koon, poistaa koonvalintapainikkeet 
 * käytöstä ja asettaa pelin aloitus -painikkeen käyttöön.
 */
public class KoonValintaKuuntelija implements ActionListener {
    private final int sivu;
    private final Pelinkaynnistin pk;
    
    /**
     * 
     * @param sivu Ruudukon sivun pituus.
     * @param pk Pelinkäynnistin, josta toiminto suoritetaan.
     */
    public KoonValintaKuuntelija(int sivu, Pelinkaynnistin pk) {
        this.pk = pk;
        this.sivu = sivu;
    }
    
    /**
     * Asettaa peliin liittyvän pöydän koon. 
     * Poistaa koonvalintapainikkeet käytöstä ja asettaa pelin aloituspainikkeen käyttöön.
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        pk.getPeli().setPoyta(new Poyta(sivu));
        pk.getPieni().setEnabled(false);
        pk.getIso().setEnabled(false);
        pk.getValmis().setEnabled(true);
    }
    
}
