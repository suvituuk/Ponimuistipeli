package muistipeli.logiikka;
/**
 * 
 * Pelaaja pelaa peliä. Pelaajalla on nimi.
 */
public class Pelaaja {
    private String nimi;
    private int parejaLoydetty;
    
    public Pelaaja(String nimi) {
        this.nimi = nimi;
        this.parejaLoydetty = 0;
    }
    
    public String loydetytParit() {
        if (parejaLoydetty == 1) {
            return nimi + ": 1 pari";
        } else {
            return nimi + ": " + parejaLoydetty + " paria";
        }
    }
    
    public void lisaaPari() {
        this.parejaLoydetty++;
    }
    
    public String getNimi(){
        return this.nimi;
    }
    
    public int getParejaLoydetty(){
        return this.parejaLoydetty;
    }
    
    public void setParejaLoydetty(int parit){
        this.parejaLoydetty = parit;
    }
    
    
    
}
