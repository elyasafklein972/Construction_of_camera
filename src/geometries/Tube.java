package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

public class Tube extends RadialGeometry {
    /**
     *  represents the direction and the reference point
     */
    private final Ray _ray;

    /**
     * constructor for a new Cylinder object
     *
     * @param _radius the radius of the cylinder
     * @param _ray    the direction of the cylinder from a center point
     * @throws Exception in case of a negative radius
     */
    public Tube(double _radius, Ray _ray) {
        super(_radius);
        this._ray = new Ray(_ray);
    }

    /**
     * copy constructor for a tube object to be deep copied
     *
     * @param other the source parameter
     */
    public Tube(Tube other) {
        super(other);
        this._ray = new Ray(other._ray);
    }

    /**
     *
     * @return ray
     */
    public Ray get_ray() {
        return new Ray(_ray);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Tube))
            return false;
        if (this == obj)
            return true;
        Tube other = (Tube) obj;

        //the two vectors needs to be in the same direction,
        //but not necessary to have the same length.
        try {
            Vector v = _ray.getDirection().crossProduct(other._ray.getDirection());
        } catch (IllegalArgumentException ex) {
            return (Util.isZero(this._radius - other._radius) && _ray.getPoint().equals((_ray.getPoint())));
        }
        throw new IllegalArgumentException("direction cross product with parameter.direction == Vector(0,0,0)");
    }

    @Override
    public String toString() {
        return "ray: " + _ray +
                ", radius: " + _radius;
    }

    /**
     *
     * @param point point to calculate the normal
     * @return returns normal vector
     */
    @Override
    public Vector getNormal(Point3D point) {
        //The vector from the point of the cylinder to the given point
        Vector vector1 = point.subtract(_ray.getPoint());

        //We need the projection to multiply the _direction unit vector
        double projection = vector1.dotProduct(_ray.getDirection());

        Vector vector2 = _ray.getDirection().scale(projection);

        //This vector is orthogonal to the _direction vector.
        Vector check = vector1.subtract(vector2);
        return check.normalize();
    }
}
