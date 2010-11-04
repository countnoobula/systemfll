package GUIMainPanels;

import ProgramGUI.GUIComponents.Panes.GenericSystemPanel;
import ProgramGUI.GUIComponents.Panes.TabbedPane;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class UpdateScreen extends GenericSystemPanel {
    GridBagConstraints gc;
    

    public UpdateScreen(TabbedPane p,String n) {
        super(p,n);
        gc = new GridBagConstraints();
        this.setLayout(new GridBagLayout());
    }
    private void initComponents(){
      
    }
}
