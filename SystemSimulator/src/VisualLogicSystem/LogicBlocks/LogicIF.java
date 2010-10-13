/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package VisualLogicSystem.LogicBlocks;

import VisualLogicSystem.CodeBlock;
import VisualLogicSystem.LogicBlock;
import java.awt.Color;
import java.awt.Font;
import java.awt.Polygon;
import java.awt.Rectangle;

/**
 *
 * @author Dylan
 */
public class LogicIF extends LogicBlock{

    public LogicIF(){
        super();

        super.type = "if";

        //add connection data
        super.rects.add(new Rectangle(size - 10, 5, 10, 10));
        super.connections.add(1);
        super.linkInfo.add("0");
        super.rects.add(new Rectangle(size - 10, 35, 10, 10));
        super.connections.add(1);
        super.linkInfo.add("1");
        super.rects.add(new Rectangle(0, 5, 10, 10));
        super.linkInfo.add("next");
        super.connections.add(2);


        //le code blocks
        CodeBlock c1 = new CodeBlock(this);
        c1.setCompileCode("\nif(something == something){");
        super.codeBlocks.add(c1);


        int x[] = {(size / 2), size, size, (size / 2) - 6, (size / 2)
            - 6, (size / 2)};
        int y[] = {0, 0, size, size, (size / 2) + 3, (size / 2) - 3};

        Polygon s1 = new Polygon(x, y, 6);
        g2d.setColor(new Color(0, 192, 255, 30));
        g2d.fill(s1);
        g2d.setColor(new Color(0, 192, 255));
        g2d.draw(s1);

        g2d.setColor(new Color(0, 0, 0, 200));
        g2d.fillRect(1, 5, size - 1, 11);
        g2d.fillRect(1, 34, size - 1, 11);
        g2d.setFont(new Font("Arial", 10, 10));
        g2d.setColor(Color.WHITE);
        g2d.drawString("IF", 28, 14);
        g2d.drawString("ELSE", 10, 43);
    }

}
