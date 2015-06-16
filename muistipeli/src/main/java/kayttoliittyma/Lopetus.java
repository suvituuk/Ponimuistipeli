package kayttoliittyma;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

/**
 *
 * @author Mlle S
 */
public class Lopetus implements Runnable {

    private JFrame frame;
    private final Kayttoliittyma kl;

    /**
     * 
     * @param kl Kayttoliittyma, josta Lopetus avataan ja jonka se tarvittaessa sulkee.
     */
    public Lopetus(Kayttoliittyma kl) {
        this.kl = kl;
    }

    @Override
    public void run() {
        frame = new JFrame("Peli ohi!");

        frame.setPreferredSize(new Dimension(450, 120));

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(kl.getRuudukko());

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Luo ja lisää käyttöliittymään tarvittavat komponentit.
     * @param container Container, johon komponentit lisätään.
     */
    private void luoKomponentit(Container container) {
        container.setBackground(java.awt.Color.decode("#FFDBF9"));
        container.setLayout(new GridLayout(3, 1));
        if (kl.getPeli().getPelaajia() == 1) {
            yksinpelinKomponentit(container);
        } else {
            kaksinpelinKomponentit(container);
        }
    }

    /**
     * Palauttaa pelaajan nimen ja tarvittaessa välin.
     * @return palautettava String
     */
    private String pelaajanNimi() {
        String nimi = kl.getPeli().getPelaaja1().getNimi();
        if (!nimi.equals("")) {
            nimi = " " + kl.getPeli().getPelaaja1().getNimi();
        }
        return nimi;
    }

    /**
     * Luo kaksinpelin lopetuksessa tarvittavat komponentit.
     * Käytännössä tekstimuodossa tiedot löydetyistä pareita ja voittajasta, 
     * sekä painike uuden pelin aloitukseen.
     * @param container Container, johon komponentit lisätään.
     */
    private void kaksinpelinKomponentit(Container container) {
        container.add(new JLabel(kl.getPeli().getPelaaja1().getNimi() +" löysi "
                + kl.getPeli().getPelaaja1().getParejaLoydetty() +" paria, " + 
                kl.getPeli().getPelaaja2().getNimi() + " löysi " + 
                kl.getPeli().getPelaaja2().getParejaLoydetty() + " paria."));
        container.add(new JLabel(kl.getPeli().voittaja()));
        luoUusiPeliNappi(container);
    }

    /**
     * Luo yksinpelin lopetuksessa tarvittavat komponentit, eli JLabeleina tiedot 
     * klikkauksista ja pisteistä sekä painikkeen uuden pelin aloitukseen ja tuloksen 
     * tallennukseen, mikäli se on mahdollista.
     * @param container 
     */
    private void yksinpelinKomponentit(Container container) {
        container.add(new JLabel("Onneksi olkoon" + pelaajanNimi() + 
                ", löysit kaikki parit " + kl.getPeli().getKlikkauksia() + " klikkauksella!"));
        container.add(new JLabel(pisteTeksti()));
        if (kl.getPeli().getPelaaja1().getNimi().isEmpty()) {
            luoUusiPeliNappi(container);
        } else {
            Container nappulat = new Container();
            luoPainikkeet(nappulat);
            container.add(nappulat);
        }
    }
    
    /**
     * Paluttaa pistemäärän kertovan String-tekstin.
     * @return palautettava teksti
     */
    private String pisteTeksti() {
        String teksti = "Sait " + pistemaara() + " pistettä!";
        if (!pelaajanNimi().isEmpty()) {
            teksti += " Tallennetaanko tulos?";
        }
        return teksti;
    }

    /**
     * Luo painikkeen uuden pelin aloitukseen.
     * @param container Container-olio, johon painike lisätään.
     */
    private void luoUusiPeliNappi(Container container) {
        JButton uusiPeli = new JButton("Pelaa uudestaan!");
        uusiPeli.addActionListener(new UudenPelinKuuntelija(kl, this));
        teeHienoPinkkiNappula(uusiPeli);
        container.add(uusiPeli);
    }

    public JFrame getFrame() {
        return this.frame;
    }

    private int pistemaara() {
        double alkupisteet = ((double) kl.getPeli().getPoyta().getKortteja() / kl.getPeli().getKlikkauksia()) * 1000;
        int pisteet = (int) (alkupisteet + (kl.getPeli().getPoyta().getKortteja() * 2));
        return pisteet;
    }

    private void luoPainikkeet(Container container) {
        container.setLayout(new GridLayout());
        JButton kylla = new JButton("Tallenna");
        kylla.addActionListener(new TuloksenLisaysKuuntelija(kl, this));
        teeHienoPinkkiNappula(kylla);
//        BorderFactory.createMatteBorder(4, 4, 4, 4, Color.decode("#FFDBF9"))
        container.add(kylla);
        luoUusiPeliNappi(container);
    }
    
    public void teeHienoPinkkiNappula(JButton nappula) {
        Border border = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 20, 1, 20, Color.decode("#FFDBF9")), BorderFactory.createRaisedBevelBorder());
        nappula.setBorder(border);
        nappula.setBackground(java.awt.Color.decode("#FFAFF9"));
    }

}
