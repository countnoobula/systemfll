/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ProgramGUI.GUIComponents.Buttons;

import MainClasses.Main;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 *
 * @author Dylan
 */
public class TopPanelButton extends JButton {

    Main m;
    String title;
    boolean entered = false;

    public TopPanelButton(Main m2, String s) {
        super(s);
        title = s;
        this.m = m2;
        this.setOpaque(false);
        this.setMargin(new Insets(-3, -3, -3, -3));
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        this.setUI(new TopPanelButtonUI());
        //mouse listener for hover effects
        this.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
                entered = true;
                repaint();
            }

            public void mouseExited(MouseEvent e) {
                entered = false;
                repaint();
            }
        });
    }

    private class TopPanelButtonUI extends BasicButtonUI {

        Paint gp1, gp2, gp3;
        Font f1;

        private TopPanelButtonUI() {
            gp1 =
                    new GradientPaint(0, 10, new Color(200, 200, 200), 0, 25, new Color(255, 255,
                    255));
            gp2 =
                    new GradientPaint(0, 10, new Color(200, 200, 200, 100), 0, 25, new Color(255, 255,
                    255, 100));
            gp3 = new Color(0, 192, 255);


            f1 = m.getFonts().getFont(1).deriveFont(14.0f);
        }

        @Override
        public void paint(Graphics g, JComponent c) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.setFont(f1);
            if (entered == true) {
                g2d.setPaint(gp3);
            } else {
                g2d.setPaint(gp2);
            }


            g2d.fillRect(5, 4, 3, 10);
            g2d.fillRect(10, 4, 3, 10);
            g2d.setPaint(gp1);
            g2d.drawString(title, 20,
                    c.getHeight() / 2 + 4);
        }
    }
}
