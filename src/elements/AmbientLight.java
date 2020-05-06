
package elements;

import primitives.Color;

public class AmbientLight  extends  Light{
//    public void setIntensity(Color _intensity) {
//        this._intensity = _intensity;
//    }

    public AmbientLight(Color _intensity, double ka) {
        this._intensity = _intensity.scale(ka);
    }

}