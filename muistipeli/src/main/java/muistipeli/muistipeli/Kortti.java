
package muistipeli.muistipeli;

import javax.swing.ImageIcon;

public class Kortti {
    private final int poni;
    private boolean kaannetty;
    private ImageIcon kuva;
    /**
     * 
     * Kortissa on joku tietty poni, käytännössä numeroarvo 0-7 ja sitä vastaava kuva.
     * @param numero 
     */
    public Kortti(int numero) {
        kaannetty = false;
        poni = numero;
        setKuva();
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
    
    private void setKuva(){
        if (this.poni == 0) {
            this.kuva = new ImageIcon("twilight.jpg");
        } else if (this.poni == 1) {
            this.kuva = new ImageIcon("rarity.jpg");
        } else if (this.poni == 2) {
            this.kuva = new ImageIcon("rainbow.jpg");
        } else if (this.poni == 3) {
            this.kuva = new ImageIcon("applejack.jpg");
        } else if (this.poni == 4) {
            this.kuva = new ImageIcon("pinkie.jpg");
        } else if (this.poni == 5) {
            this.kuva = new ImageIcon("fluttershy.jpg");
        } else if (this.poni == 6) {
            this.kuva = new ImageIcon("lyra.jpg");
        } else if (this.poni == 7) {
            this.kuva = new ImageIcon("luna.jpg");
        } else {
            this.kuva = new ImageIcon("cardback.jpg");
        }
    }
    
    public ImageIcon getKuva() {
        return this.kuva;
    }
    
    @Override
    public String toString(){
        if (onkoKaannetty()){
            return "" + this.getPoni();
        }
        return "x";
    }
    
}
