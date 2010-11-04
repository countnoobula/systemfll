package GUIPlanner;

import MainClasses.Main;
import PlannerSystem.PlannerNoteData;
import ProgramGUI.GUIComponents.Buttons.SmallRoundedButton;
import ProgramGUI.GUIComponents.Panes.GenericSystemPanel;
import ProgramGUI.GUIComponents.Panes.TabbedPane;
import Resources.Images.ImageLoader;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Insets;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * The note viewer is a panel on the Planner which allows users to view and organize notes
 * which they make as they use the program
 */
public class NoteViewer extends GenericSystemPanel {

    private TopBar utilityBar;
    private BarkPanel mainCanvas;
    private Main m;
    private NoteViewer nv;
    private ArrayList<PlannerNote> notes;

    public NoteViewer(TabbedPane p,String s,Main m2) {
        super(p,s);
        
        this.m = m2;
        nv = this;

        //create new variables instances
        this.utilityBar = new TopBar();
        this.mainCanvas = new BarkPanel();
        this.setLayout(new BorderLayout());
        this.notes = new ArrayList<PlannerNote>(0);

        //do some basic layouts
        this.add(utilityBar, BorderLayout.NORTH);
        this.add(mainCanvas, BorderLayout.CENTER);
    }

    public NoteViewer.BarkPanel getMainCanvas() {
        return mainCanvas;
    }

    private class TopBar extends JPanel {

        private Paint gp1, gp2;
        private GridBagConstraints c;
        private SmallRoundedButton bt1, bt2;

        private TopBar() {
            this.setPreferredSize(new Dimension(0, 24));
            this.setLayout(new GridBagLayout());

            c = new GridBagConstraints();


            gp1 =
                    new GradientPaint(0, 0, new Color(20, 20, 20), 0, 24, new Color(40, 40, 40));
            initComponents();
        }

        private void initComponents() {
            c.insets = new Insets(3, 3, 3, 3);
            c.gridx = 0;
            c.gridy = 0;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.fill = GridBagConstraints.VERTICAL;
            c.anchor = GridBagConstraints.NORTHWEST;
            c.weightx = 0;
            c.weighty = 1;
            bt1 = new SmallRoundedButton("Add Note");
            bt2 = new SmallRoundedButton("Clear Notes");

            bt1.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    // create a temporary note and add it to the note panel stack
                    PlannerNoteData tempNote = new PlannerNoteData();
                    tempNote.setX((int) Math.random() * 300);
                    tempNote.setY((int) Math.random() * 300);

                    m.getPlanDatabase().addPlannerNote(tempNote);
                    notes.add(new PlannerNote(nv, tempNote, new Color((int) Math.round(Math.random() * 255), (int) Math.round(Math.random() * 255), (int) Math.round(Math.random() * 255), 200), new Color((int) Math.round(Math.random() * 255), (int) Math.round(Math.random() * 255), (int) Math.round(Math.random() * 255), 200)));
                    mainCanvas.resetComponents();
                }
            });
            bt2.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    m.getPlanDatabase().clearNotes();
                    notes.clear();
                    mainCanvas.resetComponents();
                }
            });

            this.add(bt1, c);
            c.gridx = 1;
            c.weightx = 0;
            this.add(bt2, c);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setPaint(gp1);
            g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

        }
    }

    public class BarkPanel extends JPanel {

        private Paint gp1;

        private BarkPanel() {
            this.setLayout(null);

            Rectangle r = new Rectangle(0, 0, 100, 100);
            try {
                gp1 =
                        new TexturePaint(ImageIO.read(ImageLoader.class.getResource("Planner/bark.png")),
                        r);
            } catch (IOException e) {
                System.out.println("Cant load the bloody bark, shit!");
            }
        }

        public void resetComponents() {
            this.removeAll();
            for (int i = 0; i < m.getPlanDatabase().getAmountOfPlannerNotes();
                    i++) {

                this.add(notes.get(i));
                notes.get(i).setBounds(notes.get(i).getXPos(),
                        notes.get(i).getYPos(),
                        notes.get(i).getWidth(),
                        notes.get(i).getHeight());
            }
            this.revalidate();
            this.updateUI();
        }

        public void updateComponent(PlannerNote n) {
            n.setBounds(n.getXPos(), n.getYPos(), n.getWidth(), n.getHeight());
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setPaint(gp1);
            g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

        }
    }
}
