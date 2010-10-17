package ProgramGUI.SectionTop;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;

import java.awt.Graphics2D;

import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import MainClasses.Main;
import ProgramGUI.GUIComponents.NullButton;
import ProgramGUI.GUIComponents.NullPanel;

import ProgramGUI.GUIComponents.SystemPopUp;

import ProgramGUI.GUIComponents.SystemPopupMenuItem;
import ProgramGUI.GUIComponents.TopPanelButton;
import Resources.Images.ImageLoader;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;

public class TopPanelMiddle extends JPanel {

    private SystemPopUp pop1, pop2, pop3;
    private Paint gp1,gp2,gp3,gp4;
    private Point p;
    private Main m;
    private NullButton bt1, bt2,end1,end2,end3;
    private GridBagConstraints gc;
    private TopPanelButton bt3,bt4,bt5;
    private Font f1;
    private Shape s1,s2;
    private int y1[] = {0,0,28,28};
    private int y2[] = {6,6,28,28};

    public TopPanelMiddle(Main m2) {

        this.m = m2;
        this.setOpaque(false);
        gc = new GridBagConstraints();
        bt1 = new NullButton("");
        bt2 = new NullButton("");
        end1 = new NullButton("");
        end2 = new NullButton("");
        end3 = new NullButton("");
        bt3 = new TopPanelButton(m,"Program");
        bt4 = new TopPanelButton(m,"Window");
        bt5 = new TopPanelButton(m,"Developer");

        bt1.setIcon(new ImageIcon(ImageLoader.class.getResource("TopBar/bt1.png")));
        bt2.setIcon(new ImageIcon(ImageLoader.class.getResource("TopBar/bt2.png")));

        pop1 = new SystemPopUp();
        pop1.add(new SystemPopupMenuItem("New Project"));
        pop1.add(new SystemPopupMenuItem("Load Project"));
        pop1.add(new SystemPopupMenuItem("Save Project"));
        pop1.add(new SystemPopupMenuItem("Revert Project"));

        pop2 = new SystemPopUp();
        pop2.add(new SystemPopupMenuItem("Visit Development Wiki"));
        pop2.add(new SystemPopupMenuItem("Contact the Developers"));
        pop2.add(new SystemPopupMenuItem("Enable Debugging mode"));
        pop2.add(new SystemPopupMenuItem("Make Jython Console Visible"));

        pop3 = new SystemPopUp();
        pop3.add(new SystemPopupMenuItem("Refresh Sockets"));



        gp1 = new GradientPaint(0, 5, new Color(51, 51, 51), 0, 25, new Color(0, 0, 0));
        gp2 = new GradientPaint(0, 5, new Color(251,251,251,100), 0, 25, new Color(200, 200, 200,100));
        gp3 = new GradientPaint(0, 5, new Color(40,40,40), 0, 25, new Color(10,10,10));
        gp4 = new GradientPaint(0, 8, new Color(0,192,255), 0, 25, new Color(0,142,200));

        f1 = m.getFonts().getFont(1).deriveFont(14.0f);

        bt2.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
               pop1.setLocation(  (int) ((int) bt2.getLocation().getX() + m.getMainWindow().getLocation().getX()), (int) ((int) (bt2.getLocation().getY() + bt2.getHeight()) + m.getMainWindow().getLocation().getY()));
               pop1.setVisible(true);
              
            }
        });
        this.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                p = e.getPoint();
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        });
        this.addMouseMotionListener(new MouseMotionListener() {

            public void mouseDragged(MouseEvent e) {
                m.getMainWindow().setLocation((int) (e.getXOnScreen() - p.getX()), (int) (e.getYOnScreen() - p.getY()));
            }

            public void mouseMoved(MouseEvent e) {
            }
        });
        this.setLayout(new GridBagLayout());
        gc.insets = new Insets(0, 0, 0, 0);
        gc.weightx = 0;
        this.add(bt1, gc);
        gc.insets = new Insets(0, 0, 0, 0);
        gc.weightx = 0;
        this.add(bt2, gc);
        gc.insets = new Insets(0, 40, 0, 0);
        gc.weightx = 0;
        this.add(bt3, gc);
        gc.insets = new Insets(0, 0, 0, -30);
        gc.weightx = 0;
        this.add(bt4, gc);
        gc.insets = new Insets(0, 10, 0, 0);
        gc.weightx = 0;
        this.add(bt5, gc);


        gc.insets = new Insets(0, 0, 0, 0);
        gc.weightx = 2;
        this.add(new NullPanel(), gc);

        this.add(end1, gc);
        gc.insets = new Insets(0, 0, 0, 0);
        gc.weightx = 0;
        this.add(end2, gc);
        gc.insets = new Insets(0, 0, 0, 0);
        gc.weightx = 0;
        this.add(end3, gc);
        gc.insets = new Insets(0, 0, 0, 0);
        gc.weightx = 0;


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int x1[] = {getWidth()-150,getWidth(),getWidth(),getWidth()-178};
        int x2[] = {getWidth()-148,getWidth()-80,getWidth()-80,getWidth()-170};
        s1 = new Polygon(x1,y1,4);
        s2 = new Polygon(x2,y2,4);
        g2d.setPaint(gp1);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setPaint(gp2);
        g2d.fill(s1);
        g2d.setPaint(gp3);
        g2d.fill(s2);
        g2d.setFont(f1);
        g2d.setPaint(gp4);
        g2d.fillRect(getWidth()-78, 6,6,22);
        g2d.fillRect(getWidth()-70, 6,6,22);
        g2d.fillRect(getWidth()-62, 6,6,22);
        g2d.setColor(Color.WHITE);
        g2d.drawString("CONN", getWidth()-140, 23);

    }
}
