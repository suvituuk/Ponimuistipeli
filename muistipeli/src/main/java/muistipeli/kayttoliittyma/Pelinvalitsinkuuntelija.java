package muistipeli.kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * ActionListener pelinvalinnalle (yksin- vai kaksinpeli). 
 * Asettaa käyttöön nimikentät (/kentän) ja ruudukon koon valinnan. 
 */
public class Pelinvalitsinkuuntelija implements ActionListener {
    private final int pelaajia;
    private final Pelinkaynnistin kayttis;
    
    /**
     * 
     * @param kayttis Pelinkäynnistin, johon vaikutetaan.
     * @param pelaajia Pelaajien määrä.
     */
    public Pelinvalitsinkuuntelija(Pelinkaynnistin kayttis, int pelaajia) {
        this.pelaajia = pelaajia;
        this.kayttis = kayttis;
    }
    
    /**
     * Poistaa käytöstä painikkeet pelin (yksin- vai kaksinpeli) valinnalle.
     */
    private void poistaPelinvalintaKaytosta() {
        kayttis.getYksinpeli().setEnabled(false);
        kayttis.getKaksinpeli().setEnabled(false);
    }
    
    /**
     * Asettaa käyttöön nimikentät (yksinpelissä nimikentän) ja lisää niihin liittyvät ohjetekstit.
     */
    private void asetaNimikentatKayttoon() {
        kayttis.getEkaNimikentta().setEnabled(true);
        if (this.pelaajia == 1) {
            kayttis.getAnnaNimi().setText("Älä käytä väliviivaa (-)!");
            kayttis.getOhjeTekti().setText("Nimi max 10 merkkiä.");
            kayttis.getEkaPelaaja().setText("Nimimerkki:");
        } else if (this.pelaajia == 2) {
            kayttis.getAnnaNimi().setText("Syöttäkää nimenne");
            kayttis.getEkaPelaaja().setText("Pelaaja 1:");
            kayttis.getTokaPelaaja().setText("Pelaaja 2:");
            kayttis.getTokaNimikentta().setEnabled(true);
        }
    }
    
    /**
     * Asettaa käyttöön painikkeet ruudukon koon valinnalle.
     */
    private void asetaKoonValintaKayttoon() {
        kayttis.getIso().setEnabled(true);
        kayttis.getPieni().setEnabled(true);
    }

    /**
     * Asettaa pelin pelaajien määrän, poistaa käytöstä penlinvalintapainikkeet 
     * asettaa käyttöön painikkeet ruudukon koon valinnalle ja tekstikentät nimien syöttöön.
     * Asettaa sopivat ohjetekstit Pelinkaynnistimeen.
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.kayttis.getPeli().setPelaajia(pelaajia);
        poistaPelinvalintaKaytosta();
        asetaKoonValintaKayttoon();
        asetaNimikentatKayttoon();
    }
    
}
