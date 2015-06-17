package muistipeli.logiikka;

import java.io.*;
import java.util.*;

/**
 *
 * KÃ¤sittelee tuloksia eli kÃ¤ytÃ¤nnÃ¶ssÃ¤ tallentaa ja hakee niitÃ¤.
 */
public class Tulostenkasittelija {

    private final FileWriter kirjaaja;

    public Tulostenkasittelija() throws IOException {
        this.kirjaaja = new FileWriter("tulokset.txt", true);
    }

    /**
     * Tallentaa tulokset-tiedostoon parametrina saamansa tuloksen.
     * @param tulos tallennettava tulos
     * @throws IOException 
     */
    public void lisaaTulos(Tulos tulos) throws IOException {
        kirjaaja.write(tulos.getTulos() + "-" + tulos.getPelaaja().getNimi() + "-" + tulos.getAika() + "\n");
        kirjaaja.close();
    }

    /**
     * Palauttaa String-muodossa pelaajan (oikeastaan kaikkien samannimisten pelaajien) tulokset.
     * @param pelaaja pelaaja, jonka tulokset halutaan
     * @return pelaajan kaikki tulokset String-muodossa
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public String omatTulokset(Pelaaja pelaaja) throws FileNotFoundException, IOException {
        String tulokset = "";
        ArrayList<Tulos> tuloksetListana = tuloksetListana();
        Collections.reverse(tuloksetListana);
        for (Tulos tulos: tuloksetListana) {
            if(tulos.getPelaaja().getNimi().equals(pelaaja.getNimi())) {
                tulokset += tulos.toString() + "\n";
            }
        }
        return tulokset;
    }
    
    /**
     * Palauttaa parhaat tulokset String-muodossa listana. 
     * @param top kuinka monta parasta tulosta halutaan
     * @return parhaat tulokset String-muodossa
     * @throws IOException 
     */
    public String top(int top) throws IOException {
        String tulokset = "";
        int kuinkaMonta = top;
        ArrayList<Tulos> tuloksetListana = tuloksetListana();
        if (tuloksetListana.size() < kuinkaMonta) {
            kuinkaMonta = tuloksetListana.size();
        }
        Collections.sort(tuloksetListana);
        int indeksi = 0;
        while (indeksi < kuinkaMonta) {
            int sijoitus = indeksi + 1;
            tulokset += sijoitus + ". " + tuloksetListana.get(indeksi) + "\n";
            indeksi++;
        }
        return tulokset;
    }
    
    /**
     * Palauttaa listan kaikista tallennetuista tuloksista.
     * @return kaikki tulokset listana
     * @throws IOException 
     */
    public ArrayList<Tulos> tuloksetListana() throws IOException {
        Scanner lukija = new Scanner(new File("tulokset.txt"));
        ArrayList<Tulos> tulokset = new ArrayList<>();
        while (lukija.hasNextLine()) {
            String rivi = lukija.nextLine();
            String[] osat = rivi.split("-");
            Tulos tulos = new Tulos(new Pelaaja(osat[1]), Integer.parseInt(osat[0]), osat[2]);
            tulokset.add(tulos);
        }
        lukija.close();
        return tulokset;
    }
}
