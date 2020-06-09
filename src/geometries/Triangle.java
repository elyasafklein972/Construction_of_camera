package geometries;

import elements.Material;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.isZero;

public class Triangle extends Polygon {

    public Triangle(Color emissionLight, Material material, Point3D p1, Point3D p2, Point3D p3) {
        super(emissionLight,material,p1,p2,p3);
    }
    public Triangle(Color emissionLight, Point3D p1, Point3D p2, Point3D p3) {
        super(emissionLight,p1, p2, p3);
    }

    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        super(p1, p2, p3);
    }
    public Vector getNormal() {
        return super.getNormal(super._plane._p);
    }


    @Override
    public String toString() {
        String result = "";
        for (Point3D p : _vertices) {
            result += p.toString();
        }
        return result;
    }

    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        List<GeoPoint> planeIntersections = _plane.findIntersections(ray);
        if (planeIntersections == null) return null;

        Point3D p0 = ray.getPoint();
        Vector v = ray.getDirection();

        Vector v1 = _vertices.get(0).subtract(p0);
        Vector v2 = _vertices.get(1).subtract(p0);
        Vector v3 = _vertices.get(2).subtract(p0);

        double s1 = v.dotProduct(v1.crossProduct(v2).normalized());
        if (isZero(s1)) return null;
        double s2 = v.dotProduct(v2.crossProduct(v3).normalized());
        if (isZero(s2)) return null;
        double s3 = v.dotProduct(v3.crossProduct(v1).normalized());
        if (isZero(s3)) return null;

        if ((s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0)) {
            //for GeoPoint
            List<GeoPoint> result = new LinkedList<>();
            for (GeoPoint geo : planeIntersections) {
                result.add(new GeoPoint(this, geo.getPoint()));
            }
            return result;
        }

        return null;

    }}