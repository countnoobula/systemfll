package VisualLogicSystem;

import GUIProgrammer.VisualLogicGL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogicBlockAddition implements ActionListener {

    LogicBlock b;

    public LogicBlockAddition(LogicBlock b) {
        this.b = b;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            LogicBlockEngine.addBlock((LogicBlock) b.clone());
        } catch (CloneNotSupportedException ex) {
            System.out.println("oh fuck!");
        }
           
                //repaint
                VisualLogicGL.getCanvas().repaint();
        
    }
}
