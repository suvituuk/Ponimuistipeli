package muistipeli.muistipeli;

public class Pelaaja {
    private String nimi;
    private int parejaLoydetty;
    
    public Pelaaja(String nimi) {
        this.nimi = nimi;
        this.parejaLoydetty = 0;
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
