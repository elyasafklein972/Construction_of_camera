package geometries;

import elements.Material;
import primitives.*;

import java.util.List;
import java.util.Objects;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class  Sphere extends RadialGeometry {
    /**
     * The center of the sphere
     */
    private final Point3D _center;

    /**
     * constructor for a new sphere object.
     *
     * @param radius the radius of the sphere
     * @param center the center point of the sphere
     *
     * @throws Exception in case of negative or zero radius from RadialGeometry constructor
     */

    public Sphere(Color emissionLight, Material material, double radius, Point3D center) {
        super(emissionLight,radius);
        this._material = material;
        this._center = new Point3D(center);
    }
    public Sphere(Color emissionLight, double radius, Point3D center) {
        this(emissionLight,new Material(0,0,0),radius,center);
    }

    public Sphere(double radius, Point3D center) {
        this(Color.BLACK,new Material(0,0,0),radius,center);
    }

    @Override
    public String toString() {
        return String.format
                ("point: " + _center + ", radius: " + _radius);
    }

    /**
     * getter for the center property
     *
     * @return the center of the sphere
     */
    public Point3D getCenter() {
        return new Point3D(_center);
    }


    /**
     * get the normal to this sphere in a given point
     */
    @Override
    public Vector getNormal(Point3D point) {
        Vector normal = point.subtract(_center);
        return normal.normalize();
    }

    @Override
    public List<GeoPoint> findIntersections(Ray ray, double maxDistance) {
        Point3D p0 = ray.getPoint();
        Vector v = ray.getDirection();
        Vector u;
        try {
            u = _center.subtract(p0);   // p0 == _center
        } catch (IllegalArgumentException e) {
            return List.of(new GeoPoint(this, (ray.getPoint(this._radius))));
        }
        double tm = alignZero(v.dotProduct(u));
        double dSquared = (tm == 0) ? u.lengthSquared() : u.lengthSquared() - tm * tm;
        double thSquared = alignZero(this._radius * this._radius - dSquared);

        if (thSquared <= 0) return null;

        double th = alignZero(Math.sqrt(thSquared));
        if (th == 0) return null;

        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);

        double t1dist = alignZero(maxDistance - t1);
        double t2dist = alignZero(maxDistance - t2);

        if (t1 <= 0 && t2 <= 0) {
            return null;
        }

        if (t1 > 0 && t2 > 0) {
            if (t1dist > 0 && t2dist > 0) {
                return List.of(
                        new GeoPoint(this, (ray.getPoint(t1))),
                        new GeoPoint(this, (ray.getPoint(t2)))); //P1 , P2
            } else if (t1dist > 0) {
                return List.of(
                        new GeoPoint(this, (ray.getPoint(t1))));
            } else if (t2dist > 0) {
                return List.of(
                        new GeoPoint(this, (ray.getPoint(t2))));
            }
        }

        if ((t1 > 0) && (t1dist > 0))
            return List.of(new GeoPoint(this, (ray.getPoint(t1))));
        else if ((t2 > 0) && (t2dist > 0))
            return List.of(new GeoPoint(this, (ray.getPoint(t2))));
        return null;
    }
}