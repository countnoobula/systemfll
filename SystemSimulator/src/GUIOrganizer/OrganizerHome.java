package GUIOrganizer;

import ProgramGUI.GUIComponents.Panes.TabbedPane;
import MainClasses.Main;

public class OrganizerHome extends TabbedPane{
    
    
    private PartsDBScreen panel_1;
    private ProjectManagerScreen panel_2;
    private Main m;
    
    public OrganizerHome(Main m2) {
        super(m2);
        //create new instances
        this.m = m2;
        String names[] = {"Project Manager","Parts Database"};
        this.panel_1 = new PartsDBScreen(this,names[0]);
        this.panel_2 = new ProjectManagerScreen(this,names[1]);
        
        //add components
        this.add(names[0], panel_2);
        this.add(names[1], panel_1);
    }
}
