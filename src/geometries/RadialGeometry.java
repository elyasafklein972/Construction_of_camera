
package geometries;

import primitives.Color;

import static primitives.Util.isZero;

/**
 * @author Eliezer
 * RadialGeometry is ana abstract class that defines
 * all radial geometries.
 */
public abstract class RadialGeometry extends Geometry{
    protected double  _radius;

    /**
     *
     * @param _radius
     */
    public RadialGeometry(Color emissionLight, double _radius) {
        super(emissionLight);
        if (isZero(_radius) || (_radius < 0.0))
            throw new IllegalArgumentException("radius "+ _radius +" is not valid");
        this._radius = _radius;
    }

    public RadialGeometry(RadialGeometry other){
        super(Color.BLACK);
        this._radius= other._radius;
    }
    public double getRadius() {
        return _radius;
    }
}