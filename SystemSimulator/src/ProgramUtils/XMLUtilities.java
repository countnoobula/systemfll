/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ProgramUtils;

import VisualLogicSystem.LogicBlock;
import java.io.File;
import java.util.ArrayList;
import org.jdom.Document;
import org.jdom.input.SAXBuilder;

/**
 *
 * @author Dylan
 */
public class XMLUtilities {

    public static LogicBlock[] loadLogicBlocks(File directory) {
        ArrayList<LogicBlock> temp = new ArrayList<LogicBlock>(0);
        //processing here
        if (directory.exists()) {
            if (directory.isDirectory()) {
                for (int i = 0; i < directory.list().length; i++) {
                    File tempFile = directory.listFiles()[i];
                    try {
                        SAXBuilder parser = new SAXBuilder();
                        Document doc = parser.build(""+tempFile.getName());
                        
                        // work with the document…
                    } catch (Exception e) {

                    }
                }
            }
        }

        return (LogicBlock[]) temp.toArray();
    }
}
