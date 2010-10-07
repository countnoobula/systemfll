package ProgramGUI.SectionBottom;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import MainClasses.Main;

import ProgramGUI.GUIComponents.SubBarAButton;
import ProgramGUI.GUIComponents.SubPaneArrow;

import java.awt.BorderLayout;

import java.util.ArrayList;

/**
 * The panel for the buttons underneith the topPanel
 */
public class SectionBottomSub extends JPanel {

    private GradientPaint gp1;
    private Main m;

    private SubPaneArrow arrow1, arrow2;
    private ArrayList<SubBarAButton> barButtons;
    private MiddleButtonsPanel middlePanel;

    public SectionBottomSub(Main m2) {

        m = m2;
        arrow1 = new SubPaneArrow();
        arrow2 = new SubPaneArrow();
        barButtons = new ArrayList<SubBarAButton>(0);    
        middlePanel = new MiddleButtonsPanel();

        gp1 =
 new GradientPaint(0, 0, new Color(20, 20, 20), 0, 30, new Color(60, 60, 60));

        this.setPreferredSize(new Dimension(0, 25));
        this.initComponents();
    }

    private class MiddleButtonsPanel extends JPanel {
        private MiddleButtonsPanel() {
            this.setOpaque(false);
            for(int i = 0;i < barButtons.size();i++){
              this.add(barButtons.get(i));
                
            }
        }
    }

    private void initComponents() {
        this.setLayout(new BorderLayout());
        /* this.add(arrow1, BorderLayout.WEST);
        this.add(arrow2, BorderLayout.EAST); */
        this.add(middlePanel,BorderLayout.CENTER);
    }

    /**
     *Paints the panel
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(gp1);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
}
