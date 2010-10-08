package ProgramGUI.GUIComponents;

import java.awt.Color;

import java.awt.Dimension;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JRootPane;

public class FloatingWindow extends JFrame {
    public FloatingWindow() {
        super("Something");
        

        this.setBackground(new Color(0, 0, 0, 80));

        JRootPane root = this.getRootPane();
        root.putClientProperty("Window.style", "small");
        this.setSize(new Dimension(200, 200));
        this.setVisible(true);
        this.repaint();
        this.validate();


    }

    public static void main(String[] args) {
        FloatingWindow f = new FloatingWindow();

    }
}
