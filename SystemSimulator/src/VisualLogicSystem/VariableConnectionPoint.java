/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package VisualLogicSystem;

import VisualLogicSystem.DataBlocks.DataObject;
import java.awt.Color;
import java.awt.Rectangle;

/**
 *
 * @author Dylan
 */
public class VariableConnectionPoint {

    //variables
    String name;
    DataObject data;
    Rectangle rect;
    Rectangle realRect;
    double colR;
    double colG;
    double colB;
    boolean input;
    boolean active;
    LogicBlock logicBlock;


    public VariableConnectionPoint(LogicBlock l,DataObject data,String name,Color color,boolean input){
        this.data = data;
        this.name = name;
        this.input = input;
        this.active = false;
        this.logicBlock = l;

        //generate a color object
        this.colR = (color.getRed()/255);
        this.colG = (color.getGreen()/255);
        this.colB = (color.getBlue()/255);
    }
    public void setDataObject(DataObject db){
        data= db;
    }

    public boolean isActive() {
        return active;
    }

    public LogicBlock getLogicBlock() {
        return logicBlock;
    }


    public void setActive(boolean active) {
        this.active = active;
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
        return data;
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







}
