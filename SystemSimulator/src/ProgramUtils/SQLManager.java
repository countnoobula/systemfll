package ProgramUtils;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.io.IOException;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Dylan
 */
public class SQLManager{

    private static String url = ("jdbc:mysql://voidblog.com:3306/voidblo1_system");
    private static Connection con = null;


    //!---------------    This will generally connect  ---------------!

    public static ResultSet executeQuery(String sql){

        
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            } catch (InstantiationException ex) {
                System.out.println("Cant find Driver Manager");
            } catch (IllegalAccessException ex) {
                System.out.println("There is No access to the site");
            }

        } catch (ClassNotFoundException ex) {
            System.out.println("cant find driver");
        }
        try {

            con =
 (Connection)DriverManager.getConnection(url, "voidblo1_shaun", "system");
            Statement statement;
            statement = (Statement)con.createStatement();
            ResultSet set = statement.executeQuery("" + sql);
            //ResultSetMetaData metaData2 = set.getMetaData();


            return set;
        } catch (SQLException ex) {
            return null;
        }

    }
    public static void main(String args[]){
        SQLManager.executeQuery("SELECT * FROM users");
    }
}
