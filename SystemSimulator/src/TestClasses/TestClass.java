package TestClasses;

import SystemObjects.PartObject;

import _3DClasses.Utilities.Loader;

public class TestClass {

    public static void main(String[] args) {
        PartObject part = new PartObject();
        part.set3DObject(Loader.load_Obj("/Axess.obj"));
        System.out.println(part.get3DObject().getVertex(0).toString());
        part.translateY(10);
        System.out.println(part.get3DObject().getVertex(0).toString());     
    }
}
