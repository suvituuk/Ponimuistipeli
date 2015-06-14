package kayttoliittyma;

import java.awt.*;
import java.io.IOException;
import javax.swing.*;
import muistipeli.logiikka.Peli;

/**
 *
 * Graafinen käyttöliittymä, jossa peli pelataan. Sisältää kortit ruudukkona ja
 * painikkeet tulosten katseluun ja uuden pelin aloittamiseen, sekä tarvittavat
 * tekstit.
 */
public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private final Peli peli;
    private final Container ruudukko;
    private JButton ekaKaannetty;
    private JButton tokaKaannetty;
    private JLabel ekaTeksti;
    private JLabel tokaTeksti;

    /**
     *
     * @param peli Pelattava peli.
     * @throws IOException
     */
    public Kayttoliittyma(Peli peli) throws IOException {
        this.peli = peli;
        this.ruudukko = new Container();
        JButton oletuskortti = new JButton("1");
        this.ekaKaannetty = oletuskortti;
        this.tokaKaannetty = oletuskortti;
        peli.setPelaajaVuorossa(peli.getPelaaja1());
    }

    @Override
    public void run() {
        frame = new JFrame("MLP-muistipeli");
        asetaKoko();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Asettaa käyttöliittymän koon sen mukaan pelataanko isolla vai pienellä
     * ruudukolla.
     */
    private void asetaKoko() {
        if (peli.getPoyta().getSivu() == 4) {
            frame.setPreferredSize(new Dimension(420, 480));
        } else {
            frame.setPreferredSize(new Dimension(620, 680));
        }
    }

    /**
     * Luo ja lisää tarvittavat komponentit käyttöliittymään.
     *
     * @param container Container johon luodut komponentit asetetaan.
     */
    private void luoKomponentit(Container container) {
        alusta(container);
        lisaaYlapalkki(container);
        lisaaRuudukko(container);
        lisaaAlapalkki(container);
    }

    /**
     * Asettaa Container-olion layoutin ja taustan.
     *
     * @param container
     */
    private void alusta(Container container) {
        container.setBackground(java.awt.Color.decode("#FFDBF9"));
        container.setLayout(new BorderLayout());
    }

    /**
     * Luo ja lisää ruudukon Container-olioon.
     *
     * @param container Container, johon ruudukko lisätään.
     */
    private void lisaaRuudukko(Container container) {
        luoRuudukko(ruudukko);
        container.add(ruudukko);
    }

    /**
     * Luo ja lisää Container-olioon yläpalkin, jossa pelin kulkuun liittyvät
     * tekstit näkyvät.
     *
     * @param container Container-olio, johon palkki lisätään.
     */
    private void lisaaYlapalkki(Container container) {
        Container ylapalkki = new Container();
        luoYlapalkki(ylapalkki);
        container.add(ylapalkki, BorderLayout.NORTH);
    }

    /**
     * Luo ja lisää Container-olioon alapalkin, jossa on painikkeet tulosten
     * katseluun ja uuden pelin aloittamiseen.
     *
     * @param container Container-olio, johon palkki lisätään.
     */
    private void lisaaAlapalkki(Container container) {
        Container alapalkki = new Container();
        luoAlapalkki(alapalkki);
        container.add(alapalkki, BorderLayout.SOUTH);
    }

    /**
     * Alustaa alapalkin asettamalla sille layoutin ja lisää tarvittavat
     * painikkeet.
     *
     * @param container alapalkki
     */
    private void luoAlapalkki(Container container) {
        GridLayout layout = new GridLayout();
        container.setLayout(layout);
        luoTuloksetNappi(container);
        luoUusiPeliNappi(container);
    }

    /**
     * Luo painikkeen tulosten katselulle.
     *
     * @param container Container-olio, johon painike lisätään.
     */
    private void luoTuloksetNappi(Container container) {
        JButton naytaTulokset = new JButton("Tulokset");
        teeHienoPinkkiNappula(naytaTulokset);
        naytaTulokset.addActionListener(new TulostenKatselunKuuntelija());
        container.add(naytaTulokset);
    }

    /**
     * Luo painikkeen uuden pelin aloitukseen.
     *
     * @param container Container-olio, johon painike lisätään.
     */
    private void luoUusiPeliNappi(Container container) {
        JButton uusiPeli = new JButton("Uusi peli");
        uusiPeli.addActionListener(new UudenPelinKuuntelija(this));
        teeHienoPinkkiNappula(uusiPeli);
        container.add(uusiPeli);
    }

    /**
     * Alustaa yläpalkin asettamalla sen layoutin ja lisää siihen tarvittavat
     * tekstikentät ja tekstit.
     *
     * @param container yläpalkki
     */
    private void luoYlapalkki(Container container) {
        GridLayout layout = new GridLayout();
        container.setLayout(layout);
        if (this.peli.getPelaajia() == 1) {
            yksinpelinTekstit(container);
        } else if (peli.getPelaajia() == 2) {
            kaksinpelinTekstit(container);
        }
    }

    private void yksinpelinTekstit(Container container) {
        container.add(new JLabel("Klikkauksia:"));
        tokaTeksti = new JLabel("0");
        container.add(tokaTeksti);
    }

    private void kaksinpelinTekstit(Container container) {
        ekaTeksti = new JLabel(peli.getPelaaja1().getNimi() + ": " + peli.getPelaaja1().getParejaLoydetty() + " paria, "
                + peli.getPelaaja2().getNimi() + ": " + peli.getPelaaja2().getParejaLoydetty() + " paria");
        container.add(ekaTeksti);
        tokaTeksti = new JLabel("Vuorossa: " + peli.getPelaaja1().getNimi());
        container.add(tokaTeksti);
    }

    /**
     * Luo peliruudukon kortteineen ja asettaa sen layoutin.
     *
     * @param container ruudukko
     */
    private void luoRuudukko(Container container) {
        GridLayout layout = new GridLayout(peli.getPoyta().getSivu(), peli.getPoyta().getSivu());
        container.setLayout(layout);
        int i = 0;
        while (i < peli.getPoyta().getSivu() * peli.getPoyta().getSivu()) {
            ImageIcon icon = new ImageIcon("kuvat/cardback.jpg");
            JButton nappi = new JButton(icon);
            nappi.addActionListener(new Kortinkuuntelija(this.peli, nappi, i, this));
            container.add(nappi);
            i++;
        }
    }

    /**
     * Tekee painikkeesta vaaleanpunaisen ja kohotetun.
     *
     * @param nappula Painike, jota muokataan.
     */
    public void teeHienoPinkkiNappula(JButton nappula) {
        nappula.setBorder(BorderFactory.createRaisedBevelBorder());
        nappula.setBackground(java.awt.Color.decode("#FFAFF9"));
    }
    
    public JFrame getFrame() {
        return frame;
    }

    public Container getRuudukko() {
        return this.ruudukko;
    }

    public void setEkaKaannetty(JButton kortti) {
        this.ekaKaannetty = kortti;
    }

    public JButton getEkaKaannetty() {
        return this.ekaKaannetty;
    }

    public void setTokaKaannetty(JButton kortti) {
        this.tokaKaannetty = kortti;
    }

    public JButton getTokaKaannetty() {
        return this.tokaKaannetty;
    }

    public JLabel getEkaTeksti() {
        return this.ekaTeksti;
    }

    public JLabel getTokaTeksti() {
        return this.tokaTeksti;
    }

    public Peli getPeli() {
        return this.peli;
    }

}
