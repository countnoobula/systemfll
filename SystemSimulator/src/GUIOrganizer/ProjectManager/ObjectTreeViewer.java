/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIOrganizer.ProjectManager;

import MainClasses.SystemProject;
import com.jogamp.opengl.util.gl2.GLUT;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.swing.Timer;

/**
 *
 *
 *  I NEEEED TO MAKE THIS 3D :D
 *
 * @author dylan
 */
public class ObjectTreeViewer extends GLCanvas {

    SystemProject proj;
    GLU glu = new GLU();
    GLUT glut = new GLUT();
    GLTreeViewer viewer;

    public ObjectTreeViewer(SystemProject proj) {
        this.proj = proj;
        this.viewer = new GLTreeViewer();

        //add the listeners
        this.addGLEventListener(viewer);
        this.addMouseMotionListener(viewer);
        this.addMouseWheelListener(viewer);
        this.addMouseListener(viewer);
        this.addKeyListener(viewer);
    }

    private class GLTreeViewer implements GLEventListener, MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {

        float zoom;
        float x;
        float y;
        float targetX;
        float targetY;
        float speed;
        float counter;
        float threshold;
        float addX;
        float addY;
        Timer timer;
        Point mousePoint;

        public GLTreeViewer() {
            zoom = -20f;
            x = 0.0f;
            y = 0.0f;
            targetX = 0;
            targetY = 0;
            speed = 30;
            counter = 0;
            threshold = 3;

        }

        public void init(GLAutoDrawable glad) {
            GL2 gl = glad.getGL().getGL2();
            GLUT glut = new GLUT();

            //the enables
            gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
            gl.glShadeModel(GL2.GL_FLAT);
            gl.glEnable(GL2.GL_BLEND);
            gl.glEnable(GL2.GL_DEPTH_TEST);
            gl.glClearColor(0.0f, 0.0f, 0.0f, 0.2f);


        }

        public void dispose(GLAutoDrawable glad) {
        }

        public void display(GLAutoDrawable glad) {
            GL2 gl = glad.getGL().getGL2();
            gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
            gl.glMatrixMode(GL2.GL_MODELVIEW);


            gl.glPushMatrix();
            gl.glTranslatef(x, y, zoom);

            for (int i = 0; i < proj.getVisualLogicSize(); i++) {
                proj.getLogicDatabase(i).displayGL(gl);
            }
            for (int i = 0; i < proj.getPlannerDatabaseSize(); i++) {
                proj.getPlannerDatabase(i).displayGL(gl);
            }

            gl.glPopMatrix();
            gl.glFlush();
        }

        public void reshape(GLAutoDrawable glad, int i, int i1, int width, int height) {
            GL2 gl = glad.getGL().getGL2();

            float h = (float) height / (float) width;

            gl.glMatrixMode(GL2.GL_PROJECTION);
            gl.glLoadIdentity();
            gl.glFrustum(-1.0f, 1.0f, -h, h, 1.0f, 40.0f);
            gl.glMatrixMode(GL2.GL_MODELVIEW);
            gl.glLoadIdentity();
            glu.gluLookAt(0, 0, 10, 0, 0, 0, 0, 1, 0);



        }

        public void mouseDragged(MouseEvent me) {
            x = (float) (me.getX()-(getWidth()/2) * 0.03);
            y = (float) (me.getY()-(getHeight()/2) * -0.03);

            repaint();
        }

        public void mouseMoved(MouseEvent me) {
        }

        public void mouseWheelMoved(MouseWheelEvent mwe) {

            zoom += (float) (mwe.getWheelRotation() * 0.03f);

            repaint();

        }

        public void keyTyped(KeyEvent ke) {
        }

        public void keyPressed(KeyEvent evt) {
            switch (evt.getKeyCode()) {
                case 37:
                    targetX -= threshold;
                    break;
                case 38:
                    targetY += threshold;
                    break;
                case 39:
                    targetX += threshold;
                    break;
                case 40:
                    targetY -= threshold;
                    break;
            }
            counter = 0;

            if (timer == null) {
                timer = new Timer((int) speed, new adjuster());
                timer.start();
            }

        }

        public void keyReleased(KeyEvent ke) {
        }

        public void mouseClicked(MouseEvent me) {
            addX = me.getX()-(getWidth()/2);
            addY = me.getY()-(getWidth()/2);
        }

        public void mousePressed(MouseEvent me) {
            this.mousePoint = me.getPoint();
    
        }

        public void mouseReleased(MouseEvent me) {
        }

        public void mouseEntered(MouseEvent me) {
        }

        public void mouseExited(MouseEvent me) {
        }

        private class adjuster implements ActionListener {

            public void actionPerformed(ActionEvent ae) {
                x += (targetX / speed);
                y += (targetY / speed);
                if (counter == speed) {
                    timer.stop();
                    timer = null;
                    targetX = 0;
                    targetY = 0;


                    counter = 0;
                }
                repaint();
                counter++;
            }
        }
    }
}
