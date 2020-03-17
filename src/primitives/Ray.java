package primitives;

import java.util.Objects;
import java.lang.Math;
public class Ray {
    private  Point3D point;
    private Vector vector;

    /**
     * constractor set Point3D and Vector
     * @param point
     * @param vector
     */
    public Ray(Point3D point, Vector vector) {
        this.point = point;
       Coordinate x =vector.get_head().get_x();
       Coordinate y =vector.get_head().get_y();
       Coordinate z =vector.get_head().get_z();
       //the size of the vector
       double S=(x.get()*x.get()+y.get()*y.get()+z.get()*z.get());

      // S=Math.sqrt(S);

        if(S==1)
        this.vector = vector;
        else throw new IllegalArgumentException (  "the vector must be with size =1");
    }

    /**
     * get fun Point in Ray
     * @return
     */
    public Point3D getPoint() {
        return point;
    }

    /**
     * get fun Vector in Ray
     * @return
     */
    public Vector getVector() {
        return vector;
    }

    /**
     * override fun equals
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return Objects.equals(point, ray.point) &&
                Objects.equals(vector, ray.vector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, vector);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "point=" + point +
                ", vector=" + vector +
                '}';

    }
}
