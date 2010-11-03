/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MainClasses;

import ProgramUtils.XMLUtilities;
import VisualLogicSystem.LogicObjects.LogicBlock;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Dylan
 */
public class SystemDepo {

    ArrayList<LogicBlock> logicLibrary;
    public SystemDepo(){
        logicLibrary = new ArrayList<LogicBlock>();
        loadLibrary(new File("logicBlocks"));
    }
    public void loadLibrary(File directory){
        for(int i = 0;i < directory.listFiles().length;i++){
            if(directory.listFiles()[i].isFile()){
                if(directory.listFiles()[i].getName().contains(".xml")){
                  logicLibrary.add(XMLUtilities.loadLogicBlocks(directory.listFiles()[i]));
                }  
            }
        }
    }

    public ArrayList<LogicBlock> getLogicLibrary() {
        return logicLibrary;
    }


}
