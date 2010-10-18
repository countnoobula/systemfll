package ProgramGUI.SectionTop;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import javax.swing.ButtonModel;
import javax.swing.JPanel;
import MainClasses.Main;
import ProgramGUI.GUIComponents.Panes.NullPanel;
import ProgramGUI.GUIComponents.Buttons.SubBarAButton;
import ProgramGUI.GUIComponents.Buttons.SubPaneArrow;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;



/**
 * The panel for the buttons underneith the topPanel
 */
public class SectionTopSub extends JPanel {

    private GradientPaint gp1;
    private SubPaneArrow arrow1, arrow2;
    private ArrayList<SubBarAButton> barButtons;
    private MiddleButtonsPanel middlePanel;
    private ButtonGroup bg;

    //program main
    private Main m;

    public SectionTopSub(Main m2) {

        m = m2;
        barButtons = new ArrayList<SubBarAButton>(0);
        bg = new ButtonGroup();

        //Here are all the buttons in their correct order
        barButtons.add(new SubBarAButton(m, "Core"));
        barButtons.add(new SubBarAButton(m, "Resources"));
        barButtons.add(new SubBarAButton(m, "Planner"));
        barButtons.add(new SubBarAButton(m, "Organizer"));
        barButtons.add(new SubBarAButton(m, "Designer"));
        barButtons.add(new SubBarAButton(m, "Programmer"));
        barButtons.add(new SubBarAButton(m, "Simulator"));
        barButtons.add(new SubBarAButton(m, "Connector"));
        bg.add(barButtons.get(0));
        bg.add(barButtons.get(1));
        bg.add(barButtons.get(2));
        bg.add(barButtons.get(3));
        bg.add(barButtons.get(4));
        bg.add(barButtons.get(5));
        bg.add(barButtons.get(6));
        bg.add(barButtons.get(7));

        middlePanel = new MiddleButtonsPanel();
        barButtons.get(0).setSelected(true);


        gp1 =
 new GradientPaint(0, 0, new Color(200, 200, 200), 0, 20, new Color(102,102, 102));

        //apply component settings
        this.setPreferredSize(new Dimension(0, 20));
        this.initComponents();
        this.installListeners();
    }

    /**
     * Installs action listeners for all the tabs
     */
    private void installListeners() {

        barButtons.get(0).addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    m.getGuiEngine().makeVisible(m.getMainWindow().getProgramWindowPanel().getPanel_2().getPanel_1());

                }
            });
        
        barButtons.get(2).addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    m.getGuiEngine().makeVisible(m.getMainWindow().getProgramWindowPanel().getPanel_2().getPanel_2());

                }
            });
        barButtons.get(3).addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    m.getGuiEngine().makeVisible(m.getMainWindow().getProgramWindowPanel().getPanel_2().getPanel_3());

                }
            });
        
        barButtons.get(5).addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    m.getGuiEngine().makeVisible(m.getMainWindow().getProgramWindowPanel().getPanel_2().getPanel_4());

                }
            });
    }

    private class MiddleButtonsPanel extends NullPanel {

        GridBagConstraints c;

        private MiddleButtonsPanel() {

            c = new GridBagConstraints();

            this.setLayout(new GridBagLayout());

            //lay out all the buttons in a neat orderly fashion
            c.gridy = 0;
            c.anchor = GridBagConstraints.NORTHWEST;
            c.fill = GridBagConstraints.NONE;
            c.weightx = 0;
            c.insets = new Insets(0, 0, 0, 4);
            c.weighty = 1;
            c.gridwidth = 1;
            c.gridheight = 1;

            for (int i = 0; i < barButtons.size(); i++) {
                c.gridx = i;
                this.add(barButtons.get(i), c);
            }

            c.weightx = 1;
            c.gridx = barButtons.size();
            this.add(new NullPanel(), c);
        }
    }

    /**
     * Called to create and lay out all the sections in this bar
     */
    private void initComponents() {
        this.setLayout(new BorderLayout());
        this.add(middlePanel, BorderLayout.CENTER);
    }

    /**
     * Paints the panel for the buttons
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
