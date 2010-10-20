package ProgramGUI.GUIComponents;

import ProgramGUI.GUIComponents.Buttons.SystemButton;
import ProgramGUI.GUIComponents.LogicControls.ControlPoints;
import ProgramGUI.GUIComponents.LogicControls.Field;
import ProgramGUI.GUIComponents.LogicControls.Slider;
import VisualLogicSystem.DataBlocks.DataObject;
import VisualLogicSystem.DataBlocks.Number;
import VisualLogicSystem.DataBlocks.Text;
import VisualLogicSystem.LogicBlock;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;

import java.awt.Graphics2D;

import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class BlockProperties extends JPanel {

    Paint gp1, gp2, gp3, gp4;
    ArrayList<DataObject> data;
    SystemButton bt;

    public BlockProperties(LogicBlock bl) {
        bt = new  SystemButton("SAVE");
        this.setOpaque(false);
        gp1 = new GradientPaint(0, 0, new Color(0, 0, 0, 0),
                0, 40, new Color(0, 0, 0, 180));
        data = bl.getData();
        bt.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                for(int i = 0;i< data.size();i++){
                   ((ControlPoints) getComponents()[i]).encodeData();

                }
            }
        });

        ConstructUpPanel();
    }
    public void attatchActionListener(ActionListener e){
        
        bt.addActionListener(e);
        
    }


    private void ConstructUpPanel() {

        //400 34
        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(400, 34 * data.size() + 55));
        
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i) instanceof VisualLogicSystem.DataBlocks.Number) {
                this.add(new Slider((Number) data.get(i)));
            }
            if (data.get(i) instanceof VisualLogicSystem.DataBlocks.Text) {
                this.add(new Field((Text) data.get(i)));
            }
        }
        this.add(bt);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        
        g2d.setPaint(gp1);
        g2d.fillRoundRect(0, 0, getWidth() - 10, getHeight() - 10,15,15);
        g2d.setPaint(Color.GRAY);
        g2d.drawRoundRect(0, 0, getWidth() - 10, getHeight() - 10,15,15);



    }
}
