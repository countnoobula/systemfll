package ProgramGUI.GUIComponents.Buttons;

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

public class SystemSmallBarButton extends JRadioButton {

    Image icon;

    public SystemSmallBarButton(Image icon) {
        super();
        this.icon = icon;
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(30, 23));
        this.setUI(new SystemSmallToolUI());

    }

    private class SystemSmallToolUI extends BasicButtonUI {

        Paint gp1, gp2;

        private SystemSmallToolUI() {
            gp1 = new Color(0,0,0,0);
            gp2 = new GradientPaint(0, 0, new Color(120, 120, 120), 0,23, new Color(30, 30, 30));

        }

        @Override
        public void paint(Graphics g, JComponent c) {
            Graphics2D g2d = (Graphics2D) g;
            if (isSelected()) {
                g2d.setPaint(gp2);
                g2d.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
            } 
            
            g2d.drawImage(icon, 0, 0, null);


        }
    }
}
