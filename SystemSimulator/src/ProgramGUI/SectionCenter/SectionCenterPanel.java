package ProgramGUI.SectionCenter;

import GUIMainPanels.SystemHome;

import GUIOrganizer.OrganizerHome;

import GUIPlanner.NoteViewer;

import GUIPlanner.PlannerPanel;

import GUIProgrammer.ProgrammerHome;

import MainClasses.Main;


import ProgramGUI.GUIComponents.Panes.TabbedPane;

import java.awt.BorderLayout;

import javax.swing.JPanel;


/**
 * Manages all the GUI engines which are centered in the middle of the program
 */
public class SectionCenterPanel extends JPanel {

    private Main m;
    private CenterLayeredPane layeredPane;
    

    public SectionCenterPanel(Main m2) {
        this.m = m2;

        this.layeredPane = new CenterLayeredPane();

        this.layeredPane.addPanel(m.getWindows().getPanel_1());
        this.layeredPane.addPanel(m.getWindows().getPanel_2());
        this.layeredPane.addPanel(m.getWindows().getPanel_3());
        this.layeredPane.addPanel(m.getWindows().getPanel_4());

        

        //perform some property changes
        this.setOpaque(false);
        //construct frame
        initComponents();
    }


    /*
     * Constructs the components, and builds the Centre panel
     */

    public void initComponents() {
        this.setLayout(new BorderLayout());
        this.add(layeredPane, BorderLayout.CENTER);
    }
    
    /**
     * Returns the layered Pane
     * @return
     * Layered Pane
     */
    public CenterLayeredPane getLayeredPane() {
        return layeredPane;
    }

}


