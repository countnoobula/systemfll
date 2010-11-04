package VisualLogicSystem.LogicObjects;

import VisualLogicSystem.DataBlockSystem.DataObject;
import java.io.Serializable;
import java.util.ArrayList;

public class CodeBlock implements Cloneable,Serializable {

    private int ID;
    private String compileCode = "";
    private ArrayList<DataObject> block;
    private boolean isLocked;


    public CodeBlock(ArrayList<DataObject> d) {
        this.block = d;
        this.isLocked = false;
    }
    public void setCode(ArrayList<DataObject> d){
        this.block = d;
    }
    public CodeBlock() {
        block = null;
        this.isLocked = false;
    }

    public String getCompileCode() {
        String value = compileCode;

        if(block != null){
        for (int i = 0; i < block.size(); i++) {
            if(block.get(i).isGlobal()){
                value = value.replace("#" + (i + 1) + "#", "" + block.get(i).getVariableName());
                value = value.replace("#'" + (i + 1) + "'#", "" + block.get(i).getVariableName());
                
                
            }else{
                 value = value.replace("#" + (i + 1) + "#", "" + block.get(i).getValue());
                 value = value.replace("#'" + (i + 1) + "'#", "\"" + block.get(i).getValue()+"\"");
            }           
        }
        }
        return "\n"+value;
    }

    public int getID() {
        return ID;
    }

    public void setCompileCode(String compileCode) {

        this.compileCode = compileCode;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    public void addClusterCode(String cluster[]){
        for(int i = 0;i < cluster.length;i++){
           compileCode += cluster[i]+"\n";
        }
    }
    public void addVariableCode(DataObject db){
           compileCode += db.getVariableType()+" "+db.getVariableName() +" = "+db.getValue()+";\n";
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        CodeBlock clone = (CodeBlock) super.clone();
        return clone;
    }




}
