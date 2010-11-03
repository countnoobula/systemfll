package VisualLogicSystem.LogicObjects;

import VisualLogicSystem.LogicObjects.LogicBlock;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;

import java.awt.geom.Ellipse2D;

import java.util.ArrayList;

import java.awt.Polygon;
import java.io.Serializable;
import javax.media.opengl.GL2;

public class LogicLink implements Cloneable {

    ArrayList<Point> points;
    LogicBlock start;
    LogicBlock end;
    int startConnection;
    int endConnection;

    public int getEndConnection() {
        return endConnection;
    }

    public LogicBlock getEnd() {
        return end;
    }

    public LogicLink() {

        //create the virtual graphic objects
        points = new ArrayList<Point>(0);

    }

    public void setStartBlock(LogicBlock begin, int connection) {
        start = begin;
        startConnection = connection;

    }

    public void setEndBlock(LogicBlock end, int connection) {
        this.end = end;
        endConnection = connection;


    }

    public void addPoint(Point p) {
        points.add(p);
    }

    public void addPoint(double x, double y) {
        points.add(new Point((int) x, (int) y));
    }

    public int getAmountOfAnchors() {
        return points.size();
    }

    public void setPoint(int id, Point p) {
        points.set(id, p);

    }

    public void removeAnchor(int i) {
        points.remove(i);
    }

    public Shape getVirtualAnchor(int i) {
        return new Ellipse2D.Double(points.get(i).getX() - 5,
                points.get(i).getY() - 5, 10, 10);
    }

    public boolean getLineBoundaries(int x1, int y1) {
        int size = 10;
        int min = (int) (size / 2);

        if (points.size() == 0) {
            //Insert 1 between the 2 logic blocks
            if (lineRectangleIntersect((int) start.getConnectionBoundReal(startConnection).getCenterX(),
                    (int) start.getConnectionBoundReal(startConnection).getCenterY(),
                    (int) end.getConnectionBoundReal(endConnection).getCenterX(),
                    (int) end.getConnectionBoundReal(endConnection).getCenterY(),
                    x1 - min, y1 - min, size, size)
                    == true) {

                points.add(new Point(x1, y1));
                return true;

            }
        } else {
            //Insert 1 at the beginning
            if (lineRectangleIntersect((int) start.getConnectionBoundReal(startConnection).getCenterX(),
                    (int) start.getConnectionBoundReal(startConnection).getCenterY(),
                    (int) points.get(0).getX(),
                    (int) points.get(0).getY(), x1 - min,
                    y1 - min, size, size) == true) {

                points.add(0, new Point(x1, y1));
                return true;

            } //Insert 1 at the end
            else if (lineRectangleIntersect((int) end.getConnectionBoundReal(endConnection).getCenterX(),
                    (int) end.getConnectionBoundReal(endConnection).getCenterY(),
                    (int) points.get(points.size()
                    - 1).getX(),
                    (int) points.get(points.size()
                    - 1).getY(),
                    x1 - min, y1 - min, size, size)
                    == true) {

                points.add(new Point(x1, y1));
                return true;
            }
            //haha, so it is one in the middle
            for (int i = 0; i < points.size() - 1; i++) {
                //Insert 1 at the beginning
                if (lineRectangleIntersect((int) points.get(i).getX(),
                        (int) points.get(i).getY(),
                        (int) points.get(i + 1).getX(),
                        (int) points.get(i + 1).getY(),
                        x1 - min, y1 - min, size, size)
                        == true) {

                    points.add(i + 1, new Point(x1, y1));
                    return true;

                }
            }


        }


        return false;
    }

    // Code from Seb Lee-Delisle:
    // http://sebleedelisle.com/2009/05/super-fast-trianglerectangle-intersection-test/
    boolean lineRectangleIntersect(float x1, float y1, float x2, float y2,
            float rx, float ry, float rw, float rh) {

        float topIntersection;
        float bottomIntersection;
        float topPoint;
        float bottomPoint;

        // Calculate m and c for the equation for the line (y = mx+c)
        float m = (y2 - y1) / (x2 - x1);
        float c = y1 - (m * x1);

        // If the line is going up from right to left then the top intersect point is on the left
        if (m > 0) {
            topIntersection = (m * rx + c);
            bottomIntersection = (m * (rx + rw) + c);
        } // Otherwise it's on the right
        else {
            topIntersection = (m * (rx + rw) + c);
            bottomIntersection = (m * rx + c);
        }

        // Work out the top and bottom extents for the triangle
        if (y1 < y2) {
            topPoint = y1;
            bottomPoint = y2;
        } else {
            topPoint = y2;
            bottomPoint = y1;
        }

        float topOverlap;
        float botOverlap;

        // Calculate the overlap between those two bounds
        topOverlap = topIntersection > topPoint ? topIntersection : topPoint;
        botOverlap =
                bottomIntersection < bottomPoint ? bottomIntersection : bottomPoint;

        return (topOverlap < botOverlap)
                && (!((botOverlap < ry) || (topOverlap > ry + rh)));

    }

