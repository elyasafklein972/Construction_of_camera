package unittests;

import org.junit.Test;
import primitives.Coordinate;

import static org.junit.Assert.assertEquals;

public class CoordinateTest {
    

    @Test
    public void testEquals() {
        Coordinate c1=new Coordinate(1.0);
        Coordinate c2=new Coordinate(1.0);
        assertEquals(c1,c2);

        Coordinate c3=new Coordinate(0.0);
        assertEquals(c3,new Coordinate(0.0));
    }

}