package GUIProgrammer;

import MainClasses.Main;

import ProgramGUI.GUIComponents.Panes.GenericSystemPanel;

import ProgramGUI.GUIComponents.Buttons.SystemSmallTool;

import Resources.Images.ImageLoader;

import VisualLogicSystem.LogicLink;
import VisualLogicSystem.LogicBlocks.Library;
import com.jogamp.newt.event.MouseEvent;
import com.jogamp.newt.event.MouseListener;
import com.jogamp.opengl.util.gl2.GLUT;


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
import javax.media.opengl.GL2;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;

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

    public VisualLogicGL(Main m2) {
        super();
        this.m = m2;
        GLCapabilities caps = new GLCapabilities(null);
        caps.setDoubleBuffered(true);
        //new instances
        menu = new TopBar();
        canvas = new LogicCanvas(caps);
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

    private class GLContext implements GLEventListener,MouseListener{


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
            gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

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
            //After this we start the drawing of object
            //We want to draw a triangle which is a type of polygon
            gl.glBegin(GL2.GL_POLYGON);
            //We want to draw triangle in red color
            //So setting the gl color to red

            


            //Our polygon ends here
            gl.glEnd();
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

        }

        public void mouseReleased(MouseEvent me) {

        }

        public void mouseMoved(MouseEvent me) {

        }

        public void mouseDragged(MouseEvent me) {

        }

        public void mouseWheelMoved(MouseEvent me) {

        }
    }

    public class LogicCanvas extends GLCanvas {

        public LogicCanvas(GLCapabilities caps) {
            super(caps);
            this.addGLEventListener(new GLContext());
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
