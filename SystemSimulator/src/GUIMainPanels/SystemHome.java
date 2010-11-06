package GUIMainPanels;

import MainClasses.Main;

import ProgramGUI.GUIComponents.Panes.TabbedPane;

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

        String names[] = {
            "Home",
            "Help",
            "Preferences",
            "News",
            "Portal",
            "Updates"};
        //create variables
        this.m = m2;
        this.panel_1 = new WelcomeScreen(this, names[0]);
        this.panel_2 = new NewsScreen(this, names[3]);
        this.panel_3 = new PortalScreen(this, names[4], m);
        this.panel_4 = new UpdateScreen(this, names[5]);
        this.panel_5 = new PreferencesScreen(this, names[2], m);
        this.panel_6 = new HelpScreen(this, names[1]);

        //add all the components
        this.add(names[0], panel_1);
        this.add(names[1], panel_6);
        this.add(names[2], panel_5);
        this.add(names[3], panel_2);
        this.add(names[4], panel_3);
        this.add(names[5], panel_4);


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
