/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ProgramGUI.GUIComponents.Panes;

import ProgramGUI.GUIComponents.BlockVariablePane;
import ProgramGUI.GUIComponents.Buttons.NullButton;
import ProgramGUI.GUIComponents.Buttons.SystemSmallBarButton;
import Resources.Images.ImageLoader;
import VisualLogicSystem.LogicObjects.LogicBlock;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import translucentshapes.AWTUtilitiesWrapper;

/**
 *
 * @author Dylan
 */
public class BlockPropertyEditor extends JWindow {

    private TitleBar title;
    private ToolBar tool;
    private GridBagConstraints gc;
    private BlockPropertyEditor nested;
    private NullPane nullPane;
    private BlockVariablePane panel_1;

    

    public BlockPropertyEditor(JFrame f) {
        super(f);
        nested  = this;
        System.setProperty("sun.java2d.opengl", "true");
        //new instances
        gc = new GridBagConstraints();
        title = new TitleBar();
        tool = new ToolBar();
        nullPane = new NullPane();
        

        this.setLayout(new GridBagLayout());
        this.setMinimumSize(new Dimension(200, 500));
        this.setAlwaysOnTop(true);
        this.getRootPane().putClientProperty("apple.awt.draggableWindowBackground", Boolean.FALSE);
        this.addFocusListener
                (new FocusListener(){

            public void focusGained(FocusEvent e) {
                
            }

            public void focusLost(FocusEvent e) {
                if(getComponents()[2] instanceof BlockVariablePane){
                
                }
            }
        });
        setUpComponents();
    }

    private void setUpComponents() {
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.weightx = 1;
        gc.weighty = 0;
        gc.fill = GridBagConstraints.HORIZONTAL;
        this.getContentPane().add(title, gc);
        gc.gridx = 0;
        gc.gridy = 1;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.weightx = 1;
        gc.weighty = 0;
        gc.fill = GridBagConstraints.HORIZONTAL;
        this.getContentPane().add(tool, gc);
        gc.gridx = 0;
        gc.gridy = 2;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.BOTH;
        this.getContentPane().add(nullPane, gc);

    }

    public void setLogicBlock(LogicBlock block) {

        
        this.getContentPane().remove(2);
        //individual panes
        panel_1 = new BlockVariablePane(block);
        this.add(panel_1,gc);
        this.validate();
        this.repaint();
       
    }


    private class TitleBar extends JPanel {

        private Paint gp1;
        private Point p;

        //close button
        private NullButton bt1;
        private JLabel label;
        private JLabel label2;



        public TitleBar() {
            gp1 = new GradientPaint(0, 0, new Color(70, 70, 70), 0, 15, new Color(40, 40, 40));
            bt1 = new NullButton("");
            label = new JLabel("  Edit Block Properties");
            label2 = new JLabel(" >>");

            this.setPreferredSize(new Dimension(0, 15));

            //mouse functions
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
                    nested.setLocation((int) (e.getXOnScreen() - p.getX()), (int) (e.getYOnScreen() - p.getY()));
                }

                public void mouseMoved(MouseEvent e) {
                }
            });

            this.setLayout(new BorderLayout());
            label.setFont(new Font("Arial",10,10));
            label.setOpaque(false);
            label.setForeground(Color.LIGHT_GRAY);
            label2.setFont(new Font("Arial",10,10));
            label2.setOpaque(false);
            label2.setForeground(new Color(0,192,255));
            bt1.addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                   nested.setVisible(false);
                }
            });
            bt1.setIcon(new ImageIcon(ImageLoader.class.getResource("GUIComponents/closeButton.png")));
            bt1.setRolloverIcon(new ImageIcon(ImageLoader.class.getResource("GUIComponents/closeButtonHover.png")));
            this.add(bt1,BorderLayout.EAST);
            this.add(label,BorderLayout.CENTER);
            this.add(label2,BorderLayout.WEST);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setPaint(gp1);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }
    private class ToolBar extends JPanel{
        private Paint gp1;
        private GridBagConstraints gc2;
        private SystemSmallBarButton[] tools;

        public ToolBar(){
            this.setLayout(new GridBagLayout());
            gp1 = new GradientPaint(0, 0, new Color(30, 30, 30), 0, 25, new Color(0, 0, 0));
            gc2 = new GridBagConstraints();
            ButtonGroup bg = new ButtonGroup();
            try {
                this.setPreferredSize(new Dimension(0, 25));
                tools = new SystemSmallBarButton[3];
                tools[0] = new SystemSmallBarButton(ImageIO.read(ImageLoader.class.getResource("GUIComponents/bar1.png")));
                tools[1] = new SystemSmallBarButton(ImageIO.read(ImageLoader.class.getResource("GUIComponents/bar2.png")));
                tools[2] = new SystemSmallBarButton(ImageIO.read(ImageLoader.class.getResource("GUIComponents/bar3.png")));

            } catch (IOException ex) {
                Logger.getLogger(BlockPropertyEditor.class.getName()).log(Level.SEVERE, null, ex);
            }
            for(int i = 0;i < tools.length;i++){
                this.add(tools[i],gc);
                bg.add(tools[i]);
            }
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setPaint(gp1);
            g2d.fillRect(0, 0, getWidth(), getHeight());
            g2d.setColor(new Color(30,30,30));
            g2d.drawLine(0, getHeight()-1, getWidth(), getHeight()-1);
        }
    }
    private class NullPane extends JPanel{
        private Font f;
        public NullPane(){
        this.setOpaque(false);
        f = new Font("Arial",12,12);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.setColor(new Color(0,0,0,140));
            g2d.fillRect(0, 0, getWidth(), getHeight());

            g2d.setColor(Color.WHITE);
            g2d.setFont(f);
            g2d.drawString("no block selected", 20, 40);
        }  
    }
    public static void main(String args[]){
        JFrame f = new JFrame();
        
        f.setUndecorated(true);
        f.setSize(0, 0);
        f.setVisible(true);

        BlockPropertyEditor editor = new BlockPropertyEditor(f);
        editor.setVisible(true);
        AWTUtilitiesWrapper.setWindowOpaque(editor, false);
        
    }


}
