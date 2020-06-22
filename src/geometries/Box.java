package geometries;
import elements.Material;


import java.util.List;
import java.util.Objects;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

import geometries.Geometry;
import geometries.Sphere;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

public class Box  {
    double minX;
    double minY;
    double minZ;
    double maxX;
    double maxY;
    double maxZ;
    Intersectable Geo;
    Point3D center;

    public Box(Intersectable Geo) {
        if (Geo instanceof Sphere) {
            center = ((Sphere) Geo).getCenter();
            minX = center.get_x().get() - ((Sphere) Geo).getRadius();
            minY = center.get_y().get() - ((Sphere) Geo).getRadius();
            minZ = center.get_z().get() - ((Sphere) Geo).getRadius();
            maxX = center.get_x().get() + ((Sphere) Geo).getRadius();
            maxY = center.get_y().get() + ((Sphere) Geo).getRadius();
            maxZ = center.get_z().get() + ((Sphere) Geo).getRadius();


        } else {
            if (Geo instanceof Polygon) {
                minX = Double.MAX_VALUE;
                minY = Double.MAX_VALUE;
                minZ = Double.MAX_VALUE;
                maxX = Double.MIN_VALUE;
                maxY = Double.MIN_VALUE;
                maxZ = Double.MIN_VALUE;

                for (Point3D p : ((Polygon) Geo)._vertices) {

                    if (p.get_x().get() < minX) {
                        minX = p.get_x().get();
                    }
                    if (p.get_y().get() < minY) {
                        minY = p.get_y().get();
                    }
                    if (p.get_z().get() < minZ) {
                        minZ = p.get_z().get();
                    }
                    if (p.get_x().get() > maxX) {
                        maxX = p.get_x().get();
                    }
                    if (p.get_y().get() > maxY) {
                        maxY = p.get_y().get();
                    }
                    if (p.get_z().get() > maxZ) {
                        maxZ = p.get_z().get();
                    }
                }
                center = new Point3D((maxX + minX) / 2, (maxY + minY) / 2, (maxZ + minZ) / 2);
            }


        }
    }

    // is Point p inside this BoundingBox?
    public boolean isIntersectionWithBox(Ray ray){

        Point3D start = ray.getStartPoint();

        double start_X = start.get_x().get();
        double start_Y = start.get_y().get();
        double start_Z = start.get_z().get();

        Point3D direction = ray.getDirection().get_head();

        double direction_X = direction.get_x().get();
        double direction_Y = direction.get_y().get();
        double direction_Z = direction.get_z().get();

        double max_t_for_X;
        double min_t_for_X;

        //If the direction_X is negative then the _min_X give the maximal value
        if (direction_X < 0) {
            max_t_for_X = (this.minX - start_X) / direction_X;
            // Check if the Intersectble is behind the camera
            if (max_t_for_X <= 0) return false;
            min_t_for_X = (this.maxX - start_X) / direction_X;
        }
        else if (direction_X > 0) {
            max_t_for_X = (this.maxX - start_X) / direction_X;
            if (max_t_for_X <= 0) return false;
            min_t_for_X = (this.minX - start_X) / direction_X;
        }
        else {
            if (start_X >= this.maxX || start_X <= this.minX)
                return false;
            else{
                max_t_for_X = Double.POSITIVE_INFINITY;
                min_t_for_X = Double.NEGATIVE_INFINITY;
            }
        }

        double max_t_for_Y;
        double min_t_for_Y;

        if (direction_Y < 0) {
            max_t_for_Y = (this.minY - start_Y) / direction_Y;
            if (max_t_for_Y <= 0) return false;
            min_t_for_Y = (this.maxY - start_Y) / direction_Y;
        }
        else if (direction_Y > 0) {
            max_t_for_Y = (this.maxY - start_Y) / direction_Y;
            if (max_t_for_Y <= 0) return false;
            min_t_for_Y = (this.minY - start_Y) / direction_Y;
        }
        else {
            if (start_Y >= this.maxY || start_Y <= this.minY)
                return false;
            else{
                max_t_for_Y = Double.POSITIVE_INFINITY;
                min_t_for_Y = Double.NEGATIVE_INFINITY;
            }
        }

        //Check the maximal and the minimal value for t
        double temp_max = Math.min(max_t_for_Y,max_t_for_X);
        double temp_min = Math.max(min_t_for_Y,min_t_for_X);
        temp_min = Math.max(temp_min,0);

        if (temp_max < temp_min) return false;

        double max_t_for_Z;
        double min_t_for_Z;

        if (direction_Z < 0) {
            max_t_for_Z = (this.minZ - start_Z) / direction_Z;
            if (max_t_for_Z <= 0) return false;
            min_t_for_Z = (this.maxZ - start_Z) / direction_Z;
        }
        else if (direction_Z > 0) {
            max_t_for_Z = (this.maxZ - start_Z) / direction_Z;
            if (max_t_for_Z <= 0) return false;
            min_t_for_Z = (this.minZ - start_Z) / direction_Z;
        }
        else {
            if (start_Z >= this.maxZ || start_Z <= this.minZ)
                return false;
            else{
                max_t_for_Z = Double.POSITIVE_INFINITY;
                min_t_for_Z = Double.NEGATIVE_INFINITY;
            }
        }

        temp_max = Math.min(max_t_for_Z,temp_max);
        temp_min = Math.max(min_t_for_Z,temp_min);

        if (temp_max < temp_min) return false;

        return true;
    }
}




