package MainClasses;

import DataSystem.PlannerDatabase;
import ProgramGUI.GUIEngine;
import ProgramGUI.ProgramWindow;
import ProgramGUI.SystemGUIWindows;
import Resources.Fonts.FontLoader;
import java.util.prefs.Preferences;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * The Main method for the program
 * Let the games Begin!
 * @author Dylan Vorster
 */
public class Main {

    //is always made first
    public SystemProject systemProject;

    //instances of all the engines etc...
    public GUIEngine guiEngine;
    public ProgramWindow mainWindow;
    public FontLoader fonts;
    public Preferences prefs;
    public EngineDepo engineDepo;
    public SystemDepo systemDepo;

    public SystemGUIWindows windows;

    public Main() {

        this.systemProject = new SystemProject("Temp", "Dylan Vorster");

        
        //register the preferences
        this.prefs = Preferences.userNodeForPackage(getClass());
        this.fonts = new FontLoader();

        
        //create new instances of all the objects
        this.engineDepo = new EngineDepo(this);
        this.systemDepo = new SystemDepo();
        this.guiEngine = new GUIEngine(this);

        //register the windows
        windows = new SystemGUIWindows(this);

        JFrame f = new JFrame();
        f.setSize(0, 0);
        f.setUndecorated(true);
        f.setVisible(true);
        this.mainWindow = new ProgramWindow(this, f);

        

        //make the frame visible
        this.mainWindow.setVisible(true);
    }

    /**
     * The main method, yes I simply HAD to comment this
     * @param args
     * I have no idea, I think it has something to do with the system
     */
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {

                Main main = new Main();
                main.getGuiEngine().makeVisible(main.getWindows().getPanel_1());
                main.getWindows().getPanel_4().getPanel_3().getDrawer().setLogicBlocks(
                        main.getSystemDepo().getLogicLibrary());
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

    public Preferences getPrefs() {
        return prefs;
    }

    public void setSystemProject(SystemProject systemProject) {
        this.systemProject = systemProject;
        this.engineDepo.updateData(systemProject);
        this.getMainWindow().repaint();
    }

    public SystemProject getSystemProject() {
        return systemProject;
    }

    public EngineDepo getEngineDepo() {
        return engineDepo;
    }

    public SystemDepo getSystemDepo() {
        return systemDepo;
    }

    public SystemGUIWindows getWindows() {
        return windows;
    }


}
