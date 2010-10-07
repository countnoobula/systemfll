package ProgramGUI.GUIComponents;

import VisualLogicSystem.LogicBlock;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;

import java.awt.Graphics2D;

import java.awt.Paint;

import javax.swing.JPanel;

public class BlockProperties extends JPanel {

    Paint gp1, gp2, gp3, gp4;

    public BlockProperties(LogicBlock bl) {
        this.setOpaque(false);
        gp1 = new Color(0, 0, 0, 80);


    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(gp1);
        gp2 =
 new GradientPaint(this.getWidth() - 10, 0, new Color(0, 0, 0, 150),
                   this.getWidth(), 0, new Color(0, 0, 0, 0));
        gp3 =
 new GradientPaint(0, this.getHeight() - 10, new Color(0, 0, 0, 150), 0,
                   this.getHeight(), new Color(0, 0, 0, 0));
        gp4 =
 new GradientPaint(this.getWidth() - 10, this.getHeight() - 10, new Color(0, 0,
                                                                          0,
                                                                          150),
                   getWidth() - 5, this.getHeight() - 5,
                   new Color(0, 0, 0, 0));


        g2d.setPaint(gp1);
        g2d.fillRect(0, 0, getWidth() - 10, getHeight() - 10);
        g2d.setPaint(Color.BLACK);
        g2d.fillRect(0, 0, getWidth() - 10, getHeight() - 10);
        g2d.setPaint(gp2);
        g2d.fillRect(getWidth() - 10, 0, 10, getHeight());
        g2d.setPaint(gp3);
        g2d.fillRect(0, getHeight() - 10, getWidth(), 10);
        g2d.setPaint(gp4);
        g2d.fillRect(getWidth() - 10, getHeight() - 10, 10, 10);


    }
}
