package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Tube extends RadialGeometry {

  private Ray _axisRay;
  public Tube(Ray _axisRay,double _radius){
      super(_radius);
      this._axisRay=_axisRay;
  }

    @Override
    public Vector getNormal(Point3D p) {
        return null; //new Vector(p).normalize();
    }
    public Ray get_axisRay() {
        return _axisRay;
    }

    @Override
    public String toString() {
        return "Tube{" +
                "_axisRay=" + _axisRay +
                "_radius="+super.get_radius()+
                '}';
    }
}
