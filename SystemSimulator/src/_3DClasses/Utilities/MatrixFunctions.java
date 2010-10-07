package _3DClasses.Utilities;
/**
 * Is a utility class for moving and working with matrices in the form of 2 dimensional arrays.
 * @author Dylan Vorster
 */
public class MatrixFunctions {
    
    //the general transform matrix used in this toolkit
    //Is an identity matrix by default
    private double transform[][] =   {{1.0,0.0,0.0,0.0},
                                      {0.0,1.0,0.0,0.0},
                                      {0.0,0.0,1.0,0.0},
                                      {0.0,0.0,0.0,1.0}};
    
    
    /* Matrix-multiply two arrays together.
     * The arrays MUST be rectangular.
     * @author Tom Christiansen & Nathan Torkington, Perl Cookbook version.
     */
    
    /**
     * Multiplies 2 matrices together
     * @param m1
     * Matrice 1
     * @param m2
     * Matrice 2
     * @return
     * Resultant Matrice
     */
    public double[][] multiply(double[][] m1, double[][] m2) {
      int m1rows = m1.length;
      int m1cols = m1[0].length;
      int m2rows = m2.length;
      int m2cols = m2[0].length;
      if (m1cols != m2rows)
        throw new IllegalArgumentException("matrices don't match: " + m1cols + " != " + m2rows);
      double[][] result = new double[m1rows][m2cols];

      // multiply
      for (int i=0; i<m1rows; i++)
        for (int j=0; j<m2cols; j++)
          for (int k=0; k<m1cols; k++)
          result[i][j] += m1[i][k] * m2[k][j];

      return result;
    }
    
  /**
     * Multiplies a 1 x 3 (x,y,z) matrice by a 4 x 4 matrice
     * @param m1
     * 1 x 3 matrice
     * @param m2
     * 4 x 4 matrice
     * @return
     * 1 x 3 matrice (x,y,z,1)
     */
  public static double[] multiply(double[] m1, double[][] m2) {
    
    double[] result = new double[4];
    
    for (int j=0; j<4; j++){
        for (int k=0; k<4; k++)
        result[j] += m1[k] * m2[k][j];
        
    }

    return result;
  }
  /**
     * Multiplies a 1 x 3 (x,y,z) matrice by the transformation matrix
     * @param m1
     * 1 x 3 matrice

     * @return
     * 1 x 3 matrice (x,y,z,1)
     */
  public double[] multiplyByTransform(double[] m1) {
    
    double[] result = new double[4];
    
    for (int j=0; j<4; j++){
        for (int k=0; k<4; k++)
        result[j] += m1[k] * transform[k][j];
        
    }

    return result;
  }
  /**
     * This method rotates a vertice around any given line drawn between 2 points in 3D space.
     * It must ALWAYS be called before the matrix multiplication on the object is called.
     * Aka, always create an instance of MatrixFunctions and call this method. Then multiply each vertice
     * seperately using the multiply() method.
     * 
     * @param p1
     * Point 1 of the axis [x y z]
     * @param p2
     * Point 2 of the axis [x y z]
     * @param angle
     * The angle (in degrees to rotate around this line)
     * @return
     */
  public void setRotationMatrix(double[] p1,double[] p2, double angle){
    /*
     * p1 = [a b c]
     * p2 = [u v w]
     * 
     * Uses the algorythm laid out at:
     * (this could do with much improvement, I simply wrote my own implementation)
     * http://inside.mines.edu/~gmurray/ArbitraryAxisRotation/ArbitraryAxisRotation.html
     * 
     */
    double[][] trans = new double[4][4];
    
    //commonly used variables that can be pre calculated
    double a = Math.toRadians(angle);
    double cos = Math.cos(a);
    double sin = Math.sin(a);
    double l = Math.sqrt(Math.pow(p2[0], 2)+Math.pow(p2[1], 2)+Math.pow(p2[2], 2));
    double l2 = Math.pow(p2[0], 2)+Math.pow(p2[1], 2)+Math.pow(p2[2],2);
    
    
    //calculate the transformation matrix
    trans[0][0] = (Math.pow(p2[0], 2)+(cos*(Math.pow(p2[1], 2)+Math.pow(p2[2], 2))))/l2;
    trans[0][1] = (p2[0]*p2[1]*(1-cos))-(p2[2]*l*sin)/l2;
    trans[0][2] = (p2[0]*p2[2]*(1-cos))+(p2[1]*l*sin)/l2;
    
    //Crazy ass mafs number 1
    trans[0][3] = ((p1[0]*(Math.pow(p2[1], 2)+Math.pow(p2[2], 2)))-(p2[0]*((p1[1]*p2[1])+(p1[2]*p2[2])))+
                  ((cos*(p2[0]*((p1[1]*p2[1])+(p1[2]*p2[2]))))-((p1[0]*(Math.pow(p2[1], 2)+Math.pow(p2[2], 2)))))+
                  (l*sin*((p1[1]*p2[2])-(p1[2]*p2[1]))))/l2;
    
    
    trans[1][0] = (p2[0]*p2[1]*(1-cos))+(p2[2]*l*sin)/l2;
    trans[1][1] = (Math.pow(p2[1], 2)+(cos*(Math.pow(p2[0], 2)+Math.pow(p2[2], 2))))/l2;
    trans[1][2] = (p2[1]*p2[2]*(1-cos))-(p2[2]*l*sin)/l2;
    
    //Crazy ass mafs number 2
    trans[1][3] = ((p1[1]*(Math.pow(p2[0], 2)+Math.pow(p2[2], 2)))-(p2[1]*((p1[0]*p2[0])+(p1[2]*p2[2])))+
                  ((cos*(p2[1]*((p1[0]*p2[0])+(p1[2]*p2[2]))))-((p1[1]*(Math.pow(p2[0], 2)+Math.pow(p2[2], 2)))))+
                  (l*sin*((p1[2]*p2[0])-(p1[0]*p2[2]))))/l2;
    
    trans[2][0] = (p2[0]*p2[2]*(1-cos))-(p2[1]*l*sin)/l2;
    trans[2][1] = (p2[1]*p2[2]*(1-cos))+(p2[0]*l*sin)/l2;
    trans[2][2] = (Math.pow(p2[2], 2)+(cos*(Math.pow(p2[0], 2)+Math.pow(p2[1], 2))))/l2;
    
    //Crazy ass mafs number 3
    trans[2][3] = ((p1[2]*(Math.pow(p2[0], 2)+Math.pow(p2[1], 2)))-(p2[2]*((p1[0]*p2[0])+(p1[1]*p2[1])))+
                  ((cos*(p2[2]*((p1[0]*p2[0])+(p1[1]*p2[1]))))-((p1[2]*(Math.pow(p2[0], 2)+Math.pow(p2[1], 2)))))+
                  (l*sin*((p1[0]*p2[1])-(p1[1]*p2[0]))))/l2;
    
    trans[3][0] = 0;
    trans[3][1] = 0;
    trans[3][2] = 0;
    trans[3][3] = 1;
    
    this.transform = trans;
  }

