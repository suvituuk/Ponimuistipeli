package kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import muistipeli.muistipeli.Kortti;
import muistipeli.muistipeli.Pelaaja;
import muistipeli.muistipeli.Peli;

/**
 *
 * Kortinkuuntelija on ActionListener joka vastaa korttien toiminnallisuudesta.
 */
public class Kortinkuuntelija implements ActionListener {

    private JButton nappi;
    private Peli peli;
    private int numero;
    private Kortti kortti;
    private Kayttoliittyma kl;

    public Kortinkuuntelija(Peli peli, JButton nappi, int kortti, Kayttoliittyma kl) {
        this.nappi = nappi;
        this.peli = peli;
        this.numero = kortti;
        this.kortti = this.peli.getPoyta().getRuudukko()[numero % peli.getPoyta().getSivu()][numero / peli.getPoyta().getSivu()];
        this.kl = kl;
    }

    /**
     * Kääntää kortin takaisin väärinpäin ja asettaa kuvaksi taustan.
     */
    private void kaannaTakaisin() {
        kortti.kaanna();
        nappi.setIcon(new ImageIcon("kuvat/cardback.jpg"));
    }

    /**
     * Kääntää kortin oikeinpäin ja asettaa kuvaksi kortin kuvan.
     */
    private void kaanna() {
        peli.kortinKaanto(kortti);
        nappi.setIcon(kortti.getKuva());
    }

    /**
     * Jos edelliset kortit eivät olleet pari, kääntää ne takaisin.
     */
    public void kaannaEdellisetTakaisin() {
        if (!kl.onkoPari()) {
            peli.setKorttejaKaannettyKierroksella(2);
            kl.getEkaKaannetty().doClick();
            peli.setKorttejaKaannettyKierroksella(2);
            kl.getTokaKaannetty().doClick();
            peli.setKorttejaKaannettyKierroksella(1);
        }
    }

    /**
     * Kääntää ensimmäisen valitun kortin.
     */
    public void ekanKaanto() {
        kaanna();
        peli.setEkaKaannetty(kortti);
        kl.setEkaKaannetty(nappi);
    }

    /**
     * Kääntää toisen valitun kortin.
     */
    public void tokanKaanto() {
        kl.setTokaKaannetty(nappi);
        peli.setTokaKaannetty(kortti);
        kaanna();
    }

    /**
     * Tapahtuma valittaessa kierroksen ensimmäinen kortti.
     */
    public void tapahtumaEkalla() {
        kaannaEdellisetTakaisin();
        if (!this.kortti.onkoKaannetty()) {
            ekanKaanto();
        } else {
            peli.setKorttejaKaannettyKierroksella(0);
        }
    }

    /**
     * Asettaa tarvittaessa pelin loputtua tuloksen lisäys -painikkeen käyttöön.
     */
    public void pelinLopetus() {
        if (peli.getPelaajia() == 1 && !peli.getPelaaja1().getNimi().equals("")) {
            kl.getTuloksenLisays().setEnabled(true);
        }
    }

    /**
     * Tapahtuma käännettäessä kierroksen toinen kortti.
     */
    public void tapahtumaTokalla() {
        if (!this.kortti.onkoKaannetty()) {
            tokanKaanto();
            if (kl.onkoPari()) {
                Pelaaja pelaaja = peli.getPelaajaVuorossa();
                pelaaja.setParejaLoydetty(pelaaja.getParejaLoydetty() + 1);
            }
            if (peli.onkoKaikkiLoydetty()) {
                pelinLopetus();
            } else if (peli.getPelaajia() == 2) {
                peli.seuraavaPelaaja();
            }
        }
        peli.setKorttejaKaannettyKierroksella(0);
    }

    /**
     * Kuinka monta paria pelaaja on löytänyt.
     * @param pelaaja Pelaaja, jonka nimi ja parit palautetaan.
     * @return Pelaajan nimi ja löydetyt parit String-muodossa.
     */
    public String loydetytParit(Pelaaja pelaaja) {
        if (pelaaja.getParejaLoydetty() == 1) {
            return pelaaja.getNimi() + ": 1 pari";
        } else {
            return pelaaja.getNimi() + ": " + pelaaja.getParejaLoydetty() + " paria";
        }
    }

    /**
     * Asettaa tarvittavat tekstit käyttöliittymän yläosaan.
     */
    public void asetaTekstit() {
        if (peli.getPelaajia() == 1) {
            kl.getTokaTeksti().setText("" + peli.getKlikkauksia());
        } else if (peli.getPelaajia() == 2) {
            kl.getEkaTeksti().setText(loydetytParit(peli.getPelaaja1()) + ", " + loydetytParit(peli.getPelaaja2()));
            if (peli.onkoKaikkiLoydetty()) {
                kl.getTokaTeksti().setText(peli.voittaja());
            } else {
                kl.getTokaTeksti().setText("Vuorossa: " + peli.getPelaajaVuorossa().getNimi());
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        peli.setKorttejaKaannettyKierroksella(peli.getKorttejaKaannettyKierroksella() + 1);
        if (peli.getKorttejaKaannettyKierroksella() == 1) {
            tapahtumaEkalla();
        } else if (peli.getKorttejaKaannettyKierroksella() == 2) {
            tapahtumaTokalla();
        } else if (peli.getKorttejaKaannettyKierroksella() == 3) {
            kaannaTakaisin();
        }
        asetaTekstit();
    }

}
