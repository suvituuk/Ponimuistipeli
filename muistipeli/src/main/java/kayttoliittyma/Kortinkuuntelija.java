/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import kayttoliittyma.Kayttoliittyma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import muistipeli.muistipeli.Kortti;
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
        this.kortti = this.peli.getPoyta().getRuudukko()[numero % 4][numero / 4];
        this.kl = kl;
    }

    private void kaannaTakaisin() {
        kortti.kaanna();
        nappi.setIcon(new ImageIcon("cardback.jpg"));
    }

    private void kaanna() {
        peli.kortinKaanto(kortti);
        nappi.setIcon(kortti.getKuva());
    }

    private void tarkistaOnkoPari() {
        if (peli.getEkaKaannetty().getPoni() == kortti.getPoni()) {
            kl.setOnkoPari(true);
        } else {
            kl.setOnkoPari(false);
        }
    }

    public void kaannaEdellisetTakaisin() {
        if (!kl.getOnkoPari()) {
            peli.setKorttejaKaannettyKierroksella(2);
            kl.getEkaKaannetty().doClick();
            peli.setKorttejaKaannettyKierroksella(2);
            kl.getTokaKaannetty().doClick();
            peli.setKorttejaKaannettyKierroksella(1);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        peli.setKorttejaKaannettyKierroksella(peli.getKorttejaKaannettyKierroksella() + 1);
        if (peli.getKorttejaKaannettyKierroksella() == 1) {
            kaannaEdellisetTakaisin();
            if (!this.kortti.onkoKaannetty()) {
                kaanna();
                peli.setEkaKaannetty(kortti);
                kl.setEkaKaannetty(nappi);
            } else {
                peli.setKorttejaKaannettyKierroksella(0);
            }
        } else if (peli.getKorttejaKaannettyKierroksella() == 2) {
            if (!this.kortti.onkoKaannetty()) {
                kl.setTokaKaannetty(nappi);
                peli.setTokaKaannetty(kortti);
                kaanna();
//                tarkistaOnkoPari();
            }
            peli.setKorttejaKaannettyKierroksella(0);
        } else if (peli.getKorttejaKaannettyKierroksella() == 3) {
            kaannaTakaisin();
        }
//        
//        ImageIcon kuva;
//        if (!kortti.onkoKaannetty()) {
//            kuva = kortti.getKuva();
//        } else {
//            kuva = new ImageIcon("cardback.jpg");
//        }
//        kortti.kaanna();
//        nappi.setIcon(kuva);
    }

    public void odota() {
        try {
            Thread.sleep(1000);                 //1000 milliseconds is one second.
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

}
