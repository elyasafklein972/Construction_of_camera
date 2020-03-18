package primitives;

import java.util.Objects;

public class Point3D {
    private  Coordinate _x;
    private Coordinate _y;
    private Coordinate _z;

    public Point3D(Coordinate _x, Coordinate _y, Coordinate _z) {
        this._x = _x;
        this._y = _y;
        this._z = _z;
    }

    public Coordinate get_x() {
        return _x;
    }

    public void set_x(Coordinate _x) {
        this._x = _x;
    }

    public Coordinate get_y() {
        return _y;
    }

    public void set_y(Coordinate _y) {
        this._y = _y;
    }

    public Coordinate get_z() {
        return _z;
    }

    public void set_z(Coordinate _z) {
        this._z = _z;
    }

    /**
     * override equals
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point3D point3D = (Point3D) o;
        return _x.equals(point3D._x) &&
                _y.equals(point3D._y) &&
                _z.equals(point3D._z);
    }

    public Vector subtract(Point3D vertex) {
       if (this.equals(vertex)==true)
           return Vector.ZERO;
       else {
           double dX=(this._x.get()-vertex._x.get());
           double dY=(this._y.get()-vertex._y.get());
           double dZ=(this._z.get()-vertex._z.get());

           return new Vector(new Point3D( new Coordinate(dX),new Coordinate(dY),new Coordinate(dZ)));

       }


        return
    }

    @Override
    public String toString() {
        return "Point3D{" +
                "_x=" + _x +
                ", _y=" + _y +
                ", _z=" + _z +
                '}';
    }
}