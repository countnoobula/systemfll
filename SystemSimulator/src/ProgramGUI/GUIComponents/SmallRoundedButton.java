package ProgramGUI.GUIComponents;

import ProgramUtils.SystemInformation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;

import java.awt.Graphics2D;

import java.awt.Insets;
import java.awt.Paint;
import java.awt.RenderingHints;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicButtonUI;

public class SmallRoundedButton extends JButton {

    String title;

    public SmallRoundedButton(String s) {

        super(s);
        this.title = s;
        this.setOpaque(false);
        this.setUI(new SmallRoundedButtonUI());
        this.setMargin(new Insets(-3, -3, -3, -3));
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        if (SystemInformation.isWindows()) {
            this.setPreferredSize(new Dimension((int)getPreferredSize().getWidth() +
                                                50,
                                                (int)getPreferredSize().getHeight()));
        }


    }


    public SmallRoundedButton() {
    }


    private class SmallRoundedButtonUI extends BasicButtonUI {

        Font f1;
        Paint gp1;

        private SmallRoundedButtonUI() {
            f1 = new Font("Arial", 12, 12);
            gp1 =
 new GradientPaint(0, 0, new Color(40, 40, 40), 0, 20, new Color(80, 80, 80));

        }

        @Override
        public void paint(Graphics g, JComponent c) {
            Graphics2D g2d = (Graphics2D)g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                 RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setPaint(gp1);
            g2d.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 15, 15);
            g2d.setPaint(Color.GRAY);
            g2d.drawRoundRect(0, 0, c.getWidth() - 1, c.getHeight() - 1, 15,
                              15);
            g2d.setColor(Color.WHITE);
            g2d.setFont(f1);
            g2d.drawString(title, 10, c.getHeight() - 4);

        }
    }
}
