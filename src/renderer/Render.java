package renderer;

import elements.*;
import geometries.Box;
import geometries.FlatGeometry;
import geometries.Geometry;
import geometries.Intersectable;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static geometries.Intersectable.GeoPoint;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 *
 */
public class Render {
    private Scene scene;
    private ImageWriter imageWriter;
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static  final int MAX_RAYS = 6;
    private final ImageWriter _imageWriter;
    private final Scene _scene;

 //   private double _supersamplingRate;

    public Render(ImageWriter imageWriter, Scene scene) {
        this._imageWriter = imageWriter;
        this._scene = scene;
   //     this._supersamplingRate = 0d;
    }
    // ...........
    private int _threads = 1;
    private final int SPARE_THREADS = 2;
    private boolean _print = false;

    /**
     * Pixel is an internal helper class whose objects are associated with a Render object that
     * they are generated in scope of. It is used for multithreading in the Renderer and for follow up
     * its progress.<br/>
     * There is a main follow up object and several secondary objects - one in each thread.
     * @author Dan
     *
     */
    private class Pixel {
        private long _maxRows = 0;
        private long _maxCols = 0;
        private long _pixels = 0;
        public volatile int row = 0;
        public volatile int col = -1;
        private long _counter = 0;
        private int _percents = 0;
        private long _nextCounter = 0;

        /**
         * The constructor for initializing the main follow up Pixel object
         * @param maxRows the amount of pixel rows
         * @param maxCols the amount of pixel columns
         */
        public Pixel(int maxRows, int maxCols) {
            _maxRows = maxRows;
            _maxCols = maxCols;
            _pixels = maxRows * maxCols;
            _nextCounter = _pixels / 100;
            if (Render.this._print) System.out.printf("\r %02d%%", _percents);
        }

        /**
         *  Default constructor for secondary Pixel objects
         */
        public Pixel() {}

        /**
         * Internal function for thread-safe manipulating of main follow up Pixel object - this function is
         * critical section for all the threads, and main Pixel object data is the shared data of this critical
         * section.<br/>
         * The function provides next pixel number each call.
         * @param target target secondary Pixel object to copy the row/column of the next pixel
         * @return the progress percentage for follow up: if it is 0 - nothing to print, if it is -1 - the task is
         * finished, any other value - the progress percentage (only when it changes)
         */
        private synchronized int nextP(Pixel target) {
            ++col;
            ++_counter;
            if (col < _maxCols) {
                target.row = this.row;
                target.col = this.col;
                if (_counter == _nextCounter) {
                    ++_percents;
                    _nextCounter = _pixels * (_percents + 1) / 100;
                    return _percents;
                }
                return 0;
            }
            ++row;
            if (row < _maxRows) {
                col = 0;
                if (_counter == _nextCounter) {
                    ++_percents;
                    _nextCounter = _pixels * (_percents + 1) / 100;
                    return _percents;
                }
                return 0;
            }
            return -1;
        }

        /**
         * Public function for getting next pixel number into secondary Pixel object.
         * The function prints also progress percentage in the console window.
         * @param target target secondary Pixel object to copy the row/column of the next pixel
         * @return true if the work still in progress, -1 if it's done
         */
        public boolean nextPixel(Pixel target) {
            int percents = nextP(target);
            if (percents > 0)
                if (Render.this._print) System.out.printf("\r %02d%%", percents);
            if (percents >= 0)
                return true;
            if (Render.this._print) System.out.printf("\r %02d%%", 100);
            return false;
        }
    }





