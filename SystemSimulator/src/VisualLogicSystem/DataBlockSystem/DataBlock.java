package VisualLogicSystem.DataBlockSystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
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

/**
 * A data variable used in the visual logic system
 * @author Dylan
 */
public class DataBlock {

    //sizing variables
    private int x;
    private int y;
    private int width;
    private int height;

    //JOGL variables for graphics
    private BufferedImage bi;
    private Graphics2D g2d;
    private WritableRaster raster;
    private ByteBuffer dest = null;
    private Color color;
    private Font f1;

    private Point connectionPoint;

    private DataObject data;
    private ArrayList<DataLink> dataLinks;



    public DataBlock(DataObject da) {
        


        //create new instances
        f1 = new Font("Arial",11,11);
        if(da.getControlType() == DataObject.TEXTFIELD){
        color = new Color(0,192,255,180);
        }
        if(da.getControlType() == DataObject.SLIDER){
        color = new Color(255,129,0,180);
        }
        if(da.getControlType() == DataObject.YES_NO){
        color = new Color(192,255,0,180);
        }
        

        width = 50;
        height = 20;

        connectionPoint = new Point(x+(width/2),y+(height/2));
        dataLinks = new ArrayList<DataLink>();

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

        //create a new buffered image with jogl specifications (translated)
        bi = new BufferedImage(colorModel, raster, false, null);
        g2d = bi.createGraphics();
        AffineTransform gt = new AffineTransform();
        gt.translate(0, height);
        gt.scale(1, -1d);
        g2d.transform(gt);
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        setData(da);
        
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

        gl.glRasterPos2i(getX(), getY() + height);
        gl.glDrawPixels(width, height, GL2.GL_RGBA, GL2.GL_UNSIGNED_BYTE, dest);
    }

    public DataObject getData() {
        return data;
    }

    public void setData(DataObject data) {
        this.data = data;
        g2d.setPaint(color);
        g2d.fillRoundRect(0, 0, width, height,10,10);
        
        g2d.setFont(f1);
        g2d.setPaint(Color.BLACK);
        g2d.drawString(data.getVariableName(), 5, 8);
        g2d.setPaint(Color.WHITE);
        g2d.drawString(data.getValue(), 5, 18);
        GenerateGLBlock();

    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Point getConnectionPoint() {
        
        return connectionPoint;
    }
    public Rectangle getBounds(){
        return new Rectangle(x,y,width,height);
    }
    public void setLocation(int x,int y){
        this.x = x;
        this.y = y;
        this.connectionPoint.setLocation(x+(width/2),y+(height/2));

    }

    public DataLink getDataLink(int index) {
        return dataLinks.get(index);
    }

    public void addDataLink(DataLink dataLink) {
        this.dataLinks.add(dataLink);
    }
    public void removeDataLink(DataLink d){
        this.dataLinks.remove(d);
    }
    public int getDataLinkSize(){
        return dataLinks.size();
    }


}
