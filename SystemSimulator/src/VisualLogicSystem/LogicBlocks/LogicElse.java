package VisualLogicSystem.LogicBlocks;

import VisualLogicSystem.CodeBlock;
import VisualLogicSystem.LogicBlock;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Polygon;

/**
 * The end block for an RCX program
 */
public class LogicElse extends LogicBlock {


    public LogicElse() {
        super();

        super.type = "else";
        
        //add connection data
        super.rects.add(new Rectangle(size-10, 5, 10, 10));
        super.connections.add(1);
        super.linkInfo.add("0");
        super.rects.add(new Rectangle(0, 5, 10, 10));
        super.connections.add(2);
        super.linkInfo.add("stop");
        super.rects.add(new Rectangle(0, 35, 10, 10));
        super.connections.add(2);
        super.linkInfo.add("break");

        //le code blocks
        CodeBlock c1 = new CodeBlock(this);
        c1.setCompileCode("\n }\nelse{ ");
        super.codeBlocks.add(c1);
        CodeBlock c2 = new CodeBlock(this);
        c2.setCompileCode("\n }");
        super.codeBlocks.add(c2);
           
        int x[] =
        { (size / 2), size, size, (size / 2) - 6, (size / 2) -
          6, (size / 2)};
        int y[] =
        { 0, 0, size, size, (size / 2) + 3, (size / 2) - 3 };
        
        Polygon s1 = new Polygon(x, y, 6);
        g2d.setColor(new Color(0, 192, 255, 30));
        g2d.fill(s1);
        g2d.setColor(new Color(0,192, 255));
        g2d.draw(s1);
        g2d.setColor(new Color(0, 0, 0, 150));
        g2d.fillRect(1, 15, size - 1, size - 15);
        g2d.setFont(new Font("Arial", 10, 10));
        g2d.setColor(Color.WHITE);
        g2d.drawString("ELSE", 4, 12);
    }
}
