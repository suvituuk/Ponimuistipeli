package muistipeli.kayttoliittyma;

import java.awt.event.*;
import javax.swing.SwingUtilities;

/**
 *
 * ActionListener, joka avaa uuden graafisen käyttöliittymän, jossa tuloksia voi katsella.
 */
public class TulostenKatselunKuuntelija implements ActionListener {

    /**
     * Avaa Tulokset-käyttöliittymän.
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Tulokset tulokset = new Tulokset();
        SwingUtilities.invokeLater(tulokset);
    }
    
}
