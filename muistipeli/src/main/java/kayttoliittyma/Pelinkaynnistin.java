package kayttoliittyma;

import java.awt.*;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.*;
import muistipeli.logiikka.Peli;

/**
 *
 * Graafinen käyttöliittymä, joka sisältää tarvittavat komponentit pelin valintaan ja käynnistykseen.
 */
public class Pelinkaynnistin implements Runnable {
    private JFrame frame;
    private final Peli peli;
    private JLabel valitsePeli;
    private JLabel ekaPelaaja;
    private JLabel tokaPelaaja;
    private JLabel annaNimi;
    private JLabel ohjeTeksti;
    private JTextArea ekaNimikentta;
    private JTextArea tokaNimikentta;
    private JButton valmis;
    private JButton yksinpeli;
    private JButton kaksinpeli;
    private JButton iso;
    private JButton pieni;
    
    public Pelinkaynnistin() throws IOException {
        this.peli = new Peli();
    }

    @Override
    public void run() {
        frame = new JFrame("MLP-muistipeli");
        frame.setPreferredSize(new Dimension(350, 230));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }
    
    /**
     * Luo käyttöliittymään tarvittavat komponentit.
     * @param container Container-olio, johon komponentit asetetaan.
     */
    private void luoKomponentit(Container container) {
        alusta(container);
        luoPelinValinta(container);
        luoKoonValinta(container);
        luoNimienSyotto(container);
        container.add(new JLabel(""));
        luoValmis(container);
    }
    
    /**
     * Luo tarvittavat komponentit nimien syöttöön.
     * @param container Container-olio, johon komponentit lisätään.
     */
    public void luoNimienSyotto(Container container) {
        annaNimi = new JLabel("");
        container.add(annaNimi);
        ohjeTeksti = new JLabel("");
        container.add(ohjeTeksti);
        luoNimikentat(container);
    }
    
    /**
     * Alustaa Container-olion asettamalla sille layoutin ja taustan.
     * @param container 
     */
    public void alusta(Container container) {
        container.setBackground(java.awt.Color.decode("#FFDBF9"));
        container.setLayout(new GridLayout(7, 2));
    }
    
    /**
     * Luo tarvittavat komponentit pelin (yksin- vai kaksinpeli) valintaan.
     * @param container Container-olio, johon komponentit asetetaan.
     */
    public void luoPelinValinta(Container container) {
        valitsePeli = new JLabel("Valitse peli:");
        container.add(valitsePeli);
        container.add(new JLabel(""));
        luoNappulat(container);
    }
    
    /**
     * Luo painikkeen pelin aloitukseen.
     * @param container Container, johon painike asetetaan.
     */
    public void luoValmis(Container container) {
        valmis = new JButton("Aloita peli!");
        valmis.addActionListener(new Kaynnistinkuuntelija(this));
        teeHienoPinkkiNappula(valmis);
        valmis.setEnabled(false);
        container.add(valmis);
    }
    
    /**
     * Tekee painikkeesta vaaleanpunaisen ja kohotetun.
     * @param nappula Painike, jota muokataan.
     */
    public void teeHienoPinkkiNappula(JButton nappula) {
        nappula.setBorder(BorderFactory.createRaisedBevelBorder());
        nappula.setBackground(Color.decode("#FFAFF9"));
    }
    
    /**
     * Luo tarvittavat komponentit ruudukon koon valintaan.
     * @param container Container, johon komponentit asetetaan.
     */
    private void luoKoonValinta(Container container) {
        pieni = new JButton("Pieni ruudukko");
        pieni.addActionListener(new KoonValintaKuuntelija(4, this));
        pieni.setEnabled(false);
        teeHienoPinkkiNappula(pieni);
        container.add(pieni);
        iso = new JButton("Iso ruudukko");
        iso.addActionListener(new KoonValintaKuuntelija(6, this));
        iso.setEnabled(false);
        teeHienoPinkkiNappula(iso);
        container.add(iso);
    }
    
    /**
     * Luo painikkeet pelin valintaan.
     * @param container Container, johon painikkeet asetetaan.
     */
    private void luoNappulat(Container container) {
        yksinpeli = new JButton("Yksinpeli");
        yksinpeli.addActionListener(new Pelinvalitsinkuuntelija(this, 1));
        teeHienoPinkkiNappula(yksinpeli);
        container.add(yksinpeli);
        kaksinpeli = new JButton("Kaksinpeli");
        kaksinpeli.addActionListener(new Pelinvalitsinkuuntelija(this, 2));
        teeHienoPinkkiNappula(kaksinpeli);
        container.add(kaksinpeli);
    }
    
    /**
     * Luo tekstikentät nimien syöttöön.
     * @param container Container, johon kentät asetetaan.
     */
    private void luoNimikentat(Container container) {
        ekaPelaaja = new JLabel("");
        container.add(ekaPelaaja);
        ekaNimikentta = new JTextArea();
        ekaNimikentta.setBorder(BorderFactory.createLoweredBevelBorder());
        ekaNimikentta.setEnabled(false);
        container.add(ekaNimikentta);
        tokaPelaaja = new JLabel("");
        container.add(tokaPelaaja);
        tokaNimikentta = new JTextArea();
        tokaNimikentta.setBorder(BorderFactory.createLoweredBevelBorder());
        tokaNimikentta.setEnabled(false);
        container.add(tokaNimikentta);
    }
    
    public JFrame getFrame() {
        return frame;
    }
    
    public Peli getPeli() {
        return this.peli;
    }
    
    public JButton getYksinpeli() {
        return this.yksinpeli;
    }
    
    public JButton getKaksinpeli() {
        return this.kaksinpeli;
    }
    
    public JTextArea getEkaNimikentta() {
        return this.ekaNimikentta;
    }
    
    public JTextArea getTokaNimikentta() {
        return this.tokaNimikentta;
    }
    
    public JLabel getEkaPelaaja() {
        return this.ekaPelaaja;
    }
    
    public JLabel getTokaPelaaja() {
        return this.tokaPelaaja;
    }
    
    public JButton getValmis() {
        return this.valmis;
    }
    
    public JLabel getAnnaNimi() {
        return this.annaNimi;
    }
    
    public JButton getPieni() {
        return this.pieni;
    }
    
    public JButton getIso() {
        return this.iso;
    }
    
    public JLabel getOhjeTekti() {
        return this.ohjeTeksti;
    }
}
