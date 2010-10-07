package ProgramGUI.GUIComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import java.awt.Paint;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JRadioButton;
import javax.swing.plaf.basic.BasicButtonUI;


public class SystemSmallTool extends JRadioButton {
    Image icon;

    public SystemSmallTool(Image icon) {
        super();
        this.icon = icon;
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(25, 25));
        this.setUI(new SystemSmallToolUI());
        
    }

    private class SystemSmallToolUI extends BasicButtonUI {

        Paint gp1, gp2,gp3;

        private SystemSmallToolUI() {
            gp1 = new Color(60, 60, 60);
          gp3 = new Color(0, 192, 255);
            gp2 = new GradientPaint(0,0,new Color(20, 20, 20),25,25,new Color(50,50,50));
          
        }

        @Override
        public void paint(Graphics g, JComponent c) {
            Graphics2D g2d = (Graphics2D)g;
            g2d.setPaint(gp2);
            g2d.fillRect(0, 0, getWidth(), getHeight());
            if(isSelected()){
            g2d.setPaint(gp3);
            }
            else{
              g2d.setPaint(gp1); 
            }
            g2d.drawRect(0, 0, getWidth()-1, getHeight()-1);
            g2d.drawImage(icon, 0, 0,null);


        }
    }
}
