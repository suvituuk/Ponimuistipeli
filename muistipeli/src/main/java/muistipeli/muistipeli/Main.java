package muistipeli.muistipeli;

import java.io.IOException;
import javax.swing.SwingUtilities;
import kayttoliittyma.Pelinkaynnistin;

public class Main {

    public static void main(String[] args) throws IOException {
        Pelinkaynnistin kaynnistin = new Pelinkaynnistin();
        SwingUtilities.invokeLater(kaynnistin);
        
    }
}
