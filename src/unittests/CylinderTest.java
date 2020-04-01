package unittests;


import geometries.Cylinder;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.Assert.*;
/**
 * Unit tests for geometries.Cylinder class
 * @author elyasaf and omer
 */
public class CylinderTest {

    /**
     * Test method for {@link geometries.Cylinder#getNormal(primitives.Point3D)}.
     */
    @Test
    public void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        Point3D p=new Point3D(1,2,3);
        Vector v=new Vector(4,5,6);
        Ray r =new Ray(p,v);
        Cylinder c=new Cylinder(8,r,9);
        Vector v2=c.getNormal(new Point3D(2,3,4));

assertEquals(v2,new Vector(new Point3D(0.7909115788387004, 0.09304842103984645, -0.604814736759006)));

        Point3D p2=new Point3D(1,0,0);
        Vector v3=new Vector(new Point3D(1,1,1));
        Ray r2=new Ray(p2,v3);
        Cylinder c2=new Cylinder(1,r2,2);

        // =============== Boundary Values Tests ==================

            Vector v4 = c2.getNormal(new Point3D(1, 0, 0));
            assertEquals(v4,v3.normalized());


    }
    /**
     * Test method for {@link geometries.Cylinder#get_height()}.
     */

    @Test
    public void get_height() {
        // ============ Equivalence Partitions Tests ==============
        Point3D p=new Point3D(1,2,3);
        Vector v=new Vector(4,5,6);
        Ray r =new Ray(p,v);
        Cylinder c=new Cylinder(8,r,9);

        assertEquals(8,c.get_height(),0);
    }
}