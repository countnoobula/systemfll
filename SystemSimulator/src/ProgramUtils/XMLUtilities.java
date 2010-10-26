/*
 * This utilties package loads xml resources and creates various objects
 * which the files represent. All methods are to be static
 */
package ProgramUtils;

import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import VisualLogicSystem.CodeBlock;
import VisualLogicSystem.ConnectionPoint;
import VisualLogicSystem.DataObject;
import VisualLogicSystem.LogicBlock;
import VisualLogicSystem.VariableConnectionPoint;
import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
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
                    Document doc = parser.build("" + xml.getPath());
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
                    String width = graphics.getChildText("width");
                    String height = graphics.getChildText("height");
                    Element imports = root.getChild("imports");
                    //load the imports
                    @SuppressWarnings(value = "unchecked")
                    List<Element> listOfImports = (List<Element>) imports.getChildren();
                    String[] importList = new String[listOfImports.size()];
                    for (int i = 0; i < listOfImports.size(); i++) {
                        importList[i] = listOfImports.get(i).getText();
                    }
                    //load connection points
                    Element data = root.getChild("data");
                    List<Element> dataObject = (List<Element>) data.getChildren();
                    ArrayList<DataObject> dataObjects = new ArrayList<DataObject>(0);
                    for (int i = 0; i < dataObject.size(); i++) {
                        if (dataObject.get(i).getChildText("type").equals("textfield")) {
                            temp.addDataObject(new DataObject("" + dataObject.get(i).getChildText("value"), DataObject.TEXTFIELD));
                        }
                    }
                    //load connection points
                    Element connectionPoints = root.getChild("connectionPoints");
                    List<Element> connectionPoint = (List<Element>) connectionPoints.getChildren();
                    for (int i = 0; i < connectionPoint.size(); i++) {
                        String flowRule = connectionPoint.get(i).getChildText("flowRule");
                        String linkRule = connectionPoint.get(i).getChildText("linkRule");
                        String desc = connectionPoint.get(i).getChildText("description");
                        String code = connectionPoint.get(i).getChildText("code");
                        CodeBlock cb = null;
                        if (code != null) {
                            cb = new CodeBlock(temp.data);
                            cb.setCompileCode(code);
                        }
                        Rectangle tempRect = new Rectangle(Integer.parseInt(connectionPoint.get(i).getChild("rectangle").getChildText("x")), Integer.parseInt(connectionPoint.get(i).getChild("rectangle").getChildText("y")), Integer.parseInt(connectionPoint.get(i).getChild("rectangle").getChildText("width")), Integer.parseInt(connectionPoint.get(i).getChild("rectangle").getChildText("height")));
                        Color colTemp = new Color(Integer.parseInt(connectionPoint.get(i).getChild("color").getChildText("r")), Integer.parseInt(connectionPoint.get(i).getChild("color").getChildText("g")), Integer.parseInt(connectionPoint.get(i).getChild("color").getChildText("b")));
                        temp.addConnectionPoint(new ConnectionPoint(flowRule, desc, Integer.parseInt(linkRule), cb, tempRect, colTemp));
                       
                    }
                    //load variable connection points
                    Element variablePoints = root.getChild("variablePoints");
                    List<Element> variablePoint = (List<Element>) variablePoints.getChildren();
                    for (int i = 0; i < variablePoint.size(); i++) {
                        String dataID = variablePoint.get(i).getChildText("dataID");
                        String title2 = variablePoint.get(i).getChildText("title");
                        String input = variablePoint.get(i).getChildText("input");
                        boolean inp = false;
                        if (input.equals("true")) {
                            inp = true;
                        }
                        if (input.equals("false")) {
                            inp = false;
                        }
                        Color colTemp2 = new Color(Integer.parseInt(variablePoint.get(i).getChild("color").getChildText("r")), Integer.parseInt(variablePoint.get(i).getChild("color").getChildText("g")), Integer.parseInt(variablePoint.get(i).getChild("color").getChildText("b")));
                        temp.addVariableConnectionPoint(new VariableConnectionPoint(temp, temp.getData().get(Integer.parseInt(dataID)), title2, colTemp2, inp));
                        //load variable connection points
                        Image im = ImageIO.read(new File("logicBlocks/images/" + icon));
                        
                        temp.setType(type);
                        temp.setImports(importList);
                        temp.setImage(im);
                        temp.GenerateGLBlock();
                    }
                } catch (JDOMException ex) {
                    System.out.println("something weird");
                } catch (IOException ex) {
                    System.out.println("io exception");
                }
            }
        }
        return temp;
    }
     /**
     * Returns a copy of the object, or null if the object cannot
     * be serialized.
     */
    public static Object copy(Object orig) {
        Object obj = null;
        try {
            // Write the object out to a byte array
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(orig);
            out.flush();
            out.close();

            // Make an input stream from the byte array and read
            // a copy of the object back in.
            ObjectInputStream in = new ObjectInputStream(
                new ByteArrayInputStream(bos.toByteArray()));
            obj = in.readObject();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
        return obj;
    }


    public static void main(String args[]) {
        XMLUtilities.loadLogicBlocks(new File("/SystemSimulator/logicBlocks/Block1.xml"));


    }
}
