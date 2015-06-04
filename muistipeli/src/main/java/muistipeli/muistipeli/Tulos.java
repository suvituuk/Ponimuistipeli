package muistipeli.muistipeli;

import java.io.*;
import java.util.Scanner;

public class Tulos {
    private Pelaaja pelaaja;
    private int tulos;
    private FileWriter kirjaaja;
    
    public Tulos(Pelaaja pelaaja, int tulos) throws IOException{
        this.pelaaja = pelaaja;
        this.tulos = tulos;
        this.kirjaaja = new FileWriter("src/tulokset.txt", true);
    }
    
//    public String lataaTulokset() throws FileNotFoundException{
//        File tiedosto = new File("src/tulokset.txt");
//        Scanner lukija = new Scanner(tiedosto);
//        String tiedostonSisalto = "";
//        while (lukija.hasNextLine()){
//            tiedostonSisalto += lukija.nextLine() + "\n";
//        }
//        return tiedostonSisalto;
//    }
    /**
     * 
     * Pelillä on tulos (kuinka monella klikkauksella löysi kaikki parit) jonka voi halutessaan tallentaa. 
     * Tulokseen liittyy myös tuloksen tekijä eli pelaaja.
     * @throws IOException 
     */
    public void lisaaTulos() throws IOException{
        kirjaaja.write(this.tulos + ":" + this.pelaaja.getNimi() + "\n");
        kirjaaja.close();
    }
    
    @Override
    public String toString(){
        return this.tulos + " - " + pelaaja.getNimi();
    }
}
