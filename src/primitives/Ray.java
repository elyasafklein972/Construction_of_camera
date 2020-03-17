package primitives;

import java.util.Objects;

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
        this.vector = vector;
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
