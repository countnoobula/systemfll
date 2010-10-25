package VisualLogicSystem.LogicBlocks;

import VisualLogicSystem.CodeBlock;
import VisualLogicSystem.ConnectionPoint;
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

        CodeBlock code1 = new CodeBlock(this);
        code1.setCompileCode("}");


        super.connectionPoints.add(new ConnectionPoint(
                "0",
                "The first connection point of the block",
                1,
                null,
                new Rectangle(size - 10, 5, 10, 10),
                new Color(0, 192, 255)));

        super.connectionPoints.add(new ConnectionPoint(
                "break",
                "The end of the 1st if condition",
                2,
                null,
                new Rectangle(0, 5, 10, 10),
                new Color(0, 192, 255)));

        super.connectionPoints.add(new ConnectionPoint(
                "next",
                "End of all statemements",
                2,
                code1,
                new Rectangle(0, 35, 10, 10),
                new Color(0, 192, 255)));

        //!------- Begin graphic drawing ------!


        int x[] = {(size / 2), size, size, (size / 2) - 6, (size / 2)
            - 6, (size / 2)};
        int y[] = {0, 0, size, size, (size / 2) + 3, (size / 2) - 3};

        Polygon s1 = new Polygon(x, y, 6);
        g2d.setColor(new Color(0, 192, 255, 30));
        g2d.fill(s1);
        g2d.setColor(new Color(0, 192, 255));
        g2d.draw(s1);
        g2d.setColor(new Color(0, 0, 0, 150));
        g2d.fillRect(1, 15, size - 1, size - 15);
        g2d.setFont(new Font("Arial", 10, 10));
        g2d.setColor(Color.WHITE);
        g2d.drawString("END", 4 + 10, 12 + 15);
        g2d.drawString("ELSE", 4 + 10, 24 + 15);
        GenerateGLBlock();
    }
}
