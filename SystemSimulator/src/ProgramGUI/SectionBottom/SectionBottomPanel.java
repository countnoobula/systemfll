package ProgramGUI.SectionBottom;

import javax.swing.JPanel;

import MainClasses.Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * This is the bottom section panel of the program.
 */
public class SectionBottomPanel extends JPanel {

    private GradientPaint gp1;
    private Point p;
    private Main m;
    private boolean allowed = false;

    public SectionBottomPanel(Main m2) {
        this.m = m2;
        this.setPreferredSize(new Dimension(0, 25));
        this.setMinimumSize(new Dimension(0, 25));

        gp1 = new GradientPaint(0, 0, new Color(51, 51, 51), 0, 25, new Color(0, 0, 0));

        this.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                if (e.getX() > m.getMainWindow().getWidth() - 20) {
                    p = m.getMainWindow().getLocation();
                    allowed = true;
                }

            }

            public void mouseReleased(MouseEvent e) {
                allowed = false;
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        });
        this.addMouseMotionListener(new MouseMotionListener() {

            public void mouseDragged(MouseEvent e) {
                if (allowed == true) {
                    m.getMainWindow().setSize((int) (e.getXOnScreen() - p.getX()), (int) (e.getYOnScreen() - p.getY()));
                }
            }

            public void mouseMoved(MouseEvent e) {
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(gp1);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());


    }
}
