package muistipeli.logiikka;
/**
 * 
 * Pelaaja pelaa peliä. Pelaajalla on nimi ja jokin määrä löydettyjä pareja.
 */
public class Pelaaja {
    private final String nimi;
    private int parejaLoydetty;
    
    /**
     * 
     * @param nimi Pelaajan nimi tai nimimerkki
     */
    public Pelaaja(String nimi) {
        this.nimi = nimi;
        this.parejaLoydetty = 0;
    }
    
    /**
     * Palauttaa Stringin, jossa lukee pelaajan nimi ja löydettyjen parien määrä.
     * @return String
     */
    public String loydetytParit() {
        if (parejaLoydetty == 1) {
            return nimi + ": 1 pari";
        } else {
            return nimi + ": " + parejaLoydetty + " paria";
        }
    }
    
    /**
     * Lisää yhden löydetyn parin.
     */
    public void lisaaPari() {
        this.parejaLoydetty++;
    }
    
    public String getNimi() {
        return this.nimi;
    }
    
    public int getParejaLoydetty() {
        return this.parejaLoydetty;
    }
    
    public void setParejaLoydetty(int parit) {
        this.parejaLoydetty = parit;
    }
    
    
    
}
