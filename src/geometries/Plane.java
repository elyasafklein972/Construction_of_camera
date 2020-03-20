package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Plane implements Geometry {

    Point3D _p;
    primitives.Vector _normal;

    public Plane(Point3D p1, Point3D p2, Point3D p3) {
        _p = new Point3D(p1);

        Vector U = new Vector(p1, p2);
        Vector V = new Vector(p1, p3);
        Vector N = U.crossProduct(V);
        N.normalize();

        _normal = N.scale(-1);

    }

    public Plane(Point3D _p, Vector _normal) {
        this._p = new Point3D(_p);
        this._normal = new Vector(_normal);
    }

    @Override
    public Vector getNormal(Point3D p) {
        return _normal;
    }

    //because polygon
    public Vector getNormal() {
        return getNormal(null);
    }

}
