package ProgramGUI.GUIComponents;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.Rectangle;

import java.awt.RenderingHints;

import java.awt.Stroke;

import javax.swing.AbstractButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicCheckBoxUI;

public class SystemCheckBox extends JCheckBox {
    String title;
    SystemCheckBox nested;

    public SystemCheckBox(String s) {
        super(s);
        nested = this;
        this.title = s;
        this.setOpaque(false);
        this.setUI(new SystemCheckBoxUI());
      this.setMargin(new Insets(-3, -3, -3, -3));
                  this.setBorderPainted(false);
                  this.setFocusPainted(false);
                  this.setContentAreaFilled(false);
    }

    private class SystemCheckBoxUI extends BasicCheckBoxUI {

        Paint gp1, gp2, gp3;
        Stroke s1;
        Font f1;

        private SystemCheckBoxUI() {

            gp3 =
 new GradientPaint(0, 0, new Color(30, 30, 30), 0, 25, new Color(70, 70, 70));
            gp2 =
 new GradientPaint(5, 5, new Color(0, 192, 255), 20, 20, new Color(0, 142,
                                                                   200));
            gp1 =
 new GradientPaint(5, 5, new Color(0, 192, 255, 50), 20, 20, new Color(0, 142,
                                                                       200,
                                                                       50));
            s1 = new BasicStroke(1.6f);
            f1 = new Font("Arial", 12, 12);
        }

        @Override
        public synchronized void paint(Graphics g, JComponent c) {
            Graphics2D g2d = (Graphics2D)g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                 RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setStroke(s1);
            if (nested.isSelected() == true) {
                g2d.setPaint(gp1);

            } else {
                g2d.setPaint(gp3);
            }
            g2d.fillRoundRect(0, 0, 18, 18, 6, 6);
            g2d.setPaint(Color.GRAY);
            g2d.drawRoundRect(0, 0, 18, 18, 6, 6);
            if (nested.isSelected() == true) {
                g2d.setPaint(gp2);
                g2d.fillOval(5, 5, 9, 9);

            }
            g2d.setFont(f1);
            g2d.setColor(Color.WHITE);
            g2d.drawString(title, 30, c.getHeight() - 7);
        }

    }
}
