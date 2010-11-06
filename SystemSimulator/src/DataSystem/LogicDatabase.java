
package DataSystem;

import SystemObjects.SystemObject;
import VisualLogicSystem.DataBlockSystem.DataBlock;
import VisualLogicSystem.DataBlockSystem.DataLink;
import VisualLogicSystem.LogicObjects.LogicBlock;
import VisualLogicSystem.LogicObjects.LogicLink;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author dylan
 */
public class LogicDatabase extends SystemObject implements Serializable{

    //variables
    public ArrayList<LogicBlock> blocks;
    public ArrayList<LogicLink> links;
    public ArrayList<DataBlock> dataBlocks;
    public ArrayList<DataLink> dataLinks;

    public LogicDatabase(){
        super("Visual Logic Diagram");
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
