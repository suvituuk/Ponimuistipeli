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
import muistipeli.logiikka.Kortti;
import muistipeli.logiikka.Pelaaja;
import muistipeli.logiikka.Peli;
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
    
    @Test
    public void kaikkiEiLoydettyAlussa() throws IOException{
        Peli peli = new Peli();
        assertEquals(peli.onkoKaikkiLoydetty(), false);
    }
    
    @Test
    public void onkoKaikkiLoydettyToimiiII() throws IOException{
        Peli peli = new Peli();
        peli.getPoyta().getRuudukko()[1][1].kaanna();
        assertEquals(peli.onkoKaikkiLoydetty(), false);
    }
    
    @Test
    public void onkoKaikkiLoydettyToimiiIII() throws IOException{
        Peli peli = new Peli();
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
        Peli peli = new Peli();
        assertEquals(peli.getPoyta().getRuudukko()[0][0].onkoKaannetty(), false);
    }
    
    @Test
    public void kortinKaantoKaantaaKortin() throws IOException{
        Peli peli = new Peli();
        Kortti kortti = peli.getPoyta().getRuudukko()[0][0];
        peli.kortinKaanto(kortti);
        assertEquals(kortti.onkoKaannetty(), true);
    }
    
    @Test
    public void pelaajaVaihtuu() throws IOException{
        Peli peli = new Peli();
        peli.setPelaaja1(new Pelaaja("eka"));
        peli.setPelaaja2(new Pelaaja("toka"));
        peli.setPelaajaVuorossa(peli.getPelaaja1());
        peli.seuraavaPelaaja();
        assertEquals(peli.getPelaajaVuorossa(), peli.getPelaaja2());
    }
    
    @Test
    public void pelaajaVaihtuuII() throws IOException{
        Peli peli = new Peli();
        peli.setPelaaja1(new Pelaaja("eka"));
        peli.setPelaaja2(new Pelaaja("toka"));
        peli.setPelaajaVuorossa(peli.getPelaaja2());
        peli.seuraavaPelaaja();
        assertEquals(peli.getPelaajaVuorossa(), peli.getPelaaja1());
    }
    
    @Test
    public void setPelaajaToimii() throws IOException {
        Peli peli = new Peli();
        Pelaaja pelaaja = new Pelaaja("Toni");
        peli.setPelaaja1(pelaaja);
        assertEquals(peli.getPelaaja1().getNimi(), "Toni");
    }
    
    @Test
    public void setPelaajaToimiiII() throws IOException {
        Peli peli = new Peli();
        Pelaaja pelaaja = new Pelaaja("Suvi");
        peli.setPelaaja2(pelaaja);
        assertEquals(peli.getPelaaja2().getNimi(), "Suvi");
    }
    
    @Test
    public void voittajaStringOikeinI() throws IOException {
        Peli peli = new Peli();
        Pelaaja suvi = new Pelaaja("Suvi");
        Pelaaja toni = new Pelaaja("Toni");
        peli.setPelaaja1(suvi);
        peli.setPelaaja2(toni);
        Assert.assertEquals("Tasapeli!", peli.voittaja());
    }
    
    @Test
    public void VoittajaStringOikeinII() throws IOException  {
        Peli peli = new Peli();
        Pelaaja suvi = new Pelaaja("Suvi");
        Pelaaja toni = new Pelaaja("Toni");
        peli.setPelaaja1(suvi);
        peli.setPelaaja2(toni);
        toni.setParejaLoydetty(2);
        Assert.assertEquals("Voittaja on: Toni!", peli.voittaja());
    }
    
    @Test
    public void VoittajaStringOikeinIII() throws IOException  {
        Peli peli = new Peli();
        Pelaaja suvi = new Pelaaja("Suvi");
        Pelaaja toni = new Pelaaja("Toni");
        peli.setPelaaja1(suvi);
        peli.setPelaaja2(toni);
        suvi.setParejaLoydetty(2);
        String expected =  "Voittaja on: Suvi!";
        Assert.assertEquals(expected, peli.voittaja());
    }
    
    @Test
    public void tuloksenTallennusLisaaTulosrivin() throws IOException {
        Peli peli = new Peli();
        peli.getPelaaja1().setParejaLoydetty(2);
        int rivitAlussa = laskeRivit();
        peli.tallennaTulos();
        assertEquals(rivitAlussa+1, laskeRivit());
        poistaVika();
    }
    
    @Test
    public void onkoPariPalauttaaFalseJosEriKortit() throws IOException {
        Peli peli = new Peli();
        peli.setEkaKaannetty(new Kortti(1));
        peli.setTokaKaannetty(new Kortti(2));
        assertFalse(peli.onkoPari());
    }
    
    @Test
    public void onkoPariPalauttaaTrueJosSamatKortit() throws IOException {
        Peli peli = new Peli();
        peli.setEkaKaannetty(new Kortti(1));
        peli.setTokaKaannetty(new Kortti(1));
        assertTrue(peli.onkoPari());
    }
    
    public void poistaVika() throws FileNotFoundException, IOException {
        int riveja;
        try (Scanner lukija = new Scanner(new File("src/tulokset.txt"))) {
            riveja = 0;
            while (lukija.hasNextLine()) {
                lukija.nextLine();
                riveja++;
            }
        }
        
        String tiedosto;
        try (Scanner uusilukija = new Scanner(new File("src/tulokset.txt"))) {
            tiedosto = "";
            int i = 1;
            while (i < riveja) {
                String rivi = uusilukija.nextLine();
                tiedosto+= rivi + "\n";
                i++;
            }
        }
        
        try (FileWriter kirjaaja = new FileWriter("src/tulokset.txt")) {
            kirjaaja.write(tiedosto);
        }
    }
    
    public int laskeRivit() throws FileNotFoundException {
        int riveja;
        try (Scanner lukija = new Scanner(new File("src/tulokset.txt"))) {
            riveja = 0;
            while (lukija.hasNextLine()) {
                lukija.nextLine();
                riveja++;
            }
        }
        return riveja;
    }
    
}
