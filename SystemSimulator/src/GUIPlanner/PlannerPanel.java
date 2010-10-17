package GUIPlanner;

import ProgramGUI.GUIComponents.TabbedPane;

import MainClasses.Main;

public class PlannerPanel extends TabbedPane {

    private Main m;
    private NoteViewer panel_1;

    public PlannerPanel(Main m2) {
        super(m2);
        this.m = m2;

        this.panel_1 = new NoteViewer(m);

        this.add("Notes", panel_1);
        this.setVisible(false);

    }


}
