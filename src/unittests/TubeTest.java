package unittests;

import geometries.Tube;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static primitives.Util.isZero;

/**
 * Unit tests for geometries.Tube class
 * @author elyasaf and omer
 */
public class TubeTest {

    /**
     * Test method for {@link geometries.Tube#get_ray()}.
     */
        @Test
        public void get_ray() {
            // ============ Equivalence Partitions Tests ==============

          Ray _r=new Ray(new Point3D(1,1,1),new Vector(1,2,3));

            assertEquals(new Point3D(1,1,1),_r.getPoint());
        }


    /**
     * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
     */
        @Test
        public void getNormal() {
            // ============ Equivalence Partitions Tests ==============

            Ray _r=new Ray(new Point3D(1,1,1),new Vector(1,2,3));
            Tube _t=new Tube(2,_r);

            Point3D p2=new Point3D(1,2,3);

            Vector c2=new Vector(0.0,0.4472135954999579,0.8944271909999159);

            assertEquals(c2,_t.getNormal(p2));
        }


    /**
        @Test
        public void findIntersections() {
        }
        */
    }

