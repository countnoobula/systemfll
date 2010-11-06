package GUIOrganizer;

import GUIOrganizer.ProjectManager.ObjectTreeViewer;
import MainClasses.Main;
import ProgramGUI.GUIComponents.Panes.GenericSystemPanel;
import ProgramGUI.GUIComponents.Panes.TabbedPane;
import java.awt.BorderLayout;

public class ProjectManagerScreen extends GenericSystemPanel{

    ObjectTreeViewer viewer;
    public ProjectManagerScreen(TabbedPane p,String n,Main m) {
        super(p,n);

        //create new instances
        viewer = new ObjectTreeViewer(m.getSystemProject());

        this.setLayout(new BorderLayout());
        this.add(viewer,BorderLayout.CENTER);
    }
}
