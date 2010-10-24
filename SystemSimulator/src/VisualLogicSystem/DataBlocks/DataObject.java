package VisualLogicSystem.DataBlocks;

/**
 * This is a generic Data Block otherwise known as a variable
 * @author Dylan
 */
public class DataObject {

    String variableType;
    String variableName;
    String value;
    String type;

    int max;
    int min;

    String selections[];

    boolean global;

    public int Controltype;

    public static int TEXTFIELD = 0;
    public static int SLIDER = 1;
    public static int YES_NO = 2;
    public static int SELECTION = 3;
    public static int CONSTRAINED_VALUE = 4;

    //created as global variables
    public DataObject(String variableType,String variableName,String value,int controlType){
          this.variableType = variableType;
          this.variableName = variableName;
          this.value = value;
          this.Controltype = controlType;
          this.global = true;
    }
    //created as global variables
    public DataObject(String value,int controlType){
          this.value = value;
          this.Controltype = controlType;
          this.global = false;
    }

    public void setControlType(int type){
        this.Controltype = type;
    }
    public int getControlType(){
        return Controltype;
    }


    public boolean isGlobal() {
        return global;
    }

    public void setGlobal(boolean global) {
        this.global = global;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public String[] getSelections() {
        return selections;
    }

    public void setSelections(String[] selections) {
        this.selections = selections;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public String getVariableType() {
        return variableType;
    }

    public void setVariableType(String variableType) {
        this.variableType = variableType;
    }


}
