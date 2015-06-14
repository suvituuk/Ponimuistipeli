package muistipeli.logiikka;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Pelattava muistipeli. 
 * Sisältää muuttujina mm. pelaajien määrän ja pelaajat, peliin liittyvän pöydän ja klikkausten määrän.
 */
public class Peli {

    private Poyta poyta;
    private int klikkauksia;
    private Pelaaja pelaaja1;
    private Pelaaja pelaaja2;
    private int korttejaKaannettyKierroksella;
    private Kortti ekaKaannetty;
    private Kortti tokaKaannetty;
    private final Tulostenkasittelija tulokset;
    private int pelaajia;
    private Pelaaja pelaajaVuorossa;

    public Peli() throws IOException {
        poyta = new Poyta(4);
        this.klikkauksia = 0;
        this.korttejaKaannettyKierroksella = 0;
        Kortti oletuskortti = new Kortti(200);
        this.ekaKaannetty = new Kortti(297);
        this.tokaKaannetty = oletuskortti;
        this.tulokset = new Tulostenkasittelija();
        this.pelaaja1 = new Pelaaja("pelaajanNimi");
        this.pelaajaVuorossa = pelaaja1;
    }

    /**
     * Tallentaa pelin tuloksen.
     * @throws IOException 
     */
    public void tallennaTulos() throws IOException {
        double alkupisteet = ((double)this.poyta.getKortteja()/this.klikkauksia)*1000;
        int pisteet = (int) (alkupisteet+(this.poyta.getKortteja()*2));
        Tulos tulos = new Tulos(pelaaja1, pisteet, aika());
        this.tulokset.lisaaTulos(tulos);
    }
    
    /**
     * Palauttaa nykyisen päivämäärän ja kellonajan.
     * @return aika nyt
     */
    public String aika() {
        Calendar kalenteri = Calendar.getInstance();
        Timestamp aika = new java.sql.Timestamp(kalenteri.getTime().getTime());
        String formatisoituAika = new SimpleDateFormat("MM/dd/yyyy HH:mm").format(aika);
        return formatisoituAika;
    }

    /**
     *
     * Kertoo kaksinpelin voittajan ja sen, kuinka monta paria kumpikin
     * pelaaja löysi.
     * @return palauttaa voittajan stringinä
     */
    public String voittaja() {
        if (pelaaja1.getParejaLoydetty() > pelaaja2.getParejaLoydetty()) {
            return "Voittaja on: " + pelaaja1.getNimi() + "!";
        } else if (pelaaja1.getParejaLoydetty() < pelaaja2.getParejaLoydetty()) {
            return "Voittaja on: " + pelaaja2.getNimi() + "!";
        } else {
            return "Tasapeli!";
        }
    }

    /**
     * Tarkistaa, onko kaikki parit jo löydetty.
     * @return true/false
     */
    public boolean onkoKaikkiLoydetty() {
        int rivi = 0;
        while (rivi < poyta.getSivu()) {
            int sarake = 0;
            while (sarake < poyta.getSivu()) {
                if (!poyta.getRuudukko()[sarake][rivi].onkoKaannetty()) {
                    return false;
                }
                sarake++;
            }
            rivi++;
        }
        return true;
    }

    /**
     * Kaksinpelissä seuraavaPelaaja vaihtaa vuorossa olevan pelaajan.
     */
    public void seuraavaPelaaja() {
        if (pelaajaVuorossa == pelaaja1) {
            pelaajaVuorossa = pelaaja2;
        } else if (pelaajaVuorossa == pelaaja2) {
            pelaajaVuorossa = pelaaja1;
        }
    }
    
    public boolean onkoPari() {
        return ekaKaannetty.getPoni() == tokaKaannetty.getPoni();
    }

    /**
     * Kääntää kortin.
     *
     * @param kortti käännettävä kortti
     */
    public void kortinKaanto(Kortti kortti) {
        kortti.kaanna();
        this.klikkauksia += 1;
//        System.out.println("\n" + poyta.toString());
    }
    
    public void setKorttejaKaannettyKierroksella(int kaannetty) {
        this.korttejaKaannettyKierroksella = kaannetty;
    }

    public int getKorttejaKaannettyKierroksella() {
        return this.korttejaKaannettyKierroksella;
    }

    public Poyta getPoyta() {
        return this.poyta;
    }
    
    public void setPoyta(Poyta poyta) {
        this.poyta = poyta;
    }

    public void setPelaaja1(Pelaaja pelaaja) {
        this.pelaaja1 = pelaaja;
    }

    public Pelaaja getPelaaja1() {
        return this.pelaaja1;
    }

    public void setPelaaja2(Pelaaja pelaaja) {
        this.pelaaja2 = pelaaja;
    }

    public Pelaaja getPelaaja2() {
        return this.pelaaja2;
    }

    public void setEkaKaannetty(Kortti kortti) {
        this.ekaKaannetty = kortti;
    }

    public Kortti getEkaKaannetty() {
        return this.ekaKaannetty;
    }

    public void setTokaKaannetty(Kortti kortti) {
        this.tokaKaannetty = kortti;
    }

    public Kortti getTokaKaannetty() {
        return this.tokaKaannetty;
    }

    public void setPelaajia(int pelaajienMaara) {
        this.pelaajia = pelaajienMaara;
    }

    public int getPelaajia() {
        return this.pelaajia;
    }

    public int getKlikkauksia() {
        return this.klikkauksia;
    }

    public Pelaaja getPelaajaVuorossa() {
        return this.pelaajaVuorossa;
    }

    public void setPelaajaVuorossa(Pelaaja pelaaja) {
        this.pelaajaVuorossa = pelaaja;
    }

}
