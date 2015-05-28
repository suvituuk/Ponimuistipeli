
package muistipeli.muistipeli;
import java.util.*;

public class Poyta {
    private Kortti[][] ruudukko;
    
    public Poyta(){
        ruudukko = new Kortti[4][4];
        jaaKortit();
    }
    
    public void jaaKortit(){
        ArrayList<Integer> kortit = kortitListana();
        int rivi = 0;
        while(rivi < 4){
            int sarake = 0;
            while(sarake < 4){
                ruudukko[sarake][rivi] = new Kortti(kortit.get(0));
                kortit.remove(0);
                sarake++;
            }
            rivi++;
        }
    }
    
    public ArrayList<Integer> kortitListana(){
        ArrayList<Integer> kortit = new ArrayList<>();
        int parienMaara = 8;
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
