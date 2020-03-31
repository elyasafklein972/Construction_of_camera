package unittests;

import geometries.Plane;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.Assert.*;

public class PlaneTest {

    @Test
    public void getNormal() {
        Plane p=new Plane(new Point3D(1,0,0),new Point3D(0,1,0),new Point3D(0,0,1));
        Vector v=new Vector(-0.5773502691896258, -0.5773502691896258, -0.5773502691896258);
        assertEquals(p.getNormal(),v);
    }

    @Test
    public void testGetNormal() {
        Plane p=new Plane(new Point3D(1,0,0),new Point3D(0,1,0),new Point3D(0,0,1));
        Vector v=new Vector(-0.5773502691896258, -0.5773502691896258, -0.5773502691896258);
        assertEquals(p.getNormal(p.get_p()),v);

    }
}