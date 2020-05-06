package geometries;

import elements.Material;
import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * interface Geometry is the basic interface for all geometric objects
 * who are implementing getNormal method.
 *
 * @author Bobby McFerrin don't worry be Happy
 */
public abstract class Geometry implements Intersectable {

    protected Color _emission;
    protected Material _material;


    public Geometry(Color _emission, Material _material) {
        this._emission = _emission;
        this._material = _material;
    }

    public Geometry(Color _emission) {
        this(_emission, new Material(0d, 0d, 0));
    }

   public Geometry() {
        this(Color.BLACK);
    }

    public Color getEmissionLight() {
        return (_emission);
    }

    public Material getMaterial() {
        return _material;
    }

    abstract public Vector getNormal(Point3D p);
}