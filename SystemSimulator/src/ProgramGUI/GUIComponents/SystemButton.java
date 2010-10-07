package ProgramGUI.GUIComponents;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;

import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.RenderingHints;

import java.awt.Stroke;

import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.plaf.basic.BasicButtonUI;

import java.awt.event.MouseListener;

public class SystemButton extends JButton {
    String title;
    boolean entered = false;

    public SystemButton(String s) {
        super(s);
        this.title = s;
        this.setUI(new SystemButtonUI());
        this.setOpaque(false);
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
      this.setMargin(new Insets(-3, -3, -3, -3));
                  this.setBorderPainted(false);
                  this.setFocusPainted(false);
                  this.setContentAreaFilled(false);


    }

    public static void main(String[] args) {
        SystemButton s = new SystemButton("Connect");
        JFrame f = new JFrame();
        f.setBackground(new Color(40, 40, 40));
        f.setBounds(300, 300, 300, 300);
        f.setLayout(null);
        f.add(s);
        s.setBounds(100, 100, 100, 22);
        f.setVisible(true);

    }

    private class SystemButtonUI extends BasicButtonUI {


        Paint gp1, gp2, gp3;
        Stroke s1;
        Font f1;

        private SystemButtonUI() {
            gp1 =
 new GradientPaint(0, 0, new Color(0, 192, 255), 0, 25, new Color(0, 142,
                                                                  200));
            gp2 =
 new GradientPaint(0, 0, new Color(0, 0, 0), 0, 25, new Color(0, 0, 0, 0));
            
            gp3 =
 new GradientPaint(0, 0, new Color(60, 60, 60), 0, 25, new Color(0, 0, 0, 0));

            f1 = new Font("Arial",13,13);
          s1 = new BasicStroke(1.6f);
        }

        @Override
        public void paint(Graphics g, JComponent c) {
            Graphics2D g2d = (Graphics2D)g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                 RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setStroke(s1);
            if (entered == false) {
                g2d.setPaint(gp3);
            } else {
                g2d.setPaint(gp2);
            }
            g2d.fillRoundRect(0, 0, c.getWidth() - 1, c.getHeight() - 1, 15,
                              15);
            g2d.setPaint(gp1);
            g2d.drawRoundRect(0, 0, c.getWidth() - 1, c.getHeight() - 1, 15,
                              15);
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.setFont(f1);
            g2d.drawString(title, c.getWidth() / 2 - (int)(title.length() * 3.5),
                           c.getHeight() / 2 + 4);
        }
    }
}
