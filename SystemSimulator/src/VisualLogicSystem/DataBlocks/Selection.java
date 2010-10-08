package VisualLogicSystem.DataBlocks;

/**
 * This is a selection object which lists the various selections
 * @author Dylan
 */
public class Selection  extends DataObject{

    String select[];
    int selected;
    public Selection(String s,String selection[],int selected){
        super(s);
        select = selection;
        this.selected = selected;
    }


    public String[] getSelect() {
        return select;
    }

    public void setSelect(String[] select) {
        this.select = select;
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }



}
