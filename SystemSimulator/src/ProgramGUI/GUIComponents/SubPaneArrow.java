package ProgramGUI.GUIComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;

import java.awt.Graphics2D;

import java.awt.Insets;
import java.awt.Paint;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicButtonUI;

public class SubPaneArrow extends JButton{

    Paint gp1,gp2;
    public SubPaneArrow() {
        
      gp1 = new GradientPaint(0,0,new Color(0,0,0),40,25,new Color(50,50,50));
      this.setPreferredSize(new Dimension(40,0));
      this.setUI(new SubPaneArrowUI());
      this.setMargin(new Insets(-3, -3, -3, -3));
                  this.setBorderPainted(false);
                  this.setFocusPainted(false);
                  this.setContentAreaFilled(false);
    }

    private class SubPaneArrowUI extends BasicButtonUI {

        @Override
        protected void paintButtonPressed(Graphics g, AbstractButton b) {
            Graphics2D g2d = (Graphics2D)g;
        }

        @Override
        public void paint(Graphics g, JComponent c) {
            Graphics2D g2d = (Graphics2D)g;
            g2d.setPaint(gp1);
            g2d.fillRect(0, 0,c.getWidth(), c.getHeight());

        }
    }
}
