package VisualLogicSystem.LogicBlocks;

import Resources.Images.ImageLoader;
import VisualLogicSystem.CodeBlock;
import VisualLogicSystem.DataBlocks.*;
import VisualLogicSystem.LogicBlock;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Polygon;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * The start block of an RCX program
 */
public class LogicStart extends LogicBlock {


    public LogicStart() {
        super();

        super.type = "start";


        String compileCode = "import lejos.nxt.*;\n" +
            "      \n"
            + "\n//Author: #1#" +
            "      \npublic class #2# {\n" +
            "        public static void main (String[] args) {\n\n";


        super.data.add(new Text("Author","Dylan"));
        super.data.add(new Text("Name","Program"));
        
        
        //add the rules 
        super.rects.add(new Rectangle(super.size - 10, 5, 10, 10));
        super.linkInfo.add("0");
        super.connections.add(1);

        //le code blocks
        CodeBlock c1 = new CodeBlock(this);
        c1.setCompileCode(compileCode);
        super.codeBlocks.add(c1);
        
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
        try {
            g2d.drawImage(ImageIO.read(ImageLoader.class.getResource("logicblocks/start.png")),0,0,null);
        } catch (IOException ex) {
            System.out.println("cant load start image");
        }
        g2d.setColor(Color.WHITE);
        g2d.drawString("Start", 3, 11);
        GenerateGLBlock();

    }


}
