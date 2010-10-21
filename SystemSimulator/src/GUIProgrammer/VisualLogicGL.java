package GUIProgrammer;

//imports
import MainClasses.Main;
import ProgramGUI.GUIComponents.Panes.GenericSystemPanel;
import ProgramGUI.GUIComponents.Buttons.SystemSmallTool;
import ProgramGUI.GUIComponents.Panes.BlockPropertyEditor;
import ProgramGUI.GUIComponents.Panes.NullPanel;
import Resources.Images.ImageLoader;
import VisualLogicSystem.LogicBlockEngine;
import VisualLogicSystem.LogicBlocks.Library;
import VisualLogicSystem.LogicLink;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Paint;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.media.opengl.GLAutoDrawable;
import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.media.opengl.GL2;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import translucentshapes.AWTUtilitiesWrapper;

public class VisualLogicGL extends GenericSystemPanel {

    private TopBar menu;
    private static LogicCanvas canvas;
    private Timer blinkTimer;
    private LogicBlocksDrawer drawer;
    private JLayeredPane panes;
    private NullPanel topLayer;
    private GridBagConstraints gc;
    private BlockPropertyEditor properties;
    private boolean blinker = false;
    private boolean isEnabled = true;
    private JFrame f;
    //the whole program
    private Main m;
    //some interesting variables
    SystemSmallTool tools[] = new SystemSmallTool[6];
    private int cycleNumber = 0;

