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
            addNextBlock(first, b, null);

            //kablamo it finished, now reset compile variables
            for (int i = 0; i < blocks.size(); i++) {
                ((LogicBlock) blocks.get(i)).setCurrentCompileString(0);
            }



        }
        return b;
    }

    /**
     * Recursively builds an array 
     * @param b
     * @param b2
     */
    public static void addNextBlock(LogicBlock b, ArrayList<CodeBlock> blocks, LogicLink l) {



        boolean stop = false;
        boolean breaker = false;


        //!------- entering command ------!

        //make sure the block is not the start of the program
        if (l != null) {

            //checks to see if the block isnt the same block we came in on
            if (l.getStart().equals(b)) {

                //checks with the links to see if the
                if (b.getLinkInfo(l.getStartConnection()).equals("break")) {

                    stop = true;
                    //old code
                    //blocks.add(b.getCodeBlocks().get(0));
                    if (b.getConnectionPoint(l.getStartConnection()).getC() != null) {
                        blocks.add(b.getConnectionPoint(l.getStartConnection()).getC());
                        System.out.println("added at insert 1");
                    }

                } else if (b.getLinkInfo(l.getStartConnection()).equals("next")) {
                    stop = false;
                    //old code
                    //blocks.add(b.getCodeBlocks().get(1));
                    if (b.getConnectionPoint(l.getStartConnection()).getC() != null) {
                        blocks.add(b.getConnectionPoint(l.getStartConnection()).getC());
                        System.out.println("added at insert 2");
                    }
                    breaker = true;
                }

            }
            if (l.getEnd().equals(b)) {
                if (b.getLinkInfo(l.getEndConnection()).equals("break")) {

                    stop = true;
                    //old code
                    //blocks.add(b.getCodeBlocks().get(0));
                    if (b.getConnectionPoint(l.getEndConnection()).getC() != null) {
                        blocks.add(b.getConnectionPoint(l.getEndConnection()).getC());
                        System.out.println("added at insert 3");
                    }


                } else if (b.getLinkInfo(l.getEndConnection()).equals("next")) {
                    stop = false;
                    //old code
                    //blocks.add(b.getCodeBlocks().get(1));
                    if (b.getConnectionPoint(l.getEndConnection()).getC() != null) {
                        blocks.add(b.getConnectionPoint(l.getEndConnection()).getC());
                        System.out.println("added at insert 4");
                    }
                    breaker = true;
                }
            }
        }


        //!------- Designation command ------!

        if (stop == false) {

            //loop through the blocks connection points
            //  > i < is the connection square
            for (int i = 0; i < b.getLinkInfoSize(); i++) {
                try {


                    int currentSquare = Integer.parseInt(b.getLinkInfo(i));
                    if (currentSquare == b.getCurrentCompileString()) {
                            //add the first part of the code block



                        if (b.getConnectionPoint(i).getC() != null) {

                            blocks.add(b.getConnectionPoint(i).getC());
                            System.out.println("added at insert loop");
                        }
                        


                        //this means that we are going to  run the connection lines from top to bottom

                        //we now need to check if the lines' start and end points equal the current square
                        for (int j = 0; j < b.getNodes().size(); j++) {

                            if (b.getNodes().get(j).getStart().equals(b)) {

                                if (b.getNodes().get(j).getStartConnection() == i) {
                                    //check whether this line's start or end points match the current on

                                    System.out.println("started recursion 1");
                                    addNextBlock(b.getNodes().get(j).getEnd(), blocks, b.getNodes().get(j));

                                }



                            } else if (b.getNodes().get(j).getEnd().equals(b)) {

                                //check whether this line's start or end points match the current one

                                if (b.getNodes().get(j).getEndConnection() == i) {

                                    System.out.println("started recursion 2");
                                    addNextBlock(b.getNodes().get(j).getStart(), blocks, b.getNodes().get(j));
                                }
                            }
                        }


                        //and then increase the string for the next row
                        b.setCurrentCompileString(b.getCurrentCompileString() + 1);
                    }

                } catch (Exception e) {
                    //this means that we have encountered a compile command, it is not an error,
                    //its designed this way
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
