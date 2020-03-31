package geometries;

import primitives.*;
import primitives.*;
import primitives.Vector;

public class Cylinder extends Tube  {
    private  double _height;
        public Cylinder(double _height,Ray _axisRay,double _radius){
            super(_radius,_axisRay);
            this._height=_height;
        }

    /**
     *
     * @param point point to calculate the normal
     * @return returns normal vector
     */
    @Override
    public Vector getNormal(Point3D point) {
        Point3D o = super.get_ray().getPoint();
        Vector v = super.get_ray().getDirection();

        // projection of P-O on the ray:
        double t;
        try {
            t = Util.alignZero(point.subtract(o).dotProduct(v));
        } catch (IllegalArgumentException e) { // P = O
            return v;
        }

        // if the point is at a base
        if (t == 0 ||Util.isZero(_height - t)) // if it's close to 0, we'll get ZERO vector exception
            return v;

        o = o.add(v.scale(t));
        return point.subtract(o).normalize();
    }
    public double get_height() {
        return _height;
    }

    @Override
        public String toString() {
            return "Cylinder{" +
                    "_height="+_height+
                    "_axisRay=" + super.get_ray() +
                    "_radius="+super.get_radius()+
                    '}';
        }

    }


