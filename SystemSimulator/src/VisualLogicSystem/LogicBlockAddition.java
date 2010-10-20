package VisualLogicSystem;

import GUIProgrammer.VisualLogic;
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

            Class c = b.getClass();
            LogicBlockEngine.addBlock((LogicBlock)c.newInstance());
            VisualLogicGL.getCanvas().repaint();
        }

        catch (InstantiationException f) {
        } catch (IllegalAccessException f) {
        }
    }
}
