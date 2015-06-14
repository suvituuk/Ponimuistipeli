package muistipeli.testit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import muistipeli.logiikka.Kortti;
import muistipeli.logiikka.Poyta;
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
        Poyta poyta = new Poyta(4);
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
        Poyta poyta = new Poyta(4);
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
        Poyta poyta = new Poyta(4);
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
        Poyta poyta = new Poyta(4);
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
    
    @Test
    public void kortitListanaSisaltaaLuvutNollastaSeiskaan() {
        Poyta poyta = new Poyta(4);
        int luku = 0;
        while (luku < 8){
            assertTrue(poyta.kortitListana().contains(luku));
            luku++;
        }
    }
    
    @Test
    public void kortitListanaSisaltaaKaksiJokaista() {
        Poyta poyta = new Poyta(4);
        int luku = 0;
        ArrayList<Integer> lista = poyta.kortitListana();
        Collections.sort(lista);
        while (luku < 8) {
            assertEquals(lista.get(0).intValue(), luku);
            lista.remove(0);
            assertEquals(lista.get(0).intValue(), luku);
            lista.remove(0);
            luku++;
        }
        
    }
    
    @Test
    public void KorttejaOnSivuKertaaSivu() {
        Poyta poyta = new Poyta(4);
        assertEquals(poyta.getSivu()*poyta.getSivu(), poyta.getKortteja());
    }
    
    @Test
    public void sivuOnParametri() {
        Poyta poyta = new Poyta(6);
        assertEquals(6, poyta.getSivu());
    }
}
