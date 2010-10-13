package VisualLogicSystem.LogicBlocks;

import Resources.Images.ImageLoader;
import VisualLogicSystem.CodeBlock;
import VisualLogicSystem.LogicBlock;

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
        super();

        //add connection data
        super.rects.add(new Rectangle(size - 10, 5, 10, 10));
        super.connections.add(1);
        super.linkInfo.add("0");
        super.rects.add(new Rectangle(0, 5, 10, 10));
        super.connections.add(2);
        super.linkInfo.add("next");

        //le code blocks
        CodeBlock c1 = new CodeBlock(this);
        c1.setCompileCode("\n System.out.println('#1#');");
        super.codeBlocks.add(c1);


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

    }
}
