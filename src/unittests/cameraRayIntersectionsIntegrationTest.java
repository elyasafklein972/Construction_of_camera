package unittests;
import elements.Camera;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
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
    public void cameraRaySphereIntegrationTest1() {
        Camera cam = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
        List<Point3D> intersections=new ArrayList<Point3D>();
        Sphere sphere = new Sphere(1, new Point3D(0, 0, 3));
        int count=0;

        for (int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                intersections = sphere.findIntersections(cam.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
                if (intersections != null)
                count+=intersections.size();
            }
        }

    if (intersections != null)
        assertEquals("wronge intersection",2,count);
    }



    @Test
    public void cameraRaySphereIntegrationTest2() {
        Camera cam = new Camera(new Point3D(0, 0, -0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));
        Sphere sphere = new Sphere(2.5, new Point3D(0, 0, 2.5));
       List<Point3D> intersections=new ArrayList<Point3D>();
        int count = 0;


        for (int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                intersections = sphere.findIntersections(cam.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
                if (intersections != null)
                    count+=intersections.size();
            }
        }

        if (intersections != null)
            assertEquals("wronge intersection",18,count);
    }


    @Test
    public void cameraRaySphereIntegrationTest3() {
        Camera cam = new Camera(new Point3D(0, 0, -0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));
        Sphere sphere = new Sphere(2, new Point3D(0, 0, 2));
        List<Point3D> intersections=new ArrayList<Point3D>();
        int count = 0;


        for (int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                intersections = sphere.findIntersections(cam.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
                if (intersections != null)
                    count+=intersections.size();
            }
        }

        if (intersections != null)
            assertEquals("wronge intersection",10,count);
    }

    @Test
    public void cameraRaySphereIntegrationTest4() {
        Camera cam = new Camera(new Point3D(2, 2, 2), new Vector(0, 0, 1), new Vector(0, -1, 0));
        Sphere sphere = new Sphere(4, new Point3D(2, 2, 2));
        List<Point3D> intersections=new ArrayList<Point3D>();
        int count = 0;


        for (int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                intersections = sphere.findIntersections(cam.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
                if (intersections != null)
                    count+=intersections.size();
            }
        }

        if (intersections != null)
            assertEquals("wronge intersection",9,count);
    }

    @Test
    public void cameraRaySphereIntegrationTest5() {
        Camera cam = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
        Sphere sphere = new Sphere(0.5, new Point3D(0, 0, -1));
        List<Point3D> intersections=new ArrayList<Point3D>();
        int count = 0;


        for (int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                intersections = sphere.findIntersections(cam.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
                if (intersections != null)
                    count+=intersections.size();
            }
        }

        if (intersections != null)
            assertEquals("wronge intersection",0,count);
    }

    @Test
    public void cameraRayPlaneIntegrationTest1(){
        Camera cam = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
        Plane plane = new Plane(new Point3D(0,0,4),new Vector(0,0,1));
        List<Point3D> intersections=new ArrayList<Point3D>();
        int count = 0;


        for (int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                intersections = plane.findIntersections(cam.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
                if (intersections != null)
                    count+=intersections.size();
            }
        }
        if (intersections != null)
            assertEquals("wronge intersection",9,count);
    }

    @Test
    public void cameraRayPlaneIntegrationTest2(){
        Camera cam = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
        Plane plane = new Plane(new Point3D(0,0,4),new Vector(2,2,0));
        List<Point3D> intersections=new ArrayList<Point3D>();
        int count = 0;


        for (int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                intersections = plane.findIntersections(cam.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
                if (intersections != null)
                    count+=intersections.size();
            }
        }
        if (intersections != null)
            assertEquals("wronge intersection",9,count);
    }
    @Test
    public void cameraRayPlaneIntegrationTest3(){
        Camera cam = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
        Plane plane = new Plane(new Point3D(0,0,4),new Vector(1,0,0));
        List<Point3D> intersections=new ArrayList<Point3D>();
        int count = 0;


        for (int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                intersections = plane.findIntersections(cam.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
                if (intersections != null)
                    count+=intersections.size();
            }
        }
        if (intersections != null)
            assertEquals("wronge intersection",6,count);
    }
    @Test
    public void cameraRayTriangleIntegrationTest1(){
        Camera cam = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
       Triangle tri=new Triangle(new Point3D(0,-1,2),new Point3D(1,1,2),new Point3D(-1,1,2));
        List<Point3D> intersections=new ArrayList<Point3D>();
        int count = 0;


        for (int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                intersections = tri.findIntersections(cam.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
                if (intersections != null)
                    count+=intersections.size();
            }
        }
        if (intersections != null)
            assertEquals("wronge intersection",1,count);
    }
    @Test
    public void cameraRayTriangleIntegrationTest2(){
        Camera cam = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
        Triangle tri=new Triangle(new Point3D(0,-20,2),new Point3D(1,1,2),new Point3D(-1,1,2));
        List<Point3D> intersections=new ArrayList<Point3D>();
        int count = 0;


        for (int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                intersections = tri.findIntersections(cam.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
                if (intersections != null)
                    count+=intersections.size();
            }
        }
        if (intersections != null)
            assertEquals("wronge intersection",2,count);
    }
    @Test
    public void cameraRayTriangleIntegrationTest3(){
        Camera cam = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
        Triangle tri=new Triangle(new Point3D(0,-20,2),new Point3D(1,20,2),new Point3D(-1,20,2));
        List<Point3D> intersections=new ArrayList<Point3D>();
        int count = 0;


        for (int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                intersections = tri.findIntersections(cam.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
                if (intersections != null)
                    count+=intersections.size();
            }
        }
        if (intersections != null)
            assertEquals("wronge intersection",3,count);
    }





}
