package kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import muistipeli.muistipeli.Poyta;

/**
 * ActionListener, joka asettaa ruudukon koon, poistaa koonvalintapainikkeet 
 * käytöstä ja asettaa pelin aloitus -painikkeen käyttöön.
 */
public class KoonValintaKuuntelija implements ActionListener {
    private final int sivu;
    private final Pelinkaynnistin pk;
    
    public KoonValintaKuuntelija(int sivu, Pelinkaynnistin pk) {
        this.pk = pk;
        this.sivu = sivu;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        pk.getPeli().setPoyta(new Poyta(sivu));
        pk.getPieni().setEnabled(false);
        pk.getIso().setEnabled(false);
        pk.getValmis().setEnabled(true);
    }
    
}
