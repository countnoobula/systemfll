package ProgramGUI.GUIComponents.Buttons;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Shape;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicButtonUI;

import MainClasses.Main;


public class AuxPanelButton extends JButton {

    GradientPaint gp2;
    Color gp1;
    Shape s1, s2;
    Main m;
    String name = "";
    boolean entered = false;
    AuxPanelButton nested;

    public AuxPanelButton(Main m2, String s, Color c1) {
        super(s);
        name = "" + s;
        this.m = m2;
        nested = this;
        gp1 = c1;

        this.addMouseListener(new MouseListener() {
                public void mouseClicked(MouseEvent e) {
                }

                public void mousePressed(MouseEvent e) {
                }

                public void mouseReleased(MouseEvent e) {
                }

                public void mouseEntered(MouseEvent e) {
                    entered = true;
                    nested.repaint();

                }

                public void mouseExited(MouseEvent e) {
                    entered = false;
                    nested.repaint();
                }
            });

        int x1[] = { 0, 12, 16, 36, 36, 16, 14, 0 };
        int y1[] = { 0, 0, 4, 4, 36, 36, 32, 32 };
        s1 = new Polygon(x1, y1, 8);
        int x2[] = { 0, 12, 16, 36, 36, 16, 14, 0 };
        int y2[] = { 4, 4, 8, 8, 32, 32, 28, 20 };
        s2 = new Polygon(x2, y2, 8);

        this.setUI(new AuxPanelButtonUI());
        this.setPreferredSize(new Dimension(36, 36));
        this.setOpaque(false);
    }

    private class AuxPanelButtonUI extends BasicButtonUI {

        @Override
        protected void paintText(Graphics g, JComponent c, Rectangle textRect,
                                 String t) {

        }

        @Override
        public void paint(Graphics g, JComponent c) {
            Graphics2D g2d = (Graphics2D)g;
            if (entered == false) {
                g2d.setPaint(gp1);
            } else {
                g2d.setPaint(Color.WHITE);
            }
            g2d.fillRect(2, 2, 36, 36);
            g2d.setPaint(gp2);
            g2d.setColor(Color.BLACK);
            g2d.drawRect(0, 0, 35, 35);
            g2d.setColor(new Color(0, 0, 0, 50));
            g2d.fill(s1);
            g2d.fill(s2);
            g2d.setColor(Color.WHITE);
            g2d.setFont(m.getFonts().getFont(0));
            g2d.drawString(name, 3, 24);

        }

        @Override
        protected void paintButtonPressed(Graphics g, AbstractButton b) {
            Graphics2D g2d = (Graphics2D)g;
            g2d.setColor(Color.WHITE);
            g2d.fillRect(2, 2, 36, 36);
            g2d.setPaint(gp2);
            g2d.setColor(Color.BLACK);
            g2d.drawRect(0, 0, 35, 35);
            g2d.setColor(new Color(0, 0, 0, 50));
            g2d.fill(s1);
            g2d.fill(s2);
            g2d.setColor(Color.BLACK);
            g2d.setFont(m.getFonts().getFont(0));
            g2d.drawString(name, 3, 24);
        }
    }
}
