
package muistipeli.logiikka;

import javax.swing.ImageIcon;

public class Kortti {
    private final int poni;
    private boolean kaannetty;
    private ImageIcon kuva;
    /**
     * 
     * Kortissa on joku tietty poni, käytännössä numeroarvo 0-7 ja sitä vastaava kuva.
     * @param numero Numero, jota vastaava poni kortissa on.
     */
    public Kortti(int numero) {
        kaannetty = false;
        poni = numero;
        setKuva();
    }
    
    /**
     * Kääntää kortin.
     */
    public void kaanna() {
        this.kaannetty = kaannetty == false;
    }
    
    /**
     * Onko kortti käännettynä.
     * @return true/false
     */
    public boolean onkoKaannetty() {
        return kaannetty;
    }
    
    public int getPoni() {
        return poni;
    }
    
    private void setKuva() {
        if (this.poni == 0) {
            this.kuva = new ImageIcon("kuvat/twilight.jpg");
        } else if (this.poni == 1) {
            this.kuva = new ImageIcon("kuvat/rarity.jpg");
        } else if (this.poni == 2) {
            this.kuva = new ImageIcon("kuvat/rainbow.jpg");
        } else if (this.poni == 3) {
            this.kuva = new ImageIcon("kuvat/applejack.jpg");
        } else if (this.poni == 4) {
            this.kuva = new ImageIcon("kuvat/pinkie.jpg");
        } else if (this.poni == 5) {
            this.kuva = new ImageIcon("kuvat/fluttershy.jpg");
        } else if (this.poni == 6) {
            this.kuva = new ImageIcon("kuvat/lyra.jpg");
        } else if (this.poni == 7) {
            this.kuva = new ImageIcon("kuvat/luna.jpg");
        } else if (this.poni == 8) {
            this.kuva = new ImageIcon("kuvat/celestia.jpg");
        } else if (this.poni == 9) {
            this.kuva = new ImageIcon("kuvat/shiny.jpg");
        } else if (this.poni == 10) {
            this.kuva = new ImageIcon("kuvat/cadance.jpg");
        } else if (this.poni == 11) {
            this.kuva = new ImageIcon("kuvat/sapphire.jpg");
        } else if (this.poni == 12) {
            this.kuva = new ImageIcon("kuvat/sweetie.jpg");
        } else if (this.poni == 13) {
            this.kuva = new ImageIcon("kuvat/cupcake.jpg");
        } else if (this.poni == 14) {
            this.kuva = new ImageIcon("kuvat/zecora.jpg");
        } else if (this.poni == 15) {
            this.kuva = new ImageIcon("kuvat/cheerilee.jpg");
        } else if (this.poni == 16) {
            this.kuva = new ImageIcon("kuvat/diamond.jpg");
        } else if (this.poni == 17) {
            this.kuva = new ImageIcon("kuvat/sunset.jpg");
        } else {
            this.kuva = new ImageIcon("kuvat/cardback.jpg");
        }
    }
    
    public ImageIcon getKuva() {
        return this.kuva;
    }
    
    @Override
    public String toString() {
        if (onkoKaannetty()) {
            return "" + this.getPoni();
        }
        return "x";
    }
    
}
