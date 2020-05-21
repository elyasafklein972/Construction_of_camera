package elements;

public class Material {
    private double _kD;
    private double _kS;
    private int _nShininess;

    private double _Kr; // Reflection coefficient (1 for mirror)
    private double _Kt; // Refraction coefficient (1 for transparent)

    public Material(double _kD, double _kS, int _nShininess) {
        this._kD = _kD;
        this._kS = _kS;
        this._nShininess = _nShininess;
    }

    public Material(double _kD, double _kS, int _nShininess, double _Kr, double _Kt) {
        this._kD = _kD;
        this._kS = _kS;
        this._nShininess = _nShininess;
        this._Kr = _Kr;
        this._Kt = _Kt;
    }

    // ***************** Constructors ********************** //
    public Material() {
        _kD = 1;
        _kS = 1;
        _Kr = 0;
        _Kt = 0;
        _nShininess = 1;
    }

    public Material(Material material)
    {
        _kD = material.getkD();
        _kS = material.getkS();
        _Kr = material.getKr();
        _Kt = material.getKt();
        _nShininess = material.getnShininess();
    }

    public void setkD(double _kD) {
        this._kD = _kD;
    }

    public void setkS(double _kS) {
        this._kS = _kS;
    }

    public void setNShininess(int _nShininess) {
        this._nShininess = _nShininess;
    }

    public void setKr(double _Kr) {
        this._Kr = _Kr;
    }

    public void setKt(double _Kt) {
        this._Kt = _Kt;
    }

    public double getkD() {
        return _kD;
    }

    public double getkS() {
        return _kS;
    }

    public int getnShininess() {
        return _nShininess;
    }

    public double getKr() {
        return _Kr;
    }

    public double getKt() {
        return _Kt;
    }
    // ***************** Getters/Setters ********************** //

}