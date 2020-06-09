package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

import java.util.List;

public class DirectionalLight extends Light implements LightSource {
    private final Vector _direction;
    public Point3D getPosition(){
        return null;
    }
    @Override
    public double getRadius() {
        return 0d;
    }
public List<Vector> getLs(Point3D p)
{
  return null;
}
    /**
     * Initialize directional light with it's intensity and direction, direction
     * vector will be normalized.
     *
     * @param colorintensity intensity of the light
     * @param direction      direction vector
     */

    public DirectionalLight(Color colorintensity, Vector direction) {
        _intensity = colorintensity;
        _direction = direction.normalized();
    }

    /**
     * @param p the lighted point is not used and is mentioned
     *          only for compatibility with LightSource
     * @return fixed intensity of the directionLight
     */
    @Override
    public Color getIntensity(Point3D p) {
        return super.getIntensity();
        //       return _intensity;
    }

    //instead of getDirection()
    @Override
    public Vector getL(Point3D p) {
        return _direction;
    }

    @Override
    public double getDistance(Point3D point) {
        return Double.POSITIVE_INFINITY;
    }
}