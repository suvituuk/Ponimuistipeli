package kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;

/**
 *
 * ActionListener, joka avaa uuden graafisenkäyttöliittymän, jossa tuloksia voi katsella.
 */
public class TulostenKatselunKuuntelija implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        Tulokset tulokset = new Tulokset();
        SwingUtilities.invokeLater(tulokset);
    }
    
}
