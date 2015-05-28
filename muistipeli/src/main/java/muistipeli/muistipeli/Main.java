package muistipeli.muistipeli;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Peli peli = new Peli(new Scanner(System.in));
        peli.kaynnista();
        
    }
}
