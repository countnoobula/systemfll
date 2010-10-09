package VisualLogicSystem;

import ProgramGUI.GUIComponents.BlockProperties;
import VisualLogicSystem.DataBlocks.DataObject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicButtonUI;

public abstract class LogicBlock implements LogicBlockInterface, Cloneable {

    //GUI data
    public Paint gp1, gp2, gp3, gp4, gp5;
    public Graphics2D g2d;
    public Rectangle bounds;
    public BufferedImage bi;
    public int size = 50;
    //ID data
    public int ID;
    //Connection DATA
    public ArrayList<Rectangle> rects;
    public ArrayList<Integer> connections;
    public ArrayList<LogicLink> nodes;
    public ArrayList<DataObject> data;
    //Code stuffs
    public CodeBlock codeBlock;

    public int getID() {
        return ID;
    }

    public LogicBlock() {

        bounds = new Rectangle();

        rects = new ArrayList<Rectangle>(0);
        nodes = new ArrayList<LogicLink>(0);
        connections = new ArrayList<Integer>(0);
        data = new ArrayList<DataObject>(0);

        codeBlock = new CodeBlock(this);

        bounds.setSize(size, size);



        //create a new buffered image
        bi = new BufferedImage(size + 10, size + 10, BufferedImage.TYPE_INT_ARGB);
        g2d = bi.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);



        // a few grphics objects which are useful
        gp1 = new Color(20, 20, 20);
        gp2 = new Color(160, 160, 160);
        gp3 =
                new GradientPaint(size, 0, new Color(0, 0, 0, 100), size + 10, 0, new Color(0,
                0,
                0,
                0));
        gp4 =
                new GradientPaint(0, size, new Color(0, 0, 0, 100), 0, size + 10, new Color(0,
                0,
                0,
                0));
        gp5 =
                new GradientPaint(size, size, new Color(0, 0, 0, 100), size + 5, size + 5,
                new Color(0, 0, 0, 0));


        drawBackground();


}

    public ArrayList<LogicLink> getNodes() {
        return nodes;
    }


    public void addLink(LogicLink link) {
        nodes.add(link);

    }
    public void removeLink(LogicLink l){
        nodes.remove(l);
    }

    public BlockProperties getProperties() {
        return new BlockProperties(this);
    }

    public LogicLink getLinks(int index) {
        return nodes.get(index);
    }

    public int getLinksArraySize() {
        return nodes.size();
    }

    public int getConnectionRules(int i) {
        return connections.get(i);
    }

    public CodeBlock getCodeBlock() {
        return codeBlock;
    }

    public ArrayList<DataObject> getData() {
        return data;
    }


    public void drawBackground() {

        g2d.setPaint(gp1);
        g2d.fillRect(0, 0, size, size);
        g2d.setPaint(gp3);
        g2d.fillRect(size, 5, 10, size - 5);
        g2d.setPaint(gp4);
        g2d.fillRect(5, size, size - 5, 10);
        g2d.setPaint(gp5);
        g2d.fillRect(size, size, 10, 10);
        g2d.setPaint(gp2);
        g2d.drawRect(0, 0, size, size);

    }

    /**
     * Set the location of this object.
     * @param x
     * The X location
     * @param y
     * The Y location
     */
    public void setLocation(int x, int y) {
        this.bounds.setLocation(x, y);
    }

    public Rectangle getConnectionBoundReal(int index) {
        return new Rectangle((int) (rects.get(index).getX() + bounds.getX()),
                (int) (rects.get(index).getY() + bounds.getY()),
                (int) (rects.get(index).getWidth()),
                (int) (rects.get(index).getHeight()));
    }

    public Rectangle getConnectionBound(int index) {
        return rects.get(index);
    }

    public int getX() {
        return (int) bounds.getX();
    }

    public int getY() {
        return (int) bounds.getY();
    }

    public Rectangle getBounds() {
        return bounds;
    }

    private class LogicButtonUI extends BasicButtonUI {

        @Override
        public void paint(Graphics g, JComponent c) {
            g.drawImage(bi, 0, 0, null);
        }
    }

    public JButton getButton() {
        JButton temp = new JButton("");
        ImageIcon i = new ImageIcon(bi);
        temp.setUI(new LogicButtonUI());
        temp.setOpaque(false);
        temp.setPreferredSize(new Dimension(size + 10, size + 10));
        temp.addActionListener(new LogicBlockAddition(this));
        return temp;
    }

    public int getAmountBounds() {
        return rects.size();
    }

    public Image getGraphicsObject() {
        return bi;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
