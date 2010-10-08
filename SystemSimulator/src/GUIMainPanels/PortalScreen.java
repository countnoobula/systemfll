package GUIMainPanels;

import ProgramGUI.GUIComponents.GenericSystemPanel;

import ProgramGUI.GUIComponents.NullPanel;

import ProgramGUI.GUIComponents.SystemButton;
import ProgramGUI.GUIComponents.SystemLabel;
import ProgramGUI.GUIComponents.SystemPassField;
import ProgramGUI.GUIComponents.SystemTextField;

import ProgramUtils.SQLManager;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;

import java.awt.GridBagLayout;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.ResultSet;

import java.util.ArrayList;

import MainClasses.Main;

public class PortalScreen extends GenericSystemPanel {

    private CenterLogin panel_1;
    private CenterRegistration panel_2;
    
    GridBagConstraints gc;
    Main m;
    public PortalScreen(Main m2) {
        super();
        this.m = m2;
        gc = new GridBagConstraints();
        panel_1 = new CenterLogin();
        panel_2 = new CenterRegistration();
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
                        ResultSet set =
                            SQLManager.executeQuery("SELECT * FROM users");
                        System.out.println("Executed Query");
                    }
                });
            bt2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        panel_1.setVisible(false);
                        panel_2.setVisible(true);
                    }
                });
            
            field1.setText(m.getPrefs().get("username",""));
            field2.setText(m.getPrefs().get("password",""));
            this.setPreferredSize(new Dimension(300, 140));
            
        }
        public String getUsername(){
          return field1.getText();
          
        }
        public String getPassword(){
          String temp = "";
          for(int i = 0;i < field2.getPassword().length;i++){
            temp+=field2.getPassword()[i];
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
                        System.out.println("Register for an account");
                    }
                });


        }
    }
}