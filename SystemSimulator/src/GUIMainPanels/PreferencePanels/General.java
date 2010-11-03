package GUIMainPanels.PreferencePanels;

import ProgramGUI.GUIComponents.Panes.NullPanel;
import ProgramGUI.GUIComponents.SystemCheckBox;
import ProgramGUI.GUIComponents.SystemLabel;
import MainClasses.Main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class General extends NullPanel {

    //the settings
    ArrayList<SystemLabel> labels;
    ArrayList<SystemCheckBox> checks;

    //main program
    Main m;

    public General(Main m2) {
        super();
        this.m = m2;


        labels = new ArrayList<SystemLabel>();
        checks = new ArrayList<SystemCheckBox>();

        //create new instances
        labels.add(new SystemLabel("Intro Animation:"));
        labels.add(new SystemLabel("Automatic Updates:"));
        labels.add(new SystemLabel("Themed Mode:"));
        labels.add(new SystemLabel("Enable Developer Mode:"));
        labels.add(new SystemLabel("Enable Debugging:"));


        checks.add(new SystemCheckBox(""));
        checks.add(new SystemCheckBox(""));
        checks.add(new SystemCheckBox(""));
        checks.add(new SystemCheckBox(""));
        checks.add(new SystemCheckBox(""));

        updateChecks();

        for (int i = 0; i < checks.size(); i++) {
            checks.get(i).addActionListener(new ChangeListener());
        }


        this.setLayout(null);
        this.setBounds(0, 0, 500, 500);
        initComponents();
        this.setVisible(false);


    }

    public void updateChecks() {
        checks.get(0).setSelected(m.getPrefs().getBoolean("showStartAnimation",
                                                          true));
        checks.get(1).setSelected(m.getPrefs().getBoolean("enableAutomaticUpdates",
                                                          true));
        checks.get(2).setSelected(m.getPrefs().getBoolean("useSubstanceTheme",
                                                          false));
        checks.get(3).setSelected(m.getPrefs().getBoolean("enableDeveloperMode",
                                                          true));
        checks.get(4).setSelected(m.getPrefs().getBoolean("enableDebuggingMode",
                                                          true));

    }

    private class ChangeListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == checks.get(0)) {
                m.getPrefs().putBoolean("showStartAnimation",
                                        checks.get(0).isSelected());

            } else if (e.getSource() == checks.get(1)) {
                m.getPrefs().putBoolean("enableAutomaticUpdates",
                                        checks.get(1).isSelected());

            } else if (e.getSource() == checks.get(2)) {
                m.getPrefs().putBoolean("useSubstanceTheme",
                                        checks.get(2).isSelected());

            } else if (e.getSource() == checks.get(3)) {
                m.getPrefs().putBoolean("enableDeveloperMode",
                                        checks.get(3).isSelected());

            } else if (e.getSource() == checks.get(4)) {
                m.getPrefs().putBoolean("enableDebuggingMode",
                                        checks.get(4).isSelected());
            }
        }
    }

    private void initComponents() {

        //add all the components
        for (int i = 0; i < labels.size(); i++) {
            this.add(labels.get(i));
            //make the labels nice and small :)
            labels.get(i).setSmallText();
            labels.get(i).setBounds(10, i * 30, 140, 22);
        }
        for (int i = 0; i < checks.size(); i++) {
            this.add(checks.get(i));
            checks.get(i).setBounds(160, i * 30, 140, 22);
        }


    }

}
