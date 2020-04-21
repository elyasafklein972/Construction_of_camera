package elements;

import primitives.*;

import static primitives.Util.isZero;

public class Camera{
private Point3D _p0;
private Vector _vUp;
private Vector _vTo;
private Vector _vRight;


    public Camera(Point3D _p0, Vector _vTo, Vector _vUp) {

        //if the the vectors are not orthogonal, throw exception.
        if (_vUp.dotProduct(_vTo) != 0)
            throw new IllegalArgumentException("the vectors must be orthogonal");

        this._p0 =  new Point3D(_p0);

        this._vTo = new Vector( _vTo.normalized());
        this._vUp = new Vector(_vUp.normalized());

        _vRight = this._vTo.crossProduct(this._vUp.normalize());

    }

public Point3D get_p0() {
        return _p0;
        }

public Vector get_vUp() {
        return _vUp;
        }

public Vector get_vTo() {
        return _vTo;
        }

public Vector get_vRight() {
        return _vRight;
        }

    // ***************** Operations ******************** //
    /*************************************************
     * FUNCTION
     * constructRayThroughPixel
     * PARAMETERS
     * int Nx, int Ny, // Screen size
     * double x, double y, // Point
     * double screenDist, double screenWidth, double screenHeight
     * RETURN VALUE
     * Ray
     * MEANING
     * This function sends ray through pixel into the view plane
     **************************************************/
public Ray constructRayThroughPixel (int nX, int nY, int j, int i, double screenDistance, double screenWidth, double screenHeight)

{
    if (isZero(screenDistance))
    {
        throw new IllegalArgumentException("distance cannot be 0");
    }

    Point3D Pc = _p0.add(_vTo.scale(screenDistance));

    double Ry = screenHeight/nY;
    double Rx = screenWidth/nX;

    double yi =  ((i - nY/2d)*Ry + Ry/2d);
    double xj=   ((j - nX/2d)*Rx + Rx/2d);

    Point3D Pij = Pc;

    if (! isZero(xj))
    {
        Pij = Pij.add(_vRight.scale(xj));
    }
    if (! isZero(yi))
    {
        Pij = Pij.subtract(_vUp.scale(yi));
    }

    Vector Vij = Pij.subtract(_p0);

    return new Ray(_p0,Vij);

}






}
