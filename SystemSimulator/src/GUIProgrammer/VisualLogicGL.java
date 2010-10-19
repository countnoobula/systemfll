package GUIProgrammer;

import MainClasses.Main;
import ProgramGUI.GUIComponents.BlockProperties;

import ProgramGUI.GUIComponents.Panes.GenericSystemPanel;

import ProgramGUI.GUIComponents.Buttons.SystemSmallTool;

import Resources.Images.ImageLoader;

import VisualLogicSystem.LogicBlockEngine;
import VisualLogicSystem.LogicLink;
import VisualLogicSystem.LogicBlocks.Library;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.awt.image.BufferedImage;
import javax.media.opengl.GLAutoDrawable;

import javax.swing.Timer;


import javax.imageio.ImageIO;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;

public class VisualLogicGL extends GenericSystemPanel {

    private TopBar menu;
    private static LogicCanvas canvas;
    boolean blinker = false;
    private Timer blinkTimer;
    private LogicBlocksDrawer drawer;
    private boolean isEnabled = true;
    //the whole program
    Main m;
    //some interesting variables
    SystemSmallTool tools[] =
            new SystemSmallTool[6];
    int cycleNumber = 0;

    public static void refresh() {
        canvas.reAdjust();
    }

    public VisualLogicGL(Main m2) {
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
        this.setFocusTraversalKeysEnabled(false);
        Library l = new Library();
        this.drawer.setLogicBlocks(l.getLibrary());
        tools[0].setSelected(true);



    }

    private class TopBar extends JPanel {

        Paint gp1;

        private TopBar() {

            this.setLayout(null);


            try {
                tools[0] =
                        new SystemSmallTool(ImageIO.read(ImageLoader.class.getResource("LogicGate/icon1.png")));
                tools[1] =
                        new SystemSmallTool(ImageIO.read(ImageLoader.class.getResource("LogicGate/icon2.png")));
                tools[2] =
                        new SystemSmallTool(ImageIO.read(ImageLoader.class.getResource("LogicGate/icon3.png")));
                tools[3] =
                        new SystemSmallTool(ImageIO.read(ImageLoader.class.getResource("LogicGate/icon4.png")));
                tools[4] =
                        new SystemSmallTool(ImageIO.read(ImageLoader.class.getResource("LogicGate/icon5.png")));
                tools[5] =
                        new SystemSmallTool(ImageIO.read(ImageLoader.class.getResource("LogicGate/icon6.png")));

            } catch (Exception e) {
                System.out.println("Could not load the logic gate tools");
            }
            this.setPreferredSize(new Dimension(0, 30));
            gp1 =
                    new GradientPaint(0, 0, new Color(20, 20, 20), 0, 30, new Color(40, 40, 40));



            ButtonGroup group = new ButtonGroup();

            for (int i = 0; i < tools.length; i++) {
                group.add(tools[i]);
                this.add(tools[i]);
                tools[i].setBounds(2 + (i * 27), 2, 25, 25);
            }




        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setPaint(gp1);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }

        public int getSelected() {
            for (int i = 0; i < tools.length; i++) {
                if (tools[i].isSelected()) {
                    return i;
                }
            }
            return 0;


        }
    }

    private class GLContext implements GLEventListener {

        public void init(GLAutoDrawable glad) {
        }

        public void dispose(GLAutoDrawable glad) {
        }

        public void display(GLAutoDrawable glad) {
        }

