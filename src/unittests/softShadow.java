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


public class softShadow {
    @Test
    public void softShadow1()
    {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        // Scene scene = new Scene();
        //  scene.setDistance(200);
        //  scene.setBackground(new Color(249, 251, 255));
        scene.addGeometries(//
                new Sphere(new Color(0, 1, 250),new Material(0.5, 0.5, 60,.3, 1),16.9634, new Point3D(-46.67,25.69,-40)),
                new Sphere(new Color(47, 205, 24),new Material(0.5, 0.5, 60,.3, 1),7.3, new Point3D(-26.76,44.49,-40)),
                new Sphere(new Color(153,51,51),new Material(0.5, 0.5, 60,.3, 1),13.27, new Point3D(0, 0, -40)),
                new Sphere(new Color(255,0,0),new Material(0.5, 0.5, 60,.3, 1),11.737, new Point3D(0,-20 , -40)),
                new Sphere(new Color(204,255,102),new Material(0.5, 0.5, 60,.3, 1),8.5, new Point3D(10.83,5.08,-85.74)),
                new Triangle(new Color(7,39,72),new Material(0.5, 0.5, 60,.3, 1),
                        new Point3D(  -40, -40, -20),
                        new Point3D( -2.63455,  41.34024, -20),
                        new Point3D( 42.80412, -39.83175, -20)),
                new Triangle(new Color(9, 74, 90),new Material(0.5, 0.5, 60,0.5, 1),
                        new Point3D(  -40, 20, -20),
                        new Point3D( 0,  -60, -20),
                        new Point3D( 40, 20, -20)),


                new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
                        new Point3D(-150, 150, 115), new Point3D(150, 150, 135), new Point3D(75, -75, 150)), //
                new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
                        new Point3D(-150, 150, 115), new Point3D(-70, -70, 140), new Point3D(75, -75, 150)));
        //new Plane(new Color(100,100,0), new Point3D(-97.87,62.60,20),new Point3D(73.56,63.28,20),new Point3D(69.26,-48.14,20)));



        //scene.addLights(

                     //   new PointLight(new Color(700, 400, 400), //
                       //        new Point3D(90, -80, 50 ), 1, 4E-5, 2E-7,2));
        scene.addLights(   new PointLight(new Color(700, 400, 400), //
                new Point3D(-90, -80, -250), 1, 4E-5, 2E-7,5));







        ImageWriter imageWriter = new ImageWriter("shadow shadow part1", 300, 300, 600, 600);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }
    @Test
    public void softShadow2()
    {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        // Scene scene = new Scene();
        //  scene.setDistance(200);
        //  scene.setBackground(new Color(249, 251, 255));
        scene.addGeometries(//
                new Sphere(new Color(0, 1, 250),new Material(0.5, 0.5, 60,.8, 1),16.9634, new Point3D(-46.67,25.69,-40)),
                new Sphere(new Color(47, 205, 24),new Material(0.5, 0.5, 60,.5, 1),7.3, new Point3D(-26.76,44.49,-40)),
                new Sphere(new Color(153,51,51),new Material(0.5, 0.5, 60,.3, 1),13.27, new Point3D(0, 0, -40)),
                new Sphere(new Color(255,0,0),new Material(0.5, 0.5, 60,.7, 1),11.737, new Point3D(0,-20 , -40)),
                new Sphere(new Color(204,255,102),new Material(0.5, 0.5, 60,.3, 1),8.5, new Point3D(10.83,5.08,-85.74)),
                new Triangle(new Color(7,39,72),new Material(0.5, 0.5, 60,.5, 1),
                        new Point3D(  -40, -40, -20),
                        new Point3D( -2.63455,  41.34024, -20),
                        new Point3D( 42.80412, -39.83175, -20)),
                new Triangle(new Color(9, 74, 90),new Material(0.5, 0.5, 60,0.5, 1),
                        new Point3D(  -40, 20, -20),
                        new Point3D( 0,  -60, -20),
                        new Point3D( 40, 20, -20)),


                new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
                        new Point3D(-150, 150, 115), new Point3D(150, 150, 135), new Point3D(75, -75, 150)), //
                new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
                        new Point3D(-150, 150, 115), new Point3D(-70, -70, 140), new Point3D(75, -75, 150)));
        //new Plane(new Color(100,100,0), new Point3D(-97.87,62.60,20),new Point3D(73.56,63.28,20),new Point3D(69.26,-48.14,20)));



        //scene.addLights(

        //   new PointLight(new Color(700, 400, 400), //
        //        new Point3D(90, -80, 50 ), 1, 4E-5, 2E-7,2));
        scene.addLights(   new PointLight(new Color(700, 400, 400), //
                new Point3D(-150, -160, -500), 1, 4E-5, 2E-7));







        ImageWriter imageWriter = new ImageWriter("No soft shadow part1", 200, 200, 600, 600);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }
    //------------------------------
    /**
     * Produce a picture of a two triangles lighted by a spot light with a partially transparent Sphere
     *  producing partial shadow
     */
    @Test
    public void trianglesTransparentSphereSoft4() {
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

        scene.addLights(new PointLight(new Color(700, 400, 400), //
                new Point3D(90, -80, 50 ), 1, 4E-5, 2E-7,4));

        ImageWriter imageWriter = new ImageWriter("shadow with transparency4", 200, 200, 600, 600);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }
    }
