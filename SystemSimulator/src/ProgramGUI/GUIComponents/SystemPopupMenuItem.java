package ProgramGUI.GUIComponents;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;

import java.awt.Graphics2D;

import java.awt.Paint;

import java.awt.Stroke;

import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;

import java.awt.event.MouseListener;

import java.awt.Insets;
import java.awt.event.ComponentListener;

public class SystemPopupMenuItem extends JMenuItem {


    private Paint gp1, gp2, gp3;
    private Stroke s1;
    private boolean entered = false;
    private String title;
    private Font f1;

    public SystemPopupMenuItem(String s) {
        super(s);
        title = s;
        f1 = new Font("Arial",13,13);
        this.setOpaque(false);
        this.addMouseListener(new MouseListener() {
                public void mouseClicked(MouseEvent e) {
                  entered = false;
                  repaint();
                }

                public void mousePressed(MouseEvent e) {
                  entered = false;
                  repaint();
                }

                public void mouseReleased(MouseEvent e) {
                }

                public void mouseEntered(MouseEvent e) {
                    entered = true;
                    repaint();
                }

                public void mouseExited(MouseEvent e) {
                    entered = false;
                    repaint();
                }
            });
        
        gp1 = new Color(30, 30, 30);
        gp2 = new GradientPaint(0,0,new Color(250,250,250),0,25,new Color(230,230,230));
        gp3 = new Color(150, 150, 150);
        //gp4 = new Color(30, 30, 30);
        s1 = new BasicStroke(2.0f);
        super.getInsets().set(3, 10, 3, -10);
    }
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setStroke(s1);
        g2d.setFont(f1);
        if (entered == true) {
            g2d.setPaint(gp1);
            g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
            g2d.setColor(Color.WHITE);
            g2d.drawString(title, 10, this.getHeight() - 8);
        } else {
            g2d.setPaint(gp2);
            g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
            g2d.setColor(Color.BLACK);
            g2d.drawString(title, 10, this.getHeight() - 8);
        }

        g2d.setPaint(gp3);
        g2d.drawLine(10, this.getHeight() - 1, this.getWidth() - 10,
                     this.getHeight() - 1);
        g2d.setPaint(gp1);
        g2d.drawLine(0, this.getHeight() - 1, 10, this.getHeight() - 1);
        g2d.drawLine(this.getWidth() - 10, this.getHeight() - 1,
                     this.getWidth(), this.getHeight() - 1);
    }
}
