package GUIMainPanels;


import ProgramUtils.FileTreeModel;

import ProgramGUI.GUIComponents.Panes.GenericSystemPanel;
import ProgramGUI.GUIComponents.SystemTreeViewer;

import Resources.Images.ImageLoader;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import java.io.FileReader;
import java.io.IOException;

import javax.swing.JEditorPane;

import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

import java.util.ArrayList;

import java.io.File;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.*;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import javax.swing.tree.DefaultTreeCellRenderer;

public class HelpScreen extends GenericSystemPanel {

    private SystemTreeViewer panel_1;
    private HTMLViewer panel_2;
    GridBagConstraints gc;


    public HelpScreen() {
        super();
        gc = new GridBagConstraints();
        this.setLayout(new GridBagLayout());

        this.panel_1 = new SystemTreeViewer();
        this.panel_2 = new HTMLViewer();

        this.panel_1.setPreferredSize(new Dimension(250, 0));


        
        File f = new File("public_html");
        //File f = new File("http://voidblog.com");

        if (f.exists()) {
            this.panel_1.setModel(new FileTreeModel(f));
            this.panel_1.setCellRenderer(new CustomIconRenderer());
        }
        this.panel_1.addTreeSelectionListener(new TreeSelectionListener() {
                public void valueChanged(TreeSelectionEvent e) {
                    TreePath tp = e.getNewLeadSelectionPath();
                    if (tp != null)

                        System.out.println("Selected:  " +
                                           tp.getLastPathComponent());
                    if (("" + tp.getLastPathComponent()).contains(".html")) {
                        panel_2.doLoadCommand("" + tp.getLastPathComponent());
                    }
                }
            });

        //add the tree view on the left
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.weightx = 0;
        gc.weighty = 1;
        gc.insets = new Insets(30, 5, 5, 5);
        gc.fill = GridBagConstraints.VERTICAL;
        gc.anchor = GridBagConstraints.NORTHWEST;
        this.add(panel_1, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.insets = new Insets(30, 0, 5, 5);
        gc.fill = GridBagConstraints.BOTH;
        gc.anchor = GridBagConstraints.NORTHWEST;
        this.add(panel_2, gc);
    }

    class CustomIconRenderer extends DefaultTreeCellRenderer {

        ArrayList<ImageIcon> icons;


        public CustomIconRenderer() {

            icons = new ArrayList<ImageIcon>();
            icons.add(new ImageIcon(ImageLoader.class.getResource("HelpModule/txt.png")));
            icons.add(new ImageIcon(ImageLoader.class.getResource("HelpModule/node.png")));

        }

        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value,
                                                      boolean sel,
                                                      boolean expanded,
                                                      boolean leaf, int row,
                                                      boolean hasFocus) {

            super.getTreeCellRendererComponent(tree, value, sel, expanded,
                                               leaf, row, hasFocus);

            //This assigns the object from the node to a default object
            String temp = ((File)value).toString();
            String[] temp2 = temp.split("/");
            String nodeObj = temp2[temp2.length - 1];

            // This makes the icon selection based on the object value

            if (((File)value).isFile()) {

                setIcon(icons.get(0));
            }
            if (((File)value).isDirectory()) {
                setIcon(icons.get(1));
            }

            this.setText(nodeObj);
            this.setBackgroundNonSelectionColor(new Color(0, 0, 0, 0));
            this.setForeground(Color.WHITE);
            this.setBackgroundSelectionColor(new Color(0, 192, 255));

            return this;
        }
    }

    private class HTMLViewer extends JEditorPane {
        HTMLEditorKit kit = new HTMLEditorKit();

        private HTMLViewer() {
            this.setEditorKit(kit);


        }

        public void doLoadCommand(String filename) {
            FileReader reader = null;
            try {
                System.out.println("Loading");
                reader = new FileReader(filename);

                // Create empty HTMLDocument to read into
                HTMLEditorKit htmlKit = new HTMLEditorKit();
                HTMLDocument htmlDoc =
                    (HTMLDocument)htmlKit.createDefaultDocument();
                // Create parser (javax.swing.text.html.parser.ParserDelegator)
                HTMLEditorKit.Parser parser = new ParserDelegator();
                // Get parser callback from document
                HTMLEditorKit.ParserCallback callback = htmlDoc.getReader(0);
                // Load it (true means to ignore character set)
                parser.parse(reader, callback, true);
                // Replace document
                this.setDocument(htmlDoc);
                System.out.println("Loaded");

            } catch (IOException exception) {
                System.out.println("Load oops");
                exception.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException ignoredException) {
                    }
                }
            }
        }

    }
}
