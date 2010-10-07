package ProgramGUI.GUIComponents;

import Resources.Images.ImageLoader;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.plaf.basic.BasicTreeUI;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;

public class SystemTreeViewer extends JTree {
    public SystemTreeViewer() {
        super();
        this.setOpaque(false);
        this.setUI(new SystemTreeViewerUI());
        


    }

  

    private class SystemTreeViewerUI extends BasicTreeUI {

        @Override
        protected void paintExpandControl(Graphics g, Rectangle clipBounds,
                                          Insets insets, Rectangle bounds,
                                          TreePath path, int row,
                                          boolean isExpanded,
                                          boolean hasBeenExpanded,
                                          boolean isLeaf) {
            super.paintExpandControl(g, clipBounds, insets, bounds, path, row,
                                     isExpanded, hasBeenExpanded, isLeaf);
        }

        @Override
        protected void paintHorizontalLine(Graphics g, JComponent c, int y,
                                           int left, int right) {
            g.setColor(Color.DARK_GRAY);
            g.drawLine(left, y, right, y);
        }

        @Override
        protected void paintHorizontalPartOfLeg(Graphics g,
                                                Rectangle clipBounds,
                                                Insets insets,
                                                Rectangle bounds,
                                                TreePath path, int row,
                                                boolean isExpanded,
                                                boolean hasBeenExpanded,
                                                boolean isLeaf) {
            super.paintHorizontalPartOfLeg(g, clipBounds, insets, bounds, path,
                                           row, isExpanded, hasBeenExpanded,
                                           isLeaf);
        }

        @Override
        protected void paintRow(Graphics g, Rectangle clipBounds,
                                Insets insets, Rectangle bounds, TreePath path,
                                int row, boolean isExpanded,
                                boolean hasBeenExpanded, boolean isLeaf) {
            super.paintRow(g, clipBounds, insets, bounds, path, row,
                           isExpanded, hasBeenExpanded, isLeaf);
        }

        @Override
        protected void paintVerticalLine(Graphics g, JComponent c, int x,
                                         int top, int bottom) {
            g.setColor(Color.DARK_GRAY);
            g.drawLine(x, top, x, bottom);
        }

        @Override
        protected void paintVerticalPartOfLeg(Graphics g, Rectangle clipBounds,
                                              Insets insets, TreePath path) {
            super.paintVerticalPartOfLeg(g, clipBounds, insets, path);
        }

        @Override
        public void paint(Graphics g, JComponent c) {
            super.paint(g, c);
        }
    }
}
