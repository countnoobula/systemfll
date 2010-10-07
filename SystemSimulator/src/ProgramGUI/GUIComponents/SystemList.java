package ProgramGUI.GUIComponents;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.plaf.basic.BasicListUI;

import MainClasses.Main;

import java.lang.reflect.*;

import java.awt.Component;

import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SystemList extends JList {
    Main m;

    public SystemList(Main m2) {
        super();
        this.m = m2;
        this.setUI(new SystemListUI());
        this.setFixedCellHeight(25);
        this.setFocusable(false);
        this.setSelectionBackground(new Color(240, 240, 240, 120));
        this.setSelectionForeground(Color.BLACK);
        this.setForeground(Color.LIGHT_GRAY);
        this.setFont(m.getFonts().getFont(1).deriveFont(12.0f));
        this.setCellRenderer(new ComplexCellRenderer());
        this.setSelectedIndex(0);
        


    }

    class ComplexCellRenderer implements ListCellRenderer {
        protected DefaultListCellRenderer defaultRenderer =
            new DefaultListCellRenderer();

        public Component getListCellRendererComponent(JList list, Object value,
                                                      int index,
                                                      boolean isSelected,
                                                      boolean cellHasFocus) {
            Font theFont = null;
            Color theForeground = null;
            String theText = null;
            if (value instanceof String) {
                theText = (String)value;
            }

            JLabel renderer =
                (JLabel)defaultRenderer.getListCellRendererComponent(list,
                                                                     value,
                                                                     index,
                                                                     isSelected,
                                                                     cellHasFocus);

            renderer.setText("    " + theText);

            return renderer;
        }
    }

    private class SystemListUI extends BasicListUI {

        Paint gp2, gp3, gp4;

        public SystemListUI() {
            gp2 = new Color(10, 10, 10);
            gp4 = new Color(0, 192, 255);

        }

        @Override
        protected void paintCell(Graphics g, int row, Rectangle rowBounds,
                                 ListCellRenderer cellRenderer,
                                 ListModel dataModel,
                                 ListSelectionModel selModel, int leadIndex) {
            Graphics2D g2d = (Graphics2D)g;
            Paint gp1 =
                new GradientPaint(0, (int)rowBounds.getY(), new Color(20, 20,
                                                                      20), 0,
                                  (int)(rowBounds.getY() +
                                        rowBounds.getHeight()),
                                  new Color(40, 40, 40));
            g2d.setPaint(gp1);

            g2d.fillRect((int)rowBounds.getX(), (int)rowBounds.getY(),
                         (int)rowBounds.getWidth(),
                         (int)rowBounds.getHeight());
            g2d.setPaint(gp4);
            g2d.fillRect((int)rowBounds.getX(), (int)rowBounds.getY()+5, 3, 15);
            super.paintCell(g, row, rowBounds, cellRenderer, dataModel,
                            selModel, leadIndex);
        }

        @Override
        public void paint(Graphics g, JComponent c) {
            Graphics2D g2d = (Graphics2D)g;
            gp3 =
 new GradientPaint(c.getWidth() - 50, 0, new Color(10, 10, 10), c.getWidth(),
                   0, new Color(30, 30, 30));
            g2d.setPaint(gp2);
            g2d.fillRect(0, 0, c.getWidth(), c.getHeight());
            g2d.setPaint(gp3);
            g2d.fillRect(c.getWidth() - 50, 0, 50, c.getHeight());


            super.paint(g, c);

        }

        @Override
        protected void installDefaults() {

            super.installDefaults();
            super.cellHeight = 50;

        }
    }
}
