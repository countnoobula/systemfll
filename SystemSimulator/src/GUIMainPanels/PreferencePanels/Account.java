package GUIMainPanels.PreferencePanels;

import MainClasses.Main;

import ProgramGUI.GUIComponents.Panes.NullPanel;
import ProgramGUI.GUIComponents.SystemCheckBox;
import ProgramGUI.GUIComponents.SystemLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Account extends NullPanel {

    ArrayList<SystemLabel> labels;
    ArrayList<SystemCheckBox> checks;
    //main program
    Main m;

    public Account(Main m2) {
        super();
        this.m = m2;


        labels = new ArrayList<SystemLabel>();
        checks = new ArrayList<SystemCheckBox>();

        //create new instances
        labels.add(new SystemLabel("Remember Username:"));
        labels.add(new SystemLabel("Remember Password:"));



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
        checks.get(0).setSelected(m.getPrefs().getBoolean("rememberUsername",
                true));
        checks.get(1).setSelected(m.getPrefs().getBoolean("rememberPassword",
                true));

    }

    private class ChangeListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == checks.get(0)) {
                m.getPrefs().putBoolean("rememberUsername",
                        checks.get(0).isSelected());


            } else if (e.getSource() == checks.get(1)) {
                m.getPrefs().putBoolean("rememberPassword",
                        checks.get(1).isSelected());



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
            checks.get(i).setBounds(150, i * 30, 140, 22);
        }


    }
}
