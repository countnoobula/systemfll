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
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import java.util.ArrayList;
import javax.media.opengl.GL2;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicButtonUI;

public abstract class LogicBlock implements LogicBlockInterface, Cloneable {

    //GUI data
    public int list;
    public Paint gp1, gp2, gp3, gp4, gp5;
    public Graphics2D g2d;
    public Rectangle bounds;
    public BufferedImage bi;
    public WritableRaster raster;
    public int size = 50;
    public String type = "";
    public ByteBuffer dest = null;
    public int currentCompileString = 0;
    //ID data
    public int ID;
    //Connection DATA
    public ArrayList<Rectangle> rects;
    public ArrayList<Integer> connections;
    public ArrayList<LogicLink> nodes;
    public ArrayList<DataObject> data;
    public ArrayList<String> linkInfo;
    //Code stuffs
    public ArrayList<CodeBlock> codeBlocks;

    public int getID() {
        return ID;
    }
    public String compileProperty = "";

    public String getCompileProperty() {
        return compileProperty;
    }

    public void setCompileProperty(String compileProperty) {
        this.compileProperty = compileProperty;
    }

    public void GenerateGLBlock() {
        

        //the buffer array of bytes
        DataBufferByte dukeBuf = (DataBufferByte) raster.getDataBuffer();
        byte[] data = dukeBuf.getData();


        dest = ByteBuffer.allocateDirect(data.length);
        dest.order(ByteOrder.nativeOrder());
        dest.put(data, 0, data.length);
        dest.rewind(); // <- NEW STUFF NEEDED BY JSR231
    }

    public void drawGL(GL2 gl) {
        gl.glDrawPixels(size+10, size+10,
                GL2.GL_RGBA, GL2.GL_UNSIGNED_BYTE,
                dest);
    }

    public LogicBlock() {


        bounds = new Rectangle();

        //connection data
        rects = new ArrayList<Rectangle>(0);
        nodes = new ArrayList<LogicLink>(0);
        data = new ArrayList<DataObject>(0);
        linkInfo = new ArrayList<String>(0);
        connections = new ArrayList<Integer>(0);

        codeBlocks = new ArrayList<CodeBlock>(0);


        //!-------- Begin Initialization --------!


        bounds.setSize(size, size);


        //prepare the image for JOGL
        raster =
                Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE,
                size+10,
                size+10,
                4,
                null);
        ComponentColorModel colorModel =
                new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB),
                new int[]{8, 8, 8, 8},
                true,
                false,
                ComponentColorModel.TRANSLUCENT,
                DataBuffer.TYPE_BYTE);

        //create a new buffered image with jogl specifications
        bi = new BufferedImage(colorModel,
                raster,
                false,
                null);
        g2d = bi.createGraphics();
         AffineTransform gt = new AffineTransform();
        gt.translate(0, size+10);
        
        gt.scale(1, -1d);
        g2d.transform(gt);

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);



        // a few graphics objects which are useful
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

    public String getLinkInfo(int i) {
        return this.linkInfo.get(i);
    }

    public int getLinkInfoSize() {
        return linkInfo.size();
    }

    public void setCurrentCompileString(int i) {
        this.currentCompileString = i;
    }

    public int getCurrentCompileString() {
        return currentCompileString;
    }

    public int getAmmountOfCompileString() {

        int amount = 0;
        for (int i = 0; i < this.linkInfo.size(); i++) {
            try {
                int lols = Integer.parseInt("" + linkInfo.get(i));
                if (lols > 0) {
                    amount++;
                }
            } catch (Exception e) {
            }
        }
        return amount;

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<LogicLink> getNodes() {
        return nodes;
    }

    public void addLink(LogicLink link) {
        nodes.add(link);

    }

    public void removeLink(LogicLink l) {
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

    public ArrayList<DataObject> getData() {
        return data;
    }

    public ArrayList<CodeBlock> getCodeBlocks() {
        return codeBlocks;
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