    public void drawAnchors(GL2 gl) {
        gl.glColor4d(0.0, 0.0, 0.0, 0.7);
        gl.glLineWidth(3.0f);
        for (int i = 0; i < points.size(); i++) {
            gl.glBegin(GL2.GL_POLYGON);
            gl.glVertex2d((int) points.get(i).getX() - 5,
                    (int) points.get(i).getY() - 5);
            gl.glVertex2d((int) points.get(i).getX() + 5,
                    (int) points.get(i).getY() - 5);
            gl.glVertex2d((int) points.get(i).getX() + 5,
                    (int) points.get(i).getY() + 5);
            gl.glVertex2d((int) points.get(i).getX() - 5,
                    (int) points.get(i).getY() + 5);
            gl.glEnd();
        }

        gl.glLineWidth(1.0f);
        gl.glColor4d(1.0, 1.0, 1.0, 0.3);

        for (int i = 0; i < points.size(); i++) {
            gl.glBegin(GL2.GL_LINE_LOOP);
            gl.glVertex2d((int) points.get(i).getX() - 5,
                    (int) points.get(i).getY() - 5);
            gl.glVertex2d((int) points.get(i).getX() + 5,
                    (int) points.get(i).getY() - 5);
            gl.glVertex2d((int) points.get(i).getX() + 5,
                    (int) points.get(i).getY() + 5);
            gl.glVertex2d((int) points.get(i).getX() - 5,
                    (int) points.get(i).getY() + 5);
            gl.glEnd();
        }

       
    }

    public void drawGL(GL2 gl) {
        
        gl.glBegin(GL2.GL_LINES);
        gl.glColor3d(0, 0.75, 1.0);
        if (points.size() > 0) {
            
            gl.glVertex2d(start.getConnectionBoundReal(startConnection).getCenterX(),
                    start.getConnectionBoundReal(startConnection).getCenterY());
            
            gl.glVertex2d(points.get(0).getX(), points.get(0).getY());
        }
        for (int i = 1; i < points.size(); i++) {
            
            gl.glVertex2d(points.get(i - 1).getX(),
                    points.get(i - 1).getY());
            
            gl.glVertex2d(points.get(i).getX(), points.get(i).getY());

        }
        if (end != null & points.size() > 0) {
            gl.glVertex2d(end.getConnectionBoundReal(endConnection).getCenterX(),
                    end.getConnectionBoundReal(endConnection).getCenterY());
            gl.glVertex2d(points.get(points.size() - 1).getX(),
                    points.get(points.size() - 1).getY());



        } else if (end != null & points.size() == 0) {
            gl.glVertex2d(start.getConnectionBoundReal(startConnection).getCenterX(),
                    start.getConnectionBoundReal(startConnection).getCenterY());
            gl.glVertex2d(end.getConnectionBoundReal(endConnection).getCenterX(),
                    end.getConnectionBoundReal(endConnection).getCenterY());

        }
        gl.glEnd();
    }

    /**
     * Paints the links
     * @param g2d
     */
    public void paintLinks(Graphics2D g2d) {

        if (points.size() > 0) {
            g2d.drawLine((int) start.getConnectionBoundReal(startConnection).getCenterX(),
                    (int) start.getConnectionBoundReal(startConnection).getCenterY(),
                    (int) points.get(0).getX(), (int) points.get(0).getY());

        }
        for (int i = 1; i < points.size(); i++) {
            g2d.drawLine((int) points.get(i - 1).getX(),
                    (int) points.get(i - 1).getY(),
                    (int) points.get(i).getX(), (int) points.get(i).getY());
        }

        if (end != null & points.size() > 0) {
            g2d.drawLine((int) end.getConnectionBoundReal(endConnection).getCenterX(),
                    (int) end.getConnectionBoundReal(endConnection).getCenterY(),
                    (int) points.get(points.size() - 1).getX(),
                    (int) points.get(points.size() - 1).getY());

        } else if (end != null & points.size() == 0) {
            g2d.drawLine((int) start.getConnectionBoundReal(startConnection).getCenterX(),
                    (int) start.getConnectionBoundReal(startConnection).getCenterY(),
                    (int) end.getConnectionBoundReal(endConnection).getCenterX(),
                    (int) end.getConnectionBoundReal(endConnection).getCenterY());

        }

    }

    public boolean killTheLink(Point p, int size) {
        int min = (int) (size / 2);
        for (int i = 0; i < points.size() - 1; i++) {
            //Insert 1 at the beginning
            if (lineRectangleIntersect((int) points.get(i).getX(),
                    (int) points.get(i).getY(),
                    (int) points.get(i + 1).getX(),
                    (int) points.get(i + 1).getY(),
                    (float) p.getX() - min, (float) p.getY() - min,
                    size, size)
                    == true) {

                return true;

            }
        }
        return false;
    }

    public void removeBlock(LogicBlock b) {
        if (b.equals(start)) {
            start = null;

        } else if (b.equals(end)) {
            end = null;
        }


    }

    public void paintAnchors(Graphics2D g2d) {

        for (int i = 0; i < points.size(); i++) {
            g2d.fillOval((int) points.get(i).getX() - 5,
                    (int) points.get(i).getY() - 5, 10, 10);
        }


    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public LogicBlock getStart() {
        return start;
    }

    public int getStartConnection() {
        return startConnection;
    }
}
