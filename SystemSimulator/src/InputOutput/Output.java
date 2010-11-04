package InputOutput;


//le imports
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 *
 * @author dylan
 */
public class Output {

    /**
     * Writes the System Project to a file
     * @param s
     * The File URL
     */
    public static void writeDatabase(Object o,String s) {
        // Write to disk with FileOutputStream
        FileOutputStream f_out = null;
        try {
            f_out = new FileOutputStream("" + s + ".sdb");
        } catch (FileNotFoundException ex) {
            System.out.println("could not create output file");
        }

        ObjectOutputStream obj_out = null;
        try {
            obj_out = new ObjectOutputStream(f_out);
        } catch (IOException ex) {
            System.out.println("could create output object");
        }
        try {
            obj_out.writeObject(o);
            System.out.println("File saved successfully");
        } catch (IOException ex) {
            System.out.println("Could not write Object");
        }
    }
      /**
     * Reads in a system Project File
     * @param s
     * File URL
     */
    public static Object readDatabase(String s) {
        // Read from disk using FileInputStream
        FileInputStream f_in = null;
        try {
            f_in = new FileInputStream(""+s+".sdb");
        } catch (FileNotFoundException ex) {
            System.out.println("could not find file on disk");
        }

        // Read object using ObjectInputStream
        ObjectInputStream obj_in = null;
        try {
            obj_in = new ObjectInputStream(f_in);
        } catch (IOException ex) {
            System.out.println("could not read object");
        }
        try {
            Object o= obj_in.readObject();
            System.out.println("file imported succesfully");
            return o;

        } catch (IOException ex) {
            System.out.println("could not load");
            return null;
        } catch (ClassNotFoundException ex) {
            System.out.println("class not found");
            return null;
        }


    }
}
