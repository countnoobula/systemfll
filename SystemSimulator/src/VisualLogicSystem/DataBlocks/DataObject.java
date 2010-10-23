package VisualLogicSystem.DataBlocks;

/**
 * This is a generic Data Block otherwise known as a variable
 * @author Dylan
 */
public abstract class DataObject {

    String variableName;
    DataObject nested;
    public DataObject(String s) {
        variableName = s;
        nested = this;
    }

    public String getVariableName() {
        return nested.variableName;
    }

    public void setVariableName(String s) {
        nested.variableName = s;
    }
    public void setDataObject(DataObject db){
        nested = db;
    }
    public abstract String getValueRepresentation();
}
