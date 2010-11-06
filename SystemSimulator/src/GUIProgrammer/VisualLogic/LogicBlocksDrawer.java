package GUIProgrammer.VisualLogic;

import GUIProgrammer.VisualLogicGL;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import MainClasses.Main;
import MainClasses.SystemProject;
import ProgramGUI.GUIComponents.Panes.NullPanel;
import VisualLogicSystem.LogicObjects.LogicBlock;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JScrollPane;

public class LogicBlocksDrawer extends JPanel {

    private Main m;
    private Paint gp1, gp2, gp3;
    private ArrayList<LogicBlock> library;
    private JScrollPane scroll;
    private NullPanel grid;

    public LogicBlocksDrawer(Main m2) {
        this.m = m2;


        grid = new NullPanel();

        grid.setLayout(new FlowLayout());
        scroll = new JScrollPane(grid);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);


        this.setPreferredSize(new Dimension(230, 0));
        this.setOpaque(false);
        this.setLayout(new BorderLayout());

        this.add(scroll, BorderLayout.CENTER);

        gp1 = new GradientPaint(0, 40, new Color(0, 0, 0), 0, 80, new Color(30, 30, 30));
        gp2 = new GradientPaint(0, 0, new Color(40, 40, 40), 230, 40, new Color(10, 10, 10));
        gp3 = new GradientPaint(0, 0, new Color(40, 40, 40), 230, 40, new Color(70, 70, 70));

    }

    public void setLogicBlocks(ArrayList<LogicBlock> library) {
        this.library = library;
        for (int i = 0; i < library.size(); i++) {
            grid.add(new LogicPane(library.get(i)));
        }
        grid.setPreferredSize(new Dimension(225, library.size() * 40));
        grid.revalidate();

    }

    private class LogicPane extends JPanel {

        LogicBlock b;
        private boolean entered;

        public LogicPane(LogicBlock c) {
            b = c;
            entered = false;
            String text = "";
            for (int i = 0; i < b.getDescription().length() - 1; i++) {

                if (b.getDescription().charAt(i) == ' ' & b.getDescription().charAt(i + 1) == ' ') {
                    i++;
                } else {
                    text += b.getDescription().charAt(i);
                }
            }
            this.addMouseListener(new MouseListener() {

                public void mouseClicked(MouseEvent me) {
                }

                public void mousePressed(MouseEvent me) {
                    try {
                        LogicBlock clone = (LogicBlock) b.clone();
                        clone.setLocation(300, 300);
                        m.getSystemProject().getLogicDatabase(SystemProject.currentLogic).getBlocks().add(clone);
                        VisualLogicGL.getCanvas().repaint();
                    } catch (CloneNotSupportedException ex) {
                        System.out.println("logic block could not be cloned : Drawer ");
                    }
                }

                public void mouseReleased(MouseEvent me) {
                }

                public void mouseEntered(MouseEvent me) {
                    entered = true;

                    repaint();
                }

                public void mouseExited(MouseEvent me) {
                    entered = false;
                    repaint();
                }
            });
            this.setLayout(null);
            this.setPreferredSize(new Dimension(225, 55));
            this.setOpaque(false);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            if (entered) {
                g2d.setPaint(gp3);
            } else {
                g2d.setPaint(gp2);
            }

            g2d.fillRect(0, 0, getWidth(), getHeight());
            g2d.drawImage(b.getButton(), 2, 2, null);
            g2d.setPaint(new Color(0, 0, 0, 100));
            g2d.fillRect(55, 1, getWidth() - 56, 15);
            g2d.setPaint(new Color(150, 150, 150));
            g2d.setFont(new Font("Arial", 11, 11));
            g2d.drawString("" + b.getTitle(), 60, 13);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(gp1);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setPaint(Color.BLACK);
        g2d.drawLine(0, 0, 0, getHeight());
    }
}
