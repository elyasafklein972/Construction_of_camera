package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Sphere extends RadialGeometry {

    private Point3D _center;
    public Sphere(Point3D _center,double _radius)
    {
        super(_radius);
        this._center=_center;
    }

    public Point3D get_center() {
        return _center;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "_center=" + _center +
                "_radius" + super.get_radius()+
                '}';
    }

    @Override
    public Vector getNormal(Point3D p) {
        return null; //new Vector(p).normalize();
    }
}
