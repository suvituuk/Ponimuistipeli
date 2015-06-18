package muistipeli.logiikka;

import java.io.*;
import java.util.*;

/**
 *
 * Käsittelee tuloksia eli käytännössä tallentaa ja hakee niitä.
 */
public class Tulostenkasittelija {

    private final FileWriter kirjaaja;

    public Tulostenkasittelija() throws IOException {
        this.kirjaaja = new FileWriter("tulokset.txt", true);
    }

    /**
     * Tallentaa tulokset-tiedostoon parametrina saamansa tuloksen.
     * @param tulos tallennettava tulos
     * @throws IOException poikkeus
     */
    public void lisaaTulos(Tulos tulos) throws IOException {
        kirjaaja.write(tulos.getTulos() + "-" + tulos.getPelaaja().getNimi() + "-" + tulos.getAika() + "\n");
        kirjaaja.close();
    }

    /**
     * Palauttaa String-muodossa pelaajan (oikeastaan kaikkien samannimisten pelaajien) tulokset.
     * @param pelaaja pelaaja, jonka tulokset halutaan
     * @return pelaajan kaikki tulokset String-muodossa
     * @throws FileNotFoundException Tiedostoa ei löydy
     * @throws IOException poikkeus
     */
    public String omatTulokset(Pelaaja pelaaja) throws FileNotFoundException, IOException {
        String tulokset = "";
        ArrayList<Tulos> tuloksetListana = tuloksetListana();
        Collections.reverse(tuloksetListana);
        for (Tulos tulos: tuloksetListana) {
            if (tulos.getPelaaja().getNimi().equals(pelaaja.getNimi())) {
                tulokset += tulos.toString() + "\n";
            }
        }
        return tulokset;
    }
    
    /**
     * Palauttaa parhaat tulokset String-muodossa listana. 
     * @param top kuinka monta parasta tulosta halutaan
     * @return parhaat tulokset String-muodossa
     * @throws IOException poikkeus
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
     * @throws IOException poikkeus
     */
    public ArrayList<Tulos> tuloksetListana() throws IOException {
        ArrayList<Tulos> tulokset;
        try (Scanner lukija = new Scanner(new File("tulokset.txt"))) {
            tulokset = new ArrayList<>();
            while (lukija.hasNextLine()) {
                String rivi = lukija.nextLine();
                String[] osat = rivi.split("-");
                Tulos tulos = new Tulos(new Pelaaja(osat[1]), Integer.parseInt(osat[0]), osat[2]);
                tulokset.add(tulos);
            }
        }
        return tulokset;
    }
}
