package GUIProgrammer;


import MainClasses.Main;

import ProgramGUI.GUIComponents.GenericSystemPanel;

import ProgramGUI.GUIComponents.SystemSmallTool;

import Resources.Images.ImageLoader;

import VisualLogicSystem.LogicBlockEngine;
import VisualLogicSystem.LogicLink;

import VisualLogicSystem.LogicBlocks.Library;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;


public class VisualLogic extends GenericSystemPanel {

    private TopBar menu;
    private static LogicCanvas canvas;
    boolean blinker = false;
    private Timer blinkTimer;
    private LogicBlocksDrawer drawer;

    //the whole program
    Main m;

    public static void refresh() {
        canvas.reAdjust();
    }

    public VisualLogic(Main m2) {
        super();
        this.m = m2;

        //new instances
        menu = new TopBar();
        canvas = new LogicCanvas();
        drawer = new LogicBlocksDrawer(m);

        //layout properties
        this.setLayout(new BorderLayout());
        this.add(menu, BorderLayout.NORTH);
        this.add(canvas, BorderLayout.CENTER);
        this.add(drawer, BorderLayout.EAST);
        Library l = new Library();
        this.drawer.setLogicBlocks(l.getLibrary());

    }

    private class TopBar extends JPanel {
        Paint gp1;
        SystemSmallTool t1, t2, t3, t4, t5;

        private TopBar() {

            this.setLayout(null);


            try {
                t1 =
  new SystemSmallTool(ImageIO.read(ImageLoader.class.getResource("LogicGate/icon1.png")));
                t2 =
  new SystemSmallTool(ImageIO.read(ImageLoader.class.getResource("LogicGate/icon2.png")));
                t3 =
  new SystemSmallTool(ImageIO.read(ImageLoader.class.getResource("LogicGate/icon3.png")));
                t4 =
  new SystemSmallTool(ImageIO.read(ImageLoader.class.getResource("LogicGate/icon4.png")));
                t5 =
  new SystemSmallTool(ImageIO.read(ImageLoader.class.getResource("LogicGate/icon5.png")));

            } catch (Exception e) {
                System.out.println("Could not load the logic gate tools");
            }
            this.setPreferredSize(new Dimension(0, 30));
            gp1 =
 new GradientPaint(0, 0, new Color(20, 20, 20), 0, 30, new Color(40, 40, 40));

            //add the stuffs yo
            this.add(t1);
            this.add(t2);
            this.add(t3);
            this.add(t4);
            this.add(t5);

            ButtonGroup group = new ButtonGroup();
            group.add(t1);
            group.add(t2);
            group.add(t3);
            group.add(t4);
            group.add(t5);


            t1.setBounds(2, 2, 25, 25);
            t2.setBounds(29, 2, 25, 25);
            t5.setBounds(56, 2, 25, 25);
            t3.setBounds(83, 2, 25, 25);
            t4.setBounds(110, 2, 25, 25);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;
            g2d.setPaint(gp1);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }

        public int getSelected() {
            if (t1.isSelected()) {
                return 0;
            } else if (t2.isSelected()) {
                return 1;
            } else if (t3.isSelected()) {
                return 2;

            } else if (t4.isSelected()) {
                return 3;

            } else if (t5.isSelected()) {
                return 4;

            } else {
                return -1;
            }


        }
    }

    public class LogicCanvas extends JPanel {

        private BufferedImage bi;
        public Graphics2D big;
        private Paint gp1, gp2, gp3, gp4, gp5, gp6, gp7;
        private Stroke s1, s2;
        private LogicCanvas nested;

        //Object Manipulation Variables

        //the last position of the mouse
        private Point mousePoint;
        //  -1 if there is no selection
        private int selected = -1;
        private int selected2 = -1;
        private int selected3 = -1;

        private int addX = 0;
        private int addY = 0;


        //create a new object
        private LogicLink link = null;


        public LogicCanvas() {
            nested = this;
            //load the textured background
            Rectangle r = new Rectangle(0, 0, 25, 25);
            gp1 = new Color(30, 30, 30);
            gp4 = new Color(0, 192, 255, 180);
            gp5 = new Color(0, 192, 255);
            gp6 = new Color(0, 0, 0, 180);
            gp7 = new Color(0, 0, 0, 200);
            s1 = new BasicStroke(3.0f);
            float dash[] = { 10.0f };
            s2 =
  new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f,
                  dash, 0.0f);
            ;

