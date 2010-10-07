package ProgramGUI.GUIComponents;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;

import java.awt.Graphics2D;
import java.awt.Paint;

import java.awt.Stroke;

import javax.swing.JPanel;

public class GenericSystemPanel extends JPanel {

    Paint gp1, gp2;
    Stroke s1;
    public GenericSystemPanel() {
        super();
        gp1 = new Color(40, 40, 40);
        gp2 =
 new GradientPaint(0, 0, new Color(0, 0, 0), 0, 40, new Color(40, 40,
                                                                      40));
        s1 = new BasicStroke(1.2f);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d =(Graphics2D)g;
        g2d.setPaint(gp2);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        /* g2d.setColor(Color.BLACK);
        g2d.setStroke(s1);
        g2d.drawRect(1, 1, this.getWidth()-3, this.getHeight()-3); */
        
    }
}
