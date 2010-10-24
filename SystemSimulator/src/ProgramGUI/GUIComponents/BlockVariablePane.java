package ProgramGUI.GUIComponents;

import ProgramGUI.GUIComponents.Buttons.SystemButton;
import ProgramGUI.GUIComponents.LogicControls.ControlPoints;
import ProgramGUI.GUIComponents.LogicControls.Field;
import ProgramGUI.GUIComponents.LogicControls.Slider;
import VisualLogicSystem.DataBlocks.DataObject;
import VisualLogicSystem.LogicBlock;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class BlockVariablePane extends JPanel {

    Paint gp1, gp2, gp3, gp4;
    ArrayList<DataObject> data;
    SystemButton bt;
    GridBagConstraints gc;

    public BlockVariablePane(LogicBlock bl) {
        bt = new SystemButton("SAVE");
        gc = new GridBagConstraints();

        this.setOpaque(false);
        this.setLayout(new GridBagLayout());


        gp1 = new GradientPaint(0, 0, new Color(0, 0, 0, 0),
                0, 40, new Color(0, 0, 0, 180));
        data = bl.getData();
        bt.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                for (int i = 1; i < data.size(); i++) {
                    ((ControlPoints) getComponents()[i]).encodeData();

                }
            }
        });

        ConstructUpPanel();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(new Color(0, 0, 0, 140));
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    public void attatchActionListener(ActionListener e) {

        bt.addActionListener(e);

    }

    private void ConstructUpPanel() {
        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(200, 34 * data.size() + 55));
        for (int i = 0; i < data.size(); i++) {
            gc.gridy = i;
            if (data.get(i).getControlType() == DataObject.CONSTRAINED_VALUE) {
                this.add(new Slider(data.get(i)), gc);
            }
            if (data.get(i).getControlType() == DataObject.TEXTFIELD) {
                this.add(new Field(data.get(i)), gc);
            }
        }

    }
}
