package ProgramGUI.SectionCenter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;

import javax.swing.JPanel;

import MainClasses.Main;

/**
 * 
 */
public class SectionCenterSidePanels extends JPanel {

    Paint gp1;
    Main m;
    public SectionCenterSidePanels(Main m2) {
        this.setOpaque(false);
        this.setVisible(false);
        this.m = m2;
        
        gp1 = new Color(0, 0, 0, 220);
        
        //panel properties
        this.setPreferredSize(new Dimension(40,0));
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(gp1);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
}
