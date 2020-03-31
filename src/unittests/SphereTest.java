package unittests;

import geometries.Sphere;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.Assert.*;

public class SphereTest {

    @Test
    public void getCenter() {
        Sphere _s=new Sphere(2,new Point3D(1,1,1));
      assertEquals(_s.getCenter(),new Point3D(1,1,1));
//new
    }

    @Test
    public void getNormal() {
        Sphere _s=new Sphere(2,new Point3D(1,1,1));

        Vector v=new Vector(new Point3D(0.0,-0.7071067811865475, -0.7071067811865475));
        assertEquals(_s.getNormal(new Point3D(1,0,0)),v);
    }

    @Test
    public void get_center() {
        Sphere _s=new Sphere(2,new Point3D(1,1,1));
        assertEquals(_s.get_center(),new Point3D(1,1,1));
        assertNotEquals(_s.get_center(),new Point3D(-1,1,1));
    }

    @Test
    public void findIntersections() {
    }
}