package GUIProgrammer;

import ProgramGUI.GUIComponents.GenericSystemPanel;

import java.awt.BorderLayout;

import javax.swing.JEditorPane;

import javax.swing.JScrollPane;

import jsyntaxpane.DefaultSyntaxKit;

public class CodingScreen extends GenericSystemPanel {

    JEditorPane edit; 

    public CodingScreen() {

        super();

        this.setLayout(new BorderLayout());
        edit = new JEditorPane();
        JScrollPane scroll = new JScrollPane(edit);
        jsyntaxpane.DefaultSyntaxKit.initKit();
        edit.setContentType("text/java");
        this.add(scroll, BorderLayout.CENTER);
    }
}