    public VisualLogicGL(Main m2) {
        super();
        this.m = m2;
        GLCapabilities caps = new GLCapabilities(null);
        caps.setDoubleBuffered(true);

        this.setDoubleBuffered(false);

        //new instances
        menu = new TopBar();
        canvas = new LogicCanvas(caps);
        drawer = new LogicBlocksDrawer(m);
        panes = new JLayeredPane();
        topLayer = new NullPanel();
        
        //dont ask, its just required
        f = new JFrame();
        f.setUndecorated(true);
        f.setSize(0, 0);
        f.setVisible(true);
        properties = new BlockPropertyEditor(f);
        AWTUtilitiesWrapper.setWindowOpaque(properties, false);
        
        topLayer.setLayout(new GridBagLayout());

        panes.add(canvas, 0);
        panes.add(topLayer, 0);
        panes.moveToFront(topLayer);

        gc = new GridBagConstraints();
        //some pointers
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.NONE;

        //layout properties
        this.setLayout(new BorderLayout());
        this.add(menu, BorderLayout.NORTH);
        this.add(panes, BorderLayout.CENTER);
        this.add(drawer, BorderLayout.EAST);
        this.setFocusTraversalKeysEnabled(false);
        Library l = new Library();
        this.drawer.setLogicBlocks(l.getLibrary());
        tools[0].setSelected(true);

        //resize manager
        this.addComponentListener(new ComponentListener() {

            public void componentResized(ComponentEvent e) {
                canvas.setBounds(0, 0, panes.getWidth(), panes.getHeight());
                topLayer.setBounds(0, 0, panes.getWidth(), panes.getHeight());
            }

            public void componentMoved(ComponentEvent e) {
            }

            public void componentShown(ComponentEvent e) {
                properties.setVisible(true);
            }

            public void componentHidden(ComponentEvent e) {
                properties.setVisible(false);
            }
        });
        
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

    private class GLContext implements GLEventListener, MouseListener, MouseMotionListener {

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
                            canvas.repaint();
                            blinker = false;
                            repaintAgain = true;


                        } else {

                            selected = i;
                            found = true;
                            canvas.repaint();
                            blinker = true;
                            repaintAgain = true;


                        }
                    }
                }
                if (found == false) {
                    selected = -1;
                    blinker = false;
                    if (repaintAgain == true) {
                        canvas.repaint();
                        repaintAgain = false;
                    }


                }


            }
        }

        public void init(GLAutoDrawable glad) {

            GL2 gl = glad.getGL().getGL2();
            //Projection mode is for setting camera
            gl.glMatrixMode(GL2.GL_PROJECTION);

            //Modelview is for drawing
            gl.glMatrixMode(GL2.GL_MODELVIEW);
            //Depth is disabled because we are drawing in 2D
            gl.glDisable(GL2.GL_DEPTH_TEST);
            //Setting the clear color (in this case black)
            //and clearing the buffer with this set clear color
            gl.glClearColor(0.1f, 0.1f, 0.1f, 0.0f);

            //This defines how to blend when a transparent graphics
            //is placed over another (here we have blended colors of
            //two consecutively overlapping graphic objects)
            gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
            gl.glEnable(GL2.GL_BLEND);

        }

        public void dispose(GLAutoDrawable glad) {
        }

        public void display(GLAutoDrawable glad) {
            GL2 gl = glad.getGL().getGL2();

            gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
            gl.glColor3d(0.15, 0.15, 0.15);

            for (int i = 0; i < canvas.getWidth(); i += 30) {

                gl.glBegin(GL2.GL_LINES);
                gl.glVertex2d(i, 0);
                gl.glVertex2d(i, canvas.getHeight());
                gl.glEnd();
                gl.glBegin(GL2.GL_LINES);
                gl.glVertex2d(0, i);
                gl.glVertex2d(canvas.getWidth(), i);
                gl.glEnd();
            }

            gl.glEnable(GL2.GL_LINE_SMOOTH);

            //draw all the links
            for (int i = 0;
                    i < m.getEngineDepo().getLogicEngine().getLinkArraySize();
                    i++) {

                gl.glLineWidth(3.0f);
                m.getEngineDepo().getLogicEngine().getLink(i).drawGL(gl);

                m.getEngineDepo().getLogicEngine().getLink(i).drawAnchors(gl);
            }
            //draw all the blocks
            for (int i = 0;
                    i < m.getEngineDepo().getLogicEngine().getBlockArraySize();
                    i++) {
                m.getEngineDepo().getLogicEngine().getBlock(i).drawGL(gl);
            }
            gl.glDisable(GL2.GL_LINE_SMOOTH);

            gl.glColor4d(0, 0.75, 1.0,0.7);
            //draw flashing blocks
            if (blinker == true) {

                if (selected != -1) {

                    for (int j = 0;
                            j < m.getEngineDepo().getLogicEngine().getBlock(selected).getAmountBounds();
                            j++) {


                        //drawFlashingblocks
                        gl.glBegin(GL2.GL_POLYGON);

                        gl.glVertex2d((int) m.getEngineDepo().getLogicEngine().getBlock(selected).getConnectionBound(j).getX()
                                + m.getEngineDepo().getLogicEngine().getBlock(selected).getX(),
                                (int) m.getEngineDepo().getLogicEngine().getBlock(selected).getConnectionBound(j).getY()
                                + m.getEngineDepo().getLogicEngine().getBlock(selected).getY());
                        gl.glVertex2d((int) m.getEngineDepo().getLogicEngine().getBlock(selected).getConnectionBound(j).getX()
                                + m.getEngineDepo().getLogicEngine().getBlock(selected).getX() + 10,
                                (int) m.getEngineDepo().getLogicEngine().getBlock(selected).getConnectionBound(j).getY()
                                + m.getEngineDepo().getLogicEngine().getBlock(selected).getY());
                        gl.glVertex2d((int) m.getEngineDepo().getLogicEngine().getBlock(selected).getConnectionBound(j).getX()
                                + m.getEngineDepo().getLogicEngine().getBlock(selected).getX() + 10,
                                (int) m.getEngineDepo().getLogicEngine().getBlock(selected).getConnectionBound(j).getY()
                                + m.getEngineDepo().getLogicEngine().getBlock(selected).getY() + 10);
                        gl.glVertex2d((int) m.getEngineDepo().getLogicEngine().getBlock(selected).getConnectionBound(j).getX()
                                + m.getEngineDepo().getLogicEngine().getBlock(selected).getX(),
                                (int) m.getEngineDepo().getLogicEngine().getBlock(selected).getConnectionBound(j).getY()
                                + m.getEngineDepo().getLogicEngine().getBlock(selected).getY() + 10);
                        gl.glEnd();

                    }
                }
            }


            gl.glFlush();

        }

        public void reshape(GLAutoDrawable glad, int x, int y, int w, int h) {
            GL2 gl = glad.getGL().getGL2();
            //Projection mode is for setting camera
            gl.glMatrixMode(GL2.GL_PROJECTION);
            //This will set the camera for orthographic projection and allow 2D view
            //Our projection will be on 400 X 400 screen
            gl.glLoadIdentity();
            gl.glOrtho(0, canvas.getWidth(), canvas.getHeight(), 0, 0, 1);

        }

        public void mouseClicked(MouseEvent me) {
        }

        public void mouseEntered(MouseEvent me) {
        }

        public void mouseExited(MouseEvent me) {
        }

        public void mousePressed(MouseEvent me) {

            Point e = new Point(me.getX(), me.getY());
            if (isEnabled == true) {
                mousePoint = new Point(me.getX(), me.getY());

                if (menu.getSelected() == 0) {

                    blinker = false;


                    //check if the click was a block
                    for (int i = 0;
                            i < m.getEngineDepo().getLogicEngine().getBlockArraySize();
                            i++) {
                        if (m.getEngineDepo().getLogicEngine().getBlock(i).getBounds().contains(e)) {
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
                            if (m.getEngineDepo().getLogicEngine().getLink(i).getVirtualAnchor(j).contains(e)) {
                                selected2 = i;
                                selected3 = j;
                            }
                        }
                    }

                    //Okay, it look slike we found a Block, lets add some neccessary values
                    if (selected > -1) {
                        addX =
                                (int) (e.getX() - m.getEngineDepo().getLogicEngine().getBlock(selected).getBounds().getX());
                        addY =
                                (int) (e.getY() - m.getEngineDepo().getLogicEngine().getBlock(selected).getBounds().getY());
                    }
                } else if (menu.getSelected() == 1) {

                    int customSelection = -1;
                    for (int k = 0;
                            k < m.getEngineDepo().getLogicEngine().getBlockArraySize();
                            k++) {
                        if (m.getEngineDepo().getLogicEngine().getBlock(k).getBounds().contains(e)) {
                            customSelection = k;
                        }
                    }
                    if (customSelection > -1) {
                        //it is under a very specific block
                        if (m.getEngineDepo().getLogicEngine().getBlock(customSelection).getBounds().contains(mousePoint)
                                == true) {
                            int chosen = -1;
                            //loop connection points
                            for (int i = 0;
                                    i < m.getEngineDepo().getLogicEngine().getBlock(customSelection).getAmountBounds();
                                    i++) {

                                //check if its under a connection point
                                if (m.getEngineDepo().getLogicEngine().getBlock(customSelection).getConnectionBoundReal(i).contains(e)) {
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

                                    canvas.repaint();
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
                                                    (LogicLink) link.clone();
                                            m.getEngineDepo().getLogicEngine().addLogicLink(temp);

                                            m.getEngineDepo().getLogicEngine().getBlock(customSelection).addLink(temp);
                                            m.getEngineDepo().getLogicEngine().getBlock(selected2).addLink(temp);

                                            selected2 = -1;
                                        } catch (CloneNotSupportedException f) {
                                            System.out.println("Cant create a cloned version of the link");
                                        }
                                        //thats the end of it
                                        link = null;
                                        canvas.repaint();
                                    }
                                }
                            }
                        }
                    } //else it must be somewhere on the plane
                    else {

                        //it does exist, so add a point
                        if (link != null) {
                            link.addPoint(e);
                            canvas.repaint();
                        }
                    }
                } //!-------------- Delete ------------
                else if (menu.getSelected() == 2) {

                    loop:
                    for ( //check through blocks
                            int i = 0;
                            i < m.getEngineDepo().getLogicEngine().getBlockArraySize();
                            i++) {
                        if (m.getEngineDepo().getLogicEngine().getBlock(i).getBounds().contains(e)) {
                            selected = i;
                            break loop;
                        }
                    }
                    loop:
                    for ( //check if the click was a link Anchor, if so then we delete the link!
                            int i = 0;
                            i < m.getEngineDepo().getLogicEngine().getLinkArraySize();
                            i++) {
                        for (int j = 0;
                                j < m.getEngineDepo().getLogicEngine().getLink(i).getAmountOfAnchors();
                                j++) {
                            if (m.getEngineDepo().getLogicEngine().getLink(i).getVirtualAnchor(j).contains(e)) {
                                m.getEngineDepo().getLogicEngine().getLink(i).removeAnchor(j);
                                canvas.repaint();
                                break loop;
                            }
                        }
                    }
                    for ( //check if the click was a link, if so then we delete the link!
                            int i = 0;
                            i < m.getEngineDepo().getLogicEngine().getLinkArraySize();
                            i++) {
                        if (m.getEngineDepo().getLogicEngine().getLink(i).killTheLink(e,
                                10)) {
                            m.getEngineDepo().getLogicEngine().removeLink(m.getEngineDepo().getLogicEngine().getLink(i));
                            canvas.repaint();
                        }
                    }
                    //okay, we seem to have found something
                    if (selected > -1) {
                        m.getEngineDepo().getLogicEngine().removeBlock(m.getEngineDepo().getLogicEngine().getBlock(selected));
                        canvas.repaint();
                    }
                } // if we are gonna be adding points
                else if (menu.getSelected() == 4) {
                    for ( //check if the click was a link, if so then we delete the link!
                            int i = 0;
                            i < m.getEngineDepo().getLogicEngine().getLinkArraySize();
                            i++) {
                        if (m.getEngineDepo().getLogicEngine().getLink(i).getLineBoundaries((int) e.getX(),
                                (int) e.getY())) {

                            canvas.repaint();
                        }
                    }
                } else if (menu.getSelected() == 5) {
                    for ( //check if the click was a link, if so then we delete the link!
                            int i = 0;
                            i < m.getEngineDepo().getLogicEngine().getBlockArraySize();
                            i++) {
                        if (m.getEngineDepo().getLogicEngine().getBlock(i).getBounds().contains(e)) {
                            properties.setLogicBlock(m.getEngineDepo().getLogicEngine().getBlock(i));
                           
                            
                            

                        }
                    }
                }
            }
        }

        public void mouseReleased(MouseEvent me) {
            //unset the set variables

            selected = -1;

            addX = 0;
            addY = 0;
            if (selected3 > -1) {
                selected2 = -1;
                selected3 = -1;
            }
        }

        public void mouseMoved(MouseEvent me) {
            mousePoint = new Point(me.getX(), me.getY());
            if (menu.getSelected() == 1) {


                if (blinkTimer == null) {
                    blinkTimer =  new Timer(150, new hoverChecker());
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

        public void mouseDragged(MouseEvent me) {


            //check to see if there is a selection
            if (selected > -1 & menu.getSelected() == 0) {


                //methods which move the note around
                m.getEngineDepo().getLogicEngine().getBlock(selected).setLocation(me.getX()
                        - addX,
                        me.getY()
                        - addY);

                canvas.repaint();

            } //check to see if there is a selection
            else if (selected3 > -1 & menu.getSelected() == 0) {


                //methods which move the note around
                m.getEngineDepo().getLogicEngine().getLink(selected2).setPoint(selected3,
                        me.getPoint());

                canvas.repaint();

            }
        }

        public void mouseWheelMoved(MouseEvent me) {
        }
    }

    public class LogicCanvas extends GLCanvas {

        public LogicCanvas(GLCapabilities caps) {
            super(caps);
            GLContext gel = new GLContext();
            this.addGLEventListener(gel);
            this.addMouseListener(gel);
            this.addMouseMotionListener(gel);
        }
    }

    public static LogicCanvas getCanvas() {
        return canvas;
    }

    public static void setCanvas(LogicCanvas canvas) {
        VisualLogicGL.canvas = canvas;
    }

    public class DoEvent implements ActionListener {

        private Component c;

        public DoEvent(Component c) {
            this.c = c;
        }

        public void actionPerformed(ActionEvent e) {
        
        }
    }
}
