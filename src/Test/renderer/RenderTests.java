package Test.renderer;


import elements.*;
import geometries.*;
import org.junit.Test;
import primitives.*;
import renderer.ImageWriter;
import renderer.Render;
import scene.*;

/**
 * Test rendering abasic image
 *
 * @author Dan
 */
public class RenderTests {

    @Test
    public void getClosestPoint() {
    }

    @Test
    public void writeToImage() {
    }

    @Test
    public void calcColor() {
    }
    /**
     * Produce a scene with basic 3D model and render it into a jpeg image with a
     * grid
     */
    @Test
    public void basicRenderTwoColorTest1() {
        final double WIDTH = 1600.0;
        final double HEIGHT = 1000.0;
        final int NX =800;
        final int NY =500;

        Scene scene;
        scene= new Scene.SceneBuilder("Test scene 800X500")
                .addAmbientLight(new AmbientLight(new Color(50, 70, 90), 1))
                .addCamera(new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0)))
                .addDistance(100)
                .addBackground(new  Color(60, 60, 60))
                .build();

        scene.addGeometries(new Sphere(50, new Point3D(0, 0, 100)));

        scene.addGeometries(
                new Triangle(new Point3D(100, 0, 100), new Point3D(0, 100, 100), new Point3D(100, 100, 100)),
                new Triangle(new Point3D(100, 0, 100), new Point3D(0, -100, 100), new Point3D(100, -100, 100)),
                new Triangle(new Point3D(-100, 0, 100), new Point3D(0, 100, 100), new Point3D(-100, 100, 100)),
                new Triangle(new Point3D(-100, 0, 100), new Point3D(0, -100, 100), new Point3D(-100, -100, 100)));

        ImageWriter imageWriter = new ImageWriter("base render test 800X500", WIDTH, HEIGHT, NX, NY);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.printGrid(100, java.awt.Color.YELLOW);
        render.writeToImage();
    }

    /**
     * Produce a scene with basic 3D model and render it into a jpeg image with a
     * grid
     */
    @Test
    public void basicRenderTwoColorTest2() {
        final double WIDTH = 1600.0;
        final double HEIGHT = 1000.0;
        final int NX =1600;
        final int NY =1000;

        Scene scene;
        scene= new Scene.SceneBuilder("Test scene 1600x1000")
                .addAmbientLight(new AmbientLight(new Color(255, 50, 191), 1))
                .addCamera(new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0)))
                .addDistance(100)
                .addBackground(new  Color(75, 127, 250))
                .build();

        scene.addGeometries(new Sphere(50, new Point3D(0, 0, 100)));

        scene.addGeometries(
                new Triangle(new Point3D(100, 0, 100), new Point3D(0, 100, 100), new Point3D(100, 100, 100)),
                new Triangle(new Point3D(100, 0, 100), new Point3D(0, -100, 100), new Point3D(100, -100, 100)),
                new Triangle(new Point3D(-100, 0, 100), new Point3D(0, 100, 100), new Point3D(-100, 100, 100)),
                new Triangle(new Point3D(-100, 0, 100), new Point3D(0, -100, 100), new Point3D(-100, -100, 100)));

        ImageWriter imageWriter = new ImageWriter("base render test 1600X1000", WIDTH, HEIGHT, NX, NY);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.printGrid(100, java.awt.Color.YELLOW);
        render.writeToImage();
    }

    /**
     * Produce a scene with basic 3D model and render it into a jpeg image with a
     * grid
     */
    @Test
    public void basicRenderTwoColorTest3() {
        final double WIDTH = 800d;
        final double HEIGHT = 500d;
        final int NX =1600;
        final int NY =1000;

        Scene scene;
        scene= new Scene.SceneBuilder("Test scene 1600x1000 small")
                .addAmbientLight(new AmbientLight(new Color(255, 50, 191), 1))
                .addCamera(new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0)))
                .addDistance(100)
                .addBackground(new  Color(75, 127, 250))
                .build();

        scene.addGeometries(new Sphere(50, new Point3D(0, 0, 100)));
//
        scene.addGeometries(
                new Triangle(new Point3D(100, 0, 100), new Point3D(0, 100, 100), new Point3D(100, 100, 100)),
                new Triangle(new Point3D(100, 0, 100), new Point3D(0, -100, 100), new Point3D(100, -100, 100)),
                new Triangle(new Point3D(-100, 0, 100), new Point3D(0, 100, 100), new Point3D(-100, 100, 100)),
                new Triangle(new Point3D(-100, 0, 100), new Point3D(0, -100, 100), new Point3D(-100, -100, 100)));

        ImageWriter imageWriter = new ImageWriter("base render test 1600X1000 small", WIDTH, HEIGHT, NX, NY);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.printGrid(100, java.awt.Color.YELLOW);
        render.writeToImage();
    }
    /**
     * Produce a scene with basic 3D model and render it into a jpeg image with a
     * grid
     */
    @Test
    public void basicRenderTwoColorTest4() {
        final double WIDTH = 800d;
        final double HEIGHT = 500d;
        final int NX =1600;
        final int NY =1000;

        Scene scene;
        scene= new Scene.SceneBuilder("Test scene 1600x1000 small2")
                .addAmbientLight(new AmbientLight(new Color(255, 255, 255), 1))
                .addCamera(new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0)))
                .addDistance(100)
                .addBackground(new  Color(255, 255, 0))
                .build();

        scene.addGeometries(new Sphere(20, new Point3D(0, 0, 100)));
