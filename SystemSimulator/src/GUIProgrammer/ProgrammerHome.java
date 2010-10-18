package GUIProgrammer;

import ProgramGUI.GUIComponents.Panes.TabbedPane;

import MainClasses.Main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ProgrammerHome extends TabbedPane {

    private CoderAPI panel_1;
    private CodingScreen panel_2;
    private VisualLogic panel_3;

    Timer r;
    
    //main class
    private Main m;

    public ProgrammerHome(Main m2) {
        super(m2);
        this.m = m2;
        
        //create new instances
        this.panel_1 = new CoderAPI();
        this.panel_2 = new CodingScreen();
        this.panel_3 = new VisualLogic(m);
        
        //add components
        this.add("Coding API", panel_1);
        this.add("Scripter", panel_2);
        this.add("Visual Logic", panel_3);

        this.addChangeListener(new ChangeListener(){

            public void stateChanged(ChangeEvent e) {
                if(getSelectedComponent().equals(panel_3)){
                    r = new Timer(200,new DisplayTimer());
                    r.start();


                }
            }
        });



        
        
    }
    private class DisplayTimer implements ActionListener{

        public void actionPerformed(ActionEvent e) {
           panel_3.getCanvas().setVisible();
           r.stop();
        }

    }

    public VisualLogic getPanel_3() {
        return panel_3;
    }

    public void setPanel_3(VisualLogic panel_3) {
        this.panel_3 = panel_3;
    }
    
}
