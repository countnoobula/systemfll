package ProgramUtils;

import ProgramGUI.GUIComponents.Buttons.SystemButton;
import ProgramGUI.GUIComponents.Panes.NullPanel;
import ProgramGUI.GUIComponents.SystemLabel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.security.MessageDigest;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This Script is a connection script for logging into our system servers and retrieving information
 * Regarding users and their information
 * @author Dylan and Shaun
 * http://pro.dylanvorster.com
 */
public class ForumUtils {

    /**
     * Checks whether a user is logged in.
     * @param user the username of the user
     * @param pass the password of the user
     * @return An array of information regarding the user such as id, name, surname email etc..
     */
    public static Object[] login(String user, char pass[]) {

        String password = "";
        for(int i = 0;i < pass.length;i++){
            password = password+pass[i];
        }

        //All data is stored as MD5 on the servers
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("No MD5 algorythm");
            return null;
        }
        md.update(password.getBytes());
        byte byteData[] = md.digest();

        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
            String hex = Integer.toHexString(0xff & byteData[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }


        password = hexString.toString();
        user = user.toLowerCase();

        ResultSet set = SQLManager.executeQuery("SELECT * FROM users WHERE user = '" + user + "' AND pass = '" + password + "'");
        ResultSetMetaData meta = null;


        try {
            meta = set.getMetaData();


        } catch (SQLException ex) {
            System.out.println("SQL exception in meta data");
            return null;
        }

        if (set != null) {

            int cols = 0;

            try {
                cols = meta.getColumnCount();
                System.out.println("cols: " + cols);
            } catch (SQLException ex) {
                System.out.println("SQL exception for columns");
                return null;
            }


            try {
                set.next();
            } catch (SQLException ex) {
                System.out.println("Resultset cant go to next");
                return null;
            }

            Object array[] = new Object[cols];

            //itterates through the information result set and then sets up the array
            for (int i = 0; i < cols; i++) {
                try {
                    Object ob = set.getObject(i + 1);
                    array[i] = ob;
                } catch (SQLException ex) {
                    System.out.println("SQL exception in loop");
                    return null;
                }

            }
            return array;
        } else {
            JOptionPane.showMessageDialog(null, "Fail Login");
            return null;
        }

    }

    public static boolean Register(String name, String surname, String username, String pass1, String pass2, String email1, String email2, String year, String month, String day) {
        if(!pass1.equals(pass2)) {
            System.out.println("passwords not equal");
            JOptionPane.showMessageDialog(null, "Registration Failed");
            return false;
        }
        if(!email1.equals(email2)) {
            System.out.println("emails not equal");
            JOptionPane.showMessageDialog(null, "Registration Failed");
            return false;
        }
        try {
            String yob = year + "-" + month + "-" + day;
            SQLManager.insertQuery("INSERT INTO users(name, surname, user, pass, email, dob, active, ban, admin) VALUES('"+name+"', '"+surname+"', '"+pass1+"', '"+email1+"','"+yob+"', '0', '0', '')");
            JOptionPane.showMessageDialog(null, "Registration Complete");
            return true;
        } catch(Exception e) {
            System.out.println(e.toString());
            JOptionPane.showMessageDialog(null, "Registration Failed");
            return false;
        }        
    }

    public static JPanel getTopics() {
        NullPanel main = new NullPanel();
        SystemLabel text = new SystemLabel("");
        SystemButton view = new SystemButton("");
        ResultSet set = SQLManager.executeQuery("SELECT * FROM forum_topics");
        GridBagConstraints gc = new GridBagConstraints();
        main.setLayout(new GridBagLayout());
        try {
            while (set.next()) {
                text = new SystemLabel(set.getString("topics"));
                view = new SystemButton("View");
                view.addActionListener( new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("It would redirect you to topic. I will get there dylan...");
                    }
                });
                gc.gridx = 1;
                gc.gridwidth = 2;
                main.add(text, gc);
                gc.gridx = 3;
                gc.gridwidth = 0;
                main.add(view, gc);
            }
        } catch (Exception e) {
            System.out.println("Error has occured: " + e.toString());
        }
        return main;
    }
}