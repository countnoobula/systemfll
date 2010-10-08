/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package VisualLogicSystem.DataBlocks;

/**
 *
 * @author Dylan
 */
public class Boolean extends DataObject{

    boolean value;

    public Boolean(String s){
        super(s);
        value = true;

    }
    public void setValue(boolean b){
        value = b;
    }
    public boolean getValue(){
        return value;
    }

}
