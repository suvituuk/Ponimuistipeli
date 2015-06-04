package muistipeli.muistipeli;
import kayttoliittyma.Kayttoliittyma;
import java.io.IOException;
import java.util.*;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) throws IOException {
//        Peli peli = new Peli(new Scanner(System.in));
//        peli.kaynnista();
        
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma();
        SwingUtilities.invokeLater(kayttoliittyma);

    }
}
