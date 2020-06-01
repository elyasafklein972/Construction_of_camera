package geometries;

import primitives.*;


import java.util.List;

/**
 * Intersectable is a common interface for all geometries that are able
 * to intersect from a ray to their entity (Shape)
 */
public interface Intersectable {

    /**
     * @param ray ray pointing toward a Gepmtry
     * @return List<GeoPoint> return values
     */
    default List<GeoPoint> findIntersections(Ray ray) {
        return findIntersections(ray, Double.POSITIVE_INFINITY);
    }

    List<GeoPoint> findIntersections(Ray ray, double maxDistance);

    /**
     * GeoPoint is just a tuple holding
     * references to a specific point ain a specific geometry
     */
    class GeoPoint {

        protected Geometry _geometry;
        protected Point3D point;

        public GeoPoint(Geometry _geometry, Point3D pt) {
            this._geometry = _geometry;
            point = pt;
        }

        public Point3D getPoint() {
            return point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            GeoPoint geoPoint = (GeoPoint) o;

            return ((_geometry.equals(geoPoint._geometry)) && (point.equals(geoPoint.point)));
        }

        public Geometry getGeometry() {
            return _geometry;
        }
    }
    //end of GeoPoint

}