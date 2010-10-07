package ProgramGUI.GUIComponents.LogicControls;

import ProgramGUI.GUIComponents.NullPanel;
import ProgramGUI.GUIComponents.SystemSlider;

import java.awt.Dimension;

public class Slider extends NullPanel implements ControlPoints {

    SystemSlider slider;

    public Slider() {

        this.setLayout(null);

        this.slider = new SystemSlider();
        this.setOpaque(false);

        this.setPreferredSize(new Dimension(400, 60));

    }


    public Object getData() {
        return null;
    }

    public void setData(Object ob) {

    }
}
