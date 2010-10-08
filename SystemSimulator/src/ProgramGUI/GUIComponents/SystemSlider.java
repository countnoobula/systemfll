package ProgramGUI.GUIComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JSlider;

public class SystemSlider extends JSlider {

    public SystemSlider() {
        super();
        this.setOpaque(false);
        this.setUI(new SystemSliderUI(this));

    }

    private class SystemSliderUI extends javax.swing.plaf.basic.BasicSliderUI {

        JSlider slider;
        public SystemSliderUI(JSlider b) {
            super(b);
            slider = b;
        }

        @Override
        public void paintThumb(Graphics g) {
            super.paintThumb(g);        
        }

        @Override
        public void paintTicks(Graphics g) {
            super.paintTicks(g);
        }

        @Override
        public void paintTrack(Graphics g) {
            super.paintTrack(g);
        }

        @Override
        protected void paintMajorTickForHorizSlider(Graphics g, Rectangle tickBounds, int x) {
            super.paintMajorTickForHorizSlider(g, tickBounds, x);
        }

        @Override
        protected void paintMinorTickForHorizSlider(Graphics g, Rectangle tickBounds, int x) {
            super.paintMinorTickForHorizSlider(g, tickBounds, x);
        }



        
    }
}
