package VisualLogicSystem;

import GUIProgrammer.VisualLogic;

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
            //VisualLogic.refresh();
        }

        catch (InstantiationException f) {
        } catch (IllegalAccessException f) {
        }
    }
}
