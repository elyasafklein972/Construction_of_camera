package primitives;

import com.sun.deploy.security.ruleset.ExceptionRule;
import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import com.sun.xml.internal.ws.api.model.ExceptionType;

import java.util.Objects;

public class Vector {
    private  Point3D _head;

    public  final static Vector ZERO = new Vector(new Point3D(new Coordinate(0.0),new Coordinate(0.0),new Coordinate(0.0)));

    /**
     * Constractor
     * @param _head
     */

    public Vector(Point3D _head) {
        Point3D Zero=new Point3D(new Coordinate(0.0),new Coordinate(0.0),new Coordinate(0.0));

           if (_head != Zero)
               this._head = _head;

           else throw new IllegalArgumentException (  "head cannot be the zero vector");


       }

    /**
     * get Fun return _head
     * @return
     */
    public Point3D get_head() {
        return _head;
    }

    /**
     * set Fun set Point to _head
     * @param _head
     */
    public void set_head(Point3D _head) {
        this._head = _head;
    }

    public double dotProduct(Vector n) {
        return 0.0;
    }

    /**
     * override equal fun
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return Objects.equals(_head, vector._head);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_head);
    }

    public Vector crossProduct(Vector edge2) {
        return Vector.ZERO;
    }

    @Override
    public String toString() {
        return "Vector{" +
                "_head=" + _head +
                '}';
    }
}