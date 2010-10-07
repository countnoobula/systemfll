package _3DClasses;

import java.util.ArrayList;


/**
 * The polygon class represents a polygon with reference to other vertices defined in the 3D object.
 * It creates the links to all polygons and can retrieve all polygons.
 * @author Dylan
 */
public class Polygon {


    ArrayList<Integer> links = new ArrayList<Integer>();
    /**
     * Adds a vertice to the polygon.
     * @param link
     * The vertice ID to be added
     * 
     */
    public void addLinkage(int link){
        links.add(link);
    }
    /**
     * Returns the amount of links to other vertices in this polygon.
     * @return
     * The amount of links
     */
    public int getAmount(){
        return links.size();
    }
    /**
     * Returns the ID of the vetice at position i in the array.
     * @param i
     * The linked vertice to get in the ArrayList
     * @return
     * Vertice ID
     */
    public int getLinks(int i){
        return links.get(i);
    }


    @Override
    public String toString() {
       String temp = "Number of links: "+links.size();
            for(int i = 0;i<links.size();i++){
              temp.concat("\n"+i+" | "+links.get(i));
            }
        return temp;
    }
}
