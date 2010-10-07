package ProgramGUI.SectionCenter;

import GUIMainPanels.SystemHome;

import GUIOrganizer.OrganizerHome;

import GUIPlanner.NoteViewer;

import GUIPlanner.PlannerPanel;

import GUIProgrammer.ProgrammerHome;

import MainClasses.Main;


import ProgramGUI.GUIComponents.TabbedPane;

import java.awt.BorderLayout;

import javax.swing.JPanel;


/**
 * Manages all the GUI engines which are centered in the middle of the program
 */
public class SectionCenterPanel extends JPanel {

    private Main m;
    private CenterLayeredPane layeredPane;
    private SystemHome panel_1;
    private PlannerPanel panel_2;
    private OrganizerHome panel_3;
    private ProgrammerHome panel_4;

    public SectionCenterPanel(Main m2) {
        this.m = m2;

        this.layeredPane = new CenterLayeredPane();
        this.panel_1 = new SystemHome(m);
        this.panel_2 = new PlannerPanel(m);
        this.panel_3 = new OrganizerHome(m);
        this.panel_4 = new ProgrammerHome(m);

        this.layeredPane.addPanel(panel_1);
        this.layeredPane.addPanel(panel_2);
        this.layeredPane.addPanel(panel_3);
        this.layeredPane.addPanel(panel_4);

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
     * Returns the layerd Pane
     * @return
     * Layered Pane
     */
    public CenterLayeredPane getLayeredPane() {
        return layeredPane;
    }
    /**
     * Returns the System Home module which is the first button
     * @return
     * System Home TabbedPane
     */
    public SystemHome getPanel_1() {
        return panel_1;
    }
    /**
     * Returns the System planner module GUI which is the second button
     * @return
     * System Planner Panel
     */
    public PlannerPanel getPanel_2() {
        return panel_2;
    }
    /**
     * Returns the System Organizer module GUI which is the fourth button
     * @return
     * System Organizer module
     */
    public OrganizerHome getPanel_3() {
        return panel_3;
    }

    /**
     * Returns the programmer module GUI which is the 5th button
     * @return
     * System Programmer module
     */
    public ProgrammerHome getPanel_4() {
        return panel_4;
    }
}


