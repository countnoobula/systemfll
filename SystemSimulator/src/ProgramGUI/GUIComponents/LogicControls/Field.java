package ProgramGUI.GUIComponents.LogicControls;

import ProgramGUI.GUIComponents.NullPanel;
import ProgramGUI.GUIComponents.SystemSlider;
import ProgramGUI.GUIComponents.SystemTextField;
import VisualLogicSystem.DataBlocks.Text;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

public class Field extends NullPanel implements ControlPoints {

    SystemTextField field;
    JLabel label;

    //this is the actual number variable
    Text text;

    public Field(Text t) {

        text = t;

        this.field = new SystemTextField();
        this.label = new JLabel();

        this.setLayout(null);
        this.setPreferredSize(new Dimension(400, 34));

        this.add(label);
        this.add(field);
        label.setText(text.getVariableName());


        //lay out components
        label.setBounds(245, 10, 140, 22);
        field.setBounds(10,10, 225, 22);
        field.setText(text.getValue());

        label.setForeground(Color.WHITE);



    }


    public Object getData() {
        return null;
    }

    public void setData(Object ob) {
    }

    public static void main(String[] args) {


        Text n = new  Text("lols","lolol");
        Text n2 = new  Text("Something else","hahhahaha");
        Field s = new Field(n);
        Field s2 = new Field(n2);
        JFrame f = new JFrame();

        f.add(s);
        f.add(s2);
        f.setLayout(new FlowLayout());
        f.setBackground(new Color(40, 40, 40));
        f.setBounds(10, 10, 500, 300);

        f.setVisible(true);

    }
}
