package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.isZero;

public class Camera {
    Point3D _p0;
    Vector _vTo;
    Vector _vUp;
    Vector _vRight;

    public Camera(Point3D _p0, Vector _vTo, Vector _vUp) {

        //if the the vectors are not orthogonal, throw exception.
        if (_vUp.dotProduct(_vTo) != 0)
            throw new IllegalArgumentException("the vectors must be orthogonal");

        this._p0 = new Point3D(_p0);
        this._vTo = _vTo.normalized();
        this._vUp = _vUp.normalized();

        _vRight = this._vTo.crossProduct(this._vUp).normalize();

    }


    public Point3D getP0() {
        return new Point3D(_p0);
    }

    public Vector getVTo() {
        return new Vector(_vTo);
    }

    public Vector getVUp() {
        return new Vector(_vUp);
    }

    public Vector getVRight() {
        return new Vector(_vRight);
    }

    public Ray constructRayThroughPixel(int nX, int nY,
                                        int j, int i, double screenDistance,
                                        double screenWidth, double screenHeight) {
        if (isZero(screenDistance)) {
            throw new IllegalArgumentException("distance cannot be 0");
        }

        Point3D Pc = _p0.add(_vTo.scale(screenDistance));

        double Ry = screenHeight / nY;
        double Rx = screenWidth / nX;

        double yi = ((i - nY / 2d) * Ry + Ry / 2d);
        double xj = ((j - nX / 2d) * Rx + Rx / 2d);

        Point3D Pij = Pc;

        if (!isZero(xj)) {
            Pij = Pij.add(_vRight.scale(xj));
        }
        if (!isZero(yi)) {
            Pij = Pij.subtract(_vUp.scale(yi)); // Pij.add(_vUp.scale(-yi))
        }

        Vector Vij = Pij.subtract(_p0);

        return new Ray(_p0, Vij);

    }
    public Ray constructRayThroughPixel(int nX, int nY,
                                        double j, double i, double screenDistance,
                                        double screenWidth, double screenHeight) {
        if (isZero(screenDistance)) {
            throw new IllegalArgumentException("distance cannot be 0");
        }

        Point3D Pc = _p0.add(_vTo.scale(screenDistance));

        double Ry = screenHeight / nY;
        double Rx = screenWidth / nX;

        double yi = ((i - nY / 2d) * Ry + Ry / 2d);
        double xj = ((j - nX / 2d) * Rx + Rx / 2d);

        Point3D Pij = Pc;

        if (!isZero(xj)) {
            Pij = Pij.add(_vRight.scale(xj));
        }
        if (!isZero(yi)) {
            Pij = Pij.subtract(_vUp.scale(yi)); // Pij.add(_vUp.scale(-yi))
        }

        Vector Vij = Pij.subtract(_p0);

        return new Ray(_p0, Vij);

    }
    public List<Ray> constructRayBeamThroughPixel(int nX, int nY,
                                                  int j, int i, double screenDistance,
                                                  double screenWidth, double screenHeight, double supersamplingRate) {
        //TODO implements the following code properly
        if (isZero(screenDistance)) {
            throw new IllegalArgumentException("distance cannot be 0");
        }

        LinkedList<Ray> rays = new LinkedList<>();
        Point3D Pc = _p0.add(_vTo.scale(screenDistance));

        double Ry = screenHeight / nY;
        double Rx = screenWidth / nX;

        double yi = ((i - nY / 2d) * Ry + Ry / 2d);
        double xj = ((j - nX / 2d) * Rx + Rx / 2d);

        Point3D Pij = Pc;

        for (int k = 0; k < Ry; k += supersamplingRate) {
            for (int l = 0; l < Rx; l += supersamplingRate) {
                if (!isZero(xj)) {
                    Pij = Pij.add(_vRight.scale(xj + k * Rx / 2));
                }
                if (!isZero(yi)) {
                    Pij = Pij.subtract(_vUp.scale(yi + l * Ry / 2)); // Pij.add(_vUp.scale(-yi))
                }

            }
        }


        Vector Vij = Pij.subtract(_p0);

        rays.add(new Ray(_p0, Vij));
        return rays;

    }

    public List<Ray> constructRaysBeamThroughPixel(int nX, int nY, double i, double j, double screenDist, double screenWidth, double screenHeight) {

        double Rx = screenWidth / nX;//the length of pixel in X axis
        double Ry = screenHeight / nY;////the length of pixel in Y axis

        double yi = ((i - nY / 2d) * Ry + Ry / 2d);
        double xj = ((j - nX / 2d) * Rx + Rx / 2d);


        Point3D Pc = new Point3D(_p0.add(_vTo.scale(screenDist)));//new point from the camera to the screen

        //pc is the center of the view plane
        Point3D P = Pc.add(_vRight.scale(xj).subtract(_vUp.scale(yi)));

        //finding the intersection point with the view plane according the formula in the moodle

        //-----SuperSampling-----
        List<Ray> res = new LinkedList<>();//the return list, construct Rays Through Pixels

        res.add(new Ray(P, _p0.subtract(P)));//the first ray(we already find it)
        //the next 8 rays we gonna add is the same thing, but there's difference in the variation on
        // x and y arguments, some times we will change one of them and some times both of them

        // x coord middle of pixel/2 downwards
        Point3D tmp = new Point3D(P.get_x().get() - Rx / 2, P.get_y().get(), P.get_z().get());
        res.add(new Ray(tmp, new Vector(_p0.subtract(tmp)).normalized()));

        // y coord middle of pixel/2 downward
        tmp = new Point3D(P.get_x().get(), P.get_y().get() - Ry / 2, P.get_z().get());
        res.add(new Ray(tmp, new Vector(_p0.subtract(tmp)).normalized()));

        // x coord middle of pixel/2 upwards
        tmp = new Point3D(P.get_x().get() + Rx / 2, P.get_y().get(), P.get_z().get());
        res.add(new Ray(tmp, new Vector(_p0.subtract(tmp)).normalized()));

        // y coord middle of pixel/2 upward
        tmp = new Point3D(P.get_x().get(), P.get_y().get() + Ry / 2, P.get_z().get());
        res.add(new Ray(tmp, new Vector(_p0.subtract(tmp)).normalized()));

        // x coord middle of pixel/2 downwards  y coord middle of pixel/2 downward
        tmp = new Point3D(P.get_x().get() - Ry / 2, P.get_y().get() - Ry / 2, P.get_z().get());
        res.add(new Ray(tmp, new Vector(_p0.subtract(tmp)).normalized()));

        // x coord middle of pixel/2 upwards  y coord middle of pixel/2 downward
        tmp = new Point3D(P.get_x().get() + Ry / 2, P.get_y().get() - Ry / 2, P.get_z().get());
        res.add(new Ray(tmp, new Vector(_p0.subtract(tmp)).normalized()));

        // x coord middle of pixel/2 downwards    y coord middle of pixel/2 upward
        tmp = new Point3D(P.get_x().get() - Ry / 2, P.get_y().get() + Ry / 2, P.get_z().get());
        res.add(new Ray(tmp, new Vector(_p0.subtract(tmp)).normalized()));

        // x coord middle of pixel/2 upwards   y coord middle of pixel/2 upward
        tmp = new Point3D(P.get_x().get() + Ry / 2, P.get_y().get() + Ry / 2, P.get_z().get());
        res.add(new Ray(tmp, new Vector(_p0.subtract(tmp)).normalized()));

        return res;

    }
}
