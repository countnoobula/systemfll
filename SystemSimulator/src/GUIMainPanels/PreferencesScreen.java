package GUIMainPanels;

import GUIMainPanels.PreferencePanels.Account;
import GUIMainPanels.PreferencePanels.General;
import GUIMainPanels.PreferencePanels.ProgramUI;

import ProgramGUI.GUIComponents.Panes.GenericSystemPanel;
import ProgramGUI.GUIComponents.SystemList;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import MainClasses.Main;
import ProgramGUI.GUIComponents.Panes.TabbedPane;

import java.awt.Component;
import java.awt.Insets;

import java.util.Vector;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * This is the tab under CORE > preferences which facilitates the front end of all
 * Program settings and configurations.
 */
public class PreferencesScreen extends GenericSystemPanel {

    private SystemList list;
    private Vector<String> listData;
    private GridBagConstraints gc;
    private Component panels[] = new Component[3];

    //main program call
    private Main m;

    public PreferencesScreen(TabbedPane p,String n,Main m2) {
        super(p,n);
        this.m = m2;

        this.setLayout(new GridBagLayout());
        this.gc = new GridBagConstraints();

        list = new SystemList(m);
        listData = new Vector<String>();

        //create new instances of the program
        panels[0] = new General(m);
        panels[1] = new Account(m);
        panels[2] = new ProgramUI();

        listData.add("General");
        listData.add("Account");
        listData.add("Program UI");
        //listData.add("Measurements");
        //listData.add("Connections");

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {

                    makeVisible(panels[list.getSelectedIndex()]);

                }
            });
        list.setListData(listData);
        list.setPreferredSize(new Dimension(150, 0));

        //add the list on the left
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.weightx = 0;
        gc.weighty = 1;
        gc.insets = new Insets(30, 5, 5, 5);
        gc.fill = GridBagConstraints.VERTICAL;
        gc.anchor = GridBagConstraints.NORTHWEST;
        this.add(list, gc);

        //add the panels on the right
        gc.gridx = 1;
        gc.gridy = 0;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.insets = new Insets(30, 5, 5, 5);
        gc.fill = GridBagConstraints.BOTH;
        gc.anchor = GridBagConstraints.NORTHWEST;
        for (int i = 0; i < panels.length; i++) {
            this.add(panels[i], gc);
        }

        //finally, set the list at position 1
        list.setSelectedIndex(0);

    }

    /**
     * Method to make layered components invisible and then to display one component
     * @param c
     * The component to make visible int he stack
     */
    public void makeVisible(Component c) {
        for (int i = 0; i < panels.length; i++) {

            if (panels[i].isVisible() == true) {
                panels[i].setVisible(false);
            }
            c.setVisible(true);

        }
    }
}
