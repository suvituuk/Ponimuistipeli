package muistipeli.muistipeli;

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
    private final Scanner lukija;
    private int klikkauksia;
    private Pelaaja pelaaja1;
    private Pelaaja pelaaja2;
    private int korttejaKaannettyKierroksella;
    private Kortti ekaKaannetty;
    private Kortti tokaKaannetty;
    private final Tulostenkasittelija tulokset;
    private int pelaajia;
    private Pelaaja pelaajaVuorossa;

    public Peli(Scanner lukija) throws IOException {
        poyta = new Poyta(4);
        this.lukija = lukija;
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
     * Käynnistää pelin.
     * @throws IOException
     */
    public void kaynnista() throws IOException {
        System.out.println("Tervetuloa pelaamaan EEPPISEN HIENOA MLP-muistipeliä!");
        int peli = valitsePeli();
        if (peli == 1) {
            yksinpeli();
        } else {
            kaksinpeli();
        }
    }

    /**
     * Pyörittää peliä pelattaessa yksin.
     * @throws IOException
     */
    public void yksinpeli() throws IOException {
        System.out.println("Anna nimimerkki:");
        String nimi = lukija.nextLine();
        this.pelaaja1 = new Pelaaja(nimi);
        setPelaajaVuorossa(pelaaja1);
        while (true) {
            kierros();
            if (onkoKaikkiLoydetty()) {
                break;
            }
        }
        System.out.println("Onneksi olkoon " + nimi + ", löysit kaikki parit " + this.klikkauksia + " klikkauksella!");
        tuloksenTallennus();
    }

    /**
     * Kysyy, tallennetaanko tulos ja tallentaa tuloksen tarvittaessa.
     *
     * @throws IOException
     */
    public void tuloksenTallennus() throws IOException {
        try {
            System.out.println("Tallenetaanko tulos? (1=kyllä, muu=ei)");
            if (Integer.parseInt(lukija.nextLine()) == 1) {
                tallennaTulos();
            }
        } catch (NumberFormatException | IOException e) {
        }
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
     * Pyörittää peliä pelattaessa kaveria vastaan
     */
    public void kaksinpeli() {
        System.out.println("Ensimmäinen pelaaja, anna nimesi:");
        pelaaja1 = new Pelaaja(lukija.nextLine());
        System.out.println("Toinen pelaaja, anna nimesi:");
        pelaaja2 = new Pelaaja(lukija.nextLine());
        setPelaajaVuorossa(pelaaja1);
        while (true) {
            System.out.println("Vuorossa: " + pelaajaVuorossa.getNimi());
            kierros();
            if (onkoKaikkiLoydetty()) {
                break;
            }
            seuraavaPelaaja();
        }
        System.out.println(voittaja());
    }

    /**
     *
     * Kertoo kaksinpelin voittajan ja sen, kuinka monta paria kumpikin
     * pelaaja löysi.
     * @return palauttaa voittajan stringinä
     */
    public String voittaja() {
        System.out.println(pelaaja1.getNimi() + " löysi " + pelaaja1.getParejaLoydetty() + " paria.\n"
                + pelaaja2.getNimi() + " löysi " + pelaaja2.getParejaLoydetty() + " paria.");
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

    /**
     * Kysyy käyttäjältä, halutaanko pelata yksin vai kahdestaan. Palauttaa pelaajien määrän.
     * @return Pelaajien määrä valitussa pelissä eli 1(yksinpeli) tai 2(kaksinpeli).
     */
    public int valitsePeli() {
        int peli;
        while (true) {
            try {
                System.out.println("Haluatko pelata yksin(1) vai paria vastaan(2)?");
                peli = Integer.parseInt(lukija.nextLine());
                if (peli == 1) {
                    return 1;
                } else if (peli == 2) {
                    return 2;
                }
                System.out.println("Valitse 1 tai 2!");
            } catch (Exception e) {
                System.out.println("Valitse 1 tai 2!");
            }
        }
    }

    /**
     * Valitaan ja käännetään kaksi korttia ja tarkistetaan löytyikö
     * pari, jos ei niin käännetään kortit takaisin
     */
    public void kierros() {
        Kortti ekaKortti = kortinValinta();
        kortinKaanto(ekaKortti);
        Kortti tokaKortti = kortinValinta();
        kortinKaanto(tokaKortti);
        if (ekaKortti.getPoni() != tokaKortti.getPoni()) {
            ekaKortti.kaanna();
            tokaKortti.kaanna();
        } else {
            System.out.println("Pari löytyi!");
            pelaajaVuorossa.setParejaLoydetty(pelaajaVuorossa.getParejaLoydetty() + 1);
        }
    }

    /**
     * Pelaaja valitsee kortin jonka haluaa kääntää.
     * @return valittu kortti eli kortti joka halutaan kääntää
     */
    public Kortti kortinValinta() {
        while (true) {
            int sarake = valitse("sarake");
            int rivi = valitse("rivi");
            Kortti kortti = poyta.getRuudukko()[sarake][rivi];
            if (!kortti.onkoKaannetty()) {
                return kortti;
            } else {
                System.out.println("Valitse kortti, joka ei ole käännettynä!");
            }
        }
    }

    /**
     * Rivin tai sarakkeen valinta, josta kortti halutaan kääntää.
     *
     * @param riviVaiSarake valitaanko rivi vai sarake
     * @return valitun rivin tai sarakkeen indeksi 0-3
     */
    public int valitse(String riviVaiSarake) {
        while (true) {
            try {
                System.out.println("Valitse " + riviVaiSarake + ":");
                int valittu = Integer.parseInt(lukija.nextLine());
                if (valittu >= 0 && valittu <= 3) {
                    return valittu;
                } else {
                    System.out.println("Valitse 0-3!");
                }
            } catch (Exception e) {
                System.out.println("Valitse 0-3!");
            }
        }
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