    /**
     * This function renders image's pixel color map from the scene included with
     * the Renderer object
     */
    public void renderImage() {
        final int nX = _imageWriter.getNx();
        final int nY = _imageWriter.getNy();
        final double dist = _scene.getDistance();
        final double width = _imageWriter.getWidth();
        final double height = _imageWriter.getHeight();
        final Camera camera = _scene.getCamera();
        java.awt.Color background = _scene.getBackground().getColor();
        final Pixel thePixel = new Pixel(nY, nX);

        // Generate threads
        Thread[] threads = new Thread[_threads];
        for (int i = _threads - 1; i >= 0; --i) {
            threads[i] = new Thread(() -> {
                Pixel pixel = new Pixel();
                while (thePixel.nextPixel(pixel)) {
                    boolean flag = false;
                    Ray rays = camera.constructRayThroughPixel(nX, nY, pixel.col, pixel.row, dist, width, height);
                    if(_scene.get_box().size()!=0)
                    {
                        for (Box j : _scene.get_box()) {

                            if (j.isIntersectionWithBox(rays)) {
                                    flag = true;
                            }
                        }
                    }
                    else {
                        flag=true;
                    }
                        if(flag)
                        {
                            GeoPoint closestPoint = findClosestIntersection(rays);
                            if (closestPoint == null) {
                                _imageWriter.writePixel(pixel.col, pixel.row, background);
                            } else {
                                _imageWriter.writePixel(pixel.col, pixel.row, calcColor(closestPoint, rays).getColor());
                            }
                        }
                    }
                }

                );
        }

        // Start threads
        for (Thread thread : threads) thread.start();

        // Wait for all threads to finish
        for (Thread thread : threads) try { thread.join(); } catch (Exception e) {}
        if (_print) System.out.printf("\r100%%\n");
    }



    /**
     * Set multithreading <br>
     * - if the parameter is 0 - number of coress less 2 is taken
     *
     * @param threads number of threads
     * @return the Render object itself
     */
    public Render setMultithreading(int threads) {
        if (threads < 0)
            throw new IllegalArgumentException("Multithreading patameter must be 0 or higher");
        if (threads != 0)
            _threads = threads;
        else {
            int cores = Runtime.getRuntime().availableProcessors() - SPARE_THREADS;
            if (cores <= 2)
                _threads = 1;
            else
                _threads = cores;
        }
        return this;
    }

    /**
     * Set debug printing on
     *
     * @return the Render object itself
     */
    public Render setDebugPrint() {
        _print = true;
        return this;
    }
    //public double getSupersamplingRate() {return _supersamplingRate; }

   // public void setSupersamplingRate(double supersamplingRate) {
     //   _supersamplingRate = supersamplingRate;
//    }

    public void printGrid(int interval, java.awt.Color color) {
        int Nx = _imageWriter.getNx();
        int Ny = _imageWriter.getNy();
        for (int i = 0; i < Ny; i++) {
            for (int j = 0; j < Nx; j++) {
                if (i % interval == 0 || j % interval == 0) {
                    _imageWriter.writePixel(j, i, color);
                }
            }
        }
    }

    public void writeToImage() {
        _imageWriter.writeToImage();
    }



    private GeoPoint getClosestPoint(List<GeoPoint> intersectionPoints) {

        if (intersectionPoints == null) {
            return null;


        }

        GeoPoint result = null;

        Point3D p0 = _scene.getCamera().getP0();
        double minDist = Double.MAX_VALUE;
        double currentDistance;

        for (GeoPoint geoPoint : intersectionPoints) {
            currentDistance = p0.distance(geoPoint.getPoint());
            if (currentDistance < minDist) {
                minDist = currentDistance;
                result = geoPoint;
            }
        }
        return result;
    }

    /**
     * Find intersections of a ray with the scene geometries and get the
     * intersection point that is closest to the ray head. If there are no
     * intersections, null will be returned.
     *
     * @param ray intersecting the scene
     * @return the closest point
     */
    private GeoPoint findClosestIntersection(Ray ray) {

        if (ray == null) {
            return null;
        }

        GeoPoint closestPoint = null;
        double closestDistance = Double.MAX_VALUE;
        Point3D ray_p0 = ray.getPoint();

    //    Box box=new Box(_scene.getGeometries());//make new box from geometry
      //  if (box.contains(ray)) {//check the box
            List<GeoPoint> intersections = _scene.getGeometries().findIntersections(ray);
            if (intersections == null)
                return null;

            for (GeoPoint geoPoint : intersections) {
                double distance = ray_p0.distance(geoPoint.getPoint());
                if (distance < closestDistance) {
                    closestDistance = distance;
                    closestPoint = geoPoint;
                }
            }
            return closestPoint;
        }
    //   return null;
   // }

