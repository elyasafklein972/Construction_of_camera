package unittests;

import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.Assert.*;

public class RayTest {

    @Test
    public void testEquals() {
Vector tmp=new Vector(1,2,3);
        tmp.normalize();
        Ray r1= new Ray(new Point3D(1,2,3),(tmp));
        Ray r2= new Ray(new Point3D(1,2,3),(tmp));
        Ray r3= new Ray(new Point3D(1,1,3),(tmp));

        assertEquals(r1,r2);
        assertNotEquals(r1,r3);

    }
}