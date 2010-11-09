package _3DClasses.Utilities;

import _3DClasses.Polygon;
import _3DClasses.Vertex;
import _3DClasses._3DObject;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;

/**
 * This is the obect loader which will load all the 3D formats.
 * @author Dylan
 */
public class Loader {


    
    /**
     * This method loads an OBJ Wavefront file and simply loads its vertices and creates
     * all its associated polygons and vertices.
     * @param s
     * The absolute directory of the file to load in the form of a file path.
     * @return
     * Returns a 3DObject
     */
    public static _3DObject load_Obj(String s) {
        try {
            Scanner sc = new Scanner(new File(s));
            _3DObject tempObject = new _3DObject();
            while (sc.hasNext()) {
                String temp = sc.nextLine();
                if (temp.startsWith("v")) {
                    String verts[] = temp.substring(2).split(" ");

                    if(!verts[0].equals("")){
                        System.out.println("verts: "+verts[0]);
                     tempObject.addVertex(new Vertex(Float.parseFloat(verts[0]),
                            Float.parseFloat(verts[1]),
                            Float.parseFloat(verts[2])));
                    }
                    
                } else if (temp.startsWith("f")) {
                    String pols[] = temp.substring(2).split(" ");
                    Polygon pol = new Polygon();
                    for(int i = 0; i < pols.length;i++){
                    pol.addLinkage(Integer.parseInt(pols[i])-1);
                    }
                    tempObject.addPolygon(pol);
                }
            }
           
        return tempObject;
            
        } catch (FileNotFoundException ex) {
            System.out.println("Could not find obj file: "+s);
          return null;
        }
    }

}
