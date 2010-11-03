package ProgramGUI.GUIComponents.LogicControls;

import ProgramGUI.GUIComponents.Panes.NullPanel;
import ProgramGUI.GUIComponents.SystemTextField;
import VisualLogicSystem.DataBlockSystem.DataObject;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;

public class Field extends NullPanel implements ControlPoints {

    SystemTextField field;
    JLabel label;
    DataObject d;

    //this is the actual number variable


    public Field(DataObject d) {

        this.d= d;

        this.field = new SystemTextField();
        this.label = new JLabel();

        this.setLayout(null);
        this.setPreferredSize(new Dimension(200, 50));

        this.add(label);
        this.add(field);
        if(d.isGlobal()){
          label.setText(d.getVariableName());
        }

        //lay out components
        label.setBounds(10, 0, 180, 22);
        field.setBounds(10,25, 180, 22);
        field.setText(d.getValue());
        label.setForeground(Color.WHITE);



    }
    public void encodeData(){
        d.setValue(field.getText());
    }


}
