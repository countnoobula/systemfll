package ProgramGUI.GUIComponents;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicOptionPaneUI;

/**
 * Not working :(
 */
public class SystemModal1 extends JOptionPane{
    public SystemModal1() {
        super();
        this.setUI(new SystemModalUI());
    }
    private class SystemModalUI extends BasicOptionPaneUI{

        @Override
        public void paint(Graphics g, JComponent c) {
            
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 400, 400);
        }
    }
    

    public static void main(String args[]){
      SystemModal1 mode = new SystemModal1();
      mode.showMessageDialog(null, "LOLOLOL");
    }
}
