package ProgramGUI.GUIComponents.Buttons;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Polygon;

import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.awt.Shape;

import MainClasses.Main;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicButtonUI;

import ProgramUtils.SystemInformation;

public class SubBarAButton extends JButton {

    private String title;
    private boolean entered = false;
    private Main m;

    public SubBarAButton(Main m2, String s) {
        super(s);
        this.title = s;
        this.m = m2;
        this.setUI(new SubBarAButtonUI());
        this.setOpaque(false);

        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);

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

    private class SubBarAButtonUI extends BasicButtonUI {

        Shape s1, s2, s3;
        Paint gp1, gp2;
        int y[] = {0, 0, 25, 25};
        int y2[] = {13, 13, 25, 25};

        private SubBarAButtonUI() {
            gp1 =
                    new GradientPaint(0, 4, new Color(10, 10, 10), 0, 15, new Color(30, 30, 30));



        }

        @Override
        protected void paintButtonPressed(Graphics g, AbstractButton b) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setPaint(Color.BLACK);
            g2d.fillRoundRect(2, 3, b.getWidth() - 4, b.getHeight() - 4, 6, 6);
        }

        @Override
        public void paint(Graphics g, JComponent c) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.setColor(Color.BLACK);
            if (SystemInformation.isWindows()) {
                g2d.setFont(m.getFonts().getFont(1).deriveFont(11.0f));
            } else {
                g2d.setFont(m.getFonts().getFont(1).deriveFont(12.0f));
            }

            g2d.drawString(title, 14, 13);

            if (entered == true) {
                g2d.setColor(Color.CYAN);
            } else {
                g2d.setPaint(gp1);
            }
            g2d.fillRect(0, 4, 4, 10);
            g2d.fillRect(6, 4, 2, 10);



        }

        @Override
        protected void installDefaults(AbstractButton b) {
            super.installDefaults(b);
            int x[] = {0, (int) b.getPreferredSize().getWidth() - 25, (int) b.getPreferredSize().getWidth(),
                0};
            s1 = new Polygon(x, y, 4);
        }
    }
}
