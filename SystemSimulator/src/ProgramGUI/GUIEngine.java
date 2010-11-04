package ProgramGUI;

import GUIProgrammer.ProgrammerHome;
import MainClasses.Main;
import java.awt.Component;
import org.jdesktop.animation.transitions.ScreenTransition;

/**
 * This engine deals with all GUI related calls
 */
public class GUIEngine {

    Main m;
    private ScreenTransition transition;
    Component c;

    public GUIEngine(Main m2) {
        this.m = m2;
    }
    public void repaint(){
        
    }

    public void makeVisible(Component c) {
        this.c = c;
        

        for (int i = 0;
                i < m.getMainWindow().getProgramWindowPanel().getPanel_2().getLayeredPane().getComponents().length;
                i++) {

            if (m.getMainWindow().getProgramWindowPanel().getPanel_2().getLayeredPane().getComponents()[i] instanceof ProgrammerHome) {
                ((ProgrammerHome) m.getMainWindow().getProgramWindowPanel().getPanel_2().getLayeredPane().getComponents()[i]).setSelectedIndex(0);
            }
            m.getMainWindow().getProgramWindowPanel().getPanel_2().getLayeredPane().getComponents()[i].setVisible(false);
        }
        c.setVisible(true);

    }
}
