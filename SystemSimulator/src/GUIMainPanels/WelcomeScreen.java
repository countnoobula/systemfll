package GUIMainPanels;

import MainClasses.SystemInformation;

import ProgramGUI.GUIComponents.Panes.GenericSystemPanel;

import Resources.Images.ImageLoader;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import java.io.IOException;

import javax.imageio.ImageIO;

public class WelcomeScreen extends GenericSystemPanel{
    Image i1;
    public WelcomeScreen() {
        
        super();
        try {
            i1 = ImageIO.read(ImageLoader.class.getResource("SystemLogo2.png"));
        } catch (IOException e) {
            System.out.println("Could not load welcome screen image");
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
      g.drawImage(i1, (this.getWidth() / 2) - (i1.getWidth(null) / 2),
                    (this.getHeight() / 2) - (i1.getHeight(null) / 2),
                    null);
      g.setColor(Color.GRAY);
      g.drawString("Build: "+SystemInformation.buildCodeName+"  "+SystemInformation.buildVersion, this.getWidth()-200, this.getHeight()-35);
      g.drawString("Version: "+SystemInformation.SystemVersion, this.getWidth()-200, this.getHeight()-15);
    }
}
