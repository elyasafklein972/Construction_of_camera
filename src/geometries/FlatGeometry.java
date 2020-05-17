package geometries;

import elements.Material;
import primitives.Color;

/**
 * Flat Geometry is a Marker abstract class extending Geometry
 * to differentiate it from RadialGeometry
 * we did not decalre it as an interface
 */
public abstract class FlatGeometry extends Geometry {

    public FlatGeometry(Color _emission, Material _material) {
        super(_emission, _material);
    }
    public FlatGeometry(Color _emission) {
        super(_emission);
    }
    public FlatGeometry() {
        super();
    }
}
