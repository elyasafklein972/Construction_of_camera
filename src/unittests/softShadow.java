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
        scene.setCamera(new Camera(new Point3D(0, 0, -100), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(100);
        scene.setBackground(new Color(249, 251, 255));
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        // Scene scene = new Scene();
        //  scene.setDistance(200);
        //  scene.setBackground(new Color(249, 251, 255));
        scene.addGeometries(//
                new Sphere(new Color(0, 1, 250),new Material(0.4,0.3,20,0.7,0),16.9634, new Point3D(-46.67,25.69,0)),
                new Sphere(new Color(47, 205, 24),new Material(0.4,0.3,15,0,0),7.3, new Point3D(-26.76,44.49,0)),
                new Sphere(new Color(0,0,100),new Material(0.4,0.3,20,0,0),13.27, new Point3D(0, 0, 10)),
                new Sphere(new Color(0,0,100),new Material(0.4,0.3,20,0,0),11.737, new Point3D(0,-20 , 20)),
                new Sphere(new Color(0,0,100),new Material(0.4,0.3,20,0,0),8.1, new Point3D(-1.1,1.47 , 13.14)),
                new Triangle(new Color(7,39,72),new Material(0,0,0,0,1),
                        new Point3D(  -40, -40, 0),
                        new Point3D( -2.63455,  41.34024, 0),
                        new Point3D( 42.80412, -39.83175, 0)),
                new Triangle(new Color(9, 74, 90),new Material(0,0,0,0,0.5),
                        new Point3D(  -40, 20, 0),
                        new Point3D( 0,  -60, 0),
                        new Point3D( 40, 20, 0)),
                new Plane(new Point3D(-97.87,62.60,-20),new Point3D(73.56,63.28,-20),new Point3D(69.26,-48.14,-20)));




        scene.addLights(new SpotLight(new Color(43, 48, 11),  new Point3D(2000, 1500, 0),
                new Vector(500, 0, 0), 0, 0.000001, 0.00000009,1));

        scene.addLights(new PointLight(new Color(45, 51, 15),  new Point3D(2300, 2300, -150),
                0, 0.000001, 0.00000008));





        ImageWriter imageWriter = new ImageWriter("scene_with_softShadow", 2500, 2500, 5000, 5000);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }

    }
