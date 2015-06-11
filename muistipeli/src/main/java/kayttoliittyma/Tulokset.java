
package kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

/**
 *
 * Graafinen käyttöliittymä tulosten katseluun.
 */
public class Tulokset implements Runnable {
    private JFrame frame;
    private JTextArea tulokset;
    private JTextArea nimi;

    public Tulokset() {
    }

    @Override
    public void run() {
        frame = new JFrame("Tulokset");
        frame.setPreferredSize(new Dimension(300, 400));

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Luo ja lisää käyttöliittymään tarvittavat komponentit.
     * @param container Container-olio johon luodut komponentit asetetaan.
     */
    private void luoKomponentit(Container container) {
        container.setBackground(java.awt.Color.decode("#FFDBF9"));
        BorderLayout layout = new BorderLayout();
        container.setLayout(layout);
        Container valinnat = new Container();
        luoValinnat(valinnat);
        container.add(valinnat, BorderLayout.NORTH);
        luoTulokset(container);
    }
    
    /**
     * Luo JTextArean, jossa tulokset näkyvät.
     * @param container Container-olio, johon tekstikenttä asetetaan.
     */
    public void luoTulokset(Container container) {
        tulokset = new JTextArea();
        tulokset.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.decode("#FFDBF9")));
        tulokset.setBackground(Color.decode("#FEE7FA"));
        container.add(tulokset);
    }
    
    /**
     * Luo painikkeet omien ja parhaiden tulosten katseluun.
     * @param container Container-olio, johon komponentit asetetaan.
     */
    public void luoValinnat(Container container) {
        container.setLayout(new GridLayout(2, 2));
        luoOmat(container);
        luoTop(container);
        container.add(new JLabel("Nimimerkki:"));
        luoNimi(container);
    }
    
    /**
     * Luo nimikentän.
     * @param container Container, johon nimikenttä asetetaan.
     */
    public void luoNimi(Container container) {
        nimi = new JTextArea();
        nimi.setBorder(BorderFactory.createLoweredBevelBorder());
        container.add(nimi);
    }
    
    /**
     * Luo painikkeen parhaiden tulosten katseluun.
     * @param container Container, johon painike asetetaan.
     */
    public void luoTop(Container container) {
        JButton top = new JButton("Top 10");
        top.addActionListener(new TopTulostenKuuntelija(this));
        teeHienoPinkkiNappula(top);
        container.add(top);
    }
    
    /**
     * Luo painikkeen omien tulosten katseluun.
     * @param container Container, johon painike asetetaa.
     */
    public void luoOmat(Container container) {
        JButton omat = new JButton("Omat tulokset");
        omat.addActionListener(new OmienTulostenKuuntelija(this));
        teeHienoPinkkiNappula(omat);
        container.add(omat);
    }
    
    /**
     * Tekee painikkeesta vaaleanpunaisen ja kohotetun.
     * @param nappula Painike, jota muokataan.
     */
    public void teeHienoPinkkiNappula(JButton nappula) {
        nappula.setBorder(BorderFactory.createRaisedBevelBorder());
        nappula.setBackground(java.awt.Color.decode("#FFAFF9"));
    }

    public JFrame getFrame() {
        return frame;
    }
    
    public JTextArea getTulokset() {
        return this.tulokset;
    }
    
    public JTextArea getNimi() {
        return this.nimi;
    }
    
}
