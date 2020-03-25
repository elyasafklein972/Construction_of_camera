package unittests;

import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.Assert.*;

public class Point3DTest {


    @Test
    public void testEquals() {
        Point3D p1=new Point3D(1,2,3);
        Point3D p2=new Point3D(1,2,3);
        Point3D p3=new Point3D(0,0,0);

        assertEquals(p1,p2);

        assertEquals(p1,p3);

    }

    @Test
    public void subtract() {
        Point3D p1=new Point3D(1,2,3);
        Point3D p2=new Point3D(1,2,4);
        Point3D p3=new Point3D(0,0,0);

        Vector v1 = p1.subtract(p2);
        Vector v2 = p1.subtract(p3);
        assertNotEquals(v1,v2);

        Vector v3=new Vector(0,0,-1);
        assertEquals(v1,v3);
    }

    @Test
    public void distanceSquared() {
        Point3D p1=new Point3D(1,2,3);
        Point3D p2=new Point3D(1,2,3);
        Point3D p3=new Point3D(0,0,3);

        double d1 = p1.distanceSquared(p2);
        assertEquals(d1,0,0);

        double d2 =p3.distanceSquared(p3);
        assertNotEquals(d2,1,0);

    }



    @Test
    public void distance() {
        Point3D p1=new Point3D(4,2,3);
        Point3D p2=new Point3D(4,2,3);
        Point3D p3=new Point3D(0,0,3);

        double d1 = p1.distance(p2);
        assertEquals(d1,0,0);

        double d2 =p3.distance(p3);
        assertNotEquals(d2,1,0);
    }


    @Test
    public void add() {
        Point3D p1=new Point3D(2,2,3);
        Vector v1=new Vector(1,2,3);
        Point3D p2=new Point3D(3,4,6);

        Point3D p4= p1.add(v1);
        assertEquals(p4,p2);

        Point3D p3=new Point3D(0,0,0);
        Point3D p5= p3.add(v1);
        Point3D p6=new Point3D(3,4,6);

        assertNotEquals(p5,p6);
    }
}