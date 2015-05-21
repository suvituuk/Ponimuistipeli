
package muistipeli.muistipeli;

public class Kortti {
    private final int poni;
    private boolean kaannetty;
    
    public Kortti(int numero) {
        kaannetty = false;
        poni = numero;
    }
    
    public void kaanna(){
        this.kaannetty = kaannetty == false;
    }
    
    public boolean onkoKaannetty(){
        return kaannetty;
    }
    
    public int getPoni(){
        return poni;
    }
    
}
