/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import muistipeli.muistipeli.Peli;

public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private Peli peli;
    private Container ruudukko;
    private JButton ekaKaannetty;
    private JButton tokaKaannetty;
    private boolean onkoPari;

    public Kayttoliittyma() {
        this.peli = new Peli(new Scanner(System.in));
        this.ruudukko = new Container();
        this.onkoPari = true;
        JButton oletuskortti = new JButton("1");
        this.ekaKaannetty = oletuskortti;
        this.tokaKaannetty = oletuskortti;
    }

    @Override
    public void run() {
        frame = new JFrame("MLP-muistipeli");
        frame.setPreferredSize(new Dimension(420, 470));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        container.setLayout(new BorderLayout());
        Container ylapalkki = new Container();
        luoYlapalkki(ylapalkki);
        container.add(ylapalkki, BorderLayout.NORTH);
        luoRuudukko(ruudukko);
        container.add(ruudukko);
    }
    
    private void luoYlapalkki(Container container) {
        GridLayout layout = new GridLayout();
        container.setLayout(layout);
        container.add(new JButton("Omat tulokset"));
        container.add(new JButton("Parhaat tulokset"));
    }

    private void luoRuudukko(Container container) {
        GridLayout layout = new GridLayout(4, 4);
        container.setLayout(layout);
        int i = 0;       
        while (i < 16) {
            ImageIcon icon = new ImageIcon("cardback.jpg");
            JButton nappi = new JButton(icon);
            nappi.addActionListener(new Kortinkuuntelija(this.peli, nappi, i, this));
            container.add(nappi);
            i++;
        }
        
    }

//    protected ImageIcon createImageIcon(String path,
//            String description) {
//        java.net.URL imgURL = getClass().getResource(path);
//        if (imgURL != null) {
//            return new ImageIcon(imgURL, description);
//        } else {
//            System.err.println("Couldn't find file: " + path);
//            return null;
//        }
//    }

    public JFrame getFrame() {
        return frame;
    }
    
    public Container getRuudukko() {
        return this.ruudukko;
    }
    
    public void setEkaKaannetty(JButton kortti) {
        this.ekaKaannetty = kortti;
    }
    
    public JButton getEkaKaannetty() {
        return this.ekaKaannetty;
    }
    
    public void setTokaKaannetty(JButton kortti) {
        this.tokaKaannetty = kortti;
    }
    
    public JButton getTokaKaannetty() {
        return this.tokaKaannetty;
    }
    
    public boolean getOnkoPari() {
        return this.peli.getEkaKaannetty().getPoni() == this.peli.getTokaKaannetty().getPoni();
    }
    
    public void setOnkoPari(boolean pari) {
        this.onkoPari = pari;
    }
}
