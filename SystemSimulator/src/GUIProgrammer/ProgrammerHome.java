package GUIProgrammer;

import ProgramGUI.GUIComponents.Panes.TabbedPane;
import MainClasses.Main;
import javax.swing.Timer;

public class ProgrammerHome extends TabbedPane {

    private CoderAPI panel_1;
    private CodingScreen panel_2;
    private VisualLogicGL panel_3;

    Timer r;
    
    //main class
    private Main m;

    public ProgrammerHome(Main m2) {
        super(m2);
        this.m = m2;

        String names[] = {"Coding API","Scripter","Visual Logic"};
        
        //create new instances
        this.panel_1 = new CoderAPI(this,names[0]);
        this.panel_2 = new CodingScreen(this,names[1],m);
        this.panel_3 = new VisualLogicGL(this,names[2],m);
        
        //add components
        this.add(names[0], panel_1);
        this.add(names[1], panel_2);
        this.add(names[2], panel_3);
        panel_3.setOpaque(false);
        panel_3.setDoubleBuffered(false);
  
        
    }


    public VisualLogicGL getPanel_3() {
        return panel_3;
    }

    public void setPanel_3(VisualLogicGL panel_3) {
        this.panel_3 = panel_3;
    }
    
}
