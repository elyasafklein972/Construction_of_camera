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
        buildBox();

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

    private void buildBox()
    {
        Box b=new Box();
        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        double minZ = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        double maxY = Double.MIN_VALUE;
        double maxZ = Double.MIN_VALUE;

        for (Point3D p :  this._vertices)
        {

            if (p.get_x().get() < minX) {
                minX = p.get_x().get();
            }
            if (p.get_y().get() < minY) {
                minY = p.get_y().get();
            }
            if (p.get_z().get() < minZ) {
                minZ = p.get_z().get();
            }
            if (p.get_x().get() > maxX) {
                maxX = p.get_x().get();
            }
            if (p.get_y().get() > maxY) {
                maxY = p.get_y().get();
            }
            if (p.get_z().get() > maxZ) {
                maxZ = p.get_z().get();
            }
        }
        // Point3D center = new Point3D((maxX + minX) / 2, (maxY + minY) / 2, (maxZ + minZ) / 2);
        this.box._max_X=maxX;
        this.box._max_Y=maxY;
        this.box._max_Z=maxZ;
        this.box._min_X=minX;
        this.box._min_Y=minY;
        this.box._min_Z=minZ;


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
    public List<GeoPoint> getFindIntersections(Ray ray, double max) {
        List<GeoPoint> planeIntersections = _plane.findIntersections(ray,max);
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
                result.add(new GeoPoint(this, geo._point));
            }
            return result;
        }

        return null;

    }}