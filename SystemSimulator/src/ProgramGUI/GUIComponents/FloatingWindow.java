package ProgramGUI.GUIComponents;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;

public class FloatingWindow extends JFrame {

    Point p;
    boolean draggable = false;

    public FloatingWindow() {

        this.setAlwaysOnTop(true);
        this.setUndecorated(true);
        this.setSize(new Dimension(200, 200));
        this.setVisible(false);
        this.setBackground(new Color(0, 0, 0, 0));
        this.getRootPane().putClientProperty("apple.awt.draggableWindowBackground", Boolean.FALSE);
        this.setLayout(new BorderLayout());
        this.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                if(e.getY()<20){
                p = e.getPoint();
                draggable = true;
                }
            }

            public void mouseReleased(MouseEvent e) {
                draggable = false;
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        });
        this.addMouseMotionListener(new MouseMotionListener() {

            public void mouseDragged(MouseEvent e) {
                if(draggable == true){
                setLocation((int) (e.getXOnScreen() - p.getX()), (int) (e.getYOnScreen() - p.getY()));
                }
            }

            public void mouseMoved(MouseEvent e) {
            }
        });


    }

    public static void main(String[] args) {

        FloatingWindow f = new FloatingWindow();

    }
}
