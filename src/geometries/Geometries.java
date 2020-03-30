package geometries;

import primitives.Point3D;
import primitives.Ray;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.List;

public class Geometries implements Intersectable {

    private List<Intersectable> _geometries = new ArrayList<Intersectable>();

    public Geometries(Intersectable... _geometries) {
        add( _geometries);
    }

    public void add(Intersectable... geometries) {
        for (Intersectable geo : geometries ) {
            _geometries.add(geo);
        }
    }

    /**
     * Na le Hasbir befrotrot
     * @param ray
     * @return
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> intersections = null;

        for (Intersectable geometry : _geometries) {
            List<Point3D> tempIntersections = geometry.findIntersections(ray);
            if (tempIntersections != null) {
                if (intersections == null)
                    intersections = new ArrayList<Point3D>();
                intersections.addAll(tempIntersections);
            }
        }
        return intersections;

    }
}