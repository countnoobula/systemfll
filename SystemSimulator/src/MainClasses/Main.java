package MainClasses;


import DataSystem.PlannerDatabase;

import ProgramGUI.GUIEngine;
import ProgramGUI.ProgramWindow;

import Resources.Fonts.FontLoader;

import java.util.prefs.Preferences;
import javax.swing.JFrame;

import javax.swing.SwingUtilities;
import translucentshapes.AWTUtilitiesWrapper;


/**
 * The Main method for the program
 * Let the games Begin!
 * @author Dylan Vorster
 */
public class Main {

    public SystemProject systemProject;
    public GUIEngine guiEngine;
    public ProgramWindow mainWindow;
    public FontLoader fonts;
    public Preferences prefs;
    public EngineDepo engineDepo;

    public Main() {

        //register the preferences
        prefs = Preferences.userNodeForPackage(getClass());

        //create new instances of all the objects
        this.engineDepo = new EngineDepo();
        this.systemProject = new SystemProject("Temp", "Dylan Vorster");
        this.fonts = new FontLoader();
        this.guiEngine = new GUIEngine(this);
        JFrame f = new JFrame();
        f.setSize(0,0);
        f.setUndecorated(true);
        f.setVisible(true);
        this.mainWindow = new ProgramWindow(this,f);
        mainWindow.setVisible(true);
        AWTUtilitiesWrapper.setWindowOpaque(mainWindow, false);

    }

    /**
     * The main method, yes I simply HAD to comment this
     * @param args
     * I have no idea, I think it has something to do with the system
     */
    public static void main(String[] args) {

        /**
         * Kapow!
         */
        SwingUtilities.invokeLater(new Runnable() {
              

                public void run() {
                    
                    Main main = new Main();
    
                        main.getGuiEngine().makeVisible(main.getMainWindow().getProgramWindowPanel().getPanel_2().getPanel_1());

                    
                }
            });


    }


    public ProgramWindow getMainWindow() {
        return mainWindow;
    }

    public FontLoader getFonts() {
        return fonts;
    }

    public GUIEngine getGuiEngine() {
        return guiEngine;
    }

    public PlannerDatabase getPlanDatabase() {
        return systemProject.getPlannerDatabase();
    }

    public Preferences getPrefs() {
        return prefs;
    }

    public void setSystemProject(SystemProject systemProject) {
        this.systemProject = systemProject;
    }

    public SystemProject getSystemProject() {
        return systemProject;
    }

    public EngineDepo getEngineDepo() {
        return engineDepo;
    }
}
