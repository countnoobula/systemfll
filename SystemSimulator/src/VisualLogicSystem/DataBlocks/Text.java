
package VisualLogicSystem.DataBlocks;

/**
 *
 * @author Dylan
 */
public class Text extends DataObject {

    String value;
    public Text(String variableName,String value){
        super(variableName);
        this.value = value;
    }
    public String getValue(){
        return value;
    }
    public void setValue(String value){
        this.value = value;
    }

}
