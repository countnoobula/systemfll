package GUIMainPanels;

import Forum.WelcomePanel;
import ProgramGUI.GUIComponents.Panes.GenericSystemPanel;

import ProgramGUI.GUIComponents.Panes.NullPanel;

import ProgramGUI.GUIComponents.Buttons.SystemButton;
import ProgramGUI.GUIComponents.SystemLabel;
import ProgramGUI.GUIComponents.SystemPassField;
import ProgramGUI.GUIComponents.SystemTextField;

import ProgramUtils.ForumUtils;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;

import java.awt.GridBagLayout;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import MainClasses.Main;
import javax.swing.JOptionPane;

public class PortalScreen extends GenericSystemPanel {

    private CenterLogin panel_1;
    private CenterRegistration panel_2;
    private WelcomePanel wp;
    GridBagConstraints gc;
    Main m;

    public PortalScreen(Main m2) {
        super();
        this.m = m2;
        gc = new GridBagConstraints();
        panel_1 = new CenterLogin();
        panel_2 = new CenterRegistration();
        wp = new WelcomePanel();
        this.setLayout(new GridBagLayout());
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.weightx = 0;
        gc.weighty = 0;
        gc.insets = new Insets(30, 0, 0, 0);

        gc.fill = GridBagConstraints.BOTH;

        this.add(panel_1);
        this.add(panel_2);
        this.add(wp);

    }

    public PortalScreen.CenterLogin getPanel_1() {
        return panel_1;
    }

    public class CenterLogin extends NullPanel {

        SystemTextField field1;
        SystemPassField field2;
        SystemLabel label1, label2, label3;
        SystemButton bt1, bt2;

        public CenterLogin() {
            this.setLayout(null);

            field1 = new SystemTextField();
            field2 = new SystemPassField();

            label1 = new SystemLabel("Username:");
            label2 = new SystemLabel("Password:");
            label3 = new SystemLabel("Login to the Portal");
            label3.setFont(new Font("Arial", 15, 15));
            bt1 = new SystemButton("Login");
            bt2 = new SystemButton("Register");
            this.add(field1);
            this.add(field2);
            this.add(label1);
            this.add(label3);
            this.add(label2);
            this.add(bt1);
            this.add(bt2);
            field1.setBounds(100, 40, 180, 25);
            field2.setBounds(100, 70, 180, 25);
            label1.setBounds(10, 40, 90, 25);
            label2.setBounds(10, 70, 90, 25);
            label3.setBounds(100, 10, 190, 25);
            bt1.setBounds(100, 110, 85, 22);
            bt2.setBounds(195, 110, 85, 22);

            bt1.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    Object p[] = ForumUtils.login(field1.getText(), field2.getPassword());
                    if (p != null) {
                        if (m.getPrefs().getBoolean("rememberUsername",
                                true) == true) {
                            m.getPrefs().put("username", field1.getText());

                        }
                        if (m.getPrefs().getBoolean("rememberPassword",
                                true) == true) {
                            String password = "";
                            for (int i = 0; i < field2.getPassword().length; i++) {
                                password = password + field2.getPassword()[i];
                            }
                            m.getPrefs().put("password", password);
                        }
                    }
                    if (p != null) {
                        panel_1.setVisible(false);
                        panel_2.setVisible(false);
                        wp.setVisible(true);
                    }
                }
            });
            bt2.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    panel_1.setVisible(false);
                    panel_2.setVisible(true);
                }
            });

            field1.setText(m.getPrefs().get("username", ""));
            field2.setText(m.getPrefs().get("password", ""));
            this.setPreferredSize(new Dimension(300, 140));

        }

        public String getUsername() {
            return field1.getText();

        }

        public String getPassword() {
            String temp = "";
            for (int i = 0; i < field2.getPassword().length; i++) {
                temp += field2.getPassword()[i];
            }
            return temp;
        }
    }

    private class CenterRegistration extends NullPanel {

        private ArrayList<SystemLabel> labels;
        private ArrayList<SystemTextField> fields;
        private ArrayList<SystemPassField> pass;
        private SystemButton bt1, bt2;

        private CenterRegistration() {
            this.setVisible(false);
            this.setLayout(null);
            this.setPreferredSize(new Dimension(400, 400));
            labels = new ArrayList<SystemLabel>();
            fields = new ArrayList<SystemTextField>();
            pass = new ArrayList<SystemPassField>();

            bt1 = new SystemButton("Back");
            bt2 = new SystemButton("Register");

            //create new instances
            labels.add(new SystemLabel("Register your new account"));
            labels.add(new SystemLabel("Name:"));
            labels.add(new SystemLabel("Surname:"));
            labels.add(new SystemLabel("Username"));
            labels.add(new SystemLabel("Password:"));
            labels.add(new SystemLabel("Password again:"));
            labels.add(new SystemLabel("Email:"));
            labels.add(new SystemLabel("Email again:"));
            labels.add(new SystemLabel("Birth Date:"));
            labels.add(new SystemLabel("YYYY"));
            labels.add(new SystemLabel("MM"));
            labels.add(new SystemLabel("DD"));

            for (int i = 0; i < 8; i++) {
                fields.add(new SystemTextField());
                this.add(fields.get(i));
            }
            for (int i = 0; i < labels.size(); i++) {
                fields.add(new SystemTextField());
                this.add(labels.get(i));
            }
            pass.add(new SystemPassField());
            pass.add(new SystemPassField());
            this.add(pass.get(0));
            this.add(pass.get(1));
            this.add(bt1);
            this.add(bt2);


            labels.get(0).setFont(new Font("Arial", 18, 18));


            //set locations
            labels.get(0).setBounds(10, 10, 300, 25);
            labels.get(8).setBounds(10, 300, 150, 25);
            for (int i = 1; i < 8; i++) {
                labels.get(i).setBounds(10, (i * 30) + 30, 130, 25);
            }
            for (int i = 0; i < 3; i++) {
                fields.get(i).setBounds(140, ((i + 1) * 30) + 30, 150, 25);
            }
            pass.get(0).setBounds(140, 150, 150, 25);
            pass.get(1).setBounds(140, 180, 150, 25);
            fields.get(3).setBounds(140, 210, 150, 25);
            fields.get(4).setBounds(140, 240, 150, 25);

            labels.get(9).setBounds(145, 270, 50, 25);
            labels.get(10).setBounds(195, 270, 50, 25);
            labels.get(11).setBounds(245, 270, 50, 25);

            fields.get(5).setBounds(140, 300, 40, 25);
            fields.get(6).setBounds(190, 300, 40, 25);
            fields.get(7).setBounds(240, 300, 40, 25);

            bt1.setBounds(10, 340, 100, 22);
            bt2.setBounds(120, 340, 100, 22);

            bt1.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    panel_2.setVisible(false);
                    panel_1.setVisible(true);
                }
            });
            bt2.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    String password = "";
                    for (int i = 0; i < pass.get(0).getPassword().length; i++) {
                        password = password + pass.get(0).getPassword()[i];
                    }
                    String password2 = "";
                    for (int i = 0; i < pass.get(1).getPassword().length; i++) {
                        password2 = password2 + pass.get(1).getPassword()[i];
                    }
                    ForumUtils.Register(fields.get(0).getText(),
                            fields.get(1).getText(),
                            fields.get(2).getText(),
                            password,
                            password2,
                            fields.get(3).getText(),
                            fields.get(4).getText(),
                            fields.get(5).getText(),
                            fields.get(6).getText(),
                            fields.get(7).getText());
                }
            });

        }
    }
}
