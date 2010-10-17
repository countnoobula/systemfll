package ProgramGUI.SectionTop;

import java.awt.Graphics;

import javax.swing.JPanel;

import MainClasses.Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics2D;

/**
 * This is the bottom panel of the Program, everything gets attatched to this if it is going
 * to be placed on the bottom
 */
public class SectionTopPanel extends JPanel {

    private GradientPaint gp1;

    private Main m;
    private TopPanelMiddle panel_2;


    public SectionTopPanel(Main m2) {
        
        this.m = m2;
        this.panel_2 = new TopPanelMiddle(m);

        //create the graidients
        gp1 =
 new GradientPaint(0, 0, new Color(250, 250, 250), 0, 50, new Color(200, 200,
                                                                    200));
        this.setPreferredSize(new Dimension(0, 28));
        initComponents();

    }

    private void initComponents() {
        this.setLayout(new BorderLayout());
        this.add(panel_2, BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(gp1);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

    }
}
