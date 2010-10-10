package GUIProgrammer;

import ProgramGUI.GUIComponents.GenericSystemPanel;
import VisualLogicSystem.CodeBlock;
import VisualLogicSystem.LogicBlock;
import VisualLogicSystem.LogicBlockEngine;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;

import javax.swing.JEditorPane;

import javax.swing.JScrollPane;

import jsyntaxpane.DefaultSyntaxKit;

public class CodingScreen extends GenericSystemPanel {

    JEditorPane edit;
    JButton compile = new JButton("Compile");

    public CodingScreen() {

        super();

        this.setLayout(new BorderLayout());
        edit = new JEditorPane();
        JScrollPane scroll = new JScrollPane(edit);
        jsyntaxpane.DefaultSyntaxKit.initKit();
        edit.setContentType("text/java");
        this.add(scroll, BorderLayout.CENTER);
        this.add(compile,BorderLayout.NORTH);
        compile.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                String text = "";
                ArrayList<CodeBlock> logic = LogicBlockEngine.compile();
                
                for(int i = 0;i < logic.size();i++){
                    text = text+ logic.get(i).getCompileCode();
                }
                edit.setText(text);
            }
        });
    }

}

