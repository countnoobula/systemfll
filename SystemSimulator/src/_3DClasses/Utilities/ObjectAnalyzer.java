package _3DClasses.Utilities;

import _3DClasses._3DObject;
import java.util.ArrayList;

/**
 * This is a Utilities package which can analze a 3D object and return a report on the data it holds
 * The various methods in this class each perform a different analysis on the _3DObject.
 */
public class ObjectAnalyzer {

    public static Object[] unusedVertices(_3DObject ob) {
        
        ArrayList<Integer> unusedVertices = new ArrayList<Integer>();
        ArrayList<Integer> usedVertices = new ArrayList<Integer>(ob.getAmountOfVertices());

        //place used vertices inside arrayList
        for (int i = 0; i < ob.getAmountOfPolygons(); i++) {
            for (int j = 0; j < ob.getPolygon(i).getAmount(); j++) {
            usedVertices.set(ob.getPolygon(i).getLinks(j)-1, 1);
            }
        }
        
        //check which vertices are unused
        for(int i = 0;i< ob.getAmountOfVertices();i++){
            if(usedVertices.get(i) == null){
              unusedVertices.add(i);
            }
        }


        return unusedVertices.toArray();
    }
}
