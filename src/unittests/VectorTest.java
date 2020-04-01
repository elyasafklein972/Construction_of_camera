package unittests;

import org.junit.Test;
import primitives.Vector;

import static org.junit.Assert.*;
import static primitives.Util.isZero;

/**
 * Unit tests for geometries.Vector class
 * @author elyasaf and omer
 */
public class VectorTest {

    /**
     * Test method for {@link primitives.Vector#subtract(primitives.Vector)}}.
     */
    @Test
    public void subtract() {
        // ============ Equivalence Partitions Tests ==============

        Vector v1 = new Vector(1.0, 1.0, 1.0);
        Vector v2 = new Vector(-1.0, -1.0, -1.0);
        Vector v3 = new Vector(-3.0, -3.0, -3.0);

        v1 = v1.subtract(v2);
        assertNotEquals(v1, new Vector(2.0, 2.0, 3.0));


        // =============== Boundary Values Tests ==================

        v2 = v2.subtract(v1);
        assertEquals(v2, new Vector(-3.0, -3.0, -3.0));
        try {
            v2=v2.subtract(v3);
        }
        catch (IllegalArgumentException e) {
            System.out.println("test passed: v2 is zero Vector");
        }
    }


    /**
     * Test method for {@link primitives.Vector#add(primitives.Vector)}}.
     */
    @Test
    public void add() {
        // ============ Equivalence Partitions Tests ==============

        Vector v1 = new Vector(1.0, 1.0, 1.0);
        Vector v2 = new Vector(-1.0, -1.0, 1.0);
        Vector v3 = new Vector(0.0, 0.0, -2.0);

        v1 = v1.add(v2);

        assertEquals(v1, new Vector(0.0, 0.0, 2.0));
        assertNotEquals(v1, new Vector(0.0, 2.2, 4.0));

        // =============== Boundary Values Tests ==================
        try {
            v1=v1.add(v3);
        }
catch (IllegalArgumentException e){
    System.out.println( "test passed: v1 is zero Vector");
}
    }


    /**
     * Test method for {@link primitives.Vector#scale(double)}.
     */
    @Test
    public void scale() {
        // ============ Equivalence Partitions Tests ==============

        Vector v1 = new Vector(1.0, 1.0, 1.0);

        v1 = v1.scale(1);
        assertEquals(v1, new Vector(1.0, 1.0, 1.0));

        v1 = v1.scale(2);
        assertEquals(v1, new Vector(2.0, 2.0, 2.0));

        v1 = v1.scale(-2);
        assertEquals(v1, new Vector(-4.0, -4.0, -4.0));

        v1 = v1.scale(-2);
        assertNotEquals(v1, new Vector(-4.0, -4.0, -4.0));

        // =============== Boundary Values Tests ==================

        try {
            v1 = v1.scale(0.0);
        } catch (IllegalArgumentException e) {
            System.out.println("test passed: v1 is zero Vector");
        }
    }
    /**
     * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}}.
     */
    @Test
    public void dotProduct() {

        // ============ Equivalence Partitions Tests ==============

        Vector v1 = new Vector(3.5, -5, 10);
        Vector v2 = new Vector(2.5, 7, 0.5);

        assertEquals(v1.dotProduct(v2), (8.75 + -35 + 5), 0.0);

        assertEquals(v1.dotProduct(v2), (8.75 + -35 + 6), 1.0);

        assertNotEquals(v1.dotProduct(v2), (8.75 + -35 + 6), 0.0);

    }


    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    public void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);

        // ============ Equivalence Partitions Tests ==============
        Vector v3 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v3);

        // Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
        assertEquals("crossProduct() wrong result length", v1.length() * v3.length(), vr.length(), 0.00001);

        // Test cross-product result orthogonality to its operands
        assertTrue("crossProduct() result is not orthogonal to 1st operand", isZero(vr.dotProduct(v1)));
        assertTrue("crossProduct() result is not orthogonal to 2nd operand", isZero(vr.dotProduct(v3)));

        // =============== Boundary Values Tests ==================
        // test zero vector from cross-productof co-lined vectors
        try {
            v1.crossProduct(v2);
            fail("crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {}
    }


    /**
     * Test method for {@link primitives.Vector#lengthSquared()}}.
     */
        @Test
        public void lengthSquared () {

            // ============ Equivalence Partitions Tests ==============

            Vector v = new Vector(3.5, -5, 10);

            assertEquals(v.lengthSquared(), (12.25 + 25 + 100), 0.0);

            assertNotEquals(v.lengthSquared(), (12.25 + 25), 0.0);
        }


    /**
     * Test method for {@link primitives.Vector#length()}}.
     */
    @Test
    public void length() {

        // ============ Equivalence Partitions Tests ==============

        Vector v = new Vector(3.5, -5, 10);

        assertEquals(v.length(), Math.sqrt(12.25 + 25 + 100), 0.0);

        assertNotEquals(v.length(), Math.sqrt(12.25 + 25), 0.0);
    }


    /**
     * Test method for {@link primitives.Vector#equals(Object)}.
     */
    @Test
    public void testEquals() {

        // ============ Equivalence Partitions Tests ==============

        Vector v1 = new Vector(3.5, -5, 10);
        Vector v2 = new Vector(3.5, -5, 10);
        assertEquals(v1, v2);
        Vector v3 = new Vector(3, -5, 1);
        assertNotEquals(v1, v3);
    }


    /**
     * Test method for {@link primitives.Vector#normalize()}}.
     */
    @Test
    public void normalize() {

        // ============ Equivalence Partitions Tests ==============

        Vector v1 = new Vector(4, 4, 4);
        Vector v2 = new Vector(4 / v1.length(), 4 / (v1.length()), 4 / (v1.length()));
        v1 = v1.normalize();
        assertEquals(v1, v2);

        v2 = v2.add(v1);
        assertNotEquals(v1, v2);
    }


    /**
     * Test method for {@link primitives.Vector#normalized()}}.
     */
    @Test
    public void normalized() {

        // ============ Equivalence Partitions Tests ==============

        Vector v = new Vector(3.5, -5, 10);
        Vector v1 = v.normalized();
        assertEquals(1, v1.length(), 1e-10);
        assertNotEquals( 2, v1.length(), 1e-10);
    }

}