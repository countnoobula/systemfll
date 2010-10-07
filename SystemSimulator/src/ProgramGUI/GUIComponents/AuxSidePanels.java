package ProgramGUI.GUIComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import MainClasses.Main;


import java.awt.BasicStroke;
import java.awt.GradientPaint;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Shape;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.MouseMotionListener;
import java.awt.Insets;

import java.awt.Paint;
import java.awt.Stroke;

import java.util.ArrayList;

/**
 * These are the Auxiliry panels which set on the left and right of the program
 */
public class AuxSidePanels extends JPanel {

    private Main m;
    private Color cp1;
    private GradientPaint gp1, gp2;
    private Stroke s1;

    private Shape sh1, sh2;
    private boolean contained1 = false;

    private GridBagConstraints gc = new GridBagConstraints();
    private ArrayList<AuxPanelButton> buttons;


    public AuxSidePanels(Main m2) {
        this.m = m2;
        
  

        //create instance variables
        gp1 =
 new GradientPaint(0, 0, new Color(0, 0, 0), 0, 15, new Color(33, 33, 33));
        gp2 =
 new GradientPaint(0, 0, new Color(28, 28, 28), 40, 0, new Color(60, 60, 60));
        
        

        s1 = new BasicStroke(1.5f);
        int x1[] = { 6, 11, 11 };
        int y1[] = { 10, 6, 13 };
        sh1 = new Polygon(x1, y1, 3);
        int x2[] = { 13, 18, 18 };
        int y2[] = { 10, 6, 13 };
        sh2 = new Polygon(x2, y2, 3);

        //Set panel settings
        this.setPreferredSize(new Dimension(40, 0));
        this.setOpaque(false);


    }

    private void initComponents() {
        this.removeAll();
        this.setLayout(new GridBagLayout());
        gc.insets = new Insets(15, 2, 0, 2);
        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 0;
        gc.weighty = 0;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.fill = GridBagConstraints.BOTH;
        gc.anchor = GridBagConstraints.NORTHWEST;
        this.add(buttons.get(0), gc);
        gc.insets = new Insets(4, 2, 0, 2);
        for (int i = 1; i < buttons.size(); i++) {
            gc.gridy = i;
            this.add(buttons.get(i), gc);
        }
        this.setPreferredSize(new Dimension(40,
                                            (40 + 4) * buttons.size() + 5));
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(gp2);
        g2d.fillRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
        g2d.setColor(Color.black);
        g2d.setStroke(s1);
        g2d.drawRect(1, 1, this.getWidth() - 3, this.getHeight() - 3);
        paintHeader(g2d);
    }

    private void paintHeader(Graphics2D g2d) {
        g2d.setPaint(gp1);
        g2d.fillRect(0, 0, this.getWidth() - 1, 18 - 1);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(s1);
        g2d.drawRect(1, 1, this.getWidth() - 3, 18 - 3);
        g2d.setColor(Color.WHITE);
        g2d.fill(sh1);
        g2d.setColor(Color.GRAY);
        g2d.fill(sh2);


    }

    public void setButtons(ArrayList<AuxPanelButton> buttons) {
        this.buttons = buttons;
        initComponents();
        this.revalidate();
    }
}
