package geometries;

import primitives.Point3D;
import primitives.Ray;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Geometries extends Intersectable {

    private List<Intersectable> _geometries = new ArrayList<>();
    public Geometries() {
        this.box._max_X = Double.NEGATIVE_INFINITY;
        this.box._min_X = Double.POSITIVE_INFINITY;
        this.box._max_Y = Double.NEGATIVE_INFINITY;
        this.box._min_Y = Double.POSITIVE_INFINITY;
        this.box._max_Z = Double.NEGATIVE_INFINITY;
        this.box._min_Z = Double.POSITIVE_INFINITY;
        _geometries = new LinkedList<Intersectable>();
    }
    public Geometries(Intersectable... _geometries) {
        this();//default ctor
        add(_geometries);
    }

    public void add(Intersectable... geometries) {
//        for (Intersectable geo : geometries) {
//            _geometries.add(geo);
//        }
        for (Intersectable geo : geometries) {
            _geometries.add(geo);
        if (geo.box._max_X > this.box._max_X)
            this.box._max_X = geo.box._max_X;
        if (geo.box._min_X < this.box._min_X)
            this.box._min_X = geo.box._min_X;
        if (geo.box._max_Y > this.box._max_Y)
            this.box._max_Y = geo.box._max_Y;
        if (geo.box._min_Y < this.box._min_Y)
            this.box._min_Y = geo.box._min_Y;
        if (geo.box._max_Z > this.box._max_Z)
            this.box._max_Z = geo.box._max_Z;
        if (geo.box._min_Z < this.box._min_Z)
            this.box._min_Z = geo.box._min_Z;
    }
    //    _geometries.addAll(Arrays.asList(geometries));
    }

    /**
     * @param ray the ray that intersect the geometries
     * @return list of Point3D that intersect the osef
     */
    @Override
    public List<GeoPoint> findIntersections(Ray ray, double maxDistance) {
        List<GeoPoint> intersections = null;

        for (Intersectable geo : _geometries) {
            List<GeoPoint> tempIntersections = geo.findIntersections(ray, maxDistance);
            if (tempIntersections != null) {
                if (intersections == null)
                    intersections = new LinkedList<>();
                intersections.addAll(tempIntersections);
            }
        }
        return intersections;

    }

    public void remove(Intersectable... intersectables) {
        for (Intersectable geo : _geometries) {
            _geometries.remove(geo);
        }
    }
}