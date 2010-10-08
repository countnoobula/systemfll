package VisualLogicSystem.DataBlocks;

/**
 * This is a generic Data Block otherwise known as a variable
 * @author Dylan
 */
public class DataObject {

    String variableName;

    public DataObject(String s) {
        variableName = s;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String s) {
        variableName = s;
    }
}
