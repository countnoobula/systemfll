/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIOrganizer.ProjectManager;

import MainClasses.SystemProject;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;

/**
 *
 * @author dylan
 */
public class ObjectTreeViewer extends GLCanvas {

    SystemProject proj;
    
    public ObjectTreeViewer(SystemProject proj) {
        this.proj = proj;
        this.addGLEventListener(new GLTreeViewer());
    }
    

    private class GLTreeViewer implements GLEventListener {

        public void init(GLAutoDrawable glad) {
            GL2 gl = glad.getGL().getGL2();

            //set up the canvas
            gl.glMatrixMode(GL2.GL_MODELVIEW);
            gl.glDisable(GL2.GL_DEPTH_TEST);
            gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
            gl.glEnable(GL2.GL_BLEND);

            gl.glEnable(GL2.GL_LINE_SMOOTH);
            gl.glClearColor(0.2f, 0.2f, 0.2f, 0.0f);
        }

        public void dispose(GLAutoDrawable glad) {
        }

        public void display(GLAutoDrawable glad) {
            GL2 gl = glad.getGL().getGL2();
            gl.glClear(GL2.GL_COLOR_BUFFER_BIT);

            gl.glColor3d(0.3, 0.3, 0.3);
            //middle
            gl.glBegin(GL2.GL_POLYGON);
            gl.glVertex2d(30, 30);
            gl.glVertex2d(getWidth()-30, 30);
            gl.glVertex2d(getWidth()-30, getHeight()-30);
            gl.glVertex2d(30, getHeight()-30);
            gl.glEnd();

            gl.glColor3d(0.1, 0.1, 0.1);
            //top
            gl.glBegin(GL2.GL_POLYGON);
            gl.glVertex2d(0, 0);
            gl.glVertex2d(30, 30);
            gl.glVertex2d(getWidth()-30, 30);
            gl.glVertex2d(getWidth(),0);
            gl.glEnd();

            //left
            gl.glBegin(GL2.GL_POLYGON);
            gl.glVertex2d(0, getHeight());
            gl.glVertex2d(30, getHeight()-30);
            gl.glVertex2d(getWidth()-30, getHeight()-30);
            gl.glVertex2d(getWidth(),getHeight());
            gl.glEnd();


            for(int i = 0;i < proj.getVisualLogicSize();i++){
                
            }
        }

        public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) {
            GL2 gl = glad.getGL().getGL2();
            //Projection mode is for setting camera
            gl.glMatrixMode(GL2.GL_PROJECTION);
            gl.glLoadIdentity();
            gl.glOrtho(0, getWidth(), getHeight(), 0, 0, 1);
        }
    }
}
