package muistipeli.muistipeli;

import java.io.IOException;
import java.util.*;
/**
 * 
 * Tämä on tärkein luokka, joka pyörittää peliä.
 */
public class Peli {
    private Poyta poyta;
    private Scanner lukija;
    private int klikkauksia;
    private Pelaaja pelaaja1;
    private Pelaaja pelaaja2;
    private int korttejaKaannettyKierroksella;
    private Kortti ekaKaannetty;
    private Kortti tokaKaannetty;
    
    public Peli(Scanner lukija){
        poyta = new Poyta();
        this.lukija = lukija;
        this.klikkauksia = 0;
        this.korttejaKaannettyKierroksella = 0;
        Kortti oletuskortti = new Kortti(200);
        this.ekaKaannetty = new Kortti(297);
        this.tokaKaannetty = oletuskortti;
    }
    
    /**
     * 
     * Käynnistää pelin.
     * @throws IOException 
     */
    public void kaynnista() throws IOException{
        System.out.println("Tervetuloa pelaamaan EEPPISEN HIENOA MLP-muistipeliä!");
        int peli = valitsePeli();
        if (peli == 1){
            yksinpeli();
        } else {
            kaksinpeli();
        }
    }
    /**
     * 
     * yksinpeli pyörittää peliä pelattaessa yksin.
     * @throws IOException 
     */
    public void yksinpeli() throws IOException{
        System.out.println("Anna nimimerkki:");
        String nimi = lukija.nextLine();
        this.pelaaja1 = new Pelaaja(nimi);
        while(true){
            kierros(pelaaja1);
            if (onkoKaikkiLoydetty()){
                break;
            }
        }
        System.out.println("Onneksi olkoon " + nimi + ", löysit kaikki parit " + this.klikkauksia + " klikkauksella!");
        tuloksenTallennus();
    }
    
    /**
     * tallentaa tuloksen
     * @throws IOException 
     */
    public void tuloksenTallennus() throws IOException{
        try {
            System.out.println("Tallenetaanko tulos? (1=kyllä, muu=ei)");
            if (Integer.parseInt(lukija.nextLine()) == 1){
                new Tulos(new Pelaaja(pelaaja1.getNimi()), this.klikkauksia).lisaaTulos();
            }
        } catch (Exception e) {
        }
    }
    
    /**
     * 
     * kaksinpeli pyörittää peliä pelattaessa kaveria vastaan
     */
    public void kaksinpeli(){
        System.out.println("Ensimmäinen pelaaja, anna nimesi:");
        pelaaja1 = new Pelaaja(lukija.nextLine());
        System.out.println("Toinen pelaaja, anna nimesi:");
        pelaaja2 = new Pelaaja(lukija.nextLine());
        Pelaaja pelaaja = pelaaja1;
        while (true) {
            System.out.println("Vuorossa: " + pelaaja.getNimi());
            kierros(pelaaja);
            if (onkoKaikkiLoydetty()) {
                break;
            }
            pelaaja = this.seuraavaPelaaja(pelaaja);
        }
        voittaja();
    }
    
    /**
     * 
     * voittaja kertoo kaksinpelin voittajan ja sen, kuinka monta paria kumpikin pelaaja löysi
     */
    public void voittaja(){
        System.out.println(pelaaja1.getNimi() + " löysi " + pelaaja1.getParejaLoydetty() + " paria.\n" + 
                pelaaja2.getNimi() + " löysi " + pelaaja2.getParejaLoydetty() + " paria.");
        if (pelaaja1.getParejaLoydetty() > pelaaja2.getParejaLoydetty()) {
            System.out.println("Voittaja on: " + pelaaja1.getNimi() + "!");
        } else if (pelaaja1.getParejaLoydetty() < pelaaja2.getParejaLoydetty()) {
            System.out.println("Voittaja on: " + pelaaja2.getNimi() + "!");
        } else {
            System.out.println("Tasapeli!");
        }
    }
    
    public boolean onkoKaikkiLoydetty(){
        int rivi = 0;
        while(rivi < 4){
            int sarake = 0;
            while(sarake < 4){
                if(!poyta.getRuudukko()[sarake][rivi].onkoKaannetty()){
                    return false;
                }
                sarake++;
            }
            rivi++;
        }
        return true;
    }
    
