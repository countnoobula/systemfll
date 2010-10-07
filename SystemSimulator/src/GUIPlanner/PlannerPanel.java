package GUIPlanner;

import ProgramGUI.GUIComponents.TabbedPane;

import MainClasses.Main;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;

import java.awt.Graphics2D;

import java.awt.Paint;

import javax.swing.JPanel;

public class PlannerPanel extends TabbedPane {

    private Main m;
    private NoteViewer panel_1;

    public PlannerPanel(Main m2) {
        super(m2);
        this.m = m2;

        this.panel_1 = new NoteViewer(m);

        this.add("Notes", panel_1);
        this.setVisible(false);

    }


}
