package muistipeli.testit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import muistipeli.muistipeli.Kortti;
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
public class KorttiTest {
    
    public KorttiTest() {
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
    public void korttiOnAluksiVaarinpain() {
        Kortti kortti = new Kortti(1);
        assertEquals(kortti.onkoKaannetty(), false);
    }
    
    @Test
    public void korttiKaantyy(){
        Kortti kortti = new Kortti(1);
        kortti.kaanna();
        assertEquals(kortti.onkoKaannetty(), true);
    }
    
    @Test
    public void korttiKaantyyMyosTakaisin(){
        Kortti kortti = new Kortti(1);
        kortti.kaanna();
        kortti.kaanna();
        assertEquals(kortti.onkoKaannetty(), false);
    }
    
    @Test
    public void getPoniPalauttaaOikeanArvon(){
        Kortti kortti = new Kortti(42);
        assertEquals(kortti.getPoni(), 42);
    }
}
