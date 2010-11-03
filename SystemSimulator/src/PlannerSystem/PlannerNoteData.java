package PlannerSystem;

import java.io.Serializable;

/**
 * This is a sticky note object which stores note data for the PlannerNote object
 */
public class PlannerNoteData implements Serializable {
    
    private String data;
    private String title;
    private int x;
    private int y;
    private int width;
    private int height;
    
    public PlannerNoteData(){
      data = "";
      title = "";
      x = 0;
      y = 0;
      width = 200;
      height = 200;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }
}