    private Color calcColor(GeoPoint geoPoint, Ray inRay) {
        Color color = calcColor(geoPoint, inRay, MAX_CALC_COLOR_LEVEL,  1.0);
       // color = color.add(_scene.getAmbientLight().getIntensity());
        return color;
    }

    private Color calcColor(GeoPoint geoPoint, Ray inRay, int level, double k) {
        if (level == 1) {
            return Color.BLACK;
        }

        Point3D pointGeo = geoPoint.getPoint();
        Geometry geometryGeo = geoPoint.getGeometry();
        Color color = geometryGeo.getEmissionLight();

        Material material = geometryGeo.getMaterial();
        int nShininess = material.getnShininess();
        double kd = material.getkD();
        double ks = material.getkS();

        Vector v = pointGeo.subtract(_scene.getCamera().getP0()).normalize();
        Vector n = geometryGeo.getNormal(pointGeo);

//        double nv = alignZero(n.dotProduct(v));
//        if (nv == 0) {
//            //ray parallel to geometry surface ??
//            //and orthogonal to normal
//            return Color.BLACK;
//        }

        color = getColorLightSources(geoPoint, k, color, v, n, nShininess, kd, ks);

        double kr = geometryGeo.getMaterial().getKr();
        double kkr = k * kr;

        if (kkr > MIN_CALC_COLOR_K) {
            Ray reflectedRay = constructReflectedRay(pointGeo, inRay, n);
            GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
            if (reflectedPoint != null) {
                color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
            }
        }
        return color;
    }
//        double kt = geometryGeo.getMaterial().getKt();
//        double kkt = k * kt;
//
//        if (kkt > MIN_CALC_COLOR_K) {
//            Ray refractedRay = constructRefractedRay(pointGeo, inRay, n);
//            GeoPoint refractedPoint = findClosestIntersection(refractedRay);
//
//            if (refractedPoint != null) {
//                color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
//            }
//        }

    private Color getColorLightSources(GeoPoint geoPoint, double k, Color color,
                                       Vector v, Vector n, int nShininess,
                                       double kd, double ks)
    {
        Point3D pointGeo = geoPoint.getPoint();
        if (_scene.getLightSources() != null) {
            for (LightSource lightSource : _scene.getLightSources()) {


                if (lightSource.getRadius() == 0) {
                    Vector l = lightSource.getL(pointGeo);

                    double nl = n.dotProduct(l);
                    double nv = n.dotProduct(v);
                    double ktr;
                    if (nl * nv > 0) {
//                if (unshaded(lightSource, l, n, geoPoint)) {
                        //                  ktr = 1d;
                        //             } else {
                        //                 ktr = 0d;
                        //               }
                        ktr = transparency(lightSource, l, n, geoPoint);
                        if (ktr * k > MIN_CALC_COLOR_K) {
                            Color lightIntensity = lightSource.getIntensity(pointGeo).scale(ktr);
                            color = color.add(
                                    calcDiffusive(kd, nl, lightIntensity),
                                    calcSpecular(ks, l, n, nl, v, nShininess, lightIntensity));
                        }
                    }
                    return color;
                }
                else {
                    double ktr=0.0;
                    double sizeVecList=0.0;


                    double nv=0.0;

                    for (LightSource lightSource2 : _scene.getLightSources())
                        for (Vector vec : lightSource.getLs(pointGeo)) {
                            sizeVecList += 1;
                          double  nl = n.dotProduct(vec);

                            nv = n.dotProduct(v);

                            if (nl * nv > 0) {
//                if (unshaded(lightSource, l, n, geoPoint)) {
                                //                  ktr = 1d;
                                //             } else {
                                //                 ktr = 0d;
                                //               }
                                ktr += transparency(lightSource, vec, n, geoPoint);
                            }
                        }


                    ktr = ktr / sizeVecList;
                    Vector l = lightSource.getL(pointGeo);
                    double nl = n.dotProduct(l);
                    // double nl = n.dotProduct(l);
                    if (ktr * k > MIN_CALC_COLOR_K) {
                        Color lightIntensity = lightSource.getIntensity(pointGeo).scale(ktr);
                        color = color.add(
                                calcDiffusive(kd, nl, lightIntensity),
                                calcSpecular(ks, l, n, nl, v, nShininess, lightIntensity));
                    }
                }
                // return color;
            }
            return color;

        }
        return Color.BLACK;
    }

