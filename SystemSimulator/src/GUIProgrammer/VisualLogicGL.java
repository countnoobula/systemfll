package GUIProgrammer;

//imports
import MainClasses.Main;
import ProgramGUI.GUIComponents.Panes.GenericSystemPanel;
import ProgramGUI.GUIComponents.Buttons.SystemSmallTool;
import ProgramGUI.GUIComponents.Panes.BlockPropertyEditor;
import ProgramGUI.GUIComponents.Panes.NullPanel;
import Resources.Images.ImageLoader;
import VisualLogicSystem.DataBlockSystem.DataBlock;
import VisualLogicSystem.DataBlockSystem.DataLink;
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
import java.awt.MenuItem;
import java.awt.Paint;
import java.awt.Point;
import java.awt.PopupMenu;
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
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
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
    private PopupMenu pop;
    private JFrame f;
    //the whole program
    private Main m;
    //Object Manipulation Variables
    //the last position of the mouse
    private Point mousePoint;
    //some interesting variables
    SystemSmallTool tools[] = new SystemSmallTool[7];

    public VisualLogicGL(Main m2) {
        super();
        this.m = m2;
        GLProfile.initSingleton();
        GLProfile glp = GLProfile.getDefault();
        GLCapabilities caps = new GLCapabilities(glp);
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
                tools[0] = new SystemSmallTool(ImageIO.read(ImageLoader.class.getResource("LogicGate/icon1.png")));
                tools[1] = new SystemSmallTool(ImageIO.read(ImageLoader.class.getResource("LogicGate/icon2.png")));
                tools[2] = new SystemSmallTool(ImageIO.read(ImageLoader.class.getResource("LogicGate/icon3.png")));
                tools[3] = new SystemSmallTool(ImageIO.read(ImageLoader.class.getResource("LogicGate/icon4.png")));
                tools[4] = new SystemSmallTool(ImageIO.read(ImageLoader.class.getResource("LogicGate/icon5.png")));
                tools[5] = new SystemSmallTool(ImageIO.read(ImageLoader.class.getResource("LogicGate/icon6.png")));
                tools[6] = new SystemSmallTool(ImageIO.read(ImageLoader.class.getResource("LogicGate/icon7.png")));

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

        //  -1 if there is no selection
        private int selected1 = -1; //logic block
        private int selected2 = -1; //logic link
        private int selected3 = -1; //logic link anchor
        private int selected4 = -1; //Data block
        private int selected5 = -1; //logic block variable connection point
        private int selected6 = -1; //data link
        private int selected7 = -1; //data link anchor
        private int selected8 = -1; //logic block standard connection point
        //position variables
        private int addX = 0;
        private int addY = 0;
        //tempory files needed
        private LogicLink link1 = null;
        private DataLink link2 = null;
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
                            selected1 = i;
                            found = true;
                            canvas.repaint();
                            blinker = false;
                            repaintAgain = true;
                        } else {

                            selected1 = i;
                            found = true;
                            canvas.repaint();
                            blinker = true;
                            repaintAgain = true;
                        }
                    }
                }
                if (found == false) {
                    selected1 = -1;
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

            //set up the canvas
            gl.glMatrixMode(GL2.GL_MODELVIEW);
            gl.glDisable(GL2.GL_DEPTH_TEST);
            gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
            gl.glEnable(GL2.GL_BLEND);
            gl.glClearColor(0.1f, 0.1f, 0.1f, 0.0f);
            gl.glEnable(GL2.GL_LINE_SMOOTH);

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

            //draw all the links
            for (int i = 0;i < m.getEngineDepo().getLogicEngine().getLinkArraySize();i++) {

                gl.glLineWidth(3.0f);
                m.getEngineDepo().getLogicEngine().getLink(i).drawGL(gl);

                m.getEngineDepo().getLogicEngine().getLink(i).drawAnchors(gl);
            }
            //draw all the logic blocks
            for (int i = 0;i < m.getEngineDepo().getLogicEngine().getBlockArraySize();i++) {
                m.getEngineDepo().getLogicEngine().getBlock(i).drawGL(gl);
            }
            //draw all the data links
            for (int i = 0;i < m.getEngineDepo().getLogicEngine().getDataLinkSize();i++) {
                m.getEngineDepo().getLogicEngine().getDataLink(i).drawGL(gl);
            }
            //draw all the data blocks
            for (int i = 0;i < m.getEngineDepo().getLogicEngine().getDatBlocksSize();i++) {
                m.getEngineDepo().getLogicEngine().getDataBlock(i).drawGL(gl);
            }

            gl.glColor4d(0, 0.75, 1.0, 0.7);
            //draw flashing blocks
            if (blinker == true) {

                if (selected1 != -1) {

                    for (int j = 0;j < m.getEngineDepo().getLogicEngine().getBlock(selected1).getAmountBounds();j++) {

                        //drawFlashingblocks
                        gl.glBegin(GL2.GL_POLYGON);

                        gl.glVertex2d((int) m.getEngineDepo().getLogicEngine().getBlock(selected1).getConnectionBound(j).getX()
                                + m.getEngineDepo().getLogicEngine().getBlock(selected1).getX(),
                                (int) m.getEngineDepo().getLogicEngine().getBlock(selected1).getConnectionBound(j).getY()
                                + m.getEngineDepo().getLogicEngine().getBlock(selected1).getY());
                        gl.glVertex2d((int) m.getEngineDepo().getLogicEngine().getBlock(selected1).getConnectionBound(j).getX()
                                + m.getEngineDepo().getLogicEngine().getBlock(selected1).getX() + 10,
                                (int) m.getEngineDepo().getLogicEngine().getBlock(selected1).getConnectionBound(j).getY()
                                + m.getEngineDepo().getLogicEngine().getBlock(selected1).getY());
                        gl.glVertex2d((int) m.getEngineDepo().getLogicEngine().getBlock(selected1).getConnectionBound(j).getX()
                                + m.getEngineDepo().getLogicEngine().getBlock(selected1).getX() + 10,
                                (int) m.getEngineDepo().getLogicEngine().getBlock(selected1).getConnectionBound(j).getY()
                                + m.getEngineDepo().getLogicEngine().getBlock(selected1).getY() + 10);
                        gl.glVertex2d((int) m.getEngineDepo().getLogicEngine().getBlock(selected1).getConnectionBound(j).getX()
                                + m.getEngineDepo().getLogicEngine().getBlock(selected1).getX(),
                                (int) m.getEngineDepo().getLogicEngine().getBlock(selected1).getConnectionBound(j).getY()
                                + m.getEngineDepo().getLogicEngine().getBlock(selected1).getY() + 10);
                        gl.glEnd();

                    }
                }
            }


            //end the graphics stuffs
            gl.glFlush();
            

        }

        public void reshape(GLAutoDrawable glad, int x, int y, int w, int h) {
            GL2 gl = glad.getGL().getGL2();
            //Projection mode is for setting camera
            gl.glMatrixMode(GL2.GL_PROJECTION);
            //This will set the camera for orthographic projection and allow 2D view

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

            mousePoint = new Point(me.getX(), me.getY());

            //!------ Begin checking ------!

            loop:
            //check if the click was a logic block
            for (int i = 0;i < m.getEngineDepo().getLogicEngine().getBlockArraySize();i++) {
                if (m.getEngineDepo().getLogicEngine().getBlock(i).getBounds().contains(me.getPoint())) {
                    selected1 = i;
                    break loop;
                }
            }
            loop:
            //check if the click was a data block
            for (int i = 0;i < m.getEngineDepo().getLogicEngine().getDatBlocksSize();i++) {
                if (m.getEngineDepo().getLogicEngine().getDataBlock(i).getBounds().contains(me.getPoint())) {
                    selected4 = i;
                    break loop;
                }
            }
            loop:
            //check if the click was a data link anchor
            for (int i = 0;i < m.getEngineDepo().getLogicEngine().getDataLinkSize();i++) {
                for (int j = 0;j < m.getEngineDepo().getLogicEngine().getDataLink(i).getPointsSize();j++) {
                    if (m.getEngineDepo().getLogicEngine().getDataLink(i).getPointBounds(j).contains(me.getPoint())) {
                        selected7 = j;
                        selected6 = i;
                        break loop;
                    }
                }
            }

            loop:
            //check if the click was a link anchor
            for (int i = 0;i < m.getEngineDepo().getLogicEngine().getLinkArraySize();i++) {
                for (int j = 0; j < m.getEngineDepo().getLogicEngine().getLink(i).getAmountOfAnchors();j++) {
                    if (m.getEngineDepo().getLogicEngine().getLink(i).getVirtualAnchor(j).contains(me.getPoint())) {
                        selected2 = i;
                        selected3 = j;
                        break loop;
                    }
                }
            }

            //check if the click was a connection bound
            for (int i = 0;i < m.getEngineDepo().getLogicEngine().getBlockArraySize();i++) {
                //check if it was a standard logic block connection point
                loop:
                for (int j = 0;j < m.getEngineDepo().getLogicEngine().getBlock(i).getAmountBounds();j++) {

                    //check if its under a very specific connection point
                    if (m.getEngineDepo().getLogicEngine().getBlock(i).getConnectionBoundReal(j).contains(me.getPoint())) {
                        selected8 = j;
                        selected1 = i;
                        break loop;
                    }
                }
            }

            //check if the click was a link rect anchor
            for (int i = 0;i < m.getEngineDepo().getLogicEngine().getBlockArraySize();i++) {
                //check if it was a standard logic block connection point
                loop:
                for (int j = 0;j < m.getEngineDepo().getLogicEngine().getBlock(i).getVariablePointsSize(); j++) {

                    //check if its under a very specific connection point
                    if (m.getEngineDepo().getLogicEngine().getBlock(i).getVariableConnectRect(j).contains(me.getPoint())) {
                        selected5 = j;
                        selected1 = i;
                        System.out.println("found it");
                        break loop;
                    }
                }
            }


            //for displaying the pop up menu
            if (me.getButton() == MouseEvent.BUTTON3) {
                pop.show(canvas, me.getX(), me.getY());
            } else {


                if (isEnabled == true) {



                    //!------ mOVE TOOL -----!
                    if (menu.getSelected() == 0) {

                        blinker = false;

                        //Okay, it look slike we found a logic Block, lets add some neccessary values
                        if (selected1 > -1) {
                            properties.setVisible(true);
                            properties.setLogicBlock(m.getEngineDepo().getLogicEngine().getBlock(selected1));
                            addX = (int) (me.getX() - m.getEngineDepo().getLogicEngine().getBlock(selected1).getBounds().getX());
                            addY = (int) (me.getY() - m.getEngineDepo().getLogicEngine().getBlock(selected1).getBounds().getY());
                        } //Okay, it look slike we found a data Block, lets add some neccessary values
                        else if (selected4 > -1) {
                            addX = (int) (me.getX() - m.getEngineDepo().getLogicEngine().getDataBlock(selected4).getBounds().getX());
                            addY = (int) (me.getY() - m.getEngineDepo().getLogicEngine().getDataBlock(selected4).getBounds().getY());
                        }



                        //!------ CONNECTOR TOOL -----!
                    } else if (menu.getSelected() == 1) {

                        //if it is indeed under a logic block
                        if (selected1 > -1 & selected5 == -1) {

                            //if it is indeed under a point :)
                            if (selected8 > -1) {

                                //make a new object the first time (no link exists yet)
                                if (link1 == null) {
                                    link1 = new LogicLink();

                                    //add the logic block and its corresponding connection point to the link
                                    link1.setStartBlock(m.getEngineDepo().getLogicEngine().getBlock(selected1),selected8);

                                    canvas.repaint();

                                    //however, if a link exists and it is under a block, it means that
                                    //we are now signing off the link and then adding it to the database
                                } else {


                                    //check to see if the links can connect first
                                    if (LogicBlockEngine.canLinkBlocks(link1.getStart(),
                                            link1.getStartConnection(),
                                            m.getEngineDepo().getLogicEngine().getBlock(selected1),
                                            selected8)) {

                                        //add the end of the logic link
                                        link1.setEndBlock(m.getEngineDepo().getLogicEngine().getBlock(selected1),selected8);

                                        try {

                                            LogicLink temp = (LogicLink) link1.clone();
                                            temp.getStart().addLink(temp);
                                            temp.getEnd().addLink(temp);

                                            //add to the database
                                            m.getEngineDepo().getLogicEngine().addLogicLink(temp);

                                            //this error should never actually appear
                                        } catch (CloneNotSupportedException f) {
                                            System.out.println("Cant create a cloned version of the link");
                                        }
                                        //thats the end of it
                                        link1 = null;
                                        canvas.repaint();
                                        //connection blocks could not link, display error
                                    } else {
                                        JOptionPane.showMessageDialog(canvas,
                                                "You cant connect these 2 blocks\n"
                                                + "in this manner.");
                                        //dispose link
                                        link1 = null;
                                        canvas.repaint();
                                    }
                                }
                            } //else it must be somewhere on the canvas (not on a logic block)
                            else {

                                //it does exist, so add a point
                                if (link1 != null) {
                                    link1.addPoint(me.getPoint());
                                    canvas.repaint();
                                }
                            }
                        } else if (selected5 > -1 | selected4 > -1) {
                            System.out.println("underneith");
                            if (link2 == null) {
                                if (selected4 > -1) {
                                    link2 = new DataLink();
                                    link2.setDb(m.getEngineDepo().getLogicEngine().getDataBlock(selected4));
                                    link2.addPoint(m.getEngineDepo().getLogicEngine().getDataBlock(selected4).getConnectionPoint());
                                    System.out.println("added a point to the link");
                                } else if (selected5 > -1) {
                                    link2 = new DataLink();
                                    link2.setRect(m.getEngineDepo().getLogicEngine().getBlock(selected1).getVariableConnectRect(selected5));
                                    link2.setVariableConnectionPoint(m.getEngineDepo().getLogicEngine().getBlock(selected1).getVariableConnectionPoint(selected5));
                                }
                            } else if (link2 != null) {
                                if (selected4 > -1) {

                                    link2.setDb(m.getEngineDepo().getLogicEngine().getDataBlock(selected4));
                                    link2.addPoint(m.getEngineDepo().getLogicEngine().getDataBlock(selected4).getConnectionPoint());
                                } else if (selected5 > -1) {

                                    link2.setRect(m.getEngineDepo().getLogicEngine().getBlock(selected1).getVariableConnectRect(selected5));
                                    link2.setVariableConnectionPoint(m.getEngineDepo().getLogicEngine().getBlock(selected1).getVariableConnectionPoint(selected5));
                                }

                                try {


                                    DataLink temp = (DataLink) link2.clone();
                                    m.getEngineDepo().getLogicEngine().getDataBlock(temp.getDb()).addDataLink(temp);
                                    temp.createLink();

                                    //add to the database
                                    m.getEngineDepo().getLogicEngine().addDataLink(temp);

                                    System.out.println("added the link to database");
                                    link2 = null;
                                    canvas.repaint();
                                    //this error should never actually appear
                                } catch (CloneNotSupportedException f) {
                                    System.out.println("Cant create a cloned version of the data link");
                                }
                            }

                        } else {
                            if (link1 != null) {
                                link1.addPoint(me.getPoint());
                                System.out.println("added a link1 point");
                            }
                            if (link2 != null) {
                                link2.addPoint(me.getPoint());
                                System.out.println("added a link2 point");
                            }

                        }



                        //!------- DELETE --------
                    } else if (menu.getSelected() == 2) {

                        //delete log link anchor
                        if (selected3 > -1) {
                            m.getEngineDepo().getLogicEngine().getLink(selected2).removeAnchor(selected3);
                        }
                        //delete logic block
                        if (selected1 > -1) {
                            m.getEngineDepo().getLogicEngine().removeBlock(m.getEngineDepo().getLogicEngine().getBlock(selected1));
                        }
                        //delete data block
                        if (selected4 > -1) {
                            m.getEngineDepo().getLogicEngine().removeDataBlock(m.getEngineDepo().getLogicEngine().getDataBlock(selected4));
                        }
                        //delete a link
                        for (int i = 0; i < m.getEngineDepo().getLogicEngine().getLinkArraySize(); i++) {
                            m.getEngineDepo().getLogicEngine().getLink(i).killTheLink(me.getPoint(), 15);
                        }

                        canvas.repaint();




                        //!--------- POINT LINK ----------!
                    } else if (menu.getSelected() == 4) {
                        loop:
                        for (int i = 0; i < m.getEngineDepo().getLogicEngine().getLinkArraySize(); i++) {
                            if (m.getEngineDepo().getLogicEngine().getLink(i).getLineBoundaries((int) me.getX(), (int) me.getY())) {
                                //it managed to insert a point
                                canvas.repaint();
                            }
                        }
                    }
                }
            }
        }

        public void mouseReleased(MouseEvent me) {

            //unset the set variables
            selected1 = -1;
            selected2 = -1;
            selected3 = -1;
            selected4 = -1;
            selected5 = -1;
            selected6 = -1;
            selected7 = -1;

            //reset addition
            addX = 0;
            addY = 0;

        }

        public void mouseMoved(MouseEvent me) {
            mousePoint = new Point(me.getX(), me.getY());
            if (menu.getSelected() == 1) {


                if (blinkTimer == null) {
                    blinkTimer = new Timer(150, new hoverChecker());
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
            if (selected1 > -1 & menu.getSelected() == 0) {

                m.getEngineDepo().getLogicEngine().getBlock(selected1).setLocation(
                        me.getX() - addX, me.getY() - addY);

                canvas.repaint();
                //check to see if there is a selection
            } else if (selected3 > -1 & menu.getSelected() == 0) {

                m.getEngineDepo().getLogicEngine().getLink(selected2).setPoint(selected3, me.getPoint());

                canvas.repaint();

            } else if (selected4 > -1 & menu.getSelected() == 0) {

                //methods which move the note around
                m.getEngineDepo().getLogicEngine().getDataBlock(selected4).setLocation(
                        me.getX() - addX, me.getY() - addY);

                canvas.repaint();

            } else if (selected7 > -1 & menu.getSelected() == 0) {

                //methods which move the note around
                m.getEngineDepo().getLogicEngine().getDataLink(selected6).getPoint(selected7).setLocation(me.getPoint());

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
            PopupMenu pop2 = new PopupMenu("Add Variable");
            MenuItem m1 = new MenuItem("Integer");
            MenuItem m2 = new MenuItem("Double");
            MenuItem m3 = new MenuItem("float");
            MenuItem m4 = new MenuItem("Text");
            MenuItem m5 = new MenuItem("Boolean");
            pop = new PopupMenu();
            pop.add(pop2);
            pop2.add(m1);
            pop2.add(m2);
            pop2.add(m3);
            pop2.add(m4);
            pop2.add(m5);
            
            m1.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    DataBlock temp = new DataBlock(new VisualLogicSystem.DataBlocks.ConstrainedNumber("Number",10,10,100));
                    temp.setLocation((int)mousePoint.getX(),(int)mousePoint.getY());
                    m.getEngineDepo().getLogicEngine().addDataBlock(temp);
                    canvas.repaint();
                }
            });
            m2.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    DataBlock temp = new DataBlock(new VisualLogicSystem.DataBlocks.ConstrainedNumber("Kablam",10,10,100));
                    temp.setLocation((int)mousePoint.getX(),(int)mousePoint.getY());
                    m.getEngineDepo().getLogicEngine().addDataBlock(temp);
                    canvas.repaint();
                }
            });
            m3.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    DataBlock temp = new DataBlock(new VisualLogicSystem.DataBlocks.ConstrainedNumber("Kablam",10,10,100));
                    temp.setLocation((int)mousePoint.getX(),(int)mousePoint.getY());
                    m.getEngineDepo().getLogicEngine().addDataBlock(temp);
                    canvas.repaint();
                }
            });
            m4.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    DataBlock temp = new DataBlock(new VisualLogicSystem.DataBlocks.Text("Kablam","hahahah"));
                    temp.setLocation((int)mousePoint.getX(),(int)mousePoint.getY());
                    m.getEngineDepo().getLogicEngine().addDataBlock(temp);
                    canvas.repaint();
                }
            });
            m5.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    DataBlock temp = new DataBlock(new VisualLogicSystem.DataBlocks.Boolean("Kablam"));
                    temp.setLocation((int)mousePoint.getX(),(int)mousePoint.getY());
                    m.getEngineDepo().getLogicEngine().addDataBlock(temp);
                    canvas.repaint();
                }
            });
            this.add(pop);
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