        public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) {
        }
    }

    public class LogicCanvas extends GLCanvas {

        private BufferedImage bi;
        public Graphics2D big;
        private Paint gp1, gp2, gp3, gp4, gp5, gp6, gp7;
        private Stroke s1, s2, s3;
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
        private GridBagConstraints gc;

        public LogicCanvas() {

            this.addGLEventListener(new GLContext());
//
//            this.addMouseListener(new MouseListener() {
//
//                public void mouseClicked(MouseEvent e) {
//                }
//
//                //get ready for dragging the objects around
//                public void mousePressed(MouseEvent e) {
//
//                    if (isEnabled == true) {
//                        mousePoint = new Point(e.getX(), e.getY());
//
//                        if (menu.getSelected() == 0) {
//
//                            blinker = false;
//
//
//                            //check if the click was a block
//                            for (int i = 0;
//                                    i < m.getEngineDepo().getLogicEngine().getBlockArraySize();
//                                    i++) {
//                                if (m.getEngineDepo().getLogicEngine().getBlock(i).getBounds().contains(new Point(e.getX(), e.getY()))) {
//                                    selected = i;
//
//                                }
//
//                            }
//
//
//                            //check if the click was a link, if so then we are done!
//                            for (int i = 0;
//                                    i < m.getEngineDepo().getLogicEngine().getLinkArraySize();
//                                    i++) {
//                                for (int j = 0;
//                                        j < m.getEngineDepo().getLogicEngine().getLink(i).getAmountOfAnchors();
//                                        j++) {
//                                    if (m.getEngineDepo().getLogicEngine().getLink(i).getVirtualAnchor(j).contains(new Point(e.getX(), e.getY()))) {
//                                        selected2 = i;
//                                        selected3 = j;
//
//                                    }
//                                }
//
//
//                            }
//
//
//                            //Okay, it look slike we found a Block, lets add some neccessary values
//                            if (selected > -1) {
//                                addX =
//                                        (int) (e.getX() - m.getEngineDepo().getLogicEngine().getBlock(selected).getBounds().getX());
//                                addY =
//                                        (int) (e.getY() - m.getEngineDepo().getLogicEngine().getBlock(selected).getBounds().getY());
//                            }
//
//
//                        } else if (menu.getSelected() == 1) {
//
//                            int customSelection = -1;
//                            for (int k = 0;
//                                    k < m.getEngineDepo().getLogicEngine().getBlockArraySize();
//                                    k++) {
//                                if (m.getEngineDepo().getLogicEngine().getBlock(k).getBounds().contains(new Point(e.getX(), e.getY()))) {
//                                    customSelection = k;
//                                }
//                            }
//                            if (customSelection > -1) {
//                                //it is under a very specific block
//                                if (m.getEngineDepo().getLogicEngine().getBlock(customSelection).getBounds().contains(mousePoint)
//                                        == true) {
//
//                                    int chosen = -1;
//
//                                    //loop connection points
//                                    for (int i = 0;
//                                            i < m.getEngineDepo().getLogicEngine().getBlock(customSelection).getAmountBounds();
//                                            i++) {
//
//                                        //check if its under a connection point
//                                        if (m.getEngineDepo().getLogicEngine().getBlock(customSelection).getConnectionBoundReal(i).contains(new Point(e.getX(), e.getY()))) {
//                                            chosen = i;
//
//                                        }
//                                    }
//
//                                    //if it is indeed under a point :)
//                                    if (chosen > -1) {
//
//
//                                        //make a new object the first time
//                                        if (link == null) {
//                                            link = new LogicLink();
//                                            selected2 = customSelection;
//
//
//                                            link.setStartBlock(m.getEngineDepo().getLogicEngine().getBlock(customSelection),
//                                                    chosen);
//
//                                            canvas.reAdjust();
//                                        } else {
//
//
//                                            //add to the database
//                                            if (LogicBlockEngine.canLinkBlocks(link.getStart(),
//                                                    link.getStartConnection(),
//                                                    m.getEngineDepo().getLogicEngine().getBlock(customSelection),
//                                                    chosen)) {
//
//                                                link.setEndBlock(m.getEngineDepo().getLogicEngine().getBlock(customSelection),
//                                                        chosen);
//
//                                                try {
//
//
//                                                    LogicLink temp =
//                                                            (LogicLink) link.clone();
//                                                    m.getEngineDepo().getLogicEngine().addLogicLink(temp);
//
//                                                    m.getEngineDepo().getLogicEngine().getBlock(customSelection).addLink(temp);
//                                                    m.getEngineDepo().getLogicEngine().getBlock(selected2).addLink(temp);
//
//                                                    selected2 = -1;
//                                                } catch (CloneNotSupportedException f) {
//                                                    System.out.println("Cant create a cloned version of the link");
//                                                }
//                                                //thats the end of it
//                                                link = null;
//                                                canvas.reAdjust();
//                                            }
//                                        }
//                                    }
//                                }
//                            } //else it must be somewhere on the plane
//                            else {
//
//                                //it does exist, so add a point
//                                if (link != null) {
//                                    link.addPoint(new Point(e.getX(), e.getY()));
//                                    canvas.reAdjust();
//
//                                }
//                            }
//                        } //!-------------- Delete ------------
//                        else if (menu.getSelected() == 2) {
//
//                            loop:
//                            for ( //check through blocks
//                                    int i = 0;
//                                    i < m.getEngineDepo().getLogicEngine().getBlockArraySize();
//                                    i++) {
//                                if (m.getEngineDepo().getLogicEngine().getBlock(i).getBounds().contains(new Point(e.getX(), e.getY()))) {
//                                    selected = i;
//                                    break loop;
//                                }
//
//                            }
//
//                            loop:
//                            for ( //check if the click was a link Anchor, if so then we delete the link!
//                                    int i = 0;
//                                    i < m.getEngineDepo().getLogicEngine().getLinkArraySize();
//                                    i++) {
//                                for (int j = 0;
//                                        j < m.getEngineDepo().getLogicEngine().getLink(i).getAmountOfAnchors();
//                                        j++) {
//                                    if (m.getEngineDepo().getLogicEngine().getLink(i).getVirtualAnchor(j).contains(new Point(e.getX(), e.getY()))) {
//                                        m.getEngineDepo().getLogicEngine().getLink(i).removeAnchor(j);
//                                        reAdjust();
//                                        break loop;
//                                    }
//                                }
//
//
//                            }
//                            for ( //check if the click was a link, if so then we delete the link!
//                                    int i = 0;
//                                    i < m.getEngineDepo().getLogicEngine().getLinkArraySize();
//                                    i++) {
//                                if (m.getEngineDepo().getLogicEngine().getLink(i).killTheLink(new Point(e.getX(), e.getY()),
//                                        10)) {
//                                    m.getEngineDepo().getLogicEngine().removeLink(m.getEngineDepo().getLogicEngine().getLink(i));
//                                    reAdjust();
//
//
//                                }
//
//
//                            }
//                            //okay, we seem to have found something
//                            if (selected > -1) {
//                                m.getEngineDepo().getLogicEngine().removeBlock(m.getEngineDepo().getLogicEngine().getBlock(selected));
//                                reAdjust();
//                            }
//                        } // if we are gonna be adding points
//                        else if (menu.getSelected() == 4) {
//                            for ( //check if the click was a link, if so then we delete the link!
//                                    int i = 0;
//                                    i < m.getEngineDepo().getLogicEngine().getLinkArraySize();
//                                    i++) {
//                                if (m.getEngineDepo().getLogicEngine().getLink(i).getLineBoundaries((int) e.getX(),
//                                        (int) e.getY())) {
//
//                                    reAdjust();
//                                }
//
//
//                            }
//                        } else if (menu.getSelected() == 5) {
//                            for ( //check if the click was a link, if so then we delete the link!
//                                    int i = 0;
//                                    i < m.getEngineDepo().getLogicEngine().getBlockArraySize();
//                                    i++) {
//                                if (m.getEngineDepo().getLogicEngine().getBlock(i).getBounds().contains(new Point(e.getX(), e.getY()))) {
//
//                                    BlockProperties p = m.getEngineDepo().getLogicEngine().getBlock(i).getProperties();
//                                    p.attatchActionListener(new DoEvent(p));
//                                    isEnabled = false;
////                                canvas.add(p, gc);
////                                canvas.revalidate();
//
//
//                                }
//                            }
//                        }
//                    }
//                }
//
//                public void mouseReleased(MouseEvent e) {
//                    //unset the set variables
//
//                    selected = -1;
//
//                    addX = 0;
//                    addY = 0;
//                    if (selected3 > -1) {
//                        selected2 = -1;
//                        selected3 = -1;
//                    }
//
//
//                }
//
//                public void mouseEntered(MouseEvent e) {
//                }
//
//                public void mouseExited(MouseEvent e) {
//                }
//
//                public void mouseMoved(MouseEvent me) {
//                    throw new UnsupportedOperationException("Not supported yet.");
//                }
//
//                public void mouseDragged(MouseEvent me) {
//                    throw new UnsupportedOperationException("Not supported yet.");
//                }
//
//                public void mouseWheelMoved(MouseEvent me) {
//                    throw new UnsupportedOperationException("Not supported yet.");
//                }
//            });
//            this.addMouseMotionListener(new MouseMotionListener() {
//
//                public void mouseDragged(MouseEvent e) {
//
//                    //check to see if there is a selection
//                    if (selected > -1 & menu.getSelected() == 0) {
//
//
//                        //methods which move the note around
//                        m.getEngineDepo().getLogicEngine().getBlock(selected).setLocation(e.getX()
//                                - addX,
//                                e.getY()
//                                - addY);
//
//                        reAdjust();
//
//                    } //check to see if there is a selection
//                    else if (selected3 > -1 & menu.getSelected() == 0) {
//
//
//                        //methods which move the note around
//                        m.getEngineDepo().getLogicEngine().getLink(selected2).setPoint(selected3,
//                                new Point(e.getX(), e.getY()));
//
//                        reAdjust();
//
//                    }
//
//
//                }
//
//                public void mouseMoved(MouseEvent e) {
//                    mousePoint = new Point(e.getX(), e.getY());
//                    if (menu.getSelected() == 1) {
//
//
//                        if (blinkTimer == null) {
//                            blinkTimer =
//                                    new Timer(150, new hoverChecker());
//
//                            blinkTimer.start();
//                        }
//
//                    } else {
//                        if (blinkTimer != null) {
//                            if (blinkTimer.isRunning()) {
//                                blinkTimer.stop();
//                                blinkTimer = null;
//                            }
//                        }
//                    }
//                }
//
//                public void mouseDragged(java.awt.event.MouseEvent e) {
//                    throw new UnsupportedOperationException("Not supported yet.");
//                }
//
//                public void mouseMoved(java.awt.event.MouseEvent e) {
//                    throw new UnsupportedOperationException("Not supported yet.");
//                }
//            });

        }
        boolean repaintAgain = false;

        private class hoverChecker implements ActionListener {

            public void actionPerformed(ActionEvent e) {
                boolean found = false;

                for (int i = 0;
                        i < m.getEngineDepo().getLogicEngine().getBlockArraySize();
                        i++) {

                    if (m.getEngineDepo().getLogicEngine().getBlock(i).getBounds().contains(mousePoint)
                            == true) {

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
        int spacingX = 40;

        private void reAdjust() {
        }
    }

    public class DoEvent implements ActionListener {

        Component c;

        public DoEvent(Component c) {
            this.c = c;
        }

        public void actionPerformed(ActionEvent e) {
            isEnabled = true;

        }
    }
}
