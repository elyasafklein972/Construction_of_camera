package unittests;
import elements.Camera;
import geometries.Sphere;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import elements.Camera;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class cameraRayIntersectionsIntegrationTest {


    @Test
    public void cameraRaySphereIntegrationTest() {
        Camera cam1 = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
        Camera cam2 = new Camera(new Point3D(0, 0, -0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));
        Sphere sphere;
        List<Point3D> intersections=new ArrayList<Point3D>() ;
        int count;

        sphere = new Sphere(1, new Point3D(0, 0, 3));
        count =0;
        for (int i=0;i<3;i++)
        for(int j=0;j<3;j++){
            intersections=sphere.findIntersections(cam1.constructRayThroughPixel(3,3,j,i,1,3,3));
        }
    if (intersections != null)
    count+=intersections.size();
        assertEquals("wronge intersection",2,count);

    }
}
