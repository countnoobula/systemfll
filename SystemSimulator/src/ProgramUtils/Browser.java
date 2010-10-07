package ProgramUtils;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

public class Browser {
    public void openURI(URI uri) throws IOException {
        Desktop desktop = Desktop.getDesktop();
        if (Desktop.isDesktopSupported()) {
            desktop.browse(uri);
        }
   }
}