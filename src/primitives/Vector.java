package primitives;

import java.util.Objects;

public class Vector {
    private  Point3D _head;

    public  final static Vector ZERO = new Vector(new Point3D(new Coordinate(0.0),new Coordinate(0.0),new Coordinate(0.0)));

    /*
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
    public  Vector(Point3D p1,Point3D p2){
        this._head=p1.subtract(p2)._head;
    }
    public Vector (Coordinate _x,Coordinate _y,Coordinate _z){
        Point3D _head =new Point3D(new Coordinate(_x),new Coordinate(_y),new Coordinate(_z));
        //  Point3D Zero=new Point3D(new Coordinate(0.0),new Coordinate(0.0),new Coordinate(0.0));

        if (_head.get_x().get() == 0.0 &&_head.get_y().get() == 0.0 &&_head.get_x().get() == 0.0)
            throw new IllegalArgumentException (  "head cannot be the zero vector");

        else
            this._head = _head;

    }

    public Vector(double _x,double _y,double _z){

        if (_x == 0.0 &&_y == 0.0 &&_z == 0.0)
            throw new IllegalArgumentException (  "head cannot be the zero vector");
        this._head=new Point3D(_x,_y,_z);
//    else throw new IllegalArgumentException (  "head cannot be the zero vector");

    }
    public Vector(Vector _head)
    {
        Vector v2=new Vector(_head.get_head().get_x(),_head.get_head().get_y(),_head.get_head().get_z());
        this._head=v2._head;
    }
    public Vector subtract(Vector vec){
        return new Vector(
                this._head.get_x().get()-vec._head.get_x().get(),
                this._head.get_y().get()-vec._head.get_y().get(),
                this._head.get_z().get()-vec._head.get_z().get());
    }

    public Vector add(Vector vec){
        return new Vector(
                this._head.get_x().get()+vec._head.get_x().get(),
                this._head.get_y().get()+vec._head.get_y().get(),
                this._head.get_z().get()+vec._head.get_z().get());
    }

    public Vector scale(double num){
        return new Vector(
                this._head.get_x().get()*num,
                this._head.get_y().get()*num,
                this._head.get_z().get()*num);
    }

    public double dotProduct(Vector other) {


        return (
                _head.get_x().get()*other._head.get_x().get()+
                        _head.get_y().get()*other._head.get_y().get()+
                        _head.get_z().get()*other._head.get_z().get());


    }
    public Vector crossProduct(Vector other){
        double equalx =(this._head.get_x().get()/other._head.get_x().get());
        double equaly =(this._head.get_y().get()/other._head.get_y().get());
        double equalz =(this._head.get_z().get()/other._head.get_z().get());

        if(equalx == equaly && equalz == equaly)
            throw new IllegalArgumentException("Two vectors are parallel or same");

        else
            return new Vector(new Point3D(  new Coordinate(this._head.get_y().get()*other._head.get_z().get()-this._head.get_z().get()*other._head.get_y().get()),
                    new Coordinate(this._head.get_z().get()*other._head.get_x().get()-this._head.get_x().get()*other._head.get_z().get()),
                    new Coordinate(this._head.get_x().get()*other._head.get_y().get()-this._head.get_y().get()*other._head.get_x().get())));


    }
    public double lengthSquared()
    {
        return (this._head.get_x().get()*this._head.get_x().get()+
                this._head.get_y().get()*this._head.get_y().get()+
                this._head.get_z().get()*this._head.get_z().get());
    }

    public double length()
    {
        return Math.sqrt(this.lengthSquared());
    }

    /*
            * get Fun return _head
     * @return
             */
    public Point3D get_head() {
        return _head;
    }

            /*
            * set Fun set Point to _head
     * @param _head
     */
    public void set_head(Point3D _head) {
        this._head = _head;
    }



    /*
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
    /*
            * @return the same Vector after normalisation
     * @throws ArithmeticException if length = 0
            */
    public Vector normalize() {

        this._head=this.normalized()._head;

        return this;

    }
    /*
            * @return the same Vector after normalisation
     * @throws ArithmeticException if length = 0
            */
    public Vector normalized() {

        double x = this._head.get_x().get();
        double y = this._head.get_y().get();
        double z = this._head.get_z().get();

        double length = this.length();

        if (length == 0)
            throw new ArithmeticException("divide by Zero");
        Vector normal = new Vector(new Point3D(new Coordinate(x / length),new Coordinate(y / length),new Coordinate(z / length)));

        return normal;

    }



    @Override
    public String toString() {
        return "Vector{" +
                "_head=" + _head +
                '}';
    }
}