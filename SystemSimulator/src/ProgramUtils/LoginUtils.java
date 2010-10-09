package ProgramUtils;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.security.MessageDigest;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * This Script is a connection script for logging into our system servers and retrieving information
 * Regarding users and their information
 * @author Dylan and Shaun
 * http://pro.dylanvorster.com
 */
public class LoginUtils {

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
}
