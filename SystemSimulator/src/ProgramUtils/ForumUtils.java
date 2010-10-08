package ProgramUtils;

import java.sql.ResultSet;
import ProgramUtils.SQLManager;
import java.security.MessageDigest;
import java.sql.ResultSetMetaData;
import javax.swing.JOptionPane;

public class ForumUtils {

    public ForumUtils() {
    }

    public static Object[] login(String user, String pass) {
        try {
            String password = pass;
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte byteData[] = md.digest();

            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                String hex = Integer.toHexString(0xff & byteData[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            pass = hexString.toString();

            user = user.toLowerCase();

            ResultSet set = SQLManager.executeQuery("SELECT * FROM forum_users WHERE username = '" + user + "' AND user_password = '" + pass + "'");
            ResultSetMetaData meta = set.getMetaData();

            if (set != null) {
                JOptionPane.showMessageDialog(null, "Successful Login");
                int cols = meta.getColumnCount();
                Object array[] = new Object[cols];
                for (int i = 0; i < cols; i++) {
                    array[i] = set.getObject(i + 1);
                }
                return array;
            } else {
                JOptionPane.showMessageDialog(null, "Fail Login");
                return null;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }
}