            gp2 =
 new GradientPaint(0, 0, new Color(0, 0, 0), 0, 20, new Color(0, 0, 0, 0));
            gp3 =
 new GradientPaint(0, 0, new Color(0, 0, 0), 20, 0, new Color(0, 0, 0, 0));

            //initial heights and widths
            int width = 200;
            int height = 200;

            //create a new buffered image
            bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            //initialize the graphics Object for this
            big = bi.createGraphics();
            big.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                 RenderingHints.VALUE_ANTIALIAS_ON);
            this.setFocusTraversalKeysEnabled(false);


            //need this for rescaling the area
            this.addComponentListener(new ComponentListener() {

                    public void componentResized(ComponentEvent e) {
                        bi =
  new BufferedImage(nested.getWidth(), nested.getHeight(),
                    BufferedImage.TYPE_INT_RGB);
                        big = bi.createGraphics();
                        big.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                             RenderingHints.VALUE_ANTIALIAS_ON);
                        reAdjust();
                        repaint();

                    }

                    public void componentMoved(ComponentEvent e) {
                    }

                    public void componentShown(ComponentEvent e) {
                    }

                    public void componentHidden(ComponentEvent e) {
                    }
                });

            //need this for moving around the objects and it is HUGGGEGEGEGEGEEEEEEE!!!!!!!!!!!!!!!!!!!!!!!!!
            this.addMouseListener(new MouseListener() {

                    public void mouseClicked(MouseEvent e) {
                    }

                    //get ready for dragging the objects around

                    public void mousePressed(MouseEvent e) {
                        mousePoint = e.getPoint();

                        if (menu.getSelected() == 0) {

                            blinker = false;


                            //check if the click was a block
                            for (int i = 0;
                                 i < m.getEngineDepo().getLogicEngine().getBlockArraySize();
                                 i++) {
                                if (m.getEngineDepo().getLogicEngine().getBlock(i).getBounds().contains(e.getPoint())) {
                                    selected = i;

                                }

                            }


                            //check if the click was a link, if so then we are done!
                            for (int i = 0;
                                 i < m.getEngineDepo().getLogicEngine().getLinkArraySize();
                                 i++) {
                                for (int j = 0;
                                     j < m.getEngineDepo().getLogicEngine().getLink(i).getAmountOfAnchors();
                                     j++) {
                                    if (m.getEngineDepo().getLogicEngine().getLink(i).getVirtualAnchor(j).contains(e.getPoint())) {
                                        selected2 = i;
                                        selected3 = j;

                                    }
                                }


                            }


                            //Okay, it look slike we found a Block, lets add some neccessary values
                            if (selected > -1) {
                                addX =
(int)(e.getX() - m.getEngineDepo().getLogicEngine().getBlock(selected).getBounds().getX());
                                addY =
(int)(e.getY() - m.getEngineDepo().getLogicEngine().getBlock(selected).getBounds().getY());
                            }


                        }

                        else if (menu.getSelected() == 1) {

                            int customSelection = -1;
                            for (int k = 0;
                                 k < m.getEngineDepo().getLogicEngine().getBlockArraySize();
                                 k++) {
                                if (m.getEngineDepo().getLogicEngine().getBlock(k).getBounds().contains(e.getPoint())) {
                                    customSelection = k;
                                }
                            }
                            if (customSelection > -1) {
                                //it is under a very specific block
                                if (m.getEngineDepo().getLogicEngine().getBlock(customSelection).getBounds().contains(mousePoint) ==
                                    true) {

                                    int chosen = -1;

                                    //loop connection points
                                    for (int i = 0;
                                         i < m.getEngineDepo().getLogicEngine().getBlock(customSelection).getAmountBounds();
                                         i++) {

                                        //check if its under a connection point
                                        if (m.getEngineDepo().getLogicEngine().getBlock(customSelection).getConnectionBoundReal(i).contains(e.getPoint())) {
                                            chosen = i;

                                        }
                                    }

                                    //if it is indeed under a point :)
                                    if (chosen > -1) {


                                        //make a new object the first time
                                        if (link == null) {
                                            link = new LogicLink();
                                            selected2 = customSelection;


                                            link.setStartBlock(m.getEngineDepo().getLogicEngine().getBlock(customSelection),
                                                               chosen);

                                            canvas.reAdjust();
                                        } else {


                                            //add to the database
                                            if (LogicBlockEngine.canLinkBlocks(link.getStart(),
                                                                               link.getStartConnection(),
                                                                               m.getEngineDepo().getLogicEngine().getBlock(customSelection),
                                                                               chosen)) {

                                                link.setEndBlock(m.getEngineDepo().getLogicEngine().getBlock(customSelection),
                                                                 chosen);

                                                try {


                                                    LogicLink temp =
                                                        (LogicLink)link.clone();
                                                    m.getEngineDepo().getLogicEngine().addLogicLink(temp);

                                                    m.getEngineDepo().getLogicEngine().getBlock(customSelection).addLink(temp);
                                                    m.getEngineDepo().getLogicEngine().getBlock(selected2).addLink(temp);

                                                    selected2 = -1;
                                                } catch (CloneNotSupportedException f) {
                                                    System.out.println("Cant create a cloned version of the link");
                                                }
                                                //thats the end of it
                                                link = null;
                                                canvas.reAdjust();
                                            }
                                        }
                                    }
                                }
                            }

                            //else it must be somewhere on the plane
                            else {

                                //it does exist, so add a point
                                if (link != null) {
                                    link.addPoint(e.getPoint());
                                    canvas.reAdjust();

                                }
                            }
                        }
                        //!-------------- Delete ------------

                        else if (menu.getSelected() == 2) {

                            loop:
                            for (
                            //check through blocks
                                int i = 0;
                                i < m.getEngineDepo().getLogicEngine().getBlockArraySize();
                                i++) {
                                if (m.getEngineDepo().getLogicEngine().getBlock(i).getBounds().contains(e.getPoint())) {
                                    selected = i;
                                    break loop;
                                }

                            }

                            loop:
                            for (
                            //check if the click was a link Anchor, if so then we delete the link!
                                int i = 0;
                                i < m.getEngineDepo().getLogicEngine().getLinkArraySize();
                                i++) {
                                for (int j = 0;
                                     j < m.getEngineDepo().getLogicEngine().getLink(i).getAmountOfAnchors();
                                     j++) {
                                    if (m.getEngineDepo().getLogicEngine().getLink(i).getVirtualAnchor(j).contains(e.getPoint())) {
                                        m.getEngineDepo().getLogicEngine().getLink(i).removeAnchor(j);
                                        reAdjust();
                                        break loop;
                                    }
                                }


                            }
                            for (
                            //check if the click was a link, if so then we delete the link!
                                int i = 0;
                                i < m.getEngineDepo().getLogicEngine().getLinkArraySize();
                                i++) {
                                if (m.getEngineDepo().getLogicEngine().getLink(i).killTheLink(e.getPoint(),
                                                                                              10)) {
                                    m.getEngineDepo().getLogicEngine().removeLink(i);
                                    reAdjust();


                                }


                            }
                            //okay, we seem to have found something
                            if (selected > -1) {
                                m.getEngineDepo().getLogicEngine().removeBlock(m.getEngineDepo().getLogicEngine().getBlock(selected));
                                reAdjust();
                            }
                        }


                        // if we are gonna be adding points
                        else if (menu.getSelected() == 4) {
                            for (
                            //check if the click was a link, if so then we delete the link!
                                int i = 0;
                                i < m.getEngineDepo().getLogicEngine().getLinkArraySize();
                                i++) {
                                if (m.getEngineDepo().getLogicEngine().getLink(i).getLineBoundaries((int)e.getPoint().getX(),
                                                                                                    (int)e.getPoint().getY())) {

                                    reAdjust();
                                }


                            }
                        }
                    }

                    public void mouseReleased(MouseEvent e) {
                        //unset the set variables

                        selected = -1;

                        addX = 0;
                        addY = 0;
                        if (selected3 > -1) {
                            selected2 = -1;
                            selected3 = -1;
                        }


                    }

                    public void mouseEntered(MouseEvent e) {
                    }

                    public void mouseExited(MouseEvent e) {
                    }
                });
            this.addMouseMotionListener(new MouseMotionListener() {
                    public void mouseDragged(MouseEvent e) {

                        //check to see if there is a selection
                        if (selected > -1 & menu.getSelected() == 0) {


                            //methods which move the note around
                            m.getEngineDepo().getLogicEngine().getBlock(selected).setLocation(e.getX() -
                                                                                              addX,
                                                                                              e.getY() -
                                                                                              addY);

                            reAdjust();

                        }
                        //check to see if there is a selection
                        else if (selected3 > -1 & menu.getSelected() == 0) {


                            //methods which move the note around
                            m.getEngineDepo().getLogicEngine().getLink(selected2).setPoint(selected3,
                                                                                           e.getPoint());

                            reAdjust();

                        }


                    }

                    public void mouseMoved(MouseEvent e) {
                        mousePoint = e.getPoint();
                        if (menu.getSelected() == 1) {


                            if (blinkTimer == null) {
                                blinkTimer =
                                        new Timer(150, new hoverChecker());

                                blinkTimer.start();
                            }

                        } else {
                            if (blinkTimer != null) {
                                if (blinkTimer.isRunning()) {
                                    blinkTimer.stop();
                                    blinkTimer = null;
                                }
                            }
                        }
                    }
                });

        }

        boolean repaintAgain = false;

        private class hoverChecker implements ActionListener {

            public void actionPerformed(ActionEvent e) {
                boolean found = false;

                for (int i = 0;
                     i < m.getEngineDepo().getLogicEngine().getBlockArraySize();
                     i++) {

                    if (m.getEngineDepo().getLogicEngine().getBlock(i).getBounds().contains(mousePoint) ==
                        true) {

                        if (blinker == true) {


                            selected = i;
                            found = true;
                            canvas.reAdjust();
                            blinker = false;
                            repaintAgain = true;


                        } else {

                            selected = i;
                            found = true;
                            canvas.reAdjust();
                            blinker = true;
                            repaintAgain = true;


                        }
                    }
                }
                if (found == false) {
                    selected = -1;
                    blinker = false;
                    if (repaintAgain == true) {
                        canvas.reAdjust();
                        repaintAgain = false;
                    }


                }


            }

        }

        private void reAdjust() {

            big.setPaint(gp1);
            big.fillRect(0, 0, getWidth(), getHeight());
            big.setPaint(gp2);
            big.fillRect(0, 0, getWidth(), 20);
            big.setPaint(gp3);
            big.fillRect(0, 0, 20, getHeight());

            big.setStroke(s1);
            big.setPaint(gp5);

            //draw temporary link
            if (link != null) {
                link.paintLinks(big);
            }


            //draw all the links
            for (int i = 0;
                 i < m.getEngineDepo().getLogicEngine().getLinkArraySize();
                 i++) {
                m.getEngineDepo().getLogicEngine().getLink(i).paintLinks(big);
            }

            big.setPaint(gp7);
            big.setStroke(s2);
            //draw all the links
            for (int i = 0;
                 i < m.getEngineDepo().getLogicEngine().getLinkArraySize();
                 i++) {
                m.getEngineDepo().getLogicEngine().getLink(i).paintLinks(big);
            }
            big.setPaint(gp7);

            //draw all the anchors
            for (int i = 0;
                 i < m.getEngineDepo().getLogicEngine().getLinkArraySize();
                 i++) {
                m.getEngineDepo().getLogicEngine().getLink(i).paintAnchors(big);
            }


            //draw all the blocks
            for (int i = 0;
                 i < m.getEngineDepo().getLogicEngine().getBlockArraySize();
                 i++) {
                big.drawImage(m.getEngineDepo().getLogicEngine().getBlock(i).getGraphicsObject(),
                              m.getEngineDepo().getLogicEngine().getBlock(i).getX(),
                              m.getEngineDepo().getLogicEngine().getBlock(i).getY(),
                              null);
            }

            big.setPaint(gp4);
            if (blinker == true) {

                if (selected != -1) {

                    for (int j = 0;
                         j < m.getEngineDepo().getLogicEngine().getBlock(selected).getAmountBounds();
                         j++) {
                        canvas.big.fillRect((int)m.getEngineDepo().getLogicEngine().getBlock(selected).getConnectionBound(j).getX() +
                                            m.getEngineDepo().getLogicEngine().getBlock(selected).getX(),
                                            (int)m.getEngineDepo().getLogicEngine().getBlock(selected).getConnectionBound(j).getY() +
                                            m.getEngineDepo().getLogicEngine().getBlock(selected).getY(),
                                            10, 10);

                    }
                }
            }
            repaint();


        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(bi, 0, 0, this);

        }
    }


}
