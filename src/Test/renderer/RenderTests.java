package Test.renderer;


import elements.*;
import geometries.*;
import org.junit.jupiter.api.Test;
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

}