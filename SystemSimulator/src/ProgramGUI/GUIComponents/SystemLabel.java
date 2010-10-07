package ProgramGUI.GUIComponents;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class SystemLabel extends JLabel {
    public SystemLabel(String s) {
        super(s);
        this.setFont(new Font("Arial", 14, 14));
        this.setForeground(Color.WHITE);
        this.setOpaque(false);
        

    }

    public void setSmallText() {
        this.setFont(new Font("Arial", 12, 12));
    }
}
