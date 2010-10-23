package VisualLogicSystem;

//imports
import ProgramGUI.GUIComponents.BlockVariablePane;
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

    public Paint gp1, gp2, gp3, gp4, gp5;
    public Graphics2D g2d;
    public Rectangle bounds;
    public BufferedImage bi;
    public WritableRaster raster;
    public ByteBuffer dest = null;
    public int size = 50;
    public String type = "";
    public boolean displaySensorRack = false;
    public int currentCompileString = 0;
    //ID data
    public int ID;
    public ArrayList<LogicLink> nodes;
    public ArrayList<DataObject> data;
    public ArrayList<VariableConnectionPoint> variablePoints;
    public ArrayList<ConnectionPoint> connectionPoints;
    public Rectangle rectUp, rectBot;

    public LogicBlock() {


        bounds = new Rectangle();


        //create the data objects
        nodes = new ArrayList<LogicLink>(0);
        data = new ArrayList<DataObject>(0);
        variablePoints = new ArrayList<VariableConnectionPoint>(0);
        connectionPoints = new ArrayList<ConnectionPoint>(0);

        rectUp = new Rectangle(0, -15, 2, 13);
        rectBot = new Rectangle(0, size + 2, 2, 13);

        //!-------- Begin Initialization --------!


        bounds.setSize(size, size);


        //prepare the image for JOGL
        raster =
                Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE,
                size + 10,
                size + 10,
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
        gt.translate(0, size + 10);

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

    public void addVariableConnectionPoint(VariableConnectionPoint v) {
        variablePoints.add(v);
        if (v.isInput()) {
            rectUp.setSize((int) (rectUp.getWidth() + 12), 13);
            v.setRect(new Rectangle((int) (rectUp.getWidth() - 12), -15, 10, 10));
            v.setRealRect(new Rectangle((int) (rectUp.getWidth() - 12), -15, 10, 10));
        } else {
            rectBot.setSize((int) (rectBot.getWidth() + 12), 13);
            v.setRect(new Rectangle((int) (rectBot.getWidth() - 12), size + 4, 10, 10));
            v.setRealRect(new Rectangle((int) (rectBot.getWidth() - 12), size + 4, 10, 10));
        }
    }

    /**
     * Draws the Block in OpenGL
     * @param gl the GL context
     */
    public void drawGL(GL2 gl) {

        gl.glRasterPos2i(getX(), getY() + size + 10);
        gl.glDrawPixels(size + 10, size + 10, GL2.GL_RGBA, GL2.GL_UNSIGNED_BYTE, dest);



        if (rectUp.getWidth() > 2) {
            //draw input blocks
            gl.glColor3d(0.2, 0.2, 0.2);
            gl.glBegin(GL2.GL_POLYGON);
            gl.glVertex2d(rectUp.getX(), rectUp.getY());
            gl.glVertex2d(rectUp.getX() + rectUp.getWidth(), rectUp.getY());
            gl.glColor3d(0.4, 0.4, 0.4);
            gl.glVertex2d(rectUp.getX() + rectUp.getWidth(), rectUp.getY() + rectUp.getHeight());
            gl.glVertex2d(rectUp.getX(), rectUp.getY() + rectUp.getHeight());
            gl.glEnd();
        }
        if (rectBot.getWidth() > 2) {
            //draw output blocks
            gl.glColor3d(0.4, 0.4, 0.4);
            gl.glBegin(GL2.GL_POLYGON);
            gl.glVertex2d(rectBot.getX(), rectBot.getY());
            gl.glVertex2d(rectBot.getX() + rectBot.getWidth(), rectBot.getY());
            gl.glColor3d(0.2, 0.2, 0.2);
            gl.glVertex2d(rectBot.getX() + rectBot.getWidth(), rectBot.getY() + rectBot.getHeight());
            gl.glVertex2d(rectBot.getX(), rectBot.getY() + rectBot.getHeight());
            gl.glEnd();
        }


        for (int i = 0; i < getVariablePointsSize(); i++) {
            //drawFlashingblocks
            gl.glColor3d(0.0, 0.75, 1.0);
            gl.glBegin(GL2.GL_POLYGON);
            Rectangle rect = getVariableConnectRect(i);
            gl.glVertex2d(rect.getX(), rect.getY());
            gl.glVertex2d(rect.getX() + rect.getWidth(), rect.getY());
            gl.glVertex2d(rect.getX() + rect.getWidth(), rect.getY() + rect.getHeight());
            gl.glVertex2d(rect.getX(), rect.getY() + rect.getHeight());
            gl.glEnd();
        }
    }

    //!---------- Methods for generating a LogicBlock ---------!
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

    public void GenerateGLBlock() {


        //the buffer array of bytes
        DataBufferByte dukeBuf = (DataBufferByte) raster.getDataBuffer();
        byte[] data = dukeBuf.getData();


        dest = ByteBuffer.allocateDirect(data.length);
        dest.order(ByteOrder.nativeOrder());
        dest.put(data, 0, data.length);
        dest.rewind(); // <- NEW STUFF NEEDED BY JSR231
    }

    public int getID() {
        return ID;
    }

    public ConnectionPoint getConnectionPoint(int i) {
        return this.connectionPoints.get(i);
    }
    public String compileProperty = "";

    public String getCompileProperty() {
        return compileProperty;
    }

    public void setCompileProperty(String compileProperty) {
        this.compileProperty = compileProperty;
    }

    public String getLinkInfo(int i) {
        return this.connectionPoints.get(i).getCompilerRules();
    }

    public int getLinkInfoSize() {
        return this.connectionPoints.size();
    }

    public void setCurrentCompileString(int i) {
        this.currentCompileString = i;
    }

    public int getCurrentCompileString() {
        return currentCompileString;
    }

    public int getAmmountOfCompileString() {

        int amount = 0;
        for (int i = 0; i < this.connectionPoints.size(); i++) {
            try {
                int lols = Integer.parseInt("" + this.connectionPoints.get(i).getCompilerRules());
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

    public BlockVariablePane getProperties() {
        return new BlockVariablePane(this);
    }

    public LogicLink getLinks(int index) {
        return nodes.get(index);
    }

    public int getLinksArraySize() {
        return nodes.size();
    }

    public int getConnectionRules(int i) {
        return this.connectionPoints.get(i).getConnectionRule();
    }

    public ArrayList<DataObject> getData() {
        return data;
    }

    public void setLocation(int x, int y) {

        this.bounds.setLocation(x, y);
        rectUp.setLocation(x, (y - 15));
        rectBot.setLocation(x, (y + size + 2));
        for(int i = 0;i < variablePoints.size();i++){
            variablePoints.get(i).getRealRect().setLocation(
                    (int)variablePoints.get(i).getRect().getX()+x,
                    (int)variablePoints.get(i).getRect().getY()+y);
        }
        

    }

    public Rectangle getConnectionBoundReal(int index) {
        return new Rectangle((int) (this.connectionPoints.get(index).getRect().getX() + bounds.getX()),
                (int) (this.connectionPoints.get(index).getRect().getY() + bounds.getY()),
                (int) (this.connectionPoints.get(index).getRect().getWidth()),
                (int) (this.connectionPoints.get(index).getRect().getHeight()));
    }

    public Rectangle getConnectionBound(int index) {
        return this.connectionPoints.get(index).getRect();
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

    //!-------- The button class --------------
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
        return this.connectionPoints.size();
    }

    public Image getGraphicsObject() {
        return bi;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public int getVariablePointsSize() {
        return this.variablePoints.size();
    }
    public VariableConnectionPoint getVariableConnectionPoint(int index){
        return this.variablePoints.get(index);
    }

    public Rectangle getVariableConnectRect(int index) {
        return variablePoints.get(index).getRealRect();
    }
    public void setDataObject(DataObject olddb,DataObject newdb ){
        this.data.set(data.indexOf(olddb),newdb );
    }
}
