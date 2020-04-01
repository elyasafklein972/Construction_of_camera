package unittests;

import geometries.Plane;
import org.junit.Test;
import primitives.Point3D;
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
        Plane p=new Plane(new Point3D(1,0,0),new Point3D(0,1,0),new Point3D(0,0,1));
        Vector v=new Vector(-0.5773502691896258, -0.5773502691896258, -0.5773502691896258);
        assertEquals(p.getNormal(),v);
    }


    /**
     * Test method for {@link geometries.Cylinder#getNormal(primitives.Point3D)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Plane p=new Plane(new Point3D(1,0,0),new Point3D(0,1,0),new Point3D(0,0,1));
        Vector v=new Vector(-0.5773502691896258, -0.5773502691896258, -0.5773502691896258);
        assertEquals(p.getNormal(p.get_p()),v);

    }
}