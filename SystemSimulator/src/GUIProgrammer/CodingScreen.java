package GUIProgrammer;

import MainClasses.Main;
import ProgramGUI.GUIComponents.Panes.GenericSystemPanel;
import ProgramGUI.GUIComponents.Panes.TabbedPane;
import VisualLogicSystem.LogicObjects.CodeBlock;
import VisualLogicSystem.LogicBlockEngine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;

public class CodingScreen extends GenericSystemPanel {

    JEditorPane edit;
    Main m;
    public CodingScreen(TabbedPane p,String n,Main m2) {
        super(p,n);

        this.m = m2;

        this.setLayout(new BorderLayout());
        edit = new JEditorPane();
        JScrollPane scroll = new JScrollPane(edit);
        edit.setContentType("text/java");
        this.add(scroll, BorderLayout.CENTER);
        scroll.getViewport().setOpaque(false);
        scroll.setOpaque(false);
        edit.setOpaque(false);
        edit.setForeground(Color.WHITE);
        edit.setFont(new Font("Monospaced", 13, 13));
        this.addComponentListener(new ComponentListener() {

            public void componentResized(ComponentEvent ce) {
            }

            public void componentMoved(ComponentEvent ce) {
            }

            public void componentShown(ComponentEvent ce) {
                String text = "";

                ArrayList<CodeBlock> logic = m.getEngineDepo().getLogicEngine().compile();

                for (int i = 0; i < logic.size(); i++) {
                    text = text + logic.get(i).getCompileCode();
                }

                int tabSpacing = 0;
                String temp = "";
                String line = "";



                for (int i = 0; i < text.length(); i++) {


                    if (line.contains("}")) {

                        tabSpacing--;
                    }
                    if (line.contains("{")) {
                        tabSpacing++;
                    }

                    if (text.charAt(i) == '\n') {


                        String spacing = "";
                        for (int k = 0; k < tabSpacing; k++) {
                            spacing += "\t";
                        }
                        if (line.contains("{")) {
                            spacing = spacing.replaceFirst("\t", "");
                        }
                        temp = temp + spacing + line + "\n";



                        line = "";
                    } else {
                        line += text.charAt(i);
                    }
                    if (i == text.length() - 1) {
                        temp = temp + line + "\n";
                    }



                }
                edit.setText(temp);
            }

            public void componentHidden(ComponentEvent ce) {
            }
        });
    }
    Paint gp1 = new Color(0, 0, 0, 150);

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        Graphics2D g2d = (Graphics2D) grphcs;
        g2d.setPaint(gp1);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}
