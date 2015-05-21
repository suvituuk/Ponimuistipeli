
package muistipeli.muistipeli;
import java.util.*;

public class Poyta {
    private Kortti[][] ruudukko;
    
    public Poyta(){
        ruudukko = new Kortti[4][4];
        jaaKortit();
    }
    
    private void jaaKortit(){
        int rivi = 0;
        while(rivi < 4){
            int sarake = 0;
            while(sarake < 4){
                jaaYksi(sarake, rivi);
                sarake++;
            }
            rivi++;
        }
    }
    
    private void jaaYksi(int sarake, int rivi){
        while(true){
            Random random = new Random();
            int poni = random.nextInt(8);
            if(onkoJoKahdesti(poni) == false){
                ruudukko[sarake][rivi] = new Kortti(poni);
                break;
            }
        }
    }
    
    private boolean onkoJoKahdesti(int numero){
        return false;
    }
    
    public Kortti[][] getRuudukko(){
        return ruudukko;
    }
    
    @Override
    public String toString(){
        String kortit = "";
        int rivi = 0;
        while(rivi < 4){
            int sarake = 0;
            while(sarake < 4){
                if(ruudukko[sarake][rivi].onkoKaannetty()){
                    kortit += ruudukko[sarake][rivi].getPoni();
                } else {
                    kortit += "x";
                }
                sarake++;
            }
            rivi++;
            kortit += "\n";
        }
        return kortit;
    }
}
