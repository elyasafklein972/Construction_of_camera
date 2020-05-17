
package geometries;

import elements.Material;
import primitives.Color;

import static primitives.Util.isZero;

/**
 * @author Eliezer
 * RadialGeometry is ana abstract class that defines
 * all radial geometries.
 */
public abstract class RadialGeometry extends Geometry {
    protected double _radius;

    /**
     * constructor for a new extended  RadialGeometry object.
     *
     * @param radius   the radius of the RadialGeometry
     * @param material the material of the RadialGeometry
     * @throws Exception in case of negative or zero radius
     */
    public RadialGeometry(Color emissionLight, double radius, Material material) {
        super(emissionLight, material);
        setRadius(radius);
    }

    public RadialGeometry(Color emissionLight, double radius) {
        super(emissionLight);
        setRadius(radius);
    }

    public RadialGeometry(double radius) {
        super();
        setRadius(radius);
    }

    public RadialGeometry(RadialGeometry other) {
        super(other._emission, other._material);
        setRadius(other._radius);
    }

    public double getRadius() {
        return _radius;
    }

    public void setRadius(double radius) {
        if (isZero(radius) || (radius < 0.0))
            throw new IllegalArgumentException("radius " + radius + " is not valid");
        this._radius = radius;
    }
}
