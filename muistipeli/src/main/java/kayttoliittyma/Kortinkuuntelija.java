package kayttoliittyma;

import muistipeli.logiikka.Kortti;
import muistipeli.logiikka.Peli;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * Kortinkuuntelija on ActionListener joka vastaa korttien toiminnallisuudesta.
 */
public class Kortinkuuntelija implements ActionListener {

    private final JButton nappi;
    private final Peli peli;
    private final Kortti kortti;
    private final Kayttoliittyma kl;

    public Kortinkuuntelija(Peli peli, JButton nappi, int kortinNumero, Kayttoliittyma kl) {
        this.nappi = nappi;
        this.peli = peli;
        this.kortti = this.peli.getPoyta().getRuudukko()[kortinNumero % peli.getPoyta().getSivu()][kortinNumero / peli.getPoyta().getSivu()];
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
        if (!kl.getPeli().onkoPari()) {
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
        Lopetus lopetus = new Lopetus(kl);
        SwingUtilities.invokeLater(lopetus);
    }

    /**
     * Tapahtuma käännettäessä kierroksen toinen kortti.
     */
    public void tapahtumaTokalla() {
        if (!this.kortti.onkoKaannetty()) {
            tokanKaanto();
            if (kl.getPeli().onkoPari()) {
                peli.getPelaajaVuorossa().lisaaPari();
            }
            if (peli.onkoKaikkiLoydetty()) {
                pelinLopetus();
            } else if (peli.getPelaajia() == 2) {
                peli.seuraavaPelaaja();
            }
            peli.setKorttejaKaannettyKierroksella(0);
        } else {
            peli.setKorttejaKaannettyKierroksella(1);
        }
    }

    /**
     * Asettaa tarvittavat tekstit käyttöliittymän yläosaan.
     */
    public void asetaTekstit() {
        if (peli.getPelaajia() == 1) {
            kl.getTokaTeksti().setText("" + peli.getKlikkauksia());
        } else if (peli.getPelaajia() == 2) {
            kl.getEkaTeksti().setText(peli.getPelaaja1().loydetytParit() + ", " + peli.getPelaaja2().loydetytParit());
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
