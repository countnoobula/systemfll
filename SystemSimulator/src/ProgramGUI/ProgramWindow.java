package ProgramGUI;

import java.awt.Graphics;
import java.awt.Toolkit;

import MainClasses.*;

import Resources.Images.ImageLoader;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.imageio.ImageIO;

import java.awt.Image;


import java.io.IOException;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.swing.JWindow;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.transitions.ScreenTransition;
import org.jdesktop.animation.transitions.TransitionTarget;

/**
 * This is the main Program Window for the application.
 */
    public class ProgramWindow extends JFrame {

    private Main m;
    private ContentPane panel_1;
    private ProgramWindowPanel panel_2;

    private GUIEngine guiEngine;

    /**
     * Creates the program window and positions it in the center of the screen
     * @param m2
     */
    public ProgramWindow(Main m2) {

        //create new instances of the GUI components
        this.m = m2;
        this.panel_2 = new ProgramWindowPanel(m);
        this.panel_1 = new ContentPane();
        this.guiEngine = new GUIEngine(m);
        this.setUndecorated(true);

        //Variables for the program Window layout on the screen
        int sideWidth = 50;
        int topWidth = 50;
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height =
            Toolkit.getDefaultToolkit().getScreenSize().getHeight();


        //component methods
        this.setLayout(new BorderLayout());

        //add components
        this.add(panel_1, BorderLayout.CENTER);
        this.setBounds(sideWidth, topWidth, (int)(width - 2 * sideWidth),
                       (int)(height - 2 * topWidth));
        this.setMinimumSize(new Dimension(800,600));
        this.setVisible(true);
        


    }
    /*
     * This is the start up animation for the program. It will display a really sweet animation
     * and will end with System being faded out and the main program window fading in
     */


    public ProgramWindowPanel getProgramWindowPanel() {
        return panel_2;
    }

    public void startIntroAnimation() {
        panel_1.startTransition();
    }


    private class ContentPane extends JPanel implements TransitionTarget {

        private ScreenTransition transition;
        private Image i;

        public ContentPane() {
            //component methods
            this.setLayout(new BorderLayout());
            //add components
            this.add(panel_2, BorderLayout.CENTER);
            

            try {
                ImageLoader l = new ImageLoader();
                i = ImageIO.read(l.getClass().getResource("SystemLogo1.png"));
            } catch (IOException e) {
                System.out.println("Could not load main image");
            }

        }

        /**
         * This method starts the transition where the program fades into the main GUI.
         */
        public void startTransition() {
            Animator animator = new Animator(1500);

            transition = new ScreenTransition(this, this, animator);
            transition.start();
        }

        public void setupNextScreen() {
            if (panel_2.isVisible()) {
                panel_2.setVisible(false);
            } else {
                panel_2.setVisible(true);

            }
        }

        /**
         * Does Default Painting for now using a G2D image, but needs to be painted using a buffer
         * @param g
         * The Graphics Context
         */
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
            g2d.drawImage(i, (this.getWidth() / 2) - (i.getWidth(null) / 2),
                          (this.getHeight() / 2) - (i.getHeight(null) / 2),
                          null);

        }
    }

    /**
     * This is a future implementation for OpenGL if need be, but so far is not needed due to heavy
     * weight and lightweight mixing
     */
    private class GLStartUp implements GLEventListener {
        /**
         * Starts the Open GL graphics Context
         * @param glAutoDrawable
         * The GL context
         */
        public void init(GLAutoDrawable glAutoDrawable) {

        }

        /**
         * The refresh method of the program
         * @param glAutoDrawable
         * The GL context
         */
        public void dispose(GLAutoDrawable glAutoDrawable) {
        }

        /**
         *
         * @param glAutoDrawable
         * The GL context
         */
        public void display(GLAutoDrawable glAutoDrawable) {
        }

        /**
         * The reshape method
         * @param glAutoDrawable
         * The GL context
         * @param x
         * X Position of the reshape
         * @param y
         * Y Position of the reshape
         * @param width
         * The width of the new canvas
         * @param height
         * The height of the new canvas
         */
        public void reshape(GLAutoDrawable glAutoDrawable, int x, int y,
                            int width, int height) {
        }
    }

}
