package GUIMainPanels;

import MainClasses.Main;

import ProgramGUI.GUIComponents.TabbedPane;

/**
 * The starting location of the program when it loads
 */
public class SystemHome extends TabbedPane {

    private Main m;
    private WelcomeScreen panel_1;
    private NewsScreen panel_2;
    private PortalScreen panel_3;
    private UpdateScreen panel_4;
    private PreferencesScreen panel_5;
    private HelpScreen panel_6;

    public SystemHome(Main m2) {
        super(m2);

        //create variables
        this.m = m2;
        this.panel_1 = new WelcomeScreen();
        this.panel_2 = new NewsScreen();
        this.panel_3 = new PortalScreen(m);
        this.panel_4 = new UpdateScreen();
        this.panel_5 = new PreferencesScreen(m);
        this.panel_6 = new HelpScreen();

        //add all the components
        this.add("Home", panel_1);
        this.add("Help", panel_6);
        this.add("Preferences", panel_5);
        this.add("News", panel_2);
        this.add("Portal", panel_3);
        this.add("Updates", panel_4);
        

    }

    public NewsScreen getPanel_2() {
        return panel_2;
    }

    public PortalScreen getPanel_3() {
        return panel_3;
    }

    public UpdateScreen getPanel_4() {
        return panel_4;
    }

    public PreferencesScreen getPanel_5() {
        return panel_5;
    }

    public HelpScreen getPanel_6() {
        return panel_6;
    }
}
