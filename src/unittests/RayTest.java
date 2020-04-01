package unittests;

import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.Assert.*;

public class RayTest {
    /**
     * Test method for {@link primitives.Ray#Ray(Point3D, Vector)}}.
     */
    @Test
    public void TestConstructor() {
        // =============== Boundary Values Tests ==================
        try {
            Ray r =new Ray(new Point3D(0,0,0),new Vector(0,0,0));
        }
        catch (IllegalArgumentException e){
            System.out.println("Failed constructing a correct Ray with this zero Vector");
        }
    }

    /**
     * Test method for {@link primitives.Ray#equals(Object)}}.
     */
    @Test
    public void testEquals() {
        // ============ Equivalence Partitions Tests ==============
        Vector tmp=new Vector(1,2,3);
        Ray r1= new Ray(new Point3D(1,2,3),(tmp));
        Ray r2= new Ray(new Point3D(1,2,3),(tmp));
        Ray r3= new Ray(new Point3D(1,1,3),(tmp));

        assertEquals(r1,r2);
        assertNotEquals(r1,r3);

    }


    /**
     * Test method for {@link primitives.Ray#getPoint()}.
     */
    @Test
    public void TestGetPoint() {

        // ============ Equivalence Partitions Tests ==============

        Vector tmp=new Vector(1,2,3);
        Ray r1= new Ray(new Point3D(2,3,4),(tmp));
        assertEquals(r1.getPoint(),new Point3D(2,3,4));
    }


    /**
     * Test method for {@link primitives.Ray#getTargetPoint(double)}}.
     */
    @Test
    public void getTargetPoint(){

        // ============ Equivalence Partitions Tests ==============

        Vector tmp=new Vector(1,2,3);
        Ray r1= new Ray(new Point3D(2,3,4),(tmp));


        ///test when the length is normal double
        Point3D p=new Point3D( r1.getTargetPoint(1.8));
        assertEquals(p,new Point3D(2,3,4).add(new Vector(0.481070235442364,0.962140470884728,1.443210706327092)));


        ///test when the length is normal int
        Point3D p1=new Point3D( r1.getTargetPoint(5));
        assertEquals(p1,new Point3D(2,3,4).add(new Vector(1.336306209562122,2.672612419124244,4.008918628686367)));


        // =============== Boundary Values Tests ==================

        ///test when the length is zero if return _point
        Point3D p2=new Point3D( r1.getTargetPoint(0));
        assertEquals(p2,new Point3D(2,3,4));

    }


    /**
     * Test method for {@link primitives.Ray#getDirection()}.
     */
    @Test
    public void TestGetDirection() {
        // ============ Equivalence Partitions Tests ==============

        Vector tmp=new Vector(1,2,3);
        Ray r1= new Ray(new Point3D(2,3,4),(tmp));
        assertEquals(r1.getDirection(),new Vector(1,2,3).normalized());
    }

}