package muistipeli.testit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import muistipeli.muistipeli.Pelaaja;
import muistipeli.muistipeli.Tulos;
import muistipeli.muistipeli.Tulostenkasittelija;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mlle S
 */
public class TulostenkasittelijaTest {
    
    public TulostenkasittelijaTest() {
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
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void topEkaTulosOnParas() throws IOException {
        Tulostenkasittelija tk = new Tulostenkasittelija();
        Scanner lukija = new Scanner(tk.top(20));
        ArrayList<Tulos> tulokset = tk.tuloksetListana();
        Collections.sort(tulokset);
        assertEquals(lukija.nextLine(), "1. " + tulokset.get(0));
        lukija.close();
    }
    
    @Test
    public void omatTuloksetOnOmia() throws IOException {
        Tulostenkasittelija tk = new Tulostenkasittelija();
        Scanner lukija = new Scanner(tk.omatTulokset(new Pelaaja("Suvi")));
        while (lukija.hasNextLine()) {
            String rivi = lukija.nextLine();
            String[] osat = rivi.split("-");
            assertEquals(" Suvi ", osat[1]);
        }
        lukija.close();
    }
    
    @Test
    public void tulosLisataan() throws IOException {
        Tulostenkasittelija tk = new Tulostenkasittelija();
        String vastaus = "-";
        Tulos tulos = new Tulos(new Pelaaja("pelaaja"), 0, "aika");
        tk.lisaaTulos(tulos);
        Scanner lukija = new Scanner(new File("src/tulokset.txt"));
        while (lukija.hasNextLine()) {
            String rivi = lukija.nextLine();
            String[] osat = rivi.split("-");
            Tulos rivinTulos = new Tulos(new Pelaaja(osat[1]), Integer.parseInt(osat[0]), osat[2]);
            if (rivinTulos.getPelaaja().getNimi().equals(tulos.getPelaaja().getNimi()) && rivinTulos.getTulos() == tulos.getTulos() &&
                    rivinTulos.getAika().equals(tulos.getAika())) {
                vastaus = "+";
            }
        }
        assertEquals("+", vastaus);
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
}
