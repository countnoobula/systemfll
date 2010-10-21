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
            //get the type of logic block
            Class c = b.getClass();
            //add it to the engine
            LogicBlockEngine.addBlock((LogicBlock) c.newInstance());
            //repaint
            VisualLogicGL.getCanvas().repaint();
        } catch (InstantiationException f) {
            System.out.println("should not happen");
        } catch (IllegalAccessException f) {
            System.out.println("should not happen");
        }
    }
}
