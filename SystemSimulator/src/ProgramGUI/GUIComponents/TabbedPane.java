package ProgramGUI.GUIComponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;

import java.awt.Paint;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

import MainClasses.Main;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.transitions.ScreenTransition;
import org.jdesktop.animation.transitions.TransitionTarget;

public class TabbedPane extends JTabbedPane {

    TabbedPane nested;
    private ScreenTransition transition;
    Main m;

    public TabbedPane(Main m2) {
        super();

        this.nested = this;
        this.m = m2;
        

        this.setOpaque(false);
        this.setUI(new TabbedPaneUI());
        this.setFocusable(false);

    }


    @Override
    public java.awt.Component add(String title, java.awt.Component component) {
        int t1 = (int)(title.length() * 0.65);

        for (int i = 0; i < t1; i++) {
            title = title + " ";
        }

        return super.add(title, component);
    }



    private class TabbedPaneUI extends BasicTabbedPaneUI {

        Paint gp1, gp2, gp3;

        TabbedPaneUI() {
            super();


            gp1 =
 new GradientPaint(0, 0, new Color(200, 200, 200), 0, 30, new Color(255, 255,
                                                                    255));
            gp2 =
 new GradientPaint(0, 0, new Color(30, 30, 30), 0, 30, new Color(60, 60, 60));
            gp3 =
 new GradientPaint(0, 0, new Color(0, 192, 255), 0, 30, new Color(0, 192,
                                                                  180));
        }


        protected void installDefaults() {
            super.installDefaults();
            tabAreaInsets.left = 0;
            super.contentBorderInsets.top = 0;
        }


        @Override
        protected void paintTabBorder(Graphics g, int tabPlacement,
                                      int tabIndex, int x, int y, int w, int h,
                                      boolean isSelected) {
            //empty becuase no borders are being painted just yet, keeps it clean
        }

        @Override
        protected void paintTabBackground(Graphics g, int tabPlacement,
                                          int tabIndex, int x, int y, int w,
                                          int h, boolean isSelected) {
            Graphics2D g2d = (Graphics2D)g;

            if (isSelected == true) {
                g2d.setPaint(gp1);

                g2d.fillRect(x, y, w, h);
            } else {
                g2d.setPaint(gp2);
                g2d.fillRect(x, y, w, h);
                g2d.setPaint(gp3);
                g2d.fillRect(x + 3, 8, 3, 18);

            }

        }

        @Override
        protected void paintText(Graphics g, int tabPlacement, Font font,
                                 FontMetrics metrics, int tabIndex,
                                 String title, Rectangle textRect,
                                 boolean isSelected) {

            Graphics2D g2d = (Graphics2D)g;
            g2d.setFont(m.getFonts().getFont(1).deriveFont(12.0f));
            if (isSelected == true) {
                g2d.setColor(Color.BLACK);
            } else {
                g2d.setColor(Color.WHITE);
            }
            g2d.drawString(title, (int)textRect.getX(), 18);
        }

    }
}
