package GUIOrganizer;

import ProgramGUI.GUIComponents.Panes.TabbedPane;
import MainClasses.Main;

public class OrganizerHome extends TabbedPane{
    
    
    private PartsDBScreen panel_1;
    private ProjectManagerScreen panel_2;
    //main class
    private Main m;
    
    public OrganizerHome(Main m2) {
        super(m2);
        this.m = m2;
        this.panel_1 = new PartsDBScreen();
        this.panel_2 = new ProjectManagerScreen();
        
        //add components
        this.add("Project Manager", panel_2);
        this.add("Parts Database", panel_1);
    }
}
