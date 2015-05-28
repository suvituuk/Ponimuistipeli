package muistipeli.testit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import muistipeli.muistipeli.Pelaaja;
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
public class PelaajaTest {
    
    public PelaajaTest() {
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
    public void nimeaminenToimii() {
        Pelaaja pelaaja = new Pelaaja("Suvi");
        assertEquals(pelaaja.getNimi(), "Suvi");
    }
    
    @Test
    public void parejaLoydettyAluksiNolla() {
        Pelaaja pelaaja = new Pelaaja("pelaajanNimi");
        assertEquals(pelaaja.getParejaLoydetty(), 0);
    }
    
    @Test
    public void setParejaLoydettyToimii() {
        Pelaaja pelaaja = new Pelaaja("pelaajanNimi");
        pelaaja.setParejaLoydetty(3);
        assertEquals(pelaaja.getParejaLoydetty(), 3);
    }
}
