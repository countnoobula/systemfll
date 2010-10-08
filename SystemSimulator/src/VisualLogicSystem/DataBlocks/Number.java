package VisualLogicSystem.DataBlocks;

/**
 * This a number value which defines a maximum and a minimum value
 * @author Dylan
 */
public class Number extends DataObject{

    //maximum and minimum values
    int min;
    int max;

    //the actual value
    int value;

    public Number(String s,int value,int min,int max){
        super(s);

        //set the values
        this.value = value;
        this.min = min;
        this.max = max;
      
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }



}
