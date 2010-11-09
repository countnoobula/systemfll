package _3DClasses;

import _3DClasses.Utilities.MatrixFunctions;

import java.util.ArrayList;

import javax.media.opengl.GL2;


/**
 * This is a 3D object which holds vertex and Polygon data.It is used purely as a container for 3D
 * information
 * @author Dylan Vorster
 */
public class _3DObject {


    //Variables for holding 3D data
    private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
    private ArrayList<Polygon> polygons = new ArrayList<Polygon>();

    //OpenGL display list
    private int list;

    /**
     * Adds a vertix to the 3DObject
     * @param v
     * The vertex to be added
     */
    public void addVertex(Vertex v) {
        vertices.add(v);
    }

    /**
     * Adds a polygon to the 3D object
     * @param p
     * The polgon to be added
     */
    public void addPolygon(Polygon p) {
        polygons.add(p);
    }

    /**
     * Returns a specified vertex
     * @param i
     * The vertex ID to be returned
     * @return
     * The vertex object being called
     */
    public Vertex getVertex(int i) {
        return vertices.get(i);
    }

    /**
     * Returns a specified polygon
     * @param i
     * The polygon to be returned
     * @return
     * The Polygon object being called
     */
    public Polygon getPolygon(int i) {
        return polygons.get(i);
    }

    /**
     * Moves the part object along the X axis and no other axis.
     * @param dis
     * The distance to translate the Part Object
     */
    public void translateX(double dis) {

        for (int i = 0; i < vertices.size(); i++) {
            vertices.get(i).setMatrix(MatrixFunctions.translateX(vertices.get(i).getMatrix(),
                                                                 dis));
        }


    }

    /**
     * Moves the part object along the Y axis and no other axis.
     * @param dis
     * The distance to translate the Part Object
     */
    public void translateY(double dis) {
        for (int i = 0; i < vertices.size(); i++) {
            vertices.get(i).setMatrix(MatrixFunctions.translateY(vertices.get(i).getMatrix(),
                                                                 dis));
        }
    }

    /**
     * Moves the part object along the Z axis and no other axis.
     * @param dis
     * The distance to translate the Part Object
     */
    public void translateZ(double dis) {
        for (int i = 0; i < vertices.size(); i++) {
            vertices.get(i).setMatrix(MatrixFunctions.translateZ(vertices.get(i).getMatrix(),
                                                                 dis));
        }
    }

    /**
     * Translates the part object along all the different axies
     * @param x
     * The distance to translate the Part Object on the X axis
     * @param y
     * The distance to translate the Part Object on the Y axis
     * @param z
     * The distance to translate the Part Object on the Z axis
     */
    public void translateXYZ(double x, double y, double z) {

        for (int i = 0; i < vertices.size(); i++) {
            vertices.get(i).setMatrix(MatrixFunctions.translateXYZ(vertices.get(i).getMatrix(),
                                                                   x, y, z));
        }
    }

    /**
     * Rotates the part around a Z axis at the point p1
     * @param p1
     * [x y z]
     * @param angle
     * The angle in degrees
     */
    public void rotateZ(double[] p1, double angle) {

        MatrixFunctions func = new MatrixFunctions();
        double[] temp = { p1[0], p1[1], p1[2] + 1 };
        func.setRotationMatrix(p1, temp, angle);

        for (int i = 0; i < vertices.size(); i++) {
            vertices.get(i).setMatrix(func.multiplyByTransform(vertices.get(i).getMatrix()));
        }

    }

    /**
     * Rotates the part around a Y axis at the point p1
     * @param p1
     * [x y z]
     * @param angle
     * The angle in degrees
     */
    public void rotateY(double[] p1, double angle) {

        MatrixFunctions func = new MatrixFunctions();
        double[] temp = { p1[0], p1[1] + 1, p1[2] };
        func.setRotationMatrix(p1, temp, angle);

        for (int i = 0; i < vertices.size(); i++) {
            vertices.get(i).setMatrix(func.multiplyByTransform(vertices.get(i).getMatrix()));
        }

    }

    /**
     * Rotates the part around an X axis at the point p1
     * @param p1
     * [x y z]
     * @param angle
     * The angle in degrees
     */
    public void rotateX(double[] p1, double angle) {

        MatrixFunctions func = new MatrixFunctions();
        double[] temp = { p1[0] + 1, p1[1], p1[2] };
        func.setRotationMatrix(p1, temp, angle);

        for (int i = 0; i < vertices.size(); i++) {
            vertices.get(i).setMatrix(func.multiplyByTransform(vertices.get(i).getMatrix()));
        }

    }

    /**
     * Returns the amount of polygons in this 3D object.
     * @return
     * Amount
     */
    public int getAmountOfPolygons() {
        return this.polygons.size();
    }

    /**
     * Returns the amount of Vertices in this 3D object.
     * @return
     * Amount
     */
    public int getAmountOfVertices() {
        return this.vertices.size();
    }

    /**
     * Generates an OpenGl Display List object from the vertices and makes it ready for rendering.
     * @param gl
     * The GL2 context (must be passed down from the higher context)
     */
    public void generateGLList(GL2 gl) {
        list= gl.glGenLists(1);
        
        gl.glNewList(list, GL2.GL_COMPILE);
        for (int i = 0; i < polygons.size(); i++) {

            gl.glBegin(GL2.GL_POLYGON);
            for (int j = 0; j < polygons.get(i).getAmount(); j++) {
                gl.glVertex3f((float)vertices.get(polygons.get(i).getLinks(j)).getX(),
                              (float)vertices.get(polygons.get(i).getLinks(j)).getY(),
                              (float)vertices.get(polygons.get(i).getLinks(j)).getZ());
            }
            gl.glEnd();
        }
        gl.glEndList();
    }

    /**
     * Calls the display list and renders the 3D object in OpenGL
     * @param gl
     * The GL2 context (must be passed down from the higher context)
     */
    public void renderGL(GL2 gl) {
        gl.glCallList(list);
    }
}
