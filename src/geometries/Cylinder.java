package geometries;

import primitives.Point3D;
import primitives.Ray;
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
        //The vector from the point of the cylinder to the given point
        Vector vector1 = point.subtract(this.get_ray().getPoint());

        //We need the projection to multiply the _direction unit vector
        double projection = vector1.dotProduct(this.get_ray().getDirection());

        Vector vector2 = this.get_ray().getDirection().scale(projection);

        //This vector is orthogonal to the _direction vector.
        Vector check = vector1.subtract(vector2);
        return check.normalize();
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