//
        scene.addGeometries(
                new Triangle(new Color(0,0,0),new Point3D(100, 0, 100), new Point3D(0, 100, 100), new Point3D(100, 100, 100)),
                new Triangle(new Color(255,0,255),new Point3D(100, 0, 100), new Point3D(0, -100, 100), new Point3D(100, -100, 100)),
                new Triangle(new Color(0,0,255),new Point3D(-100, 0, 100), new Point3D(0, 100, 100), new Point3D(-100, 100, 100)),
                new Triangle(new Color(0,255,0),new Point3D(-100, 0, 100), new Point3D(0, -100, 100), new Point3D(-100, -100, 100)));

        ImageWriter imageWriter = new ImageWriter("base render test 1600X1000 small2", WIDTH, HEIGHT, NX, NY);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.printGrid(100, java.awt.Color.BLUE);
        render.writeToImage();
    }
    /**
     * Produce a scene with basic 3D model and render it into a jpeg image with a
     * grid
     */
    @Test
    public void basicRenderTwoColorTest5() {
        final double WIDTH = 800d;
        final double HEIGHT = 500d;
        final int NX =1600;
        final int NY =1000;

        Scene scene;
        scene= new Scene.SceneBuilder("Test scene 1600x1000 Israel flag")
                .addAmbientLight(new AmbientLight(new Color(0, 0, 255), 1))
                .addCamera(new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0)))
                .addDistance(100)
                .addBackground(new  Color(255, 255, 255))
                .build();

      //  scene.addGeometries(new Sphere(20, new Point3D(0, 0, 100)));
//
        scene.addGeometries(
                new Triangle(new Color(255,0,0),new Point3D(-150, 100, 100), new Point3D(150, 100, 100), new Point3D(0, -150, 100)),
                new Triangle(new Color(0,0,0),new Point3D(-150, -100, 100), new Point3D(150, -100, 100), new Point3D(0, 150, 100)));

        ImageWriter imageWriter = new ImageWriter("base render test 1600X1000 Israel Flag", WIDTH, HEIGHT, NX, NY);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.printGrid(100, java.awt.Color.BLUE);
        render.writeToImage();
    }



    @Test
    public void basicRenderTwoColorTest6() {
        final double WIDTH = 800d;
        final double HEIGHT = 500d;
        final int NX =1600;
        final int NY =1000;

        Scene scene;
        scene= new Scene.SceneBuilder("Test scene 1600x1000 testcase")
                .addAmbientLight(new AmbientLight(new Color(0, 0, 0), 1))
                .addCamera(new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0)))
                .addDistance(100)
                .addBackground(new  Color(0, 0, 0))
                .build();

        scene.addGeometries(new Sphere(new Color(0,153,153),50, new Point3D(0, 0, 100)));
//
        scene.addGeometries(
                new Triangle(new Color(0,153,153),new Point3D(100, 0, 100), new Point3D(0, 100, 100), new Point3D(100, 100, 100)),
                new Triangle(new Color(255,0,255),new Point3D(100, 0, 100), new Point3D(0, -100, 100), new Point3D(100, -100, 100)),
                new Triangle(new Color(0,0,255),new Point3D(-100, 0, 100), new Point3D(0, 100, 100), new Point3D(-100, 100, 100)),
                new Triangle(new Color(120,0,0),new Point3D(-100, 0, 100), new Point3D(0, -100, 100), new Point3D(-100, -100, 100)));

        ImageWriter imageWriter = new ImageWriter("base render test 1600X1000 testcase", WIDTH, HEIGHT, NX, NY);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.printGrid(100, java.awt.Color.BLUE);
        render.writeToImage();
    }
    /**
     * Produce a scene with basic 3D model and render it into a jpeg image with a
     * grid
     */
    @Test
    public void basicRenderTwoColorTest() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(100);
        scene.setBackground(new Color(75, 127, 90));
        scene.setAmbientLight(new AmbientLight(new Color(255, 191, 191), 1));

        scene.addGeometries(new Sphere(50, new Point3D(0, 0, 100)));

        scene.addGeometries(
                new Triangle(new Point3D(100, 0, 100), new Point3D(0, 100, 100), new Point3D(100, 100, 100)),
                new Triangle(new Point3D(100, 0, 100), new Point3D(0, -100, 100), new Point3D(100, -100, 100)),
                new Triangle(new Point3D(-100, 0, 100), new Point3D(0, 100, 100), new Point3D(-100, 100, 100)),
                new Triangle(new Point3D(-100, 0, 100), new Point3D(0, -100, 100), new Point3D(-100, -100, 100)));

        ImageWriter imageWriter = new ImageWriter("base render test", 500, 500, 500, 500);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.printGrid(50, java.awt.Color.YELLOW);
        render.writeToImage();
    }

    @Test
    public void basicRenderMultiColorTest() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(100);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.2));

        scene.addGeometries(new Sphere(50, new Point3D(0, 0, 100)));

        scene.addGeometries(
                new Triangle(new Color(java.awt.Color.BLUE),
                        new Point3D(100, 0, 100), new Point3D(0, 100, 100), new Point3D(100, 100, 100)),      // lower right
                new Triangle(
                        new Point3D(100, 0, 100), new Point3D(0, -100, 100), new Point3D(100, -100, 100)),    // upper right
                new Triangle(new Color(java.awt.Color.RED),
                        new Point3D(-100, 0, 100), new Point3D(0, 100, 100), new Point3D(-100, 100, 100)),    // lower left
                new Triangle(new Color(java.awt.Color.GREEN),
                        new Point3D(-100, 0, 100), new Point3D(0, -100, 100), new Point3D(-100, -100, 100))); // upper left

        ImageWriter imageWriter = new ImageWriter("color render test", 500, 500, 500, 500);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.printGrid(50, java.awt.Color.WHITE);
        render.writeToImage();
    }

}