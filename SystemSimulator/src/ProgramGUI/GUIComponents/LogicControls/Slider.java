package ProgramGUI.GUIComponents.LogicControls;

import ProgramGUI.GUIComponents.NullPanel;
import ProgramGUI.GUIComponents.SystemSlider;
import ProgramGUI.GUIComponents.SystemTextField;
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

    public Slider() {



        this.slider = new SystemSlider();
        this.label = new JLabel("Something to adjust");
        this.field = new SystemTextField();

        this.setLayout(null);
        this.setPreferredSize(new Dimension(400, 34));

        this.add(slider);
        this.add(label);
        this.add(field);

        //lay out components df HG
        slider.setBounds(10, 10, 180, 22);
        label.setBounds(245, 10, 140, 22);
        field.setBounds(195, 10, 40, 22);

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
    //method to called by the listener abstract methods

    private void calculateIt(DocumentEvent documentEvent) {
        Document type = documentEvent.getDocument();

        if (field.getCaret().isSelectionVisible()) {
            try {
                int value = Integer.parseInt(field.getText());
                if(value > -1){
                slider.setValue(value);
                }
            } catch (Exception e) {
            }

        }

    }

    public Object getData() {
        return null;
    }

    public void setData(Object ob) {
    }

    public static void main(String[] args) {
        Slider s = new Slider();
        Slider s2 = new Slider();
        JFrame f = new JFrame();

        f.add(s);
        f.add(s2);
        f.setLayout(new FlowLayout());
        f.setBackground(new Color(40, 40, 40));
        f.setBounds(10, 10, 500, 300);

        f.setVisible(true);

    }
}
