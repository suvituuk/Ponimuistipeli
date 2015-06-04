package muistipeli.testit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
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
    public void onkoKaikkiLoydettyToimiiI(){
        Peli peli = new Peli(new Scanner(System.in));
        assertEquals(peli.onkoKaikkiLoydetty(), false);
    }
    
    @Test
    public void onkoKaikkiLoydettyToimiiII(){
        Peli peli = new Peli(new Scanner(System.in));
        peli.getPoyta().getRuudukko()[1][1].kaanna();
        assertEquals(peli.onkoKaikkiLoydetty(), false);
    }
    
    @Test
    public void onkoKaikkiLoydettyToimiiIII(){
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
    public void korttiAluksiKaantamatta(){
        Peli peli = new Peli(new Scanner(System.in));
        assertEquals(peli.getPoyta().getRuudukko()[0][0].onkoKaannetty(), false);
    }
    
    @Test
    public void kortinKaantoKaantaaKortin(){
        Peli peli = new Peli(new Scanner(System.in));
        Kortti kortti = peli.getPoyta().getRuudukko()[0][0];
        peli.kortinKaanto(kortti);
        assertEquals(kortti.onkoKaannetty(), true);
    }
    
    @Test
    public void pelaajaVaihtuu(){
        Peli peli = new Peli(new Scanner(System.in));
        peli.setPelaaja1(new Pelaaja("eka"));
        peli.setPelaaja2(new Pelaaja("toka"));
        assertEquals(peli.seuraavaPelaaja(peli.getPelaaja1()), peli.getPelaaja2());
    }
    
    @Test
    public void pelaajaVaihtuuII(){
        Peli peli = new Peli(new Scanner(System.in));
        peli.setPelaaja1(new Pelaaja("eka"));
        peli.setPelaaja2(new Pelaaja("toka"));
        assertEquals(peli.seuraavaPelaaja(peli.getPelaaja2()), peli.getPelaaja1());
    }
    
    @Test
    public void setPelaajaToimii() {
        Peli peli = new Peli(new Scanner(System.in));
        Pelaaja pelaaja = new Pelaaja("Toni");
        peli.setPelaaja1(pelaaja);
        assertEquals(peli.getPelaaja1().getNimi(), "Toni");
    }
    
    @Test
    public void setPelaajaToimiiII() {
        Peli peli = new Peli(new Scanner(System.in));
        Pelaaja pelaaja = new Pelaaja("Suvi");
        peli.setPelaaja2(pelaaja);
        assertEquals(peli.getPelaaja2().getNimi(), "Suvi");
    }
    
    @Test
    public void valitsePalauttaaOikeanArvon() {
        String syote = "1\n";
        Peli peli = new Peli(new Scanner(syote));
        assertEquals(peli.valitse("rivi"), 1);
    }
    
    @Test
    public void kortinValintaToimii() {
        String syote = "0\n0\n";
        Peli peli = new Peli(new Scanner(syote));
        Kortti kortti = new Kortti(3);
        peli.getPoyta().getRuudukko()[0][0] = kortti;
        assertEquals(peli.kortinValinta(), kortti);
    }
    
    @Test
    public void valitsePeliToimii() {
        String syote = "1\n";
        Peli peli = new Peli(new Scanner(syote));
        assertEquals(peli.valitsePeli(), 1);
        String syote2 = "2\n";
        Peli peli2 = new Peli(new Scanner(syote2));
        assertEquals(peli2.valitsePeli(), 2);
    }
    
    @Test
    public void parinLoytyessaLoydettyPariLisataan(){
        String syote = "0\n0\n1\n1\n";
        Peli peli = new Peli(new Scanner(syote));
        peli.getPoyta().getRuudukko()[0][0] = new Kortti(8);
        peli.getPoyta().getRuudukko()[1][1] = new Kortti(8);
        Pelaaja suvi = new Pelaaja("Suvi");
        assertEquals(suvi.getParejaLoydetty(), 0);
        peli.kierros(suvi);
        assertEquals(suvi.getParejaLoydetty(), 1);
    }
    
    @Test
    public void josEiPariaKortitKaantyyTakaisin(){
        String syote = "0\n0\n1\n1\n";
        Peli peli = new Peli(new Scanner(syote));
        peli.getPoyta().getRuudukko()[0][0] = new Kortti(1);
        peli.getPoyta().getRuudukko()[1][1] = new Kortti(8);
        Pelaaja suvi = new Pelaaja("Suvi");
        peli.kierros(suvi);
        assertEquals(peli.getPoyta().getRuudukko()[0][0].onkoKaannetty(), false);
    }
    
    @Test
    public void VoittajaTulostuuOikeinI()  {
        Peli peli = new Peli(new Scanner(System.in));
        peli.setPelaaja1(new Pelaaja("Suvi"));
        peli.setPelaaja2(new Pelaaja("Toni"));
        peli.voittaja();
        String expected =  "Suvi löysi 0 paria.\nToni löysi 0 paria.\r\nTasapeli!\r\n";
        Assert.assertEquals(outContent.toString(), expected);
    }
    
    @Test
    public void VoittajaTulostuuOikeinII()  {
        Peli peli = new Peli(new Scanner(System.in));
        Pelaaja suvi = new Pelaaja("Suvi");
        Pelaaja toni = new Pelaaja("Toni");
        peli.setPelaaja1(suvi);
        peli.setPelaaja2(toni);
        toni.setParejaLoydetty(2);
        peli.voittaja();
        String expected =  "Suvi löysi 0 paria.\nToni löysi 2 paria.\r\nVoittaja on: Toni!\r\n";
        Assert.assertEquals(outContent.toString(), expected);
    }
    
    @Test
    public void VoittajaTulostuuOikeinIII()  {
        Peli peli = new Peli(new Scanner(System.in));
        Pelaaja suvi = new Pelaaja("Suvi");
        Pelaaja toni = new Pelaaja("Toni");
        peli.setPelaaja1(suvi);
        peli.setPelaaja2(toni);
        suvi.setParejaLoydetty(2);
        peli.voittaja();
        String expected =  "Suvi löysi 2 paria.\nToni löysi 0 paria.\r\nVoittaja on: Suvi!\r\n";
        Assert.assertEquals(outContent.toString(), expected);
    }
    
    @Test
    public void valintaTulostuuOikeinVirheellisellaSyotteella(){
        String syote = "a\n1";
        Peli peli = new Peli(new Scanner(syote));
        peli.valitse("rivi");
        String expected =  "Valitse rivi:\r\nValitse 0-3!\r\nValitse rivi:\r\n";
        Assert.assertEquals(outContent.toString(), expected);
    }
    
    @Test
    public void valintaTulostuuOikeinVaarallaLuvulla(){
        String syote = "5\n1";
        Peli peli = new Peli(new Scanner(syote));
        peli.valitse("rivi");
        String expected =  "Valitse rivi:\r\nValitse 0-3!\r\nValitse rivi:\r\n";
        Assert.assertEquals(outContent.toString(), expected);
    }
    
    @Test
    public void pelinValintaTulostuuOikeinVirheellisellaSyotteella(){
        String syote = "a\n1";
        Peli peli = new Peli(new Scanner(syote));
        peli.valitsePeli();
        String expected =  "Haluatko pelata yksin(1) vai paria vastaan(2)?\r\nValitse 1 tai 2!\r\n"
                + "Haluatko pelata yksin(1) vai paria vastaan(2)?\r\n";
        Assert.assertEquals(outContent.toString(), expected);
    }
    
    @Test
    public void pelinValintaTulostuuOikeinVaarallaLuvulla(){
        String syote = "3\n1";
        Peli peli = new Peli(new Scanner(syote));
        peli.valitsePeli();
        String expected =  "Haluatko pelata yksin(1) vai paria vastaan(2)?\r\nValitse 1 tai 2!\r\n"
                + "Haluatko pelata yksin(1) vai paria vastaan(2)?\r\n";
        Assert.assertEquals(outContent.toString(), expected);
    }
    
    @Test
    public void kortinValintaTulostuuOikeinKaannetyllaKortilla(){
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
    
    
}
