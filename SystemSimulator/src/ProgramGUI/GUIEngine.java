package ProgramGUI;

import MainClasses.Main;

import ProgramGUI.GUIComponents.AuxPanelButton;

import java.awt.Color;

import java.awt.Component;

import java.util.ArrayList;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.transitions.ScreenTransition;
import org.jdesktop.animation.transitions.TransitionTarget;

/**
 * This engine deals with all GUI realted calls
 */
public class GUIEngine  {

    Main m;
    private ScreenTransition transition;
    Component c;

    public GUIEngine(Main m2) {
        this.m = m2;
    }


    public void makeVisible(Component c) {
        this.c = c;
        for (int i = 0;
             i < m.getMainWindow().getProgramWindowPanel().getPanel_2().getLayeredPane().getComponents().length;
             i++) {

            if (m.getMainWindow().getProgramWindowPanel().getPanel_2().getLayeredPane().getComponents()[i].isVisible() !=
                false) {
                m.getMainWindow().getProgramWindowPanel().getPanel_2().getLayeredPane().getComponents()[i].setVisible(false);
            }
            c.setVisible(true);

        }
    }

}
