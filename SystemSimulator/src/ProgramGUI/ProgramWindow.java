package ProgramGUI;

import java.awt.Toolkit;

import MainClasses.*;


import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JWindow;






/**
 * This is the main Program Window for the application.
 */
    public class ProgramWindow extends JWindow {

    private Main m;
    private ProgramWindowPanel panel_2;

    private GUIEngine guiEngine;

    /**
     * Creates the program window and positions it in the center of the screen
     * @param m2
     */
    public ProgramWindow(Main m2,JFrame f) {
        super(f);
        //create new instances of the GUI components
        this.m = m2;
        this.panel_2 = new ProgramWindowPanel(m);
        this.guiEngine = new GUIEngine(m);

        //Variables for the program Window layout on the screen
        int sideWidth = 50;
        int topWidth = 50;
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height =
            Toolkit.getDefaultToolkit().getScreenSize().getHeight();


        //component methods
        this.setLayout(new BorderLayout());

        this.add(panel_2,BorderLayout.CENTER);

        this.setBounds(sideWidth, topWidth, (int)(width - 2 * sideWidth),
                       (int)(height - 2 * topWidth));
        this.setMinimumSize(new Dimension(900,675));
       this.getRootPane().putClientProperty("apple.awt.draggableWindowBackground", Boolean.FALSE);
    }
    /*
     * This is the start up animation for the program. It will display a really sweet animation
     * and will end with System being faded out and the main program window fading in
     */


    public ProgramWindowPanel getProgramWindowPanel() {
        return panel_2;
    }

    public GUIEngine getGuiEngine() {
        return guiEngine;
    }

    public void setGuiEngine(GUIEngine guiEngine) {
        this.guiEngine = guiEngine;
    }



}
