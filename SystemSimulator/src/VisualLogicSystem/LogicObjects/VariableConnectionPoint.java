/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package VisualLogicSystem.LogicObjects;

import VisualLogicSystem.DataBlockSystem.DataObject;
import VisualLogicSystem.LogicObjects.LogicBlock;
import java.awt.Color;
import java.awt.Rectangle;
import java.io.Serializable;

/**
 *
 * @author Dylan
 */
public class VariableConnectionPoint  implements Cloneable,Serializable {

    //variables
    String name;
    DataObject data1;
    DataObject data2;
    Rectangle rect;
    Rectangle realRect;
    double colR;
    double colG;
    double colB;
    boolean input;
    LogicBlock logicBlock;


    public VariableConnectionPoint(LogicBlock l,DataObject data,String name,Color color,boolean input){
        this.data1 = data;
        this.data2 = data;

        this.name = name;
        this.input = input;
 
        this.logicBlock = l;

        //generate a color object
        this.colR = (color.getRed()/255);
        this.colG = (color.getGreen()/255);
        this.colB = (color.getBlue()/255);
    }

    public LogicBlock getLogicBlock() {
        return logicBlock;
    }



    public boolean isInput() {
        return input;
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

    public DataObject getData() {
          return data1;   
    }
    public DataObject getOldData(){
        return data2;
    }

    public String getName() {
        return name;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    public Rectangle getRealRect() {
        return realRect;
    }

    public void setRealRect(Rectangle realRect) {
        this.realRect = realRect;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        VariableConnectionPoint clone = (VariableConnectionPoint) super.clone();
        clone.realRect = (Rectangle) realRect.clone();
        clone.rect = (Rectangle) rect.clone();
        return clone;
    }
    public void setData(LogicBlock l,DataObject db){
        this.data1 = db;
        this.data2 = db;
        this.logicBlock = l;
    }

}
