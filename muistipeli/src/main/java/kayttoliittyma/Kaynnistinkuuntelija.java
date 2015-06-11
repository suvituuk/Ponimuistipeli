package kayttoliittyma;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.SwingUtilities;
import muistipeli.muistipeli.Pelaaja;

/**
 *
 * ActionListener joka aloittaa varsinaisen pelin, jos kaikki on kunnossa.
 */
public class Kaynnistinkuuntelija implements ActionListener {

    private final Pelinkaynnistin kaynnistin;

    public Kaynnistinkuuntelija(Pelinkaynnistin kaynnistin) {
        this.kaynnistin = kaynnistin;
    }

    /**
     * Avaa uuden graafisen käyttöliittymän, jossa varsinainen peli pelataan ja siten aloittaa pelin.
     */
    public void avaaPeli() {
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
    public void asetaPelaajat() {
        kaynnistin.getPeli().setPelaaja1(new Pelaaja(kaynnistin.getEkaNimikentta().getText()));
        if (kaynnistin.getPeli().getPelaajia() == 2) {
            asetaKaksinpelinPelaajat();
        }
    }

    /**
     * Asettaa kaksinpelin pelaajat. Jos nimikenttä, josta pelaajan nimeä haetaan on tyhjä, 
     * asetetaan nimeksi Pelaaja 1/Pelaaja 2.
     */
    public void asetaKaksinpelinPelaajat() {
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
     * Tarkistaa onko yksinpeliä avatessa annettu nimimerkki kelvollinen, eli ei sisällä väliviivaa (-).
     * @return true/false
     */
    public boolean kelpaakoNimi() {
        if (kaynnistin.getPeli().getPelaajia() == 2) {
            return true;
        } else if (!kaynnistin.getEkaNimikentta().getText().contains("-")) {
            return true;
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (kelpaakoNimi()) {
            asetaPelaajat();
            avaaPeli();
            kaynnistin.getFrame().dispose();
        } else {
            kaynnistin.getAnnaNimi().setForeground(Color.red);
            kaynnistin.getEkaNimikentta().setText("");
        }
    }

}