    private Ray constructRefractedRay(Point3D pointGeo, Ray inRay, Vector n) {
        return new Ray(pointGeo, inRay.getDirection(), n);
    }

    private Ray constructReflectedRay(Point3D pointGeo, Ray inRay, Vector n) {
        //𝒓=𝒗 −𝟐∙(𝒗∙𝒏)∙𝒏
        Vector v = inRay.getDirection();
        double vn = v.dotProduct(n);

        if (vn == 0) {
            return null;
        }

        Vector r = v.subtract(n.scale(2 * vn));
        return new Ray(pointGeo, r, n);
    }

    /**
     * Calculate Specular component of light reflection.
     *
     * @param ks         specular component coef
     * @param l          direction from light to point
     * @param n          normal to surface at the point
     * @param nl         dot-product n*l
     * @param V          direction from point of view to point
     * @param nShininess shininess level
     * @param ip         light intensity at the point
     * @return specular component light effect at the point
     * @author Dan Zilberstein (slightly modified by me)
     * <p>
     * Finally, the Phong model has a provision for a highlight, or specular, component, which reflects light in a
     * shiny way. This is defined by [rs,gs,bs](-V.R)^p, where R is the mirror reflection direction vector we discussed
     * in class (and also used for ray tracing), and where p is a specular power. The higher the value of p, the shinier
     * the surface.
     * </p>
     */
    private Color calcSpecular(double ks, Vector l, Vector n, double nl, Vector V, int nShininess, Color ip) {
        double p = nShininess;
        if (isZero(nl)) {
            throw new IllegalArgumentException("nl cannot be Zero for scaling the normal vector");
        }
        Vector R = l.add(n.scale(-2 * nl)); // nl must not be zero!
        double VR = alignZero(V.dotProduct(R));
        if (VR >= 0) {
            return Color.BLACK; // view from direction opposite to r vector
        }
        // [rs,gs,bs]ks(-V.R)^p
        return ip.scale(ks * Math.pow(-1d * VR, p));
    }

    /**
     * Calculate Diffusive component of light reflection.
     *
     * @param kd diffusive component coef
     * @param nl dot-product n*l
     * @param ip light intensity at the point
     * @return diffusive component of light reflection
     * @author Dan Zilberstein
     * <p>
     * The diffuse component is that dot product n•L. It approximates light, originally from light source L,
     * reflecting from a surface which is diffuse, or non-glossy. One example of a non-glossysurface is paper.
     * In general, you'll also want this to have a non-gray color value,
     * so this term would in general be a color defined as: [rd,gd,bd](n•L)
     * </p>
     */
    private Color calcDiffusive(double kd, double nl, Color ip) {
        return ip.scale(Math.abs(nl) * kd);
    }

    private boolean sign(double val) {
        return (val > 0d);
    }

    private boolean unshaded_0(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geopoint.getPoint(), lightDirection, n);
        Point3D pointGeo = geopoint.getPoint();

        List<GeoPoint> intersections = _scene.getGeometries().findIntersections(lightRay);
        if (intersections == null) {
            return true;
        }
        // Flat geometry cannot self intersect
        if (geopoint.getGeometry() instanceof FlatGeometry) {
            intersections.remove(geopoint);
        }

