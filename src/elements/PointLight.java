package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Util;
import primitives.Vector;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import static primitives.Util.alignZero;

/**
 * The PointLight object specifies an attenuated light source at a fixed point in space that radiates light equally
 * in all directions away from the light source. PointLight has the same attributes as a Light node,
 * with the addition of location and attenuation parameters.
 * <p>
 * A point light contributes to diffuse and specular reflections,
 * which in turn depend on the orientation and position of a surface.
 * A point light does not contribute to ambient reflections.
 * <p>
 * A PointLight is attenuated by multiplying the contribution of the light by an attenuation factor.
 * The attenuation factor causes the the PointLight's brightness to decrease
 * as distance from the light source increases.
 * A PointLight's attenuation factor contains three values:
 * <p>
 * Constant attenuation
 * Linear attenuation
 * Quadratic attenuation
 * <p>
 * A PointLight is attenuated by the reciprocal of the sum of:
 * <p>
 * The constant attenuation factor
 * The Linear attenuation factor times the distance between the light and the vertex being illuminated
 * The quadratic attenuation factor times the square of the distance between the light and the vertex
 * <p>
 * By default, the constant attenuation value is 1 and the other two values are 0,
 * resulting in no attenuation.
 */
public class PointLight extends Light implements LightSource {
    Point3D _position;
    double _kC; // Constant attenuation
    double _kL; // Linear attenuation
    double _kQ; // Quadratic attenuation
    double _radius;

    public Point3D getPosition() {
        return _position;
    }

    public double getRadius() {
        return _radius;
    }



    public PointLight(Color colorIntensity, Point3D position, double kC, double kL, double kQ) {
        this(colorIntensity, new Point3D(position), kC, kL, kQ, 0);
//        this._intensity = colorIntensity;
//        this._position = new Point3D(position);
//        this._kC = kC;
//        this._kL = kL;
//        this._kQ = kQ;
//        this._radius=0;
    }

    // by default, the constant attenuation value is 1 and the other two values are 0
    public PointLight(Color colorIntensity, Point3D position) {
        this(colorIntensity, position, 1d, 0d, 0d);
    }


    public PointLight(Color colorIntensity, Point3D _position, double _kC, double _kL, double _kQ, double _radius) {
        this._intensity = colorIntensity;
        this._position = _position;
        this._kC = _kC;
        this._kL = _kL;
        this._kQ = _kQ;
        this._radius = _radius;
    }

    //dummy overriding Light getIntensity()
    @Override
    public Color getIntensity() {
        return super.getIntensity();
    }

    //overriding LightSource getIntensity(Point3D)
    @Override
    public Color getIntensity(Point3D p) {
        double dsquared = p.distanceSquared(_position);
        double d = p.distance(_position);

        return (_intensity.reduce(_kC + _kL * d + _kQ * dsquared));
    }

    // Light vector
    @Override
    public Vector getL(Point3D p) {

        if (p.equals(_position)) {
            return null;
        }
        return p.subtract(_position).normalize();

    }


    public List<Vector> getLs(Point3D p) {
        // double size = _radius * _radius * Math.PI;
        List<Point3D> pointLights = new LinkedList<Point3D>();
        for (int x = 0; x < _radius; x++)
            for (int y = 0; y < _radius; y++)
                for (int z = 0; z < _radius; z++) {
                    pointLights.add(new Point3D(_position.get_x().get() + x, _position.get_y().get() + y, _position.get_z().get() + z));
                    pointLights.add(new Point3D(-_position.get_x().get() + x, -_position.get_y().get() + y, -_position.get_z().get() + z));

                }

        if (p.equals(_position)) {
            return null;
        }
        List<Vector> allSubstract = new LinkedList<Vector>();
        for (Point3D po : pointLights)
            allSubstract.add(p.subtract(po).normalize());
        return allSubstract;

    }


    @Override
    public double getDistance(Point3D point) {
        return alignZero(_position.distance(point));
    }
}
