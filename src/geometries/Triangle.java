package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.isZero;


public class Triangle extends Polygon {

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Triangle)) return false;

        Triangle tr = (Triangle) obj;

        // LO Mushlam
        return _vertices.get(0).equals(tr._vertices.get(0)) &&
                _vertices.get(1).equals(tr._vertices.get(1)) &&
                _vertices.get(2).equals(tr._vertices.get(2));
    }


    @Override
    public String toString() {
        String result = "";
        for (Point3D p : _vertices ) {
            result += p.toString();
        }
        return  result;
    }

    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        super(p1, p2, p3);
    }

    public Vector getNormal() {
        return super.getNormal(super._plane._p);
    }


    @Override

    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> intersections = _plane.findIntersections(ray);
        if (intersections == null) return null;

        Point3D p0 = ray.getPoint();
        Vector v = ray.getDirection();

        Vector v1 = _vertices.get(0).subtract(p0);
        Vector v2 = _vertices.get(1).subtract(p0);
        Vector v3 = _vertices.get(2).subtract(p0);

        double s1 = v.dotProduct(v1.crossProduct(v2));
        if (isZero(s1)) return null;
        double s2 = v.dotProduct(v2.crossProduct(v3));
        if (isZero(s2)) return null;
        double s3 = v.dotProduct(v3.crossProduct(v1));
        if (isZero(s3)) return null;

        return ((s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0)) ? intersections : null;

    }
}