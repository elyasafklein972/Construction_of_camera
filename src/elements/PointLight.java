package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class PointLight extends Light implements LightSource {
    Point3D _position;
    double _kC;
    double _kL;
    double _kQ;

    public PointLight(Color colorIntensity, Point3D _position, double _kC, double _kL, double _kQ) {
        this._intensity = colorIntensity;
        this._position = new Point3D(_position);
        this._kC = _kC;
        this._kL = _kL;
        this._kQ = _kQ;

    }

    @Override
    public Color getIntensity(Point3D p) {
        double dsquared = p.distanceSquared(_position);
        double d = p.distance(_position);

        Color IL = _intensity.reduce(_kC + d*_kL +dsquared* _kQ);

        return IL;
    }

    @Override
    public Vector getL(Point3D p) {
        if (p.equals(_position)) {
            return null;
        } else {
            return p.subtract(_position).normalize();
        }
//        try {
//            return p.subtract(_position).normalize();
//        } catch (IllegalArgumentException e) {
//            return null;

    }
}