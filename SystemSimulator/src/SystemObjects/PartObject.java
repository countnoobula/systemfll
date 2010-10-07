package SystemObjects;

import _3DClasses.*;

import _3DClasses.Utilities.MatrixFunctions;

import java.util.ArrayList;

/**
 * The Part object is a 3D part which is stored in the System Database
 * @author Dylan Vorster
 */
public class PartObject extends DatabaseObject {

    //this is its own 3D Object
    private _3DObject _3dobject = new _3DObject();

    //An array List used for creating a node structure, can make this part a group
    private PartObject nestedParts;

    //3D co-ordinates      x    y    z
    double location[] = { 0.0, 0.0, 0.0 };

    //3D rotations         x    y    z
    double rotation[] = { 0.0, 0.0, 0.0 };

    //scale data
    float scale = 1.0f;

    /**
     * Returns the 3D representation of this object
     * @return
     * The 3D object
     */
    public _3DObject get3DObject() {
        return _3dobject;
    }

    /**
     * Sets the 3D object of this part node.
     * @param ob
     * The 3D object
     */
    public void set3DObject(_3DObject ob) {
        this._3dobject = ob;
    }

    /**
     * Moves the part object along the X axis and no other axis.
     * @param dis
     * The distance to translate the Part Object
     */
    public void translateX(double dis) {
        _3dobject.translateX(dis);
        location = MatrixFunctions.translateX(location, dis);

    }

    /**
     * Moves the part object along the Y axis and no other axis.
     * @param dis
     * The distance to translate the Part Object
     */
    public void translateY(double dis) {
        _3dobject.translateY(dis);
        location = MatrixFunctions.translateY(location, dis);
    }

    /**
     * Moves the part object along the Z axis and no other axis.
     * @param dis
     * The distance to translate the Part Object
     */
    public void translateZ(double dis) {
        _3dobject.translateZ(dis);
        location = MatrixFunctions.translateZ(location, dis);
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
        _3dobject.translateXYZ(x, y, z);
        location = MatrixFunctions.translateXYZ(location, x, y, z);
    }

    /**
     * Rotates the point around the parts origin (around the Z axis).
     * @param angle
     * The angle rotated in degrees
     */
    public void rotateZ(double angle) {
        _3dobject.rotateZ(location, angle);
    }

    /**
     * Rotates the point around the parts origin (around the Y axis).
     * @param angle
     * The angle rotated in degrees
     */
    public void rotateY(double angle) {
        _3dobject.rotateY(location, angle);
    }

    /**
     * Rotates the point around the parts origin (around the X axis).
     * @param angle
     * The angle rotated in degrees
     */
    public void rotateX(double angle) {
        _3dobject.rotateX(location, angle);
    }


}
