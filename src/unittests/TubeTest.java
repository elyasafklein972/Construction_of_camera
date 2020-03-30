package unittests;

import geometries.Tube;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static primitives.Util.isZero;


public class TubeTest {

        @Test
        public void get_ray() {
          Ray _r=new Ray(new Point3D(1,1,1),new Vector(1,2,3));

            assertEquals(new Point3D(1,1,1),_r.getPoint());
        }

        @Test
        public void getNormal() {
            Ray _r=new Ray(new Point3D(1,1,1),new Vector(1,2,3));
            Tube _t=new Tube(2,_r);
            Vector v=new Vector(1,2,3);
            Point3D p =new Point3D(1,1,1);
            Point3D p2=new Point3D(1,2,3);
            Vector vector1 = p2.subtract(p);

            //We need the projection to multiply the _direction unit vector
            double projection = vector1.dotProduct(v);
            if(!isZero(projection))
            {
                // projection of P-O on the ray:
                p.add(v.scale(projection));
            }


            Vector c = p2.subtract(p);
            
            assertEquals(c.normalize(),_t.getNormal(p2));
        }

        @Test
        public void findIntersections() {
        }
    }
