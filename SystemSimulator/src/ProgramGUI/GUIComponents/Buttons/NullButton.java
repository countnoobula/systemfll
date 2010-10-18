/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ProgramGUI.GUIComponents.Buttons;

import java.awt.Insets;
import javax.swing.JButton;

/**
 *
 * @author Dylan
 */
public class NullButton extends JButton {

    public NullButton(String s) {
        super(s);
        this.setOpaque(false);
        this.setMargin(new Insets(-3, -3, -3, -3));
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
    }
}
