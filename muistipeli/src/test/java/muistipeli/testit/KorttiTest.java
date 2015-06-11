package muistipeli.testit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.ImageIcon;
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
    
    @Test
    public void toStringOikeinKaannetylla() {
        Kortti kortti = new Kortti(3);
        kortti.kaanna();
        assertEquals("3", kortti.toString());
    }
    
    @Test
    public void toStringOikeinKaantamattomalla() {
        Kortti kortti = new Kortti(3);
        assertEquals("x", kortti.toString());
    }
    
    @Test
    public void oikeatKuvat() {
        assertEquals(new ImageIcon("kuvat/twilight.jpg").getImage(), new Kortti(0).getKuva().getImage());
        assertEquals(new ImageIcon("kuvat/rarity.jpg").getImage(), new Kortti(1).getKuva().getImage());
        assertEquals(new ImageIcon("kuvat/rainbow.jpg").getImage(), new Kortti(2).getKuva().getImage());
        assertEquals(new ImageIcon("kuvat/applejack.jpg").getImage(), new Kortti(3).getKuva().getImage());
        assertEquals(new ImageIcon("kuvat/pinkie.jpg").getImage(), new Kortti(4).getKuva().getImage());
        
        assertEquals(new ImageIcon("kuvat/fluttershy.jpg").getImage(), new Kortti(5).getKuva().getImage());
        assertEquals(new ImageIcon("kuvat/lyra.jpg").getImage(), new Kortti(6).getKuva().getImage());
        assertEquals(new ImageIcon("kuvat/luna.jpg").getImage(), new Kortti(7).getKuva().getImage());
        assertEquals(new ImageIcon("kuvat/celestia.jpg").getImage(), new Kortti(8).getKuva().getImage());
        assertEquals(new ImageIcon("kuvat/shiny.jpg").getImage(), new Kortti(9).getKuva().getImage());
        
        assertEquals(new ImageIcon("kuvat/cadance.jpg").getImage(), new Kortti(10).getKuva().getImage());
        assertEquals(new ImageIcon("kuvat/sapphire.jpg").getImage(), new Kortti(11).getKuva().getImage());
        assertEquals(new ImageIcon("kuvat/sweetie.jpg").getImage(), new Kortti(12).getKuva().getImage());
        assertEquals(new ImageIcon("kuvat/cupcake.jpg").getImage(), new Kortti(13).getKuva().getImage());
        assertEquals(new ImageIcon("kuvat/zecora.jpg").getImage(), new Kortti(14).getKuva().getImage());
        
        assertEquals(new ImageIcon("kuvat/cheerilee.jpg").getImage(), new Kortti(15).getKuva().getImage());
        assertEquals(new ImageIcon("kuvat/diamond.jpg").getImage(), new Kortti(16).getKuva().getImage());
        assertEquals(new ImageIcon("kuvat/sunset.jpg").getImage(), new Kortti(17).getKuva().getImage());
        
        assertEquals(new ImageIcon("kuvat/cardback.jpg").getImage(), new Kortti(42).getKuva().getImage());
    }
}
