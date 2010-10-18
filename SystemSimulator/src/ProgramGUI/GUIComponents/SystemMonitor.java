/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ProgramGUI.GUIComponents;

import MainClasses.Main;
import ProgramGUI.GUIComponents.Panes.NullPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;

/**
 *
 * @author Dylan
 */
public class SystemMonitor extends NullPanel {

    private int percent;
    private int size;
    private Color c;
    private String name;
    private double sections;
    private Paint gp1, gp2, gp3, gp4;
    private Font f1;
    Main m;

    public SystemMonitor(Main m2, int size, Color c, String name) {
        percent = 0;
        this.c = c;
        m = m2;
        this.size = size;
        this.name = name;
        sections = 100 / size;
        f1 = m.getFonts().getFont(1).deriveFont(11.0f);

        gp1 = new GradientPaint(0, 0, new Color(0, 192, 255), 0, 10, new Color(0, 142, 205));
        gp2 = new GradientPaint(0, 0, new Color(0, 192, 255, 100), 0, 10, new Color(0, 142, 205, 100));
        gp3 = new GradientPaint(0, 0, new Color(255, 0, 0), 0, 10, new Color(200, 0, 0));
        gp4 = new GradientPaint(0, 0, new Color(255, 0, 0, 100), 0, 10, new Color(200, 0, 0, 100));
        this.setPreferredSize(new Dimension((size * 6) + 40, 10));
        this.setMinimumSize(new Dimension((size * 6) + 40, 10));
    }

    public void updatePercent(int percent) {
        this.percent = percent;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setFont(f1);
        g2d.setColor(Color.WHITE);
        g2d.drawString(name, 0, 9);
        // lay out the monitor
        for (int i = 0; i < size; i++) {
            if (percent < 85) {
                if ((i * sections) < percent) {
                    g2d.setPaint(gp1);
                } else {
                    g2d.setPaint(gp2);
                }
            } else {
                if ((i * sections) < percent) {
                    g2d.setPaint(gp3);
                } else {
                    g2d.setPaint(gp4);
                }
            }
            g2d.fillRect((i * 6) + 30, 0, 4, 10);
        }
        g2d.dispose();
    }
}
