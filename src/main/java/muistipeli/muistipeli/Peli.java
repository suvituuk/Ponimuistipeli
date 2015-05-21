
package muistipeli.muistipeli;
import java.util.*;

public class Peli {
    private Poyta poyta;
    private Scanner lukija;
    
    public Peli(Scanner lukija){
        poyta = new Poyta();
        this.lukija = lukija;
    }
    
    public void kaynnista(){
        System.out.println("Tervetuloa pelaamaan EEPPISEN HIENOA MLP-muistipeliä!");
        while(true){
            kierros();
        }
    }
    
    private void kierros(){
        Kortti ekaKortti = kortinValinta();
        kortinKaanto(ekaKortti);
        Kortti tokaKortti = kortinValinta();
        kortinKaanto(tokaKortti);
        if (ekaKortti.getPoni() != tokaKortti.getPoni()){
            ekaKortti.kaanna();
            tokaKortti.kaanna();
        }
    }
    
    private Kortti kortinValinta(){
        while(true){
            System.out.println("Valitse sarake:");
            int sarake = Integer.parseInt(lukija.nextLine());
            System.out.println("Valitse rivi:");
            int rivi = Integer.parseInt(lukija.nextLine());
            Kortti kortti = poyta.getRuudukko()[sarake][rivi];
            if(!kortti.onkoKaannetty()){
                return kortti;
            } else {
                System.out.println("Valitse kortti, joka ei ole käännettynä!");
            }
        }
    }
    
    private void kortinKaanto(Kortti kortti){
        kortti.kaanna();
        System.out.println("\n" + poyta.toString());
    }
}
