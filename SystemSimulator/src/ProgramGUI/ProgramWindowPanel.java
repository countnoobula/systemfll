package ProgramGUI;

import ProgramGUI.SectionCenter.SectionCenterPanel;

import java.awt.Graphics;
import java.io.IOException;
import java.util.logging.Level;

import javax.swing.JPanel;

import MainClasses.*;

import ProgramGUI.SectionBottom.SectionBottomPanel;

import ProgramGUI.GUIComponents.Panes.AuxSidePanels;


import ProgramGUI.SectionCenter.SectionCenterSidePanels;

import ProgramGUI.SectionTop.SectionTopPanel;

import ProgramGUI.SectionTop.SectionTopSub;
import Resources.Images.ImageLoader;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import java.awt.Insets;
import javax.imageio.ImageIO;

/**
 * The panel which is overlayed onto the frame, purely programming convention
 * and also allows for animations to fade into the main panel.
 */
public class ProgramWindowPanel extends JPanel {

    private Main m;
    private SectionTopPanel panel_1;
    private SectionCenterPanel panel_2;
    private SectionBottomPanel panel_3;
    private SectionTopSub panel_4;
    private SectionCenterSidePanels leftPanel, rightPanel;

    private GradientPaint gp1;
    private Image i;
    private int insetSize;

    private GridBagConstraints gc = new GridBagConstraints();

    public ProgramWindowPanel(Main m2) {
        this.m = m2;
        if(m.getPrefs().getBoolean("showStartAnimation", true)==true){
          this.setVisible(false); 
        }
        else{
          this.setVisible(true);
        }
        try {
            i = ImageIO.read(ImageLoader.class.getResource("backdrop.png"));
        } catch (IOException ex) {
            System.out.println("oh shit");
        }

        //create new instances of the components
        this.panel_1 = new SectionTopPanel(m);
        this.panel_2 = new SectionCenterPanel(m);
        this.panel_3 = new SectionBottomPanel(m);
        this.panel_4 = new SectionTopSub(m);
        this.leftPanel = new SectionCenterSidePanels(m);
        this.rightPanel = new SectionCenterSidePanels(m);

        this.insetSize = 4;

        gp1 =
 new GradientPaint(0, 40, new Color(30, 30, 30), 0, 100, new Color(0, 0,
                                                                      0));

        //build the window
        this.initComponents();

    }
    


    /**
     * Genric construction method for the GUI panels. It constructs the components and then puts
     * together the program window.
     */
    private void initComponents() {
        this.setLayout(new GridBagLayout());

        //Place the panel_1 at the top and use its minimum size
        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 0;
        gc.gridwidth = 3;
        gc.gridheight = 1;
        gc.fill = GridBagConstraints.BOTH;
        gc.anchor = GridBagConstraints.NORTHWEST;
        this.add(panel_1, gc);

        //Place the panel_4 at the top and use its minimum size
        gc.gridx = 0;
        gc.gridy = 1;
        gc.weightx = 1;
        gc.weighty = 0;
        gc.gridwidth = 3;
        gc.gridheight = 1;
        gc.fill = GridBagConstraints.BOTH;
        gc.anchor = GridBagConstraints.NORTHWEST;
        gc.insets = new Insets(0, 0, 0, 0);
        this.add(panel_4, gc);

        //Place the left panel
        gc.gridx = 0;
        gc.gridy = 2;
        gc.weightx = 0;
        gc.weighty = 1;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.fill = GridBagConstraints.BOTH;
        gc.anchor = GridBagConstraints.NORTHWEST;
        gc.insets = new Insets(0, 0, 0, 0);
        this.add(leftPanel, gc);

        //Place the center panel
        gc.gridx = 1;
        gc.gridy = 2;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.fill = GridBagConstraints.BOTH;
        gc.anchor = GridBagConstraints.NORTHWEST;
        gc.insets = new Insets(-5,0,0,0);
        this.add(panel_2, gc);

        //Place the right panel
        gc.gridx = 2;
        gc.gridy = 2;
        gc.weightx = 0;
        gc.weighty = 1;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.fill = GridBagConstraints.BOTH;
        gc.anchor = GridBagConstraints.NORTHWEST;
        gc.insets = new Insets(0, 0, 0, 0);
        this.add(rightPanel, gc);



        //Place the bottom panel
        gc.gridx = 0;
        gc.gridy = 3;
        gc.weightx = 1;
        gc.weighty = 0;
        gc.gridwidth = 3;
        gc.gridheight = 1;
        gc.fill = GridBagConstraints.BOTH;
        gc.anchor = GridBagConstraints.NORTHWEST;
        gc.insets = new Insets(0, 0, 0, 0);
        this.add(panel_3, gc);

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(gp1);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        g2d.drawImage(i, 0, 0,null);
    }

    public SectionCenterPanel getPanel_2() {
        return panel_2;
    }
}
