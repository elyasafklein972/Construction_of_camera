package unittests;

import org.junit.Test;
import primitives.Vector;

import static org.junit.Assert.*;

public class VectorTest {
    /**
     * Test method for Vector.subtract
     */
    @Test
    public void subtract() {
        Vector v1 = new Vector(1.0, 1.0, 1.0);
        Vector v2 = new Vector(-1.0, -1.0, -1.0);

        v1 = v1.subtract(v2);
        assertNotEquals(v1, new Vector(2.0, 2.0, 3.0));

        v2 = v2.subtract(v1);
        assertEquals(v2, new Vector(-3.0, -3.0, -3.0));

    }

    /**
     * Test method for Vector.add()
     */
    @Test
    public void add() {
        Vector v1 = new Vector(1.0, 1.0, 1.0);
        Vector v2 = new Vector(-1.0, -1.0, 1.0);

        v1 = v1.add(v2);

        ///assertTrue(v1.equals(new Vector(0.0,0.0,0.0)) == 0);
        assertEquals(v1, new Vector(0.0, 0.0, 2.0));
        assertNotEquals(v1, new Vector(0.0, 2.2, 4.0));
    }

    /**
     * Test method for Vector.scale()
     */
    @Test
    public void scale() {
        Vector v1 = new Vector(1.0, 1.0, 1.0);

        v1 = v1.scale(1);
        assertEquals(v1, new Vector(1.0, 1.0, 1.0));

        v1 = v1.scale(2);
        assertEquals(v1, new Vector(2.0, 2.0, 2.0));

        v1 = v1.scale(-2);
        assertEquals(v1, new Vector(-4.0, -4.0, -4.0));

        v1 = v1.scale(-2);
        assertNotEquals(v1, new Vector(-4.0, -4.0, -4.0));
    }

    /**
     * Test method for Vector.dotProduct()
     */
    @Test
    public void dotProduct() {
        Vector v1 = new Vector(3.5, -5, 10);
        Vector v2 = new Vector(2.5, 7, 0.5);

        assertEquals(v1.dotProduct(v2), (8.75 + -35 + 5), 0.0);

        assertEquals(v1.dotProduct(v2), (8.75 + -35 + 6), 1.0);

        assertNotEquals(v1.dotProduct(v2), (8.75 + -35 + 6), 0.0);

    }

    /**
     * Test method for Vector.crossProduct()
     */
    @Test
    public void crossProduct() {
        Vector v1 = new Vector(3.5, -5.0, 10.0);
        Vector v2 = new Vector(2.5, 7, 0.5);
        Vector v3 = v1.crossProduct(v2);

        assertEquals(v3, new Vector(-72.5, 23.25, 37));

        Vector v4 = v2.crossProduct(v1);

        assertNotEquals(v3, v4);
    }

    /**
     * Test method for Vector.lengthSquared()
     */
    @Test
    public void lengthSquared() {
        Vector v = new Vector(3.5, -5, 10);

        assertEquals(v.lengthSquared(), (12.25 + 25 + 100), 0.0);

        assertNotEquals(v.lengthSquared(), (12.25 + 25), 0.0);
    }

    /**
     * Test method for Vector.length()
     */
    @Test
    public void length() {
        Vector v = new Vector(3.5, -5, 10);

        assertEquals(v.length(), Math.sqrt(12.25 + 25 + 100), 0.0);

        assertNotEquals(v.length(), Math.sqrt(12.25 + 25), 0.0);
    }


    /**
     * Test method for Vector.Equals()
     */
    @Test
    public void testEquals() {
        Vector v1 = new Vector(3.5, -5, 10);
        Vector v2 = new Vector(3.5, -5, 10);
        assertEquals(v1, v2);
        Vector v3 = new Vector(3, -5, 1);
        assertNotEquals(v1, v3);
    }

    @Test
    public void normalize() {
        Vector v1 = new Vector(4, 4, 4);
        Vector v2 = new Vector(4 / v1.length(), 4 / (v1.length()), 4 / (v1.length()));
        v1 = v1.normalize();
        assertEquals(v1, v2);

        v2 = v2.add(v1);
        assertNotEquals(v1, v2);
    }

    @Test
    public void normalized() {
        Vector v = new Vector(3.5, -5, 10);
        Vector v1 = v.normalized();
        assertEquals(1, v1.length(), 1e-10);
        assertNotEquals( 2, v1.length(), 1e-10);
    }

}