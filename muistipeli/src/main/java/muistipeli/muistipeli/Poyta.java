
package muistipeli.muistipeli;
import java.util.*;
/**
 * 
 * Pöydällä on pelin kortit 4x4- tai 6x6-ruudukossa.
 */
public class Poyta {
    private int sivu;
    private Kortti[][] ruudukko;
    
    public Poyta(int sivu){
        this.sivu = sivu;
        ruudukko = new Kortti[sivu][sivu];
        jaaKortit();
    }
    
    /**
     * Asettaa ruudukon jokaiseen ruutuun kortin.
     */
    public void jaaKortit(){
        ArrayList<Integer> kortit = kortitListana();
        int rivi = 0;
        while(rivi < sivu){
            int sarake = 0;
            while(sarake < sivu){
                ruudukko[sarake][rivi] = new Kortti(kortit.get(0));
                kortit.remove(0);
                sarake++;
            }
            rivi++;
        }
    }
    
    /**
     * Luo ArrayList-olion, jossa on kaksi jokaista eri korttia vastaavaa numeroa ja sekoittaa listan.
     * @return 
     */
    public ArrayList<Integer> kortitListana(){
        ArrayList<Integer> kortit = new ArrayList<>();
        int parienMaara = sivu*sivu/2;
        int pari = 0;
        while(pari < parienMaara){
            kortit.add(pari);
            kortit.add(pari);
            pari++;
        }
        Collections.shuffle(kortit);
        return kortit;
    }
    
    public Kortti[][] getRuudukko(){
        return ruudukko;
    }
    
    @Override
    public String toString(){
        String kortit = "";
        int rivi = 0;
        while(rivi < sivu){
            int sarake = 0;
            while(sarake < sivu){
                kortit += ruudukko[sarake][rivi];
                sarake++;
            }
            rivi++;
            kortit += "\n";
        }
        return kortit;
    }
    
    public int getSivu() {
        return this.sivu;
    }
    
    /**
     * 
     * @return Korttien määrä eli sivu^2
     */
    public int getKortteja() {
        return sivu*sivu;
    }
}
