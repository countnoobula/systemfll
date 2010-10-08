/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ProgramGUI.GUIComponents;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;

import java.awt.Stroke;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author Dylan
 */
public class SystemTextField extends JTextField {

    Paint gp1, gp2, gp3;
    Stroke s1;
    Font f1;

    public SystemTextField() {
        gp1 =
 new GradientPaint(0, 0, new Color(0, 192, 255), 0, 25, new Color(0, 142,
                                                                  200));
        gp2 =
 new GradientPaint(0, 0, new Color(0, 0, 0), 0, 25, new Color(0, 0, 0, 0));
        s1 = new BasicStroke(1.6f);
        gp3 =
 new GradientPaint(0, 0, new Color(60, 60, 60), 0, 25, new Color(0, 0, 0, 0));

        f1 = new Font("Arial", 12, 12);
        this.setOpaque(false);
        this.setFont(f1);
        this.setForeground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 5));
    }

    public static void main(String[] args) {
        SystemButton s = new SystemButton("Connect");
        SystemTextField s2 = new SystemTextField();
        JFrame f = new JFrame();
        f.setBackground(new Color(40, 40, 40));
        f.setBounds(300, 300, 300, 300);
        f.setLayout(null);
        f.add(s);
        s.setBounds(100, 100, 100, 22);
        f.add(s2);
        s2.setBounds(100, 150, 200, 22);
        f.setVisible(true);

    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setStroke(s1);

        g2d.setPaint(gp2);

        g2d.fillRoundRect(0, 0, this.getWidth() - 1, this.getHeight() - 1, 10,
                          10);
        g2d.setPaint(gp1);
        g2d.drawRoundRect(0, 0, this.getWidth() - 1, this.getHeight() - 1, 10,
                          10);
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.setFont(f1);
        super.paintComponent(g);
    }


}