    /**
     * 
     * kaksipelissa seuraavaPelaaja palauttaa seuraavaksi vuorossa olevan pelaajan
     * @param pelaaja nyt vuorossa oleva pelaaja
     * @return seuraava pelaaja
     */
    public Pelaaja seuraavaPelaaja(Pelaaja pelaaja) {
        if (pelaaja == pelaaja1) {
            pelaaja = pelaaja2;
        } else if (pelaaja == pelaaja2) {
            pelaaja = pelaaja1;
        }
        return pelaaja;
    }
    
    /**
     * 
     * voi valita haluaako pelata yksin vai kaveria vastaan
     * @return valittu peli eli yksin- tai kaksinpeli
     */
    public int valitsePeli(){
        int peli;
        while (true){
            try {
                System.out.println("Haluatko pelata yksin(1) vai paria vastaan(2)?");
                peli = Integer.parseInt(lukija.nextLine());
                if (peli == 1){
                    return 1;
                } else if (peli == 2){
                    return 2;
                }
                System.out.println("Valitse 1 tai 2!");
            } catch (Exception e) {
                System.out.println("Valitse 1 tai 2!");
            }
        }
    }
    
    /**
     * 
     * kierros eli valitaan ja käännetään kaksi korttia ja tarkistetaan löytyikö pari, jos ei niin käännetään kortit takaisin
     * @param pelaaja vuorossa oleva pelaaja
     */
    public void kierros(Pelaaja pelaaja){
        Kortti ekaKortti = kortinValinta();
        kortinKaanto(ekaKortti);
        Kortti tokaKortti = kortinValinta();
        kortinKaanto(tokaKortti);
        if (ekaKortti.getPoni() != tokaKortti.getPoni()){
            ekaKortti.kaanna();
            tokaKortti.kaanna();
        } else {
            System.out.println("Pari löytyi!");
            pelaaja.setParejaLoydetty(pelaaja.getParejaLoydetty() + 1);
        }
    }
    
    /**
     * 
     * pelaaja valitsee kortin jonka haluaa kääntää
     * @return valittu kortti eli kortti joka halutaan kääntää
     */
    public Kortti kortinValinta(){
        while(true){
            int sarake = valitse("sarake");
            int rivi = valitse("rivi");
            Kortti kortti = poyta.getRuudukko()[sarake][rivi];
            if(!kortti.onkoKaannetty()){
                return kortti;
            } else {
                System.out.println("Valitse kortti, joka ei ole käännettynä!");
            }
        }
    }
    
    /**
     * 
     * rivin tai sarakkeen valinta, josta kortti halutaan kääntää
     * @param riviVaiSarake valitaanko rivi vai sarake
     * @return valitun rivin tai sarakkeen indeksi 0-3
     */
    public int valitse(String riviVaiSarake){
        while (true){
            try {
                System.out.println("Valitse " + riviVaiSarake + ":");
                int valittu = Integer.parseInt(lukija.nextLine());
                if (valittu >= 0 && valittu <= 3){
                    return valittu;
                } else {
                    System.out.println("Valitse 0-3!");
                }
            } catch (Exception e) {
                System.out.println("Valitse 0-3!");
            }
        }
    }
    
    public void setKorttejaKaannettyKierroksella(int kaannetty) {
        this.korttejaKaannettyKierroksella = kaannetty;
    }
    
    public int getKorttejaKaannettyKierroksella() {
        return this.korttejaKaannettyKierroksella;
    }
    
    /**
     * 
     * kääntää kortin
     * @param kortti käännettävä kortti
     */
    public void kortinKaanto(Kortti kortti){
        kortti.kaanna();
        this.klikkauksia += 1;
        System.out.println("\n" + poyta.toString());
    }
    
    public Poyta getPoyta(){
        return this.poyta;
    }
    
    public void setPelaaja1(Pelaaja pelaaja){
        this.pelaaja1 = pelaaja;
    }
    
    public Pelaaja getPelaaja1(){
        return this.pelaaja1;
    }
    
    public void setPelaaja2(Pelaaja pelaaja){
        this.pelaaja2 = pelaaja;
    }
    
    public Pelaaja getPelaaja2(){
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
    
}
