/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package VisualLogicSystem.DataBlockSystem;

import VisualLogicSystem.LogicObjects.VariableConnectionPoint;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.media.opengl.GL2;

/**
 * A link object which connects the data objects to the system
 * @author Dylan
 */
public class DataLink implements Cloneable {

    //variables
    private ArrayList<Point> points;
    private DataBlock db;
    private VariableConnectionPoint variableConnectionPoint;
    private Rectangle rect;
    private boolean rectSetFirst;

    /*
     * Constructor for link object
     */
    public DataLink() {
        points = new ArrayList<Point>(0);
    }

    public VariableConnectionPoint getVariableConnectionPoint() {
        return variableConnectionPoint;
    }

    public void setVariableConnectionPoint(VariableConnectionPoint variableConnectionPoint) {
        this.variableConnectionPoint = variableConnectionPoint;
    }
    public void createLink(){
        this.variableConnectionPoint.getLogicBlock().setDataObject(variableConnectionPoint.getData(), db.getData());
    }
    public void resetLink(){
        this.variableConnectionPoint.getLogicBlock().setDataObject(db.getData(), variableConnectionPoint.getOldData());
    }

    public DataBlock getDb() {
        return db;
    }


    public void setDb(DataBlock db) {
        this.db = db;
    }

    public void addPoint(Point p) {
        points.add(p);
    }

    public Point getPoint(int i) {
        return points.get(i);
    }
    public int getPointsSize(){
        return points.size();
    }

    public void setRect(Rectangle rect) {
        if (points.isEmpty()) {
            rectSetFirst = true;
        } else {
            rectSetFirst = false;
        }
        this.rect = rect;
    }
    public Rectangle getPointBounds(int index){
        return new Rectangle((int)points.get(index).getX()-2,(int)points.get(index).getY()-2,4,4);
    }

    public void drawGL(GL2 gl) {
        if (rect != null) {
            if (rectSetFirst & points.size() > 0) {
                gl.glBegin(GL2.GL_LINE_STRIP);

                gl.glVertex2d(rect.getX() + 5, rect.getY() + 5);
                gl.glVertex2d(points.get(0).getX(), points.get(0).getY());

                gl.glEnd();
            }
        }
        gl.glBegin(GL2.GL_LINE_STRIP);
        for (int i = 0; i < points.size(); i++) {
            gl.glVertex2d(points.get(i).getX(), points.get(i).getY());

        }
        gl.glEnd();

        
        for (int i = 0; i < points.size(); i++) {
            gl.glBegin(GL2.GL_POLYGON);
            gl.glVertex2d(points.get(i).getX()-2, points.get(i).getY()-2);
            gl.glVertex2d(points.get(i).getX()-2, points.get(i).getY()+2);
            gl.glVertex2d(points.get(i).getX()+2, points.get(i).getY()+2);
            gl.glVertex2d(points.get(i).getX()+2, points.get(i).getY()-2);
             gl.glEnd();
        }
       


        if (rect != null) {
            if (rectSetFirst == false & points.size() > 0) {
                gl.glBegin(GL2.GL_LINE_STRIP);

                gl.glVertex2d(rect.getX() + 5, rect.getY() + 5);
                gl.glVertex2d(points.get(points.size() - 1).getX(), points.get(points.size() - 1).getY());

                gl.glEnd();
            }
        }

    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
