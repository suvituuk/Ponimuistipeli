
package kayttoliittyma;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

/**
 *
 * Graafinen käyttöliittymä tulosten katseluun.
 */
public class Tulokset implements Runnable {
    private JFrame frame;
    private JTextArea tulokset;
    private JTextArea nimi;
    private final String pelaaja;
    private JButton omat;
    private JButton top;

    public Tulokset() {
        this.pelaaja = "";
    }
    
    public Tulokset(String pelaaja) {
        this.pelaaja = pelaaja;
    }

    @Override
    public void run() {
        frame = new JFrame("Tulokset");
        frame.setPreferredSize(new Dimension(300, 300));

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        alkunakyma();
        frame.setVisible(true);
    }
    
    private void alkunakyma() {
        if (pelaaja.equals("")) {
            top.doClick();
        } else {
            omat.doClick();
        }
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
    private void luoTulokset(Container container) {
        tulokset = new JTextArea();
        Border ulompi = BorderFactory.createMatteBorder(5, 5, 5, 5, Color.decode("#CFB53B"));
        Border sisempi = BorderFactory.createMatteBorder(3, 3, 3, 3, Color.decode("#FEE7FA"));
        tulokset.setBorder(BorderFactory.createCompoundBorder(ulompi, sisempi));
        tulokset.setBackground(Color.decode("#FEE7FA"));
        JScrollPane sp = new JScrollPane(tulokset);
        container.add(sp);
    }
    
    /**
     * Luo painikkeet omien ja parhaiden tulosten katseluun.
     * @param container Container-olio, johon komponentit asetetaan.
     */
    private void luoValinnat(Container container) {
        container.setLayout(new GridLayout(2, 2));
        container.add(new JLabel("Nimimerkki:"));
        luoNimi(container);
        luoOmat(container);
        luoTop(container);
    }
    
    /**
     * Luo nimikentän.
     * @param container Container, johon nimikenttä asetetaan.
     */
    private void luoNimi(Container container) {
        nimi = new JTextArea();
        nimi.setBorder(BorderFactory.createLoweredBevelBorder());
        nimi.setText(pelaaja);
        container.add(nimi);
    }
    
    /**
     * Luo painikkeen parhaiden tulosten katseluun.
     * @param container Container, johon painike asetetaan.
     */
    private void luoTop(Container container) {
        top = new JButton("Top 10");
        top.addActionListener(new TopTulostenKuuntelija(this));
        teeHienoPinkkiNappula(top);
        container.add(top);
    }
    
    /**
     * Luo painikkeen omien tulosten katseluun.
     * @param container Container, johon painike asetetaa.
     */
    private void luoOmat(Container container) {
        omat = new JButton("Omat tulokset");
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
