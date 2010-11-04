/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ProgramGUI;

import GUIMainPanels.SystemHome;
import GUIOrganizer.OrganizerHome;
import GUIPlanner.PlannerPanel;
import GUIProgrammer.ProgrammerHome;
import MainClasses.Main;

/**
 *
 * @author dylan
 */
public class SystemGUIWindows {

    private SystemHome panel_1;
    private PlannerPanel panel_2;
    private OrganizerHome panel_3;
    private ProgrammerHome panel_4;

    public SystemGUIWindows(Main m){
        this.panel_1 = new SystemHome(m);
        this.panel_2 = new PlannerPanel(m);
        this.panel_3 = new OrganizerHome(m);
        this.panel_4 = new ProgrammerHome(m);
    }

    public SystemHome getPanel_1() {
        return panel_1;
    }

    public PlannerPanel getPanel_2() {
        return panel_2;
    }

    public OrganizerHome getPanel_3() {
        return panel_3;
    }

    public ProgrammerHome getPanel_4() {
        return panel_4;
    }


}
