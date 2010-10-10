package VisualLogicSystem;

import java.util.ArrayList;

/**
 * The engine which handles all the block requests
 */
public class LogicBlockEngine {

    static ArrayList<LogicBlockInterface> blocks;
    private ArrayList<LogicLink> links;
    public static boolean isTop = false;

    public static ArrayList<CodeBlock> compile() {
        ArrayList<CodeBlock> b = new ArrayList<CodeBlock>(0);
        LogicBlock first = null;

        loop:
        for (int i = 0; i < blocks.size(); i++) {
            if (((LogicBlock) blocks.get(i)).getType().equals("start")) {
                //hahah, yes, we found the first one :D

                first = (LogicBlock) blocks.get(i);
                break loop;


            }
        }

        if (first != null) {

            System.out.println("starting recursion:");
            System.out.println("--------------------------");

            //start recursive MIND FUCK!!!!!!!!!!!!!!!!!!!!!!
            addNextBlock(first, b);




        }
        return b;
    }

    /**
     * Recursively builds an array 
     * @param b
     * @param b2
     */
    public static void addNextBlock(LogicBlock b, ArrayList<CodeBlock> blocks) {

        //add the first part of the coe block
        blocks.add(b.getCodeBlocks().get(0));
        System.out.println("added a block");

        //loop through the blocks connection points
        //  > i < is the connection square
        for (int i = 0; i < b.getLinkInfoSize(); i++) {
            try {


                int currentSquare = Integer.parseInt(b.getLinkInfo(i));
                System.out.println("was able to to convert.");
                if (currentSquare == b.getCurrentCompileString()) {


                    //this means that we are going to run the rows from top to bottom

                    //we now need to check if the lines start and end points equal the current square
                    for (int j = 0; j < b.getNodes().size(); j++) {

                        if (b.getNodes().get(j).getStart().equals(b)) {

                            System.out.println("found a match at 1");
                            if (b.getNodes().get(j).getStartConnection() == i) {
                                //check whether this line's start or end points match the current on

                                System.out.println("found a connection match at 1");
                                addNextBlock(b.getNodes().get(j).getEnd(), blocks);
                            }



                        } else if (b.getNodes().get(j).getEnd().equals(b)) {

                            System.out.println("found a match at 2");
                            //check whether this line's start or end points match the current one


                            System.out.println("found a connection match at 2");
                            if (b.getNodes().get(j).getEndConnection() == i) {

                                addNextBlock(b.getNodes().get(j).getStart(), blocks);
                            }

                        }

                    }


                    //and then increase the string for the next row
                    b.setCurrentCompileString(b.getCurrentCompileString() + 1);


                }

            } catch (Exception e) {
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
