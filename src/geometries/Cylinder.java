package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends Tube  {
    private  double _height;
        public Cylinder(double _height,Ray _axisRay,double _radius){
            super(_axisRay,_radius);
            this._height=_height;
        }

        @Override
        public Vector getNormal(Point3D p) {
            return null;//new Vector(p).normalize();
        }

    public double get_height() {
        return _height;
    }

    @Override
        public String toString() {
            return "Cylinder{" +
                    "_height="+_height+
                    "_axisRay=" + super.get_axisRay() +
                    "_radius="+super.get_radius()+
                    '}';
        }
    }