        double lightDistance = light.getDistance(pointGeo);
        for (GeoPoint gp : intersections) {
            double temp = gp.getPoint().distance(pointGeo) - lightDistance;
            if (alignZero(temp) <= 0)
                return false;
        }
        return true;
    }

    private boolean occluded(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
        Point3D geometryPoint = geopoint.getPoint();
        Vector lightDirection = light.getL(geometryPoint);
        lightDirection.scale(-1);

        Vector epsVector = geopoint.getGeometry().getNormal(geometryPoint);
        epsVector.scale(epsVector.dotProduct(lightDirection) > 0 ? 2 : -2);
        geometryPoint.add(epsVector);
        Ray lightRay = new Ray(geometryPoint, lightDirection);
        List<GeoPoint> intersections = _scene.getGeometries().findIntersections(lightRay);

        // Flat geometry cannot self intersect
        if (geopoint.getGeometry() instanceof FlatGeometry) {
            intersections.remove(geopoint);
        }

        for (GeoPoint entry : intersections)
            if (entry.getGeometry().getMaterial().getKt() == 0)
                return true;
        return false;
    }

    private double transparency(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1).normalize(); // from point to light source
        Ray lightRay = new Ray(geopoint.getPoint(), lightDirection, n);
        Point3D pointGeo = geopoint.getPoint();

        List<GeoPoint> intersections = _scene.getGeometries().findIntersections(lightRay);
        if (intersections == null) {
            return 1d;
        }
        double lightDistance = light.getDistance(pointGeo);
        double ktr = 1d;
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.getPoint().distance(pointGeo) - lightDistance) <= 0) {
                ktr *= gp.getGeometry().getMaterial().getKt();
                if (ktr < MIN_CALC_COLOR_K) {
                    return 0.0;
                }
            }
        }
        return ktr;
    }

    private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geopoint.getPoint(), lightDirection, n);
        Point3D pointGeo = geopoint.getPoint();

        List<GeoPoint> intersections = _scene.getGeometries().findIntersections(lightRay);
        if (intersections == null) {
            return true;
        }
        double lightDistance = light.getDistance(pointGeo);
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.getPoint().distance(pointGeo) - lightDistance) <= 0
                    && gp.getGeometry().getMaterial().getKt() == 0) {
                return false;
            }
        }
        return true;
    }
    //this function improves ray tracing by making multiple rays through different parts of the pixel
    private List<Ray> getRaysThroughPixel(int x, int y){

        //the ratio of the screen dimensions to the number of pixels
        double Rx = imageWriter.getWidth() /imageWriter.getNx();
        double Ry = imageWriter.getHeight() / imageWriter.getNy();

        //store the rays in a list
        List<Ray> raysThroughPixel = new ArrayList<>();

        //i will go from -1/2 * the max to 1/2 max
        for (int i = (-1 * MAX_RAYS) / 2; i <= MAX_RAYS / 2; i++) {
            for (int j = (-1 * MAX_RAYS) / 2; j <= MAX_RAYS / 2; j++) {

                //calculating the coordinate of the offset rays
                double iOffSet = (i * Rx) / MAX_RAYS;
                double jOffSet = (j * Ry) / MAX_RAYS;
                //making a ray to that position and placing it in the list
                Ray ray = scene.getCamera().constructRayThroughPixel(imageWriter.getNx(), imageWriter.getNy(), x + iOffSet, y + jOffSet, scene.getDistance(), imageWriter.getWidth(), imageWriter.getHeight());
                raysThroughPixel.add(ray);
            }
        }

        return raysThroughPixel;
    }

    public Scene getScene() {
        return scene;
    }

    public ImageWriter getImageWriter() {
        return imageWriter;
    }

    public static int getMaxCalcColorLevel() {
        return MAX_CALC_COLOR_LEVEL;
    }

    public static double getMinCalcColorK() {
        return MIN_CALC_COLOR_K;
    }

    public static int getMaxRays() {
        return MAX_RAYS;
    }

    public ImageWriter get_imageWriter() {
        return _imageWriter;
    }

    public Scene get_scene() {
        return _scene;
    }

  //  public double get_supersamplingRate() {
    //    return _supersamplingRate;
   // }
}