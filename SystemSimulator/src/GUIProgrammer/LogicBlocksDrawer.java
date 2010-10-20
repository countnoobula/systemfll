package GUIProgrammer;

import java.awt.Graphics;

import javax.swing.JPanel;

import MainClasses.Main;

import VisualLogicSystem.LogicBlock;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;

import java.util.ArrayList;

public class LogicBlocksDrawer extends JPanel {

    private Main m;
    private Paint gp1;
    private ArrayList<LogicBlock> library;

    public LogicBlocksDrawer(Main m2) {
        super();

        this.m = m2;
        this.setPreferredSize(new Dimension(230, 0));
        this.setOpaque(false);
        this.setLayout(new FlowLayout());

        gp1 = new GradientPaint(0, 40, new Color(0, 0, 0), 0, 80, new Color(30, 30, 30));

    }

    public void setLogicBlocks(ArrayList<LogicBlock> library) {
        this.library = library;
        this.removeAll();
        for (int i = 0; i < library.size(); i++) {
            this.add(library.get(i).getButton());

        }
        this.revalidate();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(gp1);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setPaint(Color.BLACK);
        g2d.drawLine(0, 0, 0, getHeight());
    }
}
