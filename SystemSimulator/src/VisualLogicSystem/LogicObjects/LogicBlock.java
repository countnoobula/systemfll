package VisualLogicSystem.LogicObjects;

//imports
import ProgramGUI.GUIComponents.BlockVariablePane;
import VisualLogicSystem.DataBlockSystem.DataObject;
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
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import javax.media.opengl.GL2;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicButtonUI;

public class LogicBlock implements Cloneable, Serializable {

    //methods are public for the XML utilites
    public Paint gp1, gp2, gp3, gp4, gp5;
    public Graphics2D g2d;
    public Rectangle bounds;
    public BufferedImage bi;
    public WritableRaster raster;
    public ByteBuffer dest = null;
    public int width = 50;
    public int height = 50;
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
    public String imports[];
    public BufferedImage bi2;
    public String title;
    public String description;

    public LogicBlock() {


        bounds = new Rectangle();
        //create the data objects
        nodes = new ArrayList<LogicLink>(0);
        data = new ArrayList<DataObject>(0);
        variablePoints = new ArrayList<VariableConnectionPoint>(0);
        connectionPoints = new ArrayList<ConnectionPoint>(0);

        rectUp = new Rectangle(0, -15, 2, 13);
        rectBot = new Rectangle(0, height + 2, 2, 13);

        //!-------- Begin Initialization --------!


        bounds.setSize(width, height);



    }


    /**
     * Draws the Block in OpenGL
     * @param gl the GL context
     */
    public void drawGL(GL2 gl) {

        gl.glBegin(GL2.GL_POLYGON);

        gl.glColor4d(0.1, 0.1, 0.1, 0.0);


        gl.glColor4d(0.1, 0.1, 0.1, 0.6);
        gl.glVertex2d(getX() + width, getY() + 4);
        gl.glVertex2d(getX() + width, getY() + height);
        gl.glVertex2d(getX() + 4, getY() + height);
        gl.glVertex2d(getX() + 4, getY() + height + 4);
        gl.glVertex2d(getX() + width, getY() + height + 4);
        gl.glVertex2d(getX() + width+3, getY() + height + 3);
        gl.glVertex2d(getX() + width + 4, getY() + height);
        gl.glVertex2d(getX() + width + 4, getY() + 4);

        gl.glEnd();

        gl.glRasterPos2i(getX(), getY() + height);
        gl.glDrawPixels(width, height, GL2.GL_RGBA, GL2.GL_UNSIGNED_BYTE, dest);


        if (rectUp.getWidth() > 2) {
            //draw input blocks
            gl.glColor3d(0.1, 0.1, 0.1);
            gl.glBegin(GL2.GL_POLYGON);
            gl.glVertex2d(rectUp.getX(), rectUp.getY());
            gl.glVertex2d(rectUp.getX() + rectUp.getWidth(), rectUp.getY());

            gl.glVertex2d(rectUp.getX() + rectUp.getWidth(), rectUp.getY() + rectUp.getHeight());
            gl.glVertex2d(rectUp.getX(), rectUp.getY() + rectUp.getHeight());
            gl.glEnd();
        }
        if (rectBot.getWidth() > 2) {
            //draw output blocks
            gl.glColor3d(0.1, 0.1, 0.1);
            gl.glBegin(GL2.GL_POLYGON);
            gl.glVertex2d(rectBot.getX(), rectBot.getY());
            gl.glVertex2d(rectBot.getX() + rectBot.getWidth(), rectBot.getY());

            gl.glVertex2d(rectBot.getX() + rectBot.getWidth(), rectBot.getY() + rectBot.getHeight());
            gl.glVertex2d(rectBot.getX(), rectBot.getY() + rectBot.getHeight());
            gl.glEnd();
        }


        for (int i = 0; i < getVariablePointsSize(); i++) {
            //drawFlashingblocks
            
            gl.glBegin(GL2.GL_POLYGON);
            Rectangle rect = getVariableConnectRect(i);
            gl.glColor3d(0.0, 0.4, 1.0);
            gl.glVertex2d(rect.getX(), rect.getY());
            gl.glColor3d(0.0, 0.75, 1.0);
            gl.glVertex2d(rect.getX() + rect.getWidth(), rect.getY());
            gl.glVertex2d(rect.getX() + rect.getWidth(), rect.getY() + rect.getHeight());
            gl.glVertex2d(rect.getX(), rect.getY() + rect.getHeight());
            gl.glEnd();
        }
    }

