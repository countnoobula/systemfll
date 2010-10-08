package ProgramUtils;

import java.sql.ResultSet;
import ProgramUtils.SQLManager;
import java.math.BigInteger;
import java.security.MessageDigest;
import javax.swing.JOptionPane;

public class ForumUtils {
    public ForumUtils() {
        
    }
    public static boolean login(String user, String pass) {
        try {
            String password = pass;
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte byteData[] = md.digest();

            StringBuffer hexString = new StringBuffer();
            for (int i=0;i<byteData.length;i++) {
    		String hex = Integer.toHexString(0xff & byteData[i]);
   	     	if(hex.length()==1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            pass = hexString.toString();

            ResultSet set = SQLManager.executeQuery("SELECT * FROM forum_users WHERE username = '" + user + "' AND user_password = '" + pass + "'");
            
            if(set != null) {
                JOptionPane.showMessageDialog(null, "Successful Login");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Fail Login");
                return false;
            }
        } catch(Exception e) {
            System.out.println(e.toString());
        }
        return false;
    }
}
