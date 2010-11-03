
package VisualLogicSystem.LogicObjects;

import VisualLogicSystem.DataBlockSystem.DataObject;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * This a connection point object which is added to a logic block.
 * @author Dylan
 */
public class ConnectionPoint  implements Cloneable{

    private String compilerRules;
    private String description;
    private CodeBlock c;
    private Rectangle rect;
    private double colR;
    private double colG;
    private double colB;
    private int connectionRule;
    private Point p;
    /**
     * Heres what you need in order to create a connection point
     * @param compilerRules
     * You need to specify a compiler rule:
     *
     * 0,1,2,3...etc  specifies the id of the rectangle
     *
     * @param description
     *
     * The description of the connection point
     * @param c
     *
     * The code block which corresponds with the ID
     * @param rect
     *
     * The location of the rectangle
     * @param color
     * The 255,255,255 color of the rectangle
     */
    public ConnectionPoint(String compilerRules,String description,int connectionRule, CodeBlock c,Rectangle rect,Color color){
        this.compilerRules = compilerRules;
        this.description = description;
        this.c = c;
        this.rect = rect;
        this.colR = (color.getRed()/255);
        this.colG = (color.getGreen()/255);
        this.colB = (color.getBlue()/255);
        this.connectionRule = connectionRule;
        p = new Point((int)rect.getX()+5,(int)rect.getY()+5);

    }

    public int getConnectionRule() {
        return connectionRule;
    }
    
    public double getColB() {
        return colB;
    }

    public double getColG() {
        return colG;
    }

    public double getColR() {
        return colR;
    }

    public CodeBlock getC() {
        return c;
    }

    public String getCompilerRules() {
        return compilerRules;
    }

    public String getDescription() {
        return description;
    }

    public Rectangle getRect() {
        return rect;
    }

    public Point getP() {
        return p;
    }

    public void setData(ArrayList<DataObject> data){
        if(c!=null){
        this.c.setCode(data);
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        ConnectionPoint clone = (ConnectionPoint) super.clone();
        if(c!= null){
        clone.c = (CodeBlock) c.clone();
        }
        return clone;
    }





}
