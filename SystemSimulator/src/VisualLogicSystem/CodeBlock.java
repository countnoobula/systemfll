package VisualLogicSystem;

import VisualLogicSystem.DataBlocks.Text;

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
            if (block.getData().get(i) instanceof Text) {
                value = value.replace("#" + (i + 1) + "#", "" + ((Text) block.getData().get(i)).getValue());
            } else if (block.getData().get(i) instanceof VisualLogicSystem.DataBlocks.Number) {
                value = value.replace("#" + (i + 1) + "#", "" + ((VisualLogicSystem.DataBlocks.Number) block.getData().get(i)).getValue());
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
