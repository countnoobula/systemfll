package ProgramGUI.GUIComponents;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.Graphics2D;

import java.awt.Paint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.plaf.basic.BasicPopupMenuUI;
import java.awt.event.MouseListener;

public class SystemPopUp extends JPopupMenu{
  Paint gp1;
  SystemPopUp nested;
    public SystemPopUp() {
        super();
        nested = this;
        this.setOpaque(false);
        gp1 = new Color(0,0,0);
        
        
      
    }

    @Override
    public JMenuItem add(JMenuItem menuItem) {
        menuItem.addActionListener(new MouseActions());
        return super.add(menuItem);
    }

    private class MouseActions implements ActionListener{


        public void actionPerformed(ActionEvent e) {
            nested.setVisible(false);
        }
    }
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(gp1);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
}
