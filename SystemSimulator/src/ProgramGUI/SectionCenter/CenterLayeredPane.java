package ProgramGUI.SectionCenter;

import java.awt.BorderLayout;

import java.awt.Component;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class CenterLayeredPane extends JPanel {

    GridBagConstraints gc;

    public CenterLayeredPane() {
        this.gc = new GridBagConstraints();
        this.setLayout(new GridBagLayout());
        this.setOpaque(false);
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.anchor = GridBagConstraints.NORTHWEST;
        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 1;
        gc.weighty = 1;

    }


    public void addPanel(Component comp) {
        this.add(comp, gc);
    }
}
