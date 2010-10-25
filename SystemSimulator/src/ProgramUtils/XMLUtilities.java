/*
 * This utilties package loads xml resources and creates various objects
 * which the files represent. All methods are to be static
 */
package ProgramUtils;

import VisualLogicSystem.CodeBlock;
import VisualLogicSystem.ConnectionPoint;
import VisualLogicSystem.DataBlocks.DataObject;
import VisualLogicSystem.LogicBlock;
import java.awt.Color;
import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

/**
 *
 * @author Dylan
 */
public class XMLUtilities {

    public static LogicBlock loadLogicBlocks(File xml) {
        LogicBlock temp = new LogicBlock();
        //processing here
        if (xml.exists()) {
            if (xml.isFile()) {

                try {
                    SAXBuilder parser = new SAXBuilder();
                    Document doc = parser.build("" + xml.getName());
                    //start loading some of the entities
                    Element root = doc.getRootElement();

                    Element information = root.getChild("information");

                    //load the information
                    String title = information.getChildText("title");
                    String description = information.getChildText("description");
                    String type = information.getChildText("type");

                    Element graphics = root.getChild("graphics");

                    //load the graphics
                    String icon = graphics.getChildText("icon");
                    String width = graphics.getChildText("with");
                    String height = graphics.getChildText("height");

                    Element imports = root.getChild("imports");
                    //load the imports
                    @SuppressWarnings("unchecked")
                    List<Element> listOfImports = (List<Element>) imports.getChildren();
                    String importList[] = new String[listOfImports.size()];
                    for (int i = 0; i < listOfImports.size(); i++) {
                        importList[i] = listOfImports.get(i).getText();

                    }

                    //load connection points
                    Element data = root.getChild("data");

                    List<Element> dataObject = (List<Element>) data.getChildren();
                    ArrayList<DataObject> dataObjects = new ArrayList<DataObject>(0);
                    for (int i = 0; i < dataObject.size(); i++) {
                        if (dataObject.get(i).getChildText("type").equals("textfield")) {
                            dataObjects.add(Integer.parseInt(dataObject.get(i).getAttribute("id").getValue()),
                                    new DataObject(""
                                    + dataObject.get(i).getChildText("value"),
                                    DataObject.TEXTFIELD));

                        }
                    }

                    //load connection points
                    Element connectionPoints = root.getChild("connectionPoints");

                    List<Element> connectionPoint = (List<Element>) connectionPoints.getChildren();
                    ArrayList<ConnectionPoint> connections1 = new ArrayList<ConnectionPoint>(0);

                    for (int i = 0; i < connectionPoint.size(); i++) {


                        String flowRule = connectionPoint.get(i).getChildText("flowRule");
                        String linkRule = connectionPoint.get(i).getChildText("linkRule");
                        String desc = connectionPoint.get(i).getChildText("description");
                        String code = connectionPoint.get(i).getChildText("code");

                        CodeBlock cb = null;
                        if (code != null) {
                            cb = new CodeBlock(temp);
                            cb.setCompileCode(code);

                        }
                        //generate the rectangle for this connecion points
                        Rectangle tempRect = new Rectangle(
                                Integer.parseInt(connectionPoint.get(i).getChild("rectangle").getChildText("x")),
                                Integer.parseInt(connectionPoint.get(i).getChild("rectangle").getChildText("y")),
                                Integer.parseInt(connectionPoint.get(i).getChild("rectangle").getChildText("width")),
                                Integer.parseInt(connectionPoint.get(i).getChild("rectangle").getChildText("height")));
                        Color colTemp = new Color(
                                Integer.parseInt(connectionPoint.get(i).getChild("color").getChildText("r")),
                                Integer.parseInt(connectionPoint.get(i).getChild("color").getChildText("g")),
                                Integer.parseInt(connectionPoint.get(i).getChild("color").getChildText("b")));

                        
                        connections1.add(new ConnectionPoint(
                                flowRule,desc,Integer.parseInt(linkRule),cb,tempRect,colTemp
                                ));

                        //load variable connection points

                       


                    }


                } catch (Exception e) {
                }
            }

        }
        return temp;
    }

    public static void main(String args[]) {
        XMLUtilities.loadLogicBlocks(new File("block1.xml"));
    }
}
