package ProgramGUI.GUIComponents.Panes;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.JTabbedPane;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

import MainClasses.Main;
import ProgramGUI.GUIEngine;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;

import org.jdesktop.animation.transitions.ScreenTransition;

public class TabbedPane extends JTabbedPane {

    TabbedPane nested;
    TabbedPaneUI UI;
    private ScreenTransition transition;
    Main m;

    public TabbedPane(Main m2) {
        super();


        this.nested = this;
        UI = new TabbedPaneUI();
        this.m = m2;
        this.setOpaque(false);
        this.setUI(UI);
        this.setFocusable(false);
        this.setBackground(new Color(0, 0, 0, 0));
        this.setBorder(BorderFactory.createEmptyBorder());
        this.addMouseListener(new MouseListener(){

            public void mouseClicked(MouseEvent me) {
                if(me.getButton() == MouseEvent.BUTTON3){
                    for(int i = 0;i < nested.getTabCount();i++){
                        if(UI.getTabBounds(nested, i).contains(me.getPoint())){
                            m.getGuiEngine().openInNewWindow(nested.getComponent(i));
                        }
                    }
                 
                }
            }

            public void mousePressed(MouseEvent me) {


            }

            public void mouseReleased(MouseEvent me) {

            }

            public void mouseEntered(MouseEvent me) {

            }

            public void mouseExited(MouseEvent me) {

            }
        });

    }

    @Override
    public java.awt.Component add(String title, java.awt.Component component) {
        int t1 = (int) (title.length() * 0.65);

        for (int i = 0; i < t1; i++) {
            title = title + " ";
        }

        return super.add(title, component);
        
    }

    private class TabbedPaneUI extends BasicTabbedPaneUI {

        Paint gp1,gp2,gp3;

        TabbedPaneUI() {
            super();


            gp1 =
                    new GradientPaint(0, 0, new Color(100, 100, 100, 100), 0, 30, new Color(120, 120,
                    120, 100));
            gp2 =
                    new GradientPaint(0, 0, new Color(50, 50, 50, 100), 0, 30, new Color(80, 80,
                    80, 100));
            gp3 = new Color(255,255,255,100);
        }

        @Override
        protected void installDefaults() {
            super.installDefaults();
            this.tabInsets.top=2;
        }

        @Override
        protected void paintTabBorder(Graphics g, int tabPlacement,
                int tabIndex, int x, int y, int w, int h,
                boolean isSelected) {
            //empty becuase no borders are being painted just yet, keeps it clean
        }

        @Override
        protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {
           
        }


        @Override
        protected void paintTabBackground(Graphics g, int tabPlacement,
                int tabIndex, int x, int y, int w,
                int h, boolean isSelected) {
            Graphics2D g2d = (Graphics2D) g;

            if (isSelected == true) {
                g2d.setPaint(gp2);
                g2d.fillRect(x, y, w, h);
                g2d.setPaint(gp3);
                g2d.fillRect(x, y, 2, h);
                g2d.fillRect(x+w-2, y, 2, h);
            } else {
                g2d.setPaint(gp1);
                g2d.fillRect(x, y, w, h);
            }

        }

        @Override
        protected void paintText(Graphics g, int tabPlacement, Font font,
                FontMetrics metrics, int tabIndex,
                String title, Rectangle textRect,
                boolean isSelected) {

            Graphics2D g2d = (Graphics2D) g;
            g2d.setFont(m.getFonts().getFont(1).deriveFont(12.0f));
            if (isSelected == true) {
                g2d.setColor(Color.WHITE);
            } else {
                g2d.setColor(Color.LIGHT_GRAY);
            }
            g2d.drawString(title, (int) textRect.getX(), 19);
        }
    }
}
