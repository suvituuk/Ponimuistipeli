package muistipeli.testit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import muistipeli.muistipeli.Pelaaja;
import muistipeli.muistipeli.Tulos;
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
public class TulosTest {
    
    public TulosTest() {
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
    public void toStringToimii() throws IOException {
        Tulos tulos = new Tulos(new Pelaaja("nimi"), 42, "7.6.2015");
        assertEquals(tulos.toString(), "42 - nimi - 7.6.2015");
    }
    
    @Test
    public void vertaaPienempäänPositiivinen() throws IOException {
        Tulos eka = new Tulos(new Pelaaja(""), 2, "");
        Tulos toka = new Tulos(new Pelaaja(""), 3, "");
        assertTrue(eka.compareTo(toka) > 0);
    }
    
    @Test
    public void vertaaIsompaanNegatiivinen() throws IOException {
        Tulos eka = new Tulos(new Pelaaja(""), 2, "");
        Tulos toka = new Tulos(new Pelaaja(""), 1, "");
        assertTrue(eka.compareTo(toka) < 0);
    }
    
    @Test
    public void equalsPalauttaaTrue() throws IOException {
        Pelaaja pelaaja = new Pelaaja("nimi");
        Tulos eka = new Tulos(pelaaja, 42, "aika");
        Tulos toka = new Tulos(pelaaja, 42, "aika");
        assertTrue(eka.equals(toka));
    }
    
    @Test
    public void equalsPalauttaaFalse() throws IOException {
        Pelaaja pelaaja = new Pelaaja("nimi");
        Tulos tulos = new Tulos(pelaaja, 42, "aika");
        Tulos eka = null;
        assertFalse(tulos.equals(eka));
        Pelaaja toka = new Pelaaja("");
        assertFalse(tulos.equals(toka));
        Tulos kolmas = new Tulos(new Pelaaja("eri"), 42, "aika");
        assertFalse(tulos.equals(kolmas));
        Tulos neljas = new Tulos(pelaaja, 32, "aika");
        assertFalse(tulos.equals(neljas));
    }
}
