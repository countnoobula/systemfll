package ProgramGUI.GUIComponents.LogicControls;

import ProgramGUI.GUIComponents.Panes.NullPanel;
import ProgramGUI.GUIComponents.SystemSlider;
import ProgramGUI.GUIComponents.SystemTextField;
import VisualLogicSystem.DataObject;
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

public class Slider extends NullPanel implements ControlPoints {

    SystemSlider slider;
    JLabel label;
    SystemTextField field;
    DataObject d;

    public Slider(DataObject d) {

        this.d = d;

        this.slider = new SystemSlider();
        this.label = new JLabel();
        this.field = new SystemTextField();

        this.setLayout(null);
        this.setPreferredSize(new Dimension(200, 50));

        this.add(slider);
        this.add(label);
        this.add(field);

        slider.setMinimum(d.getMin());
        slider.setMaximum(d.getMax());
        if(d.isGlobal()){
        label.setText(d.getVariableName());
        }
        
        slider.setValue(Integer.parseInt(d.getValue()));
        field.setText(""+Integer.parseInt(d.getValue()));


        //lay out components df HG
        slider.setBounds(10, 25, 180, 22);
        label.setBounds(10, 0, 140, 22);
        field.setBounds(150, 0, 40, 22);

        label.setForeground(Color.WHITE);
        field.getDocument().addDocumentListener(new DocumentListener() {

            public void insertUpdate(DocumentEvent e) {
                calculateIt(e);
            }

            public void removeUpdate(DocumentEvent e) {
                calculateIt(e);
            }

            public void changedUpdate(DocumentEvent e) {
                calculateIt(e);
            }
        });



        slider.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                field.setText("" + slider.getValue());
                
            }
        });

    }


    public void encodeData(){
       d.setValue(""+slider.getValue());
    }
    //method to called by the listener abstract methods

    private void calculateIt(DocumentEvent documentEvent) {
        Document type = documentEvent.getDocument();

        if (field.getCaret().isSelectionVisible()) {
            try {
                int value = Integer.parseInt(field.getText());
               
                slider.setValue(value);
                
                
            } catch (Exception e) {
            }
        }
    }

}
