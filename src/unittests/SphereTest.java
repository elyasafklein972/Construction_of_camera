package unittests;

import geometries.Sphere;
import org.junit.Test;
import primitives.Point3D;

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
    }

    @Test
    public void get_center() {
    }

    @Test
    public void findIntersections() {
    }
}