package muistipeli.muistipeli;

import java.io.*;
import java.util.Objects;

/**
 *
 * Pelillä on tulos (pisteet lasketaan klikkausten ja ruudukon koon perusteella) jonka voi
 * halutessaan tallentaa. Tulokseen liittyy myös tuloksen tekijä eli pelaaja ja aika.
 *
 * @throws IOException
 */
public class Tulos implements Comparable {

    private Pelaaja pelaaja;
    private int tulos;
    private String aika;

    public Tulos(Pelaaja pelaaja, int tulos, String aika) throws IOException {
        this.pelaaja = pelaaja;
        this.tulos = tulos;
        this.aika = aika;
    }

    public Pelaaja getPelaaja() {
        return this.pelaaja;
    }

    public int getTulos() {
        return this.tulos;
    }
    
    public String getAika() {
        return this.aika;
    }

    @Override
    public String toString() {
        return this.tulos + " - " + pelaaja.getNimi() + " - " + this.aika;
    }

    @Override
    public int compareTo(Object o) {
        return o.hashCode() - this.hashCode();
    }

    @Override
    public int hashCode() {
        return this.tulos;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tulos other = (Tulos) obj;
        if (!Objects.equals(this.pelaaja, other.pelaaja)) {
            return false;
        }
        if (this.tulos != other.tulos) {
            return false;
        }
        return true;
    }
}
