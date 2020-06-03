/**
 *
 */
package unittests;

import geometries.Plane;
import geometries.Polygon;
import org.junit.Test;

import elements.*;
import geometries.Sphere;
import geometries.Triangle;
import primitives.*;
import renderer.*;
import scene.Scene;

import javax.swing.*;

/**
 * Tests for reflection and transparency functionality, test for partial shadows
 * (with transparency)
 *
 * @author dzilb
 *
 */
public class ReflectionRefractionTests {

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void twoSpheres() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));

        scene.addGeometries(
                new Sphere(new Color(java.awt.Color.BLUE), new Material(0.4, 0.3, 100, 0.3, 0), 50,
                        new Point3D(0, 0, 50)),
                new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 0.5, 100), 25, new Point3D(0, 0, 50)));

        scene.addLights(new SpotLight(new Color(1000, 600, 0), new Point3D(-100, 100, -500), new Vector(-1, 1, 2), 1,
                0.0004, 0.0000006));

        ImageWriter imageWriter = new ImageWriter("twoSpheres", 150, 150, 500, 500);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void twoSpheresOnMirrors() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -10000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(10000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

        scene.addGeometries(
                new Sphere(new Color(0, 0, 100), new Material(0.25, 0.25, 20, 0.5, 0), 400, new Point3D(-950, 900, 1000)),
                new Sphere(new Color(100, 20, 20), new Material(0.25, 0.25, 20), 200, new Point3D(-950, 900, 1000)),
                new Triangle(new Color(20, 20, 20), new Material(0, 0, 0, 0, 1), new Point3D(1500, 1500, 1500),
                        new Point3D(-1500, -1500, 1500), new Point3D(670, -670, -3000)),
                new Triangle(new Color(20, 20, 20), new Material(0, 0, 0, 0, 0.5), new Point3D(1500, 1500, 1500),
                        new Point3D(-1500, -1500, 1500), new Point3D(-1500, 1500, 2000)));

        scene.addLights(new SpotLight(new Color(1020, 400, 400),  new Point3D(-750, 750, 150),
                new Vector(-1, 1, 4), 1, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("twoSpheresMirrored", 2500, 2500, 500, 500);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }

    /**
     * Produce a picture of a two triangles lighted by a spot light with a partially transparent Sphere
     *  producing partial shadow
     */
    @Test
    public void trianglesTransparentSphere() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.addGeometries( //
                new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
                        new Point3D(-150, 150, 115), new Point3D(150, 150, 135), new Point3D(75, -75, 150)), //
                new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
                        new Point3D(-150, 150, 115), new Point3D(-70, -70, 140), new Point3D(75, -75, 150)), //
                new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), // )
                        30, new Point3D(60, -50, 50)));

        scene.addLights(new SpotLight(new Color(700, 400, 400), //
                new Point3D(60, -50, 0), new Vector(0, 0, 1), 1, 4E-5, 2E-7));

        ImageWriter imageWriter = new ImageWriter("shadow with transparency", 200, 200, 600, 600);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }
    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void twoSpheresOnMirrors2() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -10000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(10000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

        scene.addGeometries(
                new Sphere(new Color(0, 0, 100), new Material(0.25, 0.25, 20, 0.2, 0), 400, new Point3D(-950, 900, 1000)),
                new Sphere(new Color(255, 0, 0), new Material(0.25, 0.25, 20, 0.5, 0), 300, new Point3D(-950, 900, 1000)),
                new Sphere(new Color(255, 20, 20), new Material(0.25, 0.25, 20, 0.8, 0), 200, new Point3D(-950, 900, 1000)),
                new Sphere(new Color(102, 255, 51), new Material(0.25, 0.25, 20, 0.5, 0), 100, new Point3D(-950, 900, 1000)),
                new Sphere(new Color(0, 255, 0), new Material(0.25, 0.25, 20, 0.1, 0), 50, new Point3D(-950, 900, 1000)),
                new Triangle(new Color(20, 20, 20), new Material(0, 0, 0, 0, 1), new Point3D(1500, 1500, 1500),
                        new Point3D(-1500, -1500, 1500), new Point3D(670, -670, -3000)),
                new Triangle(new Color(20, 20, 20), new Material(0, 0, 0, 0, 0.5), new Point3D(1500, 1500, 1500),
                        new Point3D(-1500, -1500, 1500), new Point3D(-1500, 1500, 2000)));

        scene.addLights(new SpotLight(new Color(1020, 400, 400),  new Point3D(-750, 750, 150),
                new Vector(-1, 1, 4), 1, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("twoSpheresMirrored2", 2500, 2500, 500, 500);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }
    /**
     * Produce a picture of a two triangles lighted by a spot light with a partially transparent Sphere
     *  producing partial shadow
     */
    @Test
    public void trianglesTransparentSphere2() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.addGeometries( //
                new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
                        new Point3D(-150, 150, 115), new Point3D(150, 150, 135), new Point3D(75, -75, 150)), //
                new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
                        new Point3D(-150, 150, 115), new Point3D(-70, -70, 140), new Point3D(75, -75, 150)), //
                new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), // )
                        30, new Point3D(60, -50, 50)),
                 new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), // )
                     30, new Point3D(-60, 50, 50)));
        scene.addLights(new SpotLight(new Color(700, 400, 400), //
                new Point3D(60, -50, 0), new Vector(0, 0, 1), 1, 4E-5, 2E-7));
        scene.addLights(new SpotLight(new Color(700, 400, 400), //
                                new Point3D(-60, 50, 0), new Vector(0, 0, 1), 1, 4E-5, 2E-7));

        ImageWriter imageWriter = new ImageWriter("shadow with transparency2", 200, 200, 600, 600);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }
    /**
     * Produce a picture of a two triangles lighted by a spot light with a partially transparent Sphere
     *  producing partial shadow
     */
    @Test
    public void sphereWithTransparent() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(new Color(255,153,0));
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.BLACK), 0.15));

        scene.addGeometries(
                new Triangle((Color.BLACK), new Material(0.5, 0.5, 60,1, 0.5), //
                        new Point3D(-150, 150, 115), new Point3D(150, 150, 135), new Point3D(75, -75, 150)), //
                new Triangle((Color.BLACK), new Material(0.5, 0.5, 60,1, 0.5), //
                        new Point3D(-150, 150, 115), new Point3D(-70, -70, 140), new Point3D(75, -75, 150)), //
                new Sphere(new Color(153,51,0), new Material(0.2, 0.2, 30, 0.6, 0), 2.4083, new Point3D(0, 0, 0)),
                new Sphere(new Color(153,51,0), new Material(0.2, 0.2, 30, 0.6, 0), 1.5905, new Point3D(0, -4, 0)),
                new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), 0.6, new Point3D(1.79, -5.25, 0)),
                new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), 0.6082, new Point3D(-1.82, -5.19, 0)),
                new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), 0.8485, new Point3D(2.23, 2.32, 0)),
                new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), 0.8360, new Point3D(-2.2, 2.31, 0)),
                new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), .5261, new Point3D(3.48, 2.64, 0)),
                new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), .5, new Point3D(-4.01, -1.39, 0)),
                new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), 0.5477, new Point3D(-3.39, 2.94, 0)),
                new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), .8306, new Point3D(2.74, -1.71, 0)),
                new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), .8306, new Point3D(-2.77, -1.69, 0)),
                new Sphere(new Color(java.awt.Color.WHITE), new Material(0.2, 0.2, 30, 0.6, 0), .5, new Point3D(-.77, -4.47, 1.31)),
             //   new Sphere(new Color(java.awt.Color.GREEN), new Material(0.2, 0.2, 30, 0.6, 0), .3, new Point3D(-.77, -4.47, 1.85)),
                new Sphere(new Color(java.awt.Color.WHITE), new Material(0.2, 0.2, 30, 0.6, 0), .5, new Point3D(.67, -4.47, 1.31)),
             //   new Sphere(new Color(java.awt.Color.GREEN), new Material(0.2, 0.2, 30, 0.6, 0), .3, new Point3D(.67, -4.47, 1.85)),
                new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), .5, new Point3D(4.04, -1.55, 0)));
        scene.addLights(new SpotLight(new Color(400, 240, 0),new Point3D(0,0,0),new Vector(1, -1, 3), 1, 1E-5, 1.5E-7));
      //  scene.addLights(new SpotLight(new Color(700, 400, 400), //
          //      new Point3D(60, -50, 0), new Vector(0, 0, 1), 1, 4E-5, 2E-7));

       // scene.addLights(new SpotLight(new Color(400, 240, 0),new Point3D(0, 0, 0),new Vector(-1, -1, -3), 1, 1E-5, 1.5E-7));

        //   scene.addLights(new SpotLight(new Color(700, 400, 400), //
    //           new Point3D(0, -4, 0), new Vector(0, 0, 1), 1, 4E-5, 2E-7));

        ImageWriter imageWriter = new ImageWriter("myPicture", 50, 50, 600, 600);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }
    /**
     * Produce a picture of a two triangles lighted by a spot light with a partially transparent Sphere
     *  producing partial shadow
     */
    @Test
    public void sphereWithTransparent3() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(new Color(255,153,0));
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.BLACK), 0.15));

        scene.addGeometries(//new Triangle((Color.WHITE), new Material(0.5, 0.5, 60,1, 0.5), new Point3D(0,60,0) ,new Point3D(60,0,0), new Point3D(-60,0,0)), //
                new Triangle((new Color(0,51,204)), new Material(0.5, 0.5, 60,0, 1), new Point3D(15.54,7.8,0) ,new Point3D(0,32.87,0), new Point3D(-15.61,7.68,0)), //
                new Triangle((new Color(0,51,204)), new Material(0.5, 0.5, 60,0, 1), new Point3D(15.1,24.72,0) ,new Point3D(0,0,0), new Point3D(-15.31,24.86,0)));//
        scene.addLights(new SpotLight(new Color(700, 400, 400), //
                new Point3D(60, -50, 0), new Vector(0, 0, 1), 1, 4E-5, 2E-7));


        ImageWriter imageWriter = new ImageWriter("myPicture2", 200, 200, 600, 600);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }
    /**
     * Produce a picture of a two triangles lighted by a spot light with a partially transparent Sphere
     *  producing partial shadow
     */
    @Test
    public void carTranparent() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(new Color(255,153,0));
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.BLACK), 0.15));

        scene.addGeometries(new Polygon((Color.WHITE), new Material(0.5, 0.5, 60,1, 0.5),new Point3D(-23.61,-18.29,0),
        new Point3D(41.91,-19.91,0),new Point3D(50,-10,0),new Point3D(-23.77,-9.53,0)),
                new Polygon((Color.WHITE), new Material(0.5, 0.5, 60,1, 0.5),new Point3D(0,-28.34,0),new Point3D(23.74,-28.67,0),new Point3D(29,-19,0),new Point3D(-8.04,-18.61,0)),
        //        new Polygon((new Color(0,153,204)), new Material(0.5, 0.5, 60,1, 0.5),new Point3D(5.03,-24.8,2),new Point3D(14.6,-24.96,2),new Point3D(14.85,-20.73,2),new Point3D(5.28,-20.58,2)),
                new Triangle((new Color(153,153,102)), new Material(0.5, 0.5, 60,.5, 1), //
                        new Point3D(-150, 150, 115), new Point3D(150, 150, 135), new Point3D(75, -75, 150)), //
                new Triangle((new Color(153,153,102)), new Material(0.5, 0.5, 60,.5, 1), //
                        new Point3D(-150, 150, 115), new Point3D(-70, -70, 140), new Point3D(75, -75, 150)), //
                new Sphere(Color.BLACK, new Material(0.5, 0.5, 60,1, 0.5),4.8425,new Point3D(-10.31,-4.67,0)),
      new Sphere(Color.BLACK, new Material(0.5, 0.5, 60,1, 0.5),4.8155,new Point3D(38.01,-4.83,0)));

        //scene.addLights(new SpotLight(new Color(700, 400, 400), //
               // new Point3D(60, -50, 0), new Vector(0, 0, 1), 1, 4E-5, 2E-7));


        ImageWriter imageWriter = new ImageWriter("myPicture3", 200, 200, 600, 600);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }
}
