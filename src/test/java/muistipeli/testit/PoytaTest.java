package muistipeli.testit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import muistipeli.muistipeli.Kortti;
import muistipeli.muistipeli.Poyta;
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
public class PoytaTest {
    
    public PoytaTest() {
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
    @Test
    public void toStringTomiiI(){
        Poyta poyta = new Poyta();
        int rivi = 0;
        while(rivi < 4){
            int sarake = 0;
            while(sarake < 4){
                poyta.getRuudukko()[sarake][rivi] = new Kortti(1);
                sarake++;
            }
            rivi++;
        }
        assertEquals("xxxx"+"\n"+"xxxx"+"\n"+"xxxx"+"\n"+"xxxx"+"\n", poyta.toString());
    }
    
    @Test
    public void toStringTomiiII(){
        Poyta poyta = new Poyta();
        int rivi = 0;
        while(rivi < 4){
            int sarake = 0;
            while(sarake < 4){
                poyta.getRuudukko()[sarake][rivi] = new Kortti(1);
                sarake++;
            }
            rivi++;
        }
        poyta.getRuudukko()[0][0].kaanna();
        poyta.getRuudukko()[2][3].kaanna();
        assertEquals("1xxx"+"\n"+"xxxx"+"\n"+"xxxx"+"\n"+"xx1x"+"\n", poyta.toString());
    }
    
    @Test
    public void korttienArvoMaxSeiska() {
        Poyta poyta = new Poyta();
        int rivi = 0;
        while(rivi < 4){
            int sarake = 0;
            while(sarake < 4){
                assertTrue(poyta.getRuudukko()[sarake][rivi].getPoni() <= 7);
                sarake++;
            }
            rivi++;
        }
    }
    
    @Test
    public void korttienArvoVahNolla() {
        Poyta poyta = new Poyta();
        int rivi = 0;
        while(rivi < 4){
            int sarake = 0;
            while(sarake < 4){
                assertTrue(poyta.getRuudukko()[sarake][rivi].getPoni() >= 0);
                sarake++;
            }
            rivi++;
        }
    }
}
