package VisualLogicSystem;

public class CodeBlock {

    private int ID;
    private String compileCode = "";
    private LogicBlock block;
    private boolean isLocked;

    public CodeBlock(LogicBlock b) {
        this.block = b;
        this.isLocked = false;
    }

    public String getCompileCode() {
        String value = compileCode;

        for (int i = 0; i < block.getData().size(); i++) {
            if(block.getData().get(i).isGlobal()){
                value = value.replace("#" + (i + 1) + "#", "" + block.getData().get(i).getVariableName());
            }else{
                 value = value.replace("#" + (i + 1) + "#", "" + block.getData().get(i).getValue());
            }           
        }
        return value;
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
}
