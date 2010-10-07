package Resources.Fonts;

import java.awt.Font;
import java.awt.FontFormatException;

import java.io.IOException;
import java.io.InputStream;

import java.net.URL;

import java.util.ArrayList;
/**
 * This class loads all the fonts in this directory
 */
public class FontLoader {

    ArrayList<Font> fonts = new ArrayList<Font>();
    ArrayList<URL> url = new ArrayList<URL>();
  ArrayList<InputStream> is = new ArrayList<InputStream>();
    
    /**
     *Returns the font at position i
     * @param i
     * The font to return:<br> 1 = CafeNero Bebas Neuo
     * <br>2 = Imagine Font
     * @return
     * The selected Font, needs to first be derived
     */
    public Font getFont(int i){
        if(fonts.get(i)!=null){
          return fonts.get(i);
        }
        else{
          return new Font("Arial",14,14);
        }
    }

    public FontLoader() {
        //CafeNero Bebas Neuo
        url.add(getClass().getResource("font1.ttf"));
        //Imagine Font
        url.add(getClass().getResource("font2.ttf"));
        
        loadFonts();
    }

    
    /**
     * Invoked by the constructor to load fonts
     */
    private void loadFonts() {
        for (int i = 0; i < url.size(); i++) {
            
            try {
                
                is.add(url.get(i).openStream());
                
            } catch (IOException ex) {
                System.out.println("could not load font");
            }
            try {
                fonts.add(Font.createFont(Font.TRUETYPE_FONT,
                                          is.get(i)));
                


            } catch (FontFormatException ex) {
                System.out.println("wrong format of font");
            } catch (IOException ex) {
                System.out.println("could not load font");
            }
        }
        //
        //        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        //        ge.registerFont(font1);
    }
}
