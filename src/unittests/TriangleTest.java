package unittests;

import geometries.Sphere;
import geometries.Triangle;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.Assert.*;


public class TriangleTest {

    @Test
    public void testEquals() {
        Triangle t= new Triangle(new Point3D(1,0,0),new Point3D(0,1,0),new Point3D(0,0,1));
        Triangle t2= new Triangle(new Point3D(1,0,0),new Point3D(0,1,0),new Point3D(0,0,1));
    assertEquals(t.equals(t2),true);
    }

    @Test
    public void getNormal() {
        Triangle t= new Triangle(new Point3D(1,0,0),new Point3D(0,1,0),new Point3D(0,0,1));
        assertEquals(t.getNormal(),new Vector (new Point3D(-0.5773502691896258, -0.5773502691896258, -0.5773502691896258)));
    }

    @Test
    public void findIntersections() {

    }
}