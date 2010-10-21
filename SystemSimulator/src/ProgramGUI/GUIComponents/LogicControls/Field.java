package ProgramGUI.GUIComponents.LogicControls;

import ProgramGUI.GUIComponents.Panes.NullPanel;
import ProgramGUI.GUIComponents.SystemTextField;
import VisualLogicSystem.DataBlocks.Text;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
        this.setPreferredSize(new Dimension(200, 50));

        this.add(label);
        this.add(field);
        label.setText(text.getVariableName());


        //lay out components
        label.setBounds(10, 0, 180, 22);
        field.setBounds(10,25, 180, 22);
        field.setText(text.getValue());
        label.setForeground(Color.WHITE);



    }
    public void encodeData(){
        text.setValue(field.getText());
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