    /**
     * Translates the matrix along the X axis.
     * @param matrix
     * The 1 x 4 matrix being transformed
     * @param dis
     * The distance moved
     * @return
     * 1 x 4 matrix (new co-ordinates)
     */
    public static double[] translateX(double matrix[],double dis){
        double temp[] = {matrix[0]+dis,matrix[1],matrix[2]};
        return temp;
    }
   /**
    * Translates the matrix along the Y axis.
    * @param matrix
    * The 1 x 4 matrix being transformed
    * @param dis
    * The distance moved
    * @return
    * 1 x 4 matrix (new co-ordinates)
    */
    public static double[] translateY(double matrix[],double dis){
     double temp[] ={matrix[0],matrix[1]+dis,matrix[2]};
      return temp;
    }
   /**
    * Translates the matrix along the Z axis.
    * @param matrix
    * The 1 x 4 matrix being transformed
    * @param dis
    * The distance moved
    * @return
    * 1 x 4 matrix (new co-ordinates)
    */
    public static double[] translateZ(double matrix[],double dis){
      double temp[] ={matrix[0],matrix[1],matrix[2]+dis};
      return temp;
    }
   /**
    * Translates the matrix along the X axis.
    * @param matrix
    * The 1 x 4 matrix being transformed
    * @param x
    * The x distance moved
    * @param y
    * The y distance moved
    * @param z
    * The z distance moved
    * @return
    * 1 x 4 matrix (new co-ordinates)
    */
  public static double[] translateXYZ(double matrix[],double x,double y,double z){
    double temp[] = {matrix[0]+x,matrix[1]+y,matrix[2]+z};
    return temp;
  }
   /**
    * Some tests using the Matrix Functions class
    * @param args
    */
   public static void main(String args[]){
      double temp [] = {4,0,0,1};
      double p1 [] = {0,0,0};
      double p2 [] = {0,1,0};
      
       MatrixFunctions func = new MatrixFunctions();
       func.setRotationMatrix(p1, p2, 90);
       temp = func.multiplyByTransform(temp);
       System.out.println("x:" +temp[0]);
       System.out.println("y:" +temp[1]);
       System.out.println("z:" +temp[2]);
   }
}
