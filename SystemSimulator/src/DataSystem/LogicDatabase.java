
package DataSystem;

import VisualLogicSystem.DataBlockSystem.DataBlock;
import VisualLogicSystem.DataBlockSystem.DataLink;
import VisualLogicSystem.LogicObjects.LogicBlock;
import VisualLogicSystem.LogicObjects.LogicLink;
import java.util.ArrayList;

/**
 *
 * @author dylan
 */
public class LogicDatabase {

    //variables
    public static ArrayList<LogicBlock> blocks;
    public static ArrayList<LogicLink> links;
    public static ArrayList<DataBlock> dataBlocks;
    public static ArrayList<DataLink> dataLinks;

    public LogicDatabase(){

         //create new instances
        this.blocks = new ArrayList<LogicBlock>();
        this.links = new ArrayList<LogicLink>();
        this.dataBlocks = new ArrayList<DataBlock>();
        this.dataLinks = new ArrayList<DataLink>();

    }

    public ArrayList<LogicBlock> getBlocks() {
        return blocks;
    }

    public void setBlocks(ArrayList<LogicBlock> blocks) {
        this.blocks = blocks;
    }

    public ArrayList<DataBlock> getDataBlocks() {
        return dataBlocks;
    }

    public void setDataBlocks(ArrayList<DataBlock> dataBlocks) {
        this.dataBlocks = dataBlocks;
    }

    public ArrayList<DataLink> getDataLinks() {
        return dataLinks;
    }

    public void setDataLinks(ArrayList<DataLink> dataLinks) {
        this.dataLinks = dataLinks;
    }

    public ArrayList<LogicLink> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<LogicLink> links) {
        this.links = links;
    }
    
}
