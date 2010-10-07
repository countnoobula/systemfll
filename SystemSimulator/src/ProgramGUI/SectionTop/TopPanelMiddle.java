package ProgramGUI.SectionTop;

import Resources.Images.ImageLoader;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;

import java.awt.Graphics2D;

import java.awt.Paint;
import java.awt.Shape;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import java.awt.Image;

import java.awt.Polygon;


import java.awt.Stroke;

import java.io.IOException;

import javax.imageio.ImageIO;

import MainClasses.Main;

import ProgramGUI.GUIComponents.SystemPopUp;

import ProgramGUI.GUIComponents.SystemPopupMenuItem;

import java.awt.event.MouseMotionListener;

public class TopPanelMiddle extends JPanel {

    private SystemPopUp pop1, pop2, pop3;

    private Shape shapes[] = new Shape[5];
    private boolean checkers[] = { false, false, false, false, false };
    private Image i1, i2;
    private Stroke stk1;
    private int y1[] = { 0, 0, 40, 40 };
    private int y2[] = { 10, 10, 40, 40 };
    private int y3[] = { 15, 15, 35, 35 };
    private Paint gp1, gp2, gp3, gp4, gp5, gp6;
    private Main m;
    private String[] buttons = { "File", "Develop", "Network" };


    public TopPanelMiddle(Main m2) {

        this.m = m2;
        this.setOpaque(false);

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


        //!------- set the colors -------!

        //Dark to light
        gp1 =
 new GradientPaint(0, 0, new Color(20, 20, 20), 0, 40, new Color(50, 50, 50));

        //Black to light
        gp2 =
 new GradientPaint(0, 0, new Color(0, 0, 0), 0, 25, new Color(50, 50, 50));

        //Black to dark
        gp5 =
 new GradientPaint(0, 15, new Color(0, 0, 0), 0, 35, new Color(30, 30, 30));


        //light blue
        gp3 = new Color(0, 192, 220);
        //dark blue
        gp4 = new Color(0, 142, 200);

        gp6 =
 new GradientPaint(0, 20, new Color(0, 192, 255), 0, 35, new Color(0, 142,
                                                                   200));


        //!-------- Graphic Objects --------

        stk1 = new BasicStroke(2.0f);

        int x2[] = { 60, 100, 130, 60 };
        shapes[1] = new Polygon(x2, y2, 4);
        int x3[] = { 120, 220, 240, 140 };
        shapes[2] = new Polygon(x3, y3, 4);
        int x4[] = { 225, 340, 360, 245 };
        shapes[3] = new Polygon(x4, y3, 4);


        //Loads the images for the graphics
        try {
            i1 =
  ImageIO.read(ImageLoader.class.getResource("TopBar/System-Small-Logo.png"));
            i2 =
  ImageIO.read(ImageLoader.class.getResource("TopBar/Core-button.png"));
        } catch (IOException e) {
            System.out.println("Could not load top bar images");
        }
        this.setOpaque(false);
        this.addMouseListener(new MouseListener() {
                public void mouseClicked(MouseEvent e) {
                }

                public void mousePressed(MouseEvent e) {


                    if (checkers[2] == true) {
                        pop1.setLocation((int)(140 +
                                               m.getMainWindow().getLocation().getX()),
                                         (int)(58 +
                                               m.getMainWindow().getLocation().getY()));
                        if (pop1.isVisible()) {
                            pop1.setVisible(false);
                        } else {
                            pop1.setVisible(true);

                            pop2.setVisible(false);
                            pop3.setVisible(false);
                        }
                    }
                    if (checkers[3] == true & m.getPrefs().getBoolean("enableDeveloperMode", true) == true) {
                        pop2.setLocation((int)(245 +
                                               m.getMainWindow().getLocation().getX()),
                                         (int)(58 +
                                               m.getMainWindow().getLocation().getY()));
                        if (pop2.isVisible()) {
                            pop2.setVisible(false);
                        } else {
                            pop2.setVisible(true);
                            pop1.setVisible(false);

                            pop3.setVisible(false);
                        }
                    }
                    if (checkers[4] == true) {
                        pop3.setLocation((int)(getWidth() - 260 +
                                               m.getMainWindow().getLocation().getX()),
                                         (int)(58 +
                                               m.getMainWindow().getLocation().getY()));
                        if (pop3.isVisible()) {
                            pop3.setVisible(false);
                        } else {
                            pop3.setVisible(true);
                            pop1.setVisible(false);
                            pop2.setVisible(false);

                        }
                    }
                }

                public void mouseReleased(MouseEvent e) {
                }

                public void mouseEntered(MouseEvent e) {
                }

                public void mouseExited(MouseEvent e) {
                    for (int i = 2; i < checkers.length; i++) {
                        checkers[i] = false;
                    }
                    repaint();
                }
            });
        this.addMouseMotionListener(new MouseMotionListener() {
                public void mouseDragged(MouseEvent e) {
                }

                public void mouseMoved(MouseEvent e) {
                    loop:
                    for (int i = 2; i < shapes.length; i++) {
                        if (shapes[i].contains(e.getPoint())) {
                            checkers[i] = true;
                            repaint();


                        } else {
                            if (checkers[i] == true) {
                                checkers[i] = false;
                                repaint();


                            }
                        }
                    }

                }
            });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;

        int x1[] = { 100, this.getWidth() - 100, this.getWidth() - 140, 140 };
        shapes[0] = new Polygon(x1, y1, 4);

        int x5[] =
        { this.getWidth() - 120, this.getWidth() - 240, this.getWidth() - 260,
          this.getWidth() - 140 };

        shapes[4] = new Polygon(x5, y3, 4);

        int buttonsX[] = { 150, 255, this.getWidth() - 230 };

        //draws the background image
        g2d.setPaint(gp1);
        g2d.fill(shapes[0]);

        g2d.setStroke(stk1);

        //!-------- Left Controls -------!

        //fills the left hand controls
        g2d.setPaint(gp2);
        g2d.fill(shapes[1]);
        g2d.fillRect(0, 10, 27, 30);
        g2d.fillRect(30, 10, 27, 30);

        //draws the left hand controls
        g2d.setPaint(Color.BLACK);
        g2d.draw(shapes[1]);
        g2d.drawRect(0, 10, 27, 30);
        g2d.drawRect(30, 10, 27, 30);

        //draws the letters and the image
        g2d.setFont(m.getFonts().getFont(1).deriveFont(20.0f));
        g2d.setPaint(Color.LIGHT_GRAY);
        g2d.drawString("G", 7, 34);
        g2d.drawString("S", 36, 34);
        g2d.drawImage(i2, 65, 13, null);

        //draws the little squares on the left controls
        g2d.setPaint(gp3);
        g2d.fillRect(5, 13, 4, 4);
        g2d.fillRect(35, 13, 4, 4);
        g2d.setPaint(gp4);
        g2d.fillRect(10, 13, 4, 4);
        g2d.fillRect(40, 13, 4, 4);

        //!-------- Menu Buttons -------!

        //draws the menu buttons
        g2d.setFont(m.getFonts().getFont(1).deriveFont(14.0f));
        for (int i = 2; i < shapes.length; i++) {
            
                
                
                if (checkers[i] == true) {
                    g2d.setPaint(gp6);
                    g2d.fill(shapes[i]);
                    g2d.setPaint(Color.BLACK);
                    g2d.drawString(buttons[i - 2], buttonsX[i - 2], 30);
                } else {
                    g2d.setPaint(gp5);
                    g2d.fill(shapes[i]);
                    g2d.setPaint(Color.LIGHT_GRAY);
                    g2d.drawString(buttons[i - 2], buttonsX[i - 2], 30);
                }
            
            


        }
        g2d.setPaint(Color.DARK_GRAY);
        g2d.draw(shapes[2]);
        g2d.draw(shapes[3]);
        g2d.draw(shapes[4]);


        //!-------- Other graphics things Buttons -------!

        g2d.drawImage(i1, (this.getWidth() / 2) - (i1.getWidth(null) / 2), 6,
                      null);
    }
}
