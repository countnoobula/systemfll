package ProgramGUI.SectionBottom;

import javax.swing.JPanel;

import MainClasses.Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * This is the bottom section panel of the program.
 */
public class SectionBottomPanel extends JPanel {


    GradientPaint gp1;

    private Main m;

    public SectionBottomPanel(Main m2) {
        this.m = m2;
        this.setPreferredSize(new Dimension(0, 20));

        gp1 =
 new GradientPaint(0, 30, new Color(200, 200, 200), 0, 0, new Color(255, 255,
                                                                    255));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(gp1);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());


    }
}
