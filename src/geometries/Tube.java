package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.isZero;

/**
 * Represents an infinite tube in the 3D space.
 * That is, the cylinder does not have a length.
 */

public class Tube extends RadialGeometry {
    /**
     *  represents the direction and the reference point
     */
    protected final Ray _ray;

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
     *
     * @return ray
     */
    public Ray get_ray() {
        return new Ray(_ray);
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (obj == null || !(obj instanceof Tube))
//            return false;
//        if (this == obj)
//            return true;
//        Tube other = (Tube) obj;
//
//        //the two vectors needs to be in the same direction,
//        //but not necessary to have the same length.
//        try {
//            Vector v = _ray.getDirection().crossProduct(other._ray.getDirection());
//        } catch (IllegalArgumentException ex) {
//            return (Util.isZero(this._radius - other._radius) && _ray.getPoint().equals((_ray.getPoint())));
//        }
//        throw new IllegalArgumentException("direction cross product with parameter.direction == Vector(0,0,0)");
//    }

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
        Point3D o = _ray.getPoint();
        Vector v = _ray.getDirection();

        Vector vector1 = point.subtract(o);

        //We need the projection to multiply the _direction unit vector
        double projection = vector1.dotProduct(v);
        if(!isZero(projection))
        {
            // projection of P-O on the ray:
            o.add(v.scale(projection));
        }

        //This vector is orthogonal to the _direction vector.
        Vector check = point.subtract(o);
        return check.normalize();
    }



    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }


}
 /*
    @Override
    public Vector getNormal(Point3D point) {
        //The vector from the point of the tube to the given point
        Vector vector1 = new Vector(point.subtract(this._ray.getPoint()));

        //We need the projection to multiply the _direction unit vector
        double projection = vector1.dotProduct(this._ray.getDirection());
    }

        Vector vector2 =this._ray.getDirection().scale(projection);

        //This vector is orthogonal to the _direction vector.
        Vector check = vector1.subtract(vector2);
        return check.normalized();
*/
   /*
    /**
     * Function for finding intersections points with an infinite
     * tube.
     * @param ray The ray that we check if it intersects the tube.
     * @return A list of intersection points, if any.
     */
   /*
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> toReturn = new ArrayList<Point3D>();

        Point3D P = ray.getPoint();

        Vector V = ray.getDirection(),
                Va = this._ray.getDirection(),//_direction,
                DeltaP = new Vector(P.subtract(this._ray.getPoint())),
                temp_for_use1, temp_for_use2;

        double V_dot_Va = V.dotProduct(Va),
                DeltaP_dot_Va = DeltaP.dotProduct(Va);

        temp_for_use1 = V.subtract(Va.scale(V_dot_Va));
        temp_for_use2 = DeltaP.subtract(Va.scale(DeltaP_dot_Va));

        double A = temp_for_use1.dotProduct(temp_for_use1);
        double B = 2*V.subtract(Va.scale(V_dot_Va)).dotProduct(DeltaP.subtract(Va.scale(DeltaP_dot_Va)));
        double C = temp_for_use2.dotProduct(temp_for_use2) - _radius * _radius;
        double desc = calcs.subtract(B*B, 4*A*C);

        if (desc < 0) {//No solution
            return toReturn;
        }

        double t1 = (-B+Math.sqrt(desc))/(2*A),
                t2 = (-B-Math.sqrt(desc))/(2*A);

        if (desc == 0) {//One solution
            if (-B/(2*A) < 0)
                return toReturn;
            toReturn.add(new Vector(P.add(V.scale(-B/(2*A)).getHead())).getHead());
            return toReturn;
        }
        else if (t1 < 0 && t2 < 0){
            return toReturn;
        }
        else if (t1 < 0 && t2 > 0) {
            toReturn.add(new Vector(P.add(V.scale(t2).getHead())).getHead());
            return toReturn;
        }
        else if (t1 > 0 && t2 < 0) {
            toReturn.add(new Vector(P.add(V.scale(t1).getHead())).getHead());
            return toReturn;
        }
        else {
            toReturn.add(new Vector(P.add(V.scale(t1).getHead())).getHead());
            toReturn.add(new Vector(P.add(V.scale(t2).getHead())).getHead());
            return toReturn;
        }
    }