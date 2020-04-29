package scene;

import elements.AmbientLight;
import elements.Camera;
import geometries.Geometries;
import geometries.Intersectable;
import primitives.Color;

public class Scene {
    private final String _name;
    private final Geometries _geometries = new Geometries();

    private Color _background;
    private Camera _camera;
    private double _distance;
    private AmbientLight _ambientLight;



    public AmbientLight getAmbientLight() {
        return _ambientLight;
    }

    public Camera getCamera() {
        return _camera;
    }

    public Geometries getGeometries() {
        return _geometries;
    }

    public double getDistance() {
        return _distance;
    }


    public Scene(String _sceneName){
        this._name= _sceneName;
    }

    public Color getBackground() {
        return this._background;
    }
    public void addGeometries(Intersectable... intersectables) {
        for (Intersectable i:intersectables ) {
            _geometries.add(i);
        }
    }
    public void removeGeometries(Intersectable... intersectables) {
        for (Intersectable i:intersectables ) {
            _geometries.remove(i);
        }
    }

    public static class  SceneBuilder  {
        private  String _name;
        private Color _background;
        private  Camera _camera;
        private  double _distance;
        private  AmbientLight _ambientLight;

        public  SceneBuilder(String _name) {
            this._name = _name;
        }

        public SceneBuilder addBackground(Color _background) {
            this._background = _background;
            return this;
        }

        public SceneBuilder addCamera(Camera _camera) {
            this._camera = _camera;
            return this;
        }

        public SceneBuilder addDistance(double _distance) {
            this._distance = _distance;
            return this;
        }

        public SceneBuilder addAmbientLight(AmbientLight _ambientLight) {
            this._ambientLight = _ambientLight;
            return this;
        }

        public Scene build()
        {
            Scene scene = new Scene(_name);
            scene._camera= _camera;
            scene._distance = _distance;
            scene._background = _background;
            scene._ambientLight =_ambientLight;
            validateUserObject(scene);
            return scene;
        }
        private void validateUserObject(Scene scene) {
            //Do some basic validations to check
            //if user object does not break any assumption of system
        }
    }

}