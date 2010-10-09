package ProgramUtils;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import ProgramUtils.SQLManager;
import java.security.MessageDigest;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class LoginUtils {

    public LoginUtils() {
    }

    public static Object[] login(String user, String pass) {

        String password = pass;
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("No MD5 algorythm");
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
        pass = hexString.toString();
        System.out.println("pass: "+pass);
        user = user.toLowerCase();
        
        ResultSet set = SQLManager.executeQuery("SELECT * FROM users WHERE user = '" + user + "' AND pass = '" + pass + "'");
        ResultSetMetaData meta = null;


        try {
            meta = set.getMetaData();
            
           
        } catch (SQLException ex) {
            System.out.println("SQL exception in meta data");
        }

        if (set != null) {
            
            int cols = 0;

            try {
                cols = meta.getColumnCount();
                System.out.println("cols: "+cols);
            } catch (SQLException ex) {
                System.out.println("SQL exception for columns");
            }


            try {
                set.next();
            } catch (SQLException ex) {
                System.out.println("Resultset cant go to next");
            }

            Object array[] = new Object[cols];

            for (int i = 0; i < cols; i++) {
                try {
                    Object ob  = set.getObject(i+1);
                    array[i] = ob;
                    System.out.println(""+ ob);
                } catch (SQLException ex) {
                    System.out.println("SQL exception in loop");
                }

            }
            return array;
        } else {
            JOptionPane.showMessageDialog(null, "Fail Login");
            return null;
        }

    }
}
