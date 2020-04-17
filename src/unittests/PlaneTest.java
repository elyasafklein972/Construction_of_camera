package unittests;

import geometries.Plane;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.Assert.*;
/**
 * Unit tests for geometries.Plane class
 * @author elyasaf and omer
 */
public class PlaneTest {

    /**
     * Test method for {@link geometries.Plane#getNormal()}.
     */
    @Test
    public void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        Plane p = new Plane(new Point3D(1, 0, 0), new Point3D(0, 1, 0), new Point3D(0, 0, 1));
        Vector v = new Vector(-0.5773502691896258, -0.5773502691896258, -0.5773502691896258);
        assertEquals(p.getNormal(), v);
    }


    /**
     * Test method for {@link geometries.Cylinder#getNormal(primitives.Point3D)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Plane p = new Plane(new Point3D(1, 0, 0), new Point3D(0, 1, 0), new Point3D(0, 0, 1));
        Vector v = new Vector(-0.5773502691896258, -0.5773502691896258, -0.5773502691896258);
        assertEquals(p.getNormal(p.get_p()), v);

    }

    Plane p1 = new Plane(new Point3D(0, 0, 1), new Vector(0, 1, 0));
    Plane p2 = new Plane(new Point3D(1, 0, 0), new Vector(1, 0, 1));
    Ray r1 = new Ray(new Point3D(0, -1, 0), new Vector(0, 1, 0));
    Ray r2 = new Ray(new Point3D(0, 1, 0), new Vector(0, 1, 0));
    Ray r3 = new Ray(new Point3D(1, 0, 0), new Vector(1, 0, 0));

    @Test
    public void findIntersectionsTest() {
        assertEquals("", p1.findIntersections(r1).size(), 1);
      
        try {
            assertEquals("", p1.findIntersections(r2).size(), 0);
        }
       catch (NullPointerException e)
       {
           System.out.println("r1 no Intersections p1");
       }

        try {
            assertEquals("", p1.findIntersections(r3).size(), 0);
        }
        catch (NullPointerException e)
        {
            System.out.println("r3 no Intersections p1");
        }


        // checks if the vector is contained in the plane there is no intersections

        try {
            assertEquals("", p2.findIntersections(r3).size(), 0);
        }
        catch (NullPointerException e)
        {
            System.out.println("r3 no Intersections p2");
        }
        try {
            assertEquals("", p2.findIntersections(r1).size(), 0);        }

        catch (NullPointerException e)
        {
            System.out.println("r1 no Intersections p2");
        }
        try {
            assertEquals("", p2.findIntersections(r2).size(), 0);        }

        catch (NullPointerException e)
        {
            System.out.println("r2 no Intersections p2");
        }

    }
}