package VisualLogicSystem.LogicBlocks;

import Resources.Images.ImageLoader;
import VisualLogicSystem.CodeBlock;
import VisualLogicSystem.ConnectionPoint;
import VisualLogicSystem.DataBlocks.DataObject;
import VisualLogicSystem.LogicBlock;
import VisualLogicSystem.VariableConnectionPoint;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Polygon;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * The end block for an RCX program
 */
public class LogicSystemOut extends LogicBlock {


    public LogicSystemOut() {
    
        super.data.add(new DataObject("Hello World!",DataObject.TEXTFIELD));

        CodeBlock code1 = new CodeBlock(this);
        code1.setCompileCode("\n System.out.println('#1#');");


        super.connectionPoints.add(new ConnectionPoint(
                "0",
                "begin the statement",
                1,
                code1,
                new Rectangle(super.size - 10, 5, 10, 10),
                new Color(0, 192, 255)));

        
        super.connectionPoints.add(new ConnectionPoint(
                "next",
                "connect to something",
                2,
                null,
                new Rectangle(0, 5, 10, 10),
                new Color(0, 192, 255)));
        
        this.addVariableConnectionPoint(new VariableConnectionPoint(
                this,
                data.get(0),
                "Display Output",
                Color.YELLOW,
                true));



        int x[] = {(size / 2), size, size, (size / 2) - 6, (size / 2)
            - 6, (size / 2)};
        int y[] = {0, 0, size, size, (size / 2) + 3, (size / 2) - 3};

        Polygon s1 = new Polygon(x, y, 6);
        g2d.setColor(new Color(0, 0, 0, 30));
        g2d.fill(s1);
        g2d.setColor(new Color(40, 40, 40));
        g2d.draw(s1);
        g2d.setColor(new Color(0, 0, 0, 150));
        g2d.fillRect(1, 15, size - 1, size - 15);
        try {
            g2d.drawImage(ImageIO.read(ImageLoader.class.getResource("logicblocks/sout.png")), 0, 0, null);
        } catch (IOException ex) {
            System.out.println("cant load start image");
        }
        GenerateGLBlock();

    }
}
