package muistipeli.kayttoliittyma;

import java.awt.Color;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.SwingUtilities;
import muistipeli.logiikka.Pelaaja;

/**
 *
 * ActionListener joka aloittaa varsinaisen pelin, jos kaikki on kunnossa.
 */
public class Kaynnistinkuuntelija implements ActionListener {

    private final Pelinkaynnistin kaynnistin;

    /**
     * 
     * @param kaynnistin Pelinkaynnistin, josta peliä ollaan käynnistämässä.
     */
    public Kaynnistinkuuntelija(Pelinkaynnistin kaynnistin) {
        this.kaynnistin = kaynnistin;
    }

    /**
     * Avaa uuden graafisen käyttöliittymän, jossa varsinainen peli pelataan ja
     * siten aloittaa pelin.
     */
    private void avaaPeli() {
        try {
            Kayttoliittyma kayttoliittyma = new Kayttoliittyma(kaynnistin.getPeli());
            SwingUtilities.invokeLater(kayttoliittyma);
        } catch (IOException ex) {
            System.out.println("Hups, jostain syystä peliä ei voitu avata :(");
        }
    }

    /**
     * Asettaa pelin pelaajan tai pelaajat.
     */
    private void asetaPelaajat() {
        kaynnistin.getPeli().setPelaaja1(new Pelaaja(kaynnistin.getEkaNimikentta().getText()));
        if (kaynnistin.getPeli().getPelaajia() == 2) {
            asetaKaksinpelinPelaajat();
        }
    }

    /**
     * Asettaa kaksinpelin pelaajat. Jos nimikenttä, josta pelaajan nimeä
     * haetaan on tyhjä, asetetaan nimeksi Pelaaja 1/Pelaaja 2.
     */
    private void asetaKaksinpelinPelaajat() {
        if (kaynnistin.getEkaNimikentta().getText().equals("")) {
            kaynnistin.getPeli().setPelaaja1(new Pelaaja("Pelaaja 1"));
        }
        if (!kaynnistin.getTokaNimikentta().getText().equals("")) {
            kaynnistin.getPeli().setPelaaja2(new Pelaaja(kaynnistin.getTokaNimikentta().getText()));
        } else {
            kaynnistin.getPeli().setPelaaja2(new Pelaaja("Pelaaja 2"));
        }
    }

    /**
     * Tarkistaa onko yksinpeliä avatessa annettu nimimerkki kelvollinen, eli ei
     * sisällä väliviivaa (-) eikä ole liian pitkä.
     *
     * @return true/false
     */
    private boolean kelpaakoNimi() {
        if (kaynnistin.getPeli().getPelaajia() == 2) {
            return true;
        } else if (!kaynnistin.getEkaNimikentta().getText().contains("-") && kaynnistin.getEkaNimikentta().getText().length() <= 10) {
            return true;
        }
        return false;
    }

    /**
     * Jos syötetty nimimerkki on virheellinen, värjää ongelmaan liittyvän ohjetekstin punaiseksi.
     */
    private void virhe() {
        kaynnistin.getAnnaNimi().setForeground(Color.DARK_GRAY);
        kaynnistin.getOhjeTekti().setForeground(Color.DARK_GRAY);
        if (kaynnistin.getEkaNimikentta().getText().contains("-")) {
            kaynnistin.getAnnaNimi().setForeground(Color.red);
        }
        if (kaynnistin.getEkaNimikentta().getText().length() > 10) {
            kaynnistin.getOhjeTekti().setForeground(Color.red);
        }
        kaynnistin.getEkaNimikentta().setText("");
    }

    /**
     * Asettaa pelaajan/pelaajat, avaa pelin ja sulkee Pelinkaynnistimen, mikäli 
     * syötetty nimimerkki on kelvollinen. Jos ei ole, värjää ongelmaan liittyvän ohjetekstin huomiovärillä.
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (kelpaakoNimi()) {
            asetaPelaajat();
            avaaPeli();
            kaynnistin.getFrame().dispose();
        } else {
            virhe();
        }
    }

}
