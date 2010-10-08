package VisualLogicSystem.LogicBlocks;

import VisualLogicSystem.DataBlocks.Text;
import VisualLogicSystem.LogicBlock;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Polygon;

/**
 * The start block of an RCX program
 */
public class LogicStart extends LogicBlock {

    String programName = "";

    public LogicStart() {
        super();

        super.data.add(new Text("Name","Untitled"));

        
        //add the rules 
        super.rects.add(new Rectangle(super.size - 10, 5, 10, 10));
        super.connections.add(1);    
        
        int x[] =
        { (size / 2), size, size, (size / 2) - 6, (size / 2) -
          6, (size / 2)};
        int y[] =
        { 0, 0, size, size, (size / 2) + 3, (size / 2) - 3 };
        Polygon s1 = new Polygon(x, y, 6);
        g2d.setColor(new Color(192, 255, 0, 30));
        g2d.fill(s1);
        g2d.setColor(new Color(192, 255, 0));
        g2d.draw(s1);
        g2d.setColor(new Color(0, 0, 0, 150));
        g2d.fillRect(1, 15, size - 1, size - 15);
        g2d.setFont(new Font("Arial", 10, 10));
        g2d.setColor(Color.WHITE);
        g2d.drawString("Start", 3, 11);
    }

    public String getCompileCode() {

        return "import lejos.nxt.*;\n" +
            "      \n" +
            "      public class " + programName + " {\n" +
            "        public static void main (String[] args) {\n";

    }

}
