package ProgramUtils;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * The authentication script which will connect and run SQL statements from the System databases
 * @author Dylan and Shaun
 */
public class SQLManager{

    //the super connection url
    private static String url = ("jdbc:mysql://voidblog.com:3306/voidblo1_pro");
    private static Connection con = null;


    /**
     * Returns a result set if there was a successful query
     * @param sql The SQL SELECT statement
     * @return The resulting data set.
     */
    public static ResultSet executeQuery(String sql){

        
        try {
            try {
               Class.forName( "com.mysql.jdbc.Driver" ).newInstance() ;
            } catch (InstantiationException ex) {
                System.out.println("Cant find Driver Manager");
            } catch (IllegalAccessException ex) {
                System.out.println("There is No access to the site");
            }

        } catch (ClassNotFoundException ex) {
            System.out.println("cant find driver");
        }
        try {

            con = (Connection) DriverManager.getConnection(url, "voidblo1_shaun", "system");
            Statement statement;
            statement = (Statement) con.createStatement();
            ResultSet set = statement.executeQuery("" + sql);

            return set;
        } catch (SQLException ex) {
            
            System.out.println("SQL did not work");
            return null;
        }

    }

    /**
     * Returns a result set if there was a successful query
     * @param sql The SQL SELECT statement
     * @return The resulting data set.
     */
    public static void insertQuery(String sql){
        try {
            try {
               Class.forName( "com.mysql.jdbc.Driver" ).newInstance() ;
            } catch (InstantiationException ex) {
                System.out.println("Cant find Driver Manager");
            } catch (IllegalAccessException ex) {
                System.out.println("There is no access to the site");
            }

        } catch (ClassNotFoundException ex) {
            System.out.println("cant find driver");
        }
        try {

            con = (Connection) DriverManager.getConnection(url, "voidblo1_shaun", "system");
            Statement statement;
            statement = (Statement) con.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException ex) {

            System.out.println("SQL did not work");
            
        }
    }

    /**
     * Simple test script
     * @param args don't worry about this
     */
    public static void main(String args[]){
        SQLManager.executeQuery("SELECT * FROM forum_users;");
    }
}
