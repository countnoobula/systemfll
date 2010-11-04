package GUIPlanner;

import ProgramGUI.GUIComponents.Panes.TabbedPane;
import MainClasses.Main;

public class PlannerPanel extends TabbedPane {

    private Main m;
    private NoteViewer panel_1;

    public PlannerPanel(Main m2) {
        super(m2);
        this.m = m2;

        String names[] = {"Sticky Notes"};
        this.panel_1 = new NoteViewer(this, names[0], m);

        this.add(names[0], panel_1);
        this.setVisible(false);

    }
}
