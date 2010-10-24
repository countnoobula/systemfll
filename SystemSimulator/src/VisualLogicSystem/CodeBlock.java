package VisualLogicSystem;

import VisualLogicSystem.DataBlocks.DataObject;

public class CodeBlock {

    private int ID;
    private String compileCode = "";
    private LogicBlock block;
    private boolean isLocked;


    public CodeBlock(LogicBlock b) {
        this.block = b;
        this.isLocked = false;
    }
    public CodeBlock() {
        block = null;
        this.isLocked = false;
    }

    public String getCompileCode() {
        String value = compileCode;

        if(block != null){
        for (int i = 0; i < block.getData().size(); i++) {
            if(block.getData().get(i).isGlobal()){
                value = value.replace("#" + (i + 1) + "#", "" + block.getData().get(i).getVariableName());
                value = value.replace("#'" + (i + 1) + "'#", "" + block.getData().get(i).getVariableName());
                
                
            }else{
                 value = value.replace("#" + (i + 1) + "#", "" + block.getData().get(i).getValue());
                 value = value.replace("#'" + (i + 1) + "'#", "\"" + block.getData().get(i).getValue()+"\"");
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
}