    public void GenerateGLBlock() {


        //the buffer array of bytes
        DataBufferByte dukeBuf = (DataBufferByte) raster.getDataBuffer();
        byte[] bufferData = dukeBuf.getData();


        dest = ByteBuffer.allocateDirect(bufferData.length);
        dest.order(ByteOrder.nativeOrder());
        dest.put(bufferData, 0, bufferData.length);
        dest.rewind(); // <- NEW STUFF NEEDED BY JSR231
    }

    public void addVariableConnectionPoint(VariableConnectionPoint v) {
        variablePoints.add(v);
        if (v.isInput()) {
            rectUp.setSize((int) (rectUp.getWidth() + 12), 13);
            v.setRect(new Rectangle((int) (rectUp.getWidth() - 12), -15, 10, 10));
            v.setRealRect(new Rectangle((int) (rectUp.getWidth() - 12), -15, 10, 10));
        } else {
            rectBot.setSize((int) (rectBot.getWidth() + 12), 13);
            v.setRect(new Rectangle((int) (rectBot.getWidth() - 12), height + 4, 10, 10));
            v.setRealRect(new Rectangle((int) (rectBot.getWidth() - 12), height + 4, 10, 10));
        }
    }
    //!-------- The button class --------------

    private class LogicButtonUI extends BasicButtonUI {

        @Override
        public void paint(Graphics g, JComponent c) {
            g.drawImage(bi2, 0, 0, null);
        }
    }

    public Image getButton() {

        bi2 = new BufferedImage(60, 60, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g23 = bi2.createGraphics();
        AffineTransform gt = new AffineTransform();
        gt.translate(0, height);


        gt.scale(1, -1d);
        g23.transform(gt);
        g23.drawImage(bi, 0, 0, null);

        return bi2;
    }

    public void addConnectionPoint(ConnectionPoint cp) {
        this.connectionPoints.add(cp);
    }

    public void addDataObject(DataObject db) {
        this.data.add(db);
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setImports(String imports[]) {
        this.imports = imports;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
        //prepare the image for JOGL
        raster =
                Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE,
                width,
                height,
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
        gt.translate(0, height);

        gt.scale(1, -1d);
        g2d.transform(gt);

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
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
        rectBot.setLocation(x, (y + height + 2));
        for (int i = 0; i < variablePoints.size(); i++) {
            variablePoints.get(i).getRealRect().setLocation(
                    (int) variablePoints.get(i).getRect().getX() + x,
                    (int) variablePoints.get(i).getRect().getY() + y);
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

    public int getAmountBounds() {
        return this.connectionPoints.size();
    }

    public Image getGraphicsObject() {
        return bi;
    }

    public int getVariablePointsSize() {
        return this.variablePoints.size();
    }

    public VariableConnectionPoint getVariableConnectionPoint(int index) {
        return this.variablePoints.get(index);
    }

    public Rectangle getVariableConnectRect(int index) {
        return variablePoints.get(index).getRealRect();
    }

    public void setDataObject(DataObject olddb, DataObject newdb) {
        this.data.set(data.indexOf(olddb), newdb);
    }

    public String[] getImports() {
        return imports;
    }

    public void setImage(Image i) {
        g2d.drawImage(i, 0, 0, null);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    @SuppressWarnings("unchecked")
    public Object clone() throws CloneNotSupportedException {

        LogicBlock clone = (LogicBlock) super.clone();
        clone.bounds = (Rectangle) bounds.clone();
        clone.rectUp = (Rectangle) rectUp.clone();

        ArrayList<DataObject> clone2 = new ArrayList<DataObject>();
        for (int i = 0; i < this.data.size(); i++) {
            clone2.add(this.data.get(i));

        }

        ArrayList<ConnectionPoint> clone3 = new ArrayList<ConnectionPoint>();
        for (int i = 0; i < this.connectionPoints.size(); i++) {
            clone.connectionPoints.get(i).setData(clone2);
            clone3.add((ConnectionPoint) this.connectionPoints.get(i).clone());

        }

        ArrayList<VariableConnectionPoint> clone1 = new ArrayList<VariableConnectionPoint>();
        for (int i = 0; i < this.variablePoints.size(); i++) {
            clone1.add((VariableConnectionPoint) this.variablePoints.get(i).clone());
            clone1.get(i).setData(clone, clone2.get(i));
        }

        clone.data = clone2;
        clone.variablePoints = clone1;
        clone.connectionPoints = clone3;

        clone.nodes = new ArrayList<LogicLink>();
        clone.rectBot = (Rectangle) rectBot.clone();
        clone.rectUp = (Rectangle) rectUp.clone();


        return clone;
    }
}
