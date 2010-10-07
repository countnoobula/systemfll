package _3DClasses;

/**
 * The Vertex object is a location in 3D dimensional space. It is used in the construction of 3D polygons.
 * @author Dylan
 */
public class Vertex {

    //the vertex data
    private double[] vert;
    
    
    /**
     * Creates a new vertex object
     * @param x
     * The x position on the X axis
     * @param y
     * The y position on the Y axis
     * @param z
     * The z position on the Z axis
     */
    public Vertex(float x,float y,float z){
        vert = new double[3];
        vert[0] = (double)x;
        vert[1] = (double)y;
        vert[2] = (double)z;
        
    }
    
    /**
     * Sets the co-ordinates of the vertex.
     * @param x
     * X cord
     * @param y
     * y cord
     * @param z
     * z cord
     */
    public void setCords(double x,double y, double z){
      vert[0] = x;
      vert[1] = y;
      vert[2] = z;
    }
    /**
     * Sets the new matrix representation of the vertex.
     * @param m
     * The new Matrix (x,y,z)
     */
    public void setMatrix(double[] m){
      vert = m;
    }

    /**
     * Get the X location
     * @return
     * The X location of the vertice
     */
    public double getX(){
        return vert[0];
    }
    /**
     * Get the Y location
     * @return
     * The Y location of the vertice
     */
    public double getY(){
      return vert[1];
    }
    /**
     * Get the Z location
     * @return
     * The Z location of the vertice
     */
    public double getZ(){
      return vert[2];
    }
    /**
     * Returns the matrix containing the location data. The first 3 numbers are the x and y location
     * @return
     * The matrix
     */
    public double[] getMatrix(){
    return vert;
    }
    /**
     * The toString method
     * @return
     * Human data interpretation
     */
    @Override
     public String toString() {
        String temp;
        temp = "x: "+vert[0]+"\n";
        temp+="y: "+vert[1]+"\n";
        temp+="z: "+vert[2];
             
         return temp;
    }
}
