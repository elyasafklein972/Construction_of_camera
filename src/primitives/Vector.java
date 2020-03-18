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
     * • Builders receiving:
     * a) three coordinates,
     * b) three double-digit numbers,
     * c) a point,
     * d) a vector
     * @param _head
     */
    public Vector(Point3D _head) {
        Point3D Zero=new Point3D(new Coordinate(0.0),new Coordinate(0.0),new Coordinate(0.0));

           if (_head != Zero)
               this._head = _head;

           else throw new IllegalArgumentException (  "head cannot be the zero vector");


       }
public Vector (Coordinate _x,Coordinate _y,Coordinate _z){
        Point3D _head =new Point3D(new Coordinate(_x),new Coordinate(_y),new Coordinate(_z));
    Point3D Zero=new Point3D(new Coordinate(0.0),new Coordinate(0.0),new Coordinate(0.0));

    if (_head != Zero)
        this._head = _head;

    else throw new IllegalArgumentException (  "head cannot be the zero vector");


}

public Vector(double _x,double _y,double _z){
        Point3D _head=new Point3D(_x,_y,_z);
    Point3D Zero=new Point3D(new Coordinate(0.0),new Coordinate(0.0),new Coordinate(0.0));

    if (_head != Zero)
        this._head = _head;

    else throw new IllegalArgumentException (  "head cannot be the zero vector");

}
public Vector(Vector _head)
{
    Vector v2=new Vector(_head.get_head().get_x(),_head.get_head().get_y(),_head.get_head().get_z());
    this._head=v2._head;
}
    public Vector subtract(Vector vec){
        return new Vector(this._head.get_x().get()-vec._head.get_x().get(),this._head.get_y().get()-vec._head.get_y().get(),this._head.get_z().get()-vec._head.get_z().get());
    }
    public Vector add(Vector vec){
        return new Vector(this._head.get_x().get()+vec._head.get_x().get(),this._head.get_y().get()+vec._head.get_y().get(),this._head.get_z().get()+vec._head.get_z().get());
    }
public Vector scale(double num){
    return new Vector(this._head.get_x().get()+num,this._head.get_y().get()+num,this._head.get_z().get()+num);
}
public double dotProduct(Vector other){
        double sizeA=this._head.
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