package elements;

import primitives.Color;

public abstract class Light {
    /**
     * _intensity value, set to protected
     */
    protected Color _intensity;

    public Light(Color _intensity) {
        this._intensity = _intensity;
    }

    public Color getIntensity() {
        return new Color(_intensity);
    }
}