package ProgramGUI.GUIComponents.Panes;

import javax.swing.JPanel;

public class GenericSystemPanel extends JPanel{

    TabbedPane belongsTo;
    String name = "";
    public GenericSystemPanel(TabbedPane belongsTo,String name) {
        super();
        this.belongsTo = belongsTo;
        this.name = name;
        this.setOpaque(false);
        
        
    }

    public TabbedPane getBelongsTo() {
        return belongsTo;
    }
    public String getPanelName(){
        return name;
    }

    


}