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

    public static ArrayList<LogicBlock> compile() {
        ArrayList<LogicBlock> b = new ArrayList<LogicBlock>(0);
        LogicBlock first = null;

        loop:
        for (int i = 0; i < blocks.size(); i++) {
            if (blocks.get(i) instanceof LogicStart) {
                //hahah, yes, we found the first one :D

                first = (LogicBlock) blocks.get(i);
                System.out.println("found start");
                break loop;


            }
        }

        if (first != null) {

            //start recursive MIND FUCK!!!!!!!!!!!!!!!!!!!!!!
            addNextBlock(first, b, first.getNodes().get(0));




        }
        for (int i = 0; i < blocks.size(); i++) {
            if (blocks.get(i) instanceof LogicEnd) {
                //hahah, yes, we found the first one :D
                b.add((LogicBlock) blocks.get(i));

            }
        }
        return b;
    }

    public static void addNextBlock(LogicBlock b, ArrayList<LogicBlock> b2, LogicLink l) {


        //looping through all the connection lines
        for (int i = 0; i < b.getNodes().size(); i++) {

            //the first block
            if (b instanceof LogicStart) {
                //the blocks found on each connection line
                LogicBlock block1 = b.getNodes().get(0).getStart();
                LogicBlock block2 = b.getNodes().get(0).getEnd();

                //check if the blocks do not equal the previous block
                if (!block1.equals(b)) {
                    b2.add(block2);
                    System.out.println("added the start");
                    addNextBlock(block1, b2, b.getNodes().get(0));
                    
                }
                if (!block2.equals(b)) {
                    b2.add(block1);
                    addNextBlock(block2, b2, b.getNodes().get(0));
                    System.out.println("added the start");
                }
                
                
            } else {


                //we using a new connection line, not the old one
                if (l != b.getNodes().get(i)) {

                    //the blocks found on each connection line
                    LogicBlock block1 = b.getNodes().get(i).getStart();
                    LogicBlock block2 = b.getNodes().get(i).getEnd();

                    if (!block1.equals(b)) {
                        b2.add(block2);
                        System.out.println("added a block");
                        addNextBlock(block1, b2, b.getNodes().get(i));
                        
                    }
                    if (!block2.equals(b)) {
                        b2.add(block1);
                        System.out.println("added a block");
                        addNextBlock(block2, b2, b.getNodes().get(i));
                        
                    }
                }
            }
        }
    }

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

    public static boolean determinFirstBlocks(LogicBlock block1, int rect1,
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

    public void removeBlock(LogicBlock b) {

        //returns 2
        int length = b.getLinksArraySize();
        
        for (int i = 0; i < length; i++) {

            LogicLink temp = b.getLinks(0);
 
            temp.getStart().removeLink(temp);
            temp.getEnd().removeLink(temp);

            b.removeLink(temp);
            
            links.remove(temp);
            
           
        }

        blocks.remove(b);

    }

    public void removeLink(LogicLink l) {
        l.getStart().removeLink(l);
        l.getEnd().removeLink(l);
        links.remove(l);

    }

    public int getBlockArraySize() {
        return blocks.size();
    }

    public LogicBlock getBlock(int i) {
        return (LogicBlock) blocks.get(i);
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
