package elements;

import primitives.*;

public class Camera{
     private Point3D _p0;
     private Vector _vUp;
     private Vector _vTo;
     private Vector _vRight;

    public Camera(Point3D _p0, Vector _vUp, Vector _vTo) {
        this._p0 = _p0;
        this._vUp = _vUp.normalize();
        this._vTo = _vTo.normalize();
        try {
           if (_vUp.dotProduct(_vTo)!=0)
           throw new IllegalArgumentException("Vector _Up and Vector _To they not vertical");
            this._vRight = _vTo.crossProduct(_vUp).normalize();
        }
        catch (IllegalArgumentException e){
            System.out.println(e);
        }

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






}
