package muistipeli.testit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import muistipeli.muistipeli.Kortti;
import muistipeli.muistipeli.Pelaaja;
import muistipeli.muistipeli.Peli;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mlle S
 */
public class PeliTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    
    public PeliTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }
    
    @After
    public void tearDown() {
    }
    
    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }
    
    
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void onkoKaikkiLoydettyToimiiI() throws IOException{
        Peli peli = new Peli(new Scanner(System.in));
        assertEquals(peli.onkoKaikkiLoydetty(), false);
    }
    
    @Test
    public void onkoKaikkiLoydettyToimiiII() throws IOException{
        Peli peli = new Peli(new Scanner(System.in));
        peli.getPoyta().getRuudukko()[1][1].kaanna();
        assertEquals(peli.onkoKaikkiLoydetty(), false);
    }
    
    @Test
    public void onkoKaikkiLoydettyToimiiIII() throws IOException{
        Peli peli = new Peli(new Scanner(System.in));
        int rivi = 0;
        while (rivi < 4) {
            int sarake = 0;
            while(sarake < 4){
                peli.getPoyta().getRuudukko()[sarake][rivi].kaanna();
                sarake++;
            }
            rivi++;
        }
        assertEquals(peli.onkoKaikkiLoydetty(), true);
    }
    
    @Test
    public void korttiAluksiKaantamatta() throws IOException{
        Peli peli = new Peli(new Scanner(System.in));
        assertEquals(peli.getPoyta().getRuudukko()[0][0].onkoKaannetty(), false);
    }
    
    @Test
    public void kortinKaantoKaantaaKortin() throws IOException{
        Peli peli = new Peli(new Scanner(System.in));
        Kortti kortti = peli.getPoyta().getRuudukko()[0][0];
        peli.kortinKaanto(kortti);
        assertEquals(kortti.onkoKaannetty(), true);
    }
    
    @Test
    public void pelaajaVaihtuu() throws IOException{
        Peli peli = new Peli(new Scanner(System.in));
        peli.setPelaaja1(new Pelaaja("eka"));
        peli.setPelaaja2(new Pelaaja("toka"));
        peli.setPelaajaVuorossa(peli.getPelaaja1());
        peli.seuraavaPelaaja();
        assertEquals(peli.getPelaajaVuorossa(), peli.getPelaaja2());
    }
    
    @Test
    public void pelaajaVaihtuuII() throws IOException{
        Peli peli = new Peli(new Scanner(System.in));
        peli.setPelaaja1(new Pelaaja("eka"));
        peli.setPelaaja2(new Pelaaja("toka"));
        peli.setPelaajaVuorossa(peli.getPelaaja2());
        peli.seuraavaPelaaja();
        assertEquals(peli.getPelaajaVuorossa(), peli.getPelaaja1());
    }
    
    @Test
    public void setPelaajaToimii() throws IOException {
        Peli peli = new Peli(new Scanner(System.in));
        Pelaaja pelaaja = new Pelaaja("Toni");
        peli.setPelaaja1(pelaaja);
        assertEquals(peli.getPelaaja1().getNimi(), "Toni");
    }
    
    @Test
    public void setPelaajaToimiiII() throws IOException {
        Peli peli = new Peli(new Scanner(System.in));
        Pelaaja pelaaja = new Pelaaja("Suvi");
        peli.setPelaaja2(pelaaja);
        assertEquals(peli.getPelaaja2().getNimi(), "Suvi");
    }
    
    @Test
    public void valitsePalauttaaOikeanArvon() throws IOException {
        String syote = "1\n";
        Peli peli = new Peli(new Scanner(syote));
        assertEquals(peli.valitse("rivi"), 1);
    }
    
    @Test
    public void kortinValintaToimii() throws IOException {
        String syote = "0\n0\n";
        Peli peli = new Peli(new Scanner(syote));
        Kortti kortti = new Kortti(3);
        peli.getPoyta().getRuudukko()[0][0] = kortti;
        assertEquals(peli.kortinValinta(), kortti);
    }
    
    @Test
    public void valitsePeliToimii() throws IOException {
        String syote = "1\n";
        Peli peli = new Peli(new Scanner(syote));
        assertEquals(peli.valitsePeli(), 1);
        String syote2 = "2\n";
        Peli peli2 = new Peli(new Scanner(syote2));
        assertEquals(peli2.valitsePeli(), 2);
    }
    
    @Test
    public void parinLoytyessaLoydettyPariLisataan() throws IOException{
        String syote = "0\n0\n1\n1\n";
        Peli peli = new Peli(new Scanner(syote));
        peli.getPoyta().getRuudukko()[0][0] = new Kortti(8);
        peli.getPoyta().getRuudukko()[1][1] = new Kortti(8);
        Pelaaja suvi = new Pelaaja("Suvi");
        peli.setPelaajaVuorossa(suvi);
        assertEquals(suvi.getParejaLoydetty(), 0);
        peli.kierros();
        assertEquals(suvi.getParejaLoydetty(), 1);
    }
    
    @Test
    public void josEiPariaKortitKaantyyTakaisin() throws IOException{
        String syote = "0\n0\n1\n1\n";
        Peli peli = new Peli(new Scanner(syote));
        peli.getPoyta().getRuudukko()[0][0] = new Kortti(1);
        peli.getPoyta().getRuudukko()[1][1] = new Kortti(8);
        Pelaaja suvi = new Pelaaja("Suvi");
        peli.kierros();
        assertEquals(peli.getPoyta().getRuudukko()[0][0].onkoKaannetty(), false);
    }
    
    @Test
    public void voittajaTulostuuOikein() throws IOException  {
        Peli peli = new Peli(new Scanner(System.in));
        peli.setPelaaja1(new Pelaaja("Suvi"));
        peli.setPelaaja2(new Pelaaja("Toni"));
        peli.voittaja();
        String expected =  "Suvi löysi 0 paria.\nToni löysi 0 paria.\r\n";
        Assert.assertEquals(outContent.toString(), expected);
    }
    
    @Test
    public void voittajaStringOikeinI() throws IOException {
        Peli peli = new Peli(new Scanner(System.in));
        Pelaaja suvi = new Pelaaja("Suvi");
        Pelaaja toni = new Pelaaja("Toni");
        peli.setPelaaja1(suvi);
        peli.setPelaaja2(toni);
        Assert.assertEquals("Tasapeli!", peli.voittaja());
    }
    
    @Test
    public void VoittajaStringOikeinII() throws IOException  {
        Peli peli = new Peli(new Scanner(System.in));
        Pelaaja suvi = new Pelaaja("Suvi");
        Pelaaja toni = new Pelaaja("Toni");
        peli.setPelaaja1(suvi);
        peli.setPelaaja2(toni);
        toni.setParejaLoydetty(2);
        Assert.assertEquals("Voittaja on: Toni!", peli.voittaja());
    }
    
    @Test
    public void VoittajaStringOikeinIII() throws IOException  {
        Peli peli = new Peli(new Scanner(System.in));
        Pelaaja suvi = new Pelaaja("Suvi");
        Pelaaja toni = new Pelaaja("Toni");
        peli.setPelaaja1(suvi);
        peli.setPelaaja2(toni);
        suvi.setParejaLoydetty(2);
        String expected =  "Voittaja on: Suvi!";
        Assert.assertEquals(expected, peli.voittaja());
    }
    
    @Test
    public void valintaTulostuuOikeinVirheellisellaSyotteella() throws IOException{
        String syote = "a\n1";
        Peli peli = new Peli(new Scanner(syote));
        peli.valitse("rivi");
        String expected =  "Valitse rivi:\r\nValitse 0-3!\r\nValitse rivi:\r\n";
        Assert.assertEquals(outContent.toString(), expected);
    }
    
    @Test
    public void valintaTulostuuOikeinVaarallaLuvulla() throws IOException{
        String syote = "5\n1";
        Peli peli = new Peli(new Scanner(syote));
        peli.valitse("rivi");
        String expected =  "Valitse rivi:\r\nValitse 0-3!\r\nValitse rivi:\r\n";
        Assert.assertEquals(outContent.toString(), expected);
    }
    
    @Test
    public void pelinValintaTulostuuOikeinVirheellisellaSyotteella() throws IOException{
        String syote = "a\n1";
        Peli peli = new Peli(new Scanner(syote));
        peli.valitsePeli();
        String expected =  "Haluatko pelata yksin(1) vai paria vastaan(2)?\r\nValitse 1 tai 2!\r\n"
                + "Haluatko pelata yksin(1) vai paria vastaan(2)?\r\n";
        Assert.assertEquals(outContent.toString(), expected);
    }
    
    @Test
    public void pelinValintaTulostuuOikeinVaarallaLuvulla() throws IOException{
        String syote = "3\n1";
        Peli peli = new Peli(new Scanner(syote));
        peli.valitsePeli();
        String expected =  "Haluatko pelata yksin(1) vai paria vastaan(2)?\r\nValitse 1 tai 2!\r\n"
                + "Haluatko pelata yksin(1) vai paria vastaan(2)?\r\n";
        Assert.assertEquals(outContent.toString(), expected);
    }
    
    @Test
    public void kortinValintaTulostuuOikeinKaannetyllaKortilla() throws IOException{
        String syote = "0\n0\n1\n1\n";
        Peli peli = new Peli(new Scanner(syote));
        peli.getPoyta().getRuudukko()[0][0].kaanna();
        peli.kortinValinta();
        String expected =  "Valitse sarake:\r\nValitse rivi:\r\n"
                + "Valitse kortti, joka ei ole käännettynä!\r\nValitse sarake:\r\nValitse rivi:\r\n";
        Assert.assertEquals(outContent.toString(), expected);
    }
    
    @Test
    public void tuloksenTallennusTulostuuOikein() throws IOException{
        String syote = "\n";
        Peli peli = new Peli(new Scanner(syote));
        peli.tuloksenTallennus();
        String expected =  "Tallenetaanko tulos? (1=kyllä, muu=ei)\r\n";
        Assert.assertEquals(outContent.toString(), expected);
    }
    
    @Test
    public void loydetytParitLisataanPelissa() throws IOException {
        String syote = "1\nsuvi\n0\n0\n2\n2\n\n";
        Peli peli = new Peli(new Scanner(syote));
        
        int rivi = 0;
        while(rivi < 4) {
            int sarake = 0;
            while (sarake < 4) {
                peli.getPoyta().getRuudukko()[sarake][rivi] = new Kortti(1);
                peli.getPoyta().getRuudukko()[sarake][rivi].kaanna();
                sarake++;
            }
            rivi++;
        }
        peli.getPoyta().getRuudukko()[0][0].kaanna();
        peli.getPoyta().getRuudukko()[2][2].kaanna();
        
        peli.kaynnista();
        
        assertEquals(1, peli.getPelaaja1().getParejaLoydetty());
    }
    
    @Test
    public void paritLisataanKaksinpelissa() throws IOException {
        String syote = "2\nsuvi\ntoni\n0\n0\n2\n2\n1\n1\n3\n3\n";
        Peli peli = new Peli(new Scanner(syote));
        
        int rivi = 0;
        while(rivi < 4) {
            int sarake = 0;
            while (sarake < 4) {
                peli.getPoyta().getRuudukko()[sarake][rivi] = new Kortti(1);
                peli.getPoyta().getRuudukko()[sarake][rivi].kaanna();
                sarake++;
            }
            rivi++;
        }
        peli.getPoyta().getRuudukko()[0][0].kaanna();
        peli.getPoyta().getRuudukko()[2][2].kaanna();
        peli.getPoyta().getRuudukko()[1][1].kaanna();
        peli.getPoyta().getRuudukko()[3][3].kaanna();
        
        peli.kaynnista();
        
        assertEquals(1, peli.getPelaaja1().getParejaLoydetty());
        assertEquals(1, peli.getPelaaja2().getParejaLoydetty());
    }
    
    @Test
    public void tuloksenTallennusLisaaTulosrivin() throws IOException {
        String syote = "1\n";
        Peli peli = new Peli(new Scanner(syote));
        peli.getPelaaja1().setParejaLoydetty(2);
        int rivitAlussa = laskeRivit();
        peli.tuloksenTallennus();
        assertEquals(rivitAlussa+1, laskeRivit());
        poistaVika();
    }
    
    public void poistaVika() throws FileNotFoundException, IOException {
        Scanner lukija = new Scanner(new File("src/tulokset.txt"));
        int riveja = 0;
        while (lukija.hasNextLine()) {
            String rivi = lukija.nextLine();
            riveja++;
        }
        lukija.close();
        
        Scanner uusilukija = new Scanner(new File("src/tulokset.txt"));
        String tiedosto = "";
        int i = 1;
        while (i < riveja) {
            String rivi = uusilukija.nextLine();
            tiedosto+= rivi + "\n";
            i++;
        }
        uusilukija.close();
        
        FileWriter kirjaaja = new FileWriter("src/tulokset.txt");
        kirjaaja.write(tiedosto);
        kirjaaja.close();
    }
    
    public int laskeRivit() throws FileNotFoundException {
        Scanner lukija = new Scanner(new File("src/tulokset.txt"));
        int riveja = 0;
        while (lukija.hasNextLine()) {
            String rivi = lukija.nextLine();
            riveja++;
        }
        lukija.close();
        return riveja;
    }
    
    
}
