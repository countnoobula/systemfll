package ProgramGUI;

import GUIProgrammer.ProgrammerHome;
import MainClasses.Main;
import ProgramGUI.GUIComponents.Panes.GenericSystemPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JFrame;
import org.jdesktop.animation.transitions.ScreenTransition;

/**
 * This engine deals with all GUI related calls
 */
public class GUIEngine {

    Main m;
    private ScreenTransition transition;
    static Component c;
    static Component c3;
    static int position = 0;

    public GUIEngine(Main m2) {
        this.m = m2;
    }

    public void repaint() {
    }

    public void openInNewWindow(final Component c2) {
        final JFrame f = new JFrame();

        f.add(((GenericSystemPanel) c2), BorderLayout.CENTER);
        f.setBackground(Color.BLACK);
        f.setSize(c2.getWidth(), c2.getHeight());
        f.setVisible(true);
        f.setTitle("-| "+((GenericSystemPanel) c2).getPanelName()+" |-");
        f.addComponentListener(new ComponentListener() {

            public void componentResized(ComponentEvent ce) {
            }

            public void componentMoved(ComponentEvent ce) {
            }

            public void componentShown(ComponentEvent ce) {
            }

            public void componentHidden(ComponentEvent ce) {
                ((GenericSystemPanel) c2).getBelongsTo().add(((GenericSystemPanel) c2).getPanelName(), c2);
                f.dispose();

            }
        });
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
