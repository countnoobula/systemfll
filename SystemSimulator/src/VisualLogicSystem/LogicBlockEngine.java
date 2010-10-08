package VisualLogicSystem;

import VisualLogicSystem.LogicBlocks.LogicEnd;
import VisualLogicSystem.LogicBlocks.LogicStart;

import java.util.ArrayList;

/**
 * The engine which handles all the block requests
 */
public class LogicBlockEngine {

    static ArrayList<LogicBlockInterface> blocks;
    private ArrayList<LogicLink> links;


    public LogicBlockEngine() {
        this.blocks = new ArrayList<LogicBlockInterface>();
        this.links = new ArrayList<LogicLink>();
    }

    public static boolean canLinkBlocks(LogicBlock block1, int rect1,
                                        LogicBlock block2, int rect2) {

        String contains = "";
        contains += block1.getConnectionRules(rect1);
        contains += block2.getConnectionRules(rect2);

        if (contains.contains("1") & contains.contains("2")) {
            return true;
        }
        return false;
    }


    public static void addBlock(LogicBlock b) {
        blocks.add(b);

    }

    public void removeBlock(LogicBlock l) {
        for (int i = 0; i < l.getLinksArraySize(); i++) {
            links.remove(l.getLinks(i));

        }
        blocks.remove(l);

    }

    public void removeLink(LogicLink l) {
        links.remove(l);
    }

    public void removeLink(int i) {
        links.remove(i);
    }

    public int getBlockArraySize() {
        return blocks.size();
    }

    public LogicBlock getBlock(int i) {
        return (LogicBlock)blocks.get(i);
    }

    public int getLinkArraySize() {
        return links.size();
    }

    public LogicLink getLink(int i) {
        return links.get(i);
    }

    public void addLogicLink(LogicLink l) {
        links.add(l);
    }

}