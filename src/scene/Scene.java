package scene;

import elements.*;
import geometries.Geometries;
import geometries.Intersectable;
import primitives.Color;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    private final String _name;
    private final Geometries _geometries = new Geometries();

    private Color _background;
    private Camera _camera;
    private double _distance;
    private AmbientLight _ambientLight;
    private List<LightSource> _lights = null;


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

    public Scene(String _sceneName) {
        this._name = _sceneName;
    }

    public Color getBackground() {
        return this._background;
    }


    public List<LightSource> getLightSources() {
        return _lights;
    }

    public void addGeometries(Intersectable... intersectables) {
        for (Intersectable i : intersectables) {
            _geometries.add(i);
        }
    }

    public void removeGeometries(Intersectable... intersectables) {
        for (Intersectable i : intersectables) {
            _geometries.remove(i);
        }
    }

    public void addLights(LightSource light) {
        if (_lights == null) {
            _lights = new ArrayList<>();
        }
        _lights.add(light);
    }

    public static class SceneBuilder {
        private String name;
        private Color background;
        private Camera camera;
        private double distance;
        private AmbientLight ambientLight;

        public SceneBuilder(String name) {
            this.name = name;
        }

        public SceneBuilder addBackground(Color background) {
            this.background = background;
            return this;
        }

        public SceneBuilder addCamera(Camera camera) {
            this.camera = camera;
            return this;
        }

        public SceneBuilder addDistance(double distance) {
            this.distance = distance;
            return this;
        }

        public SceneBuilder addAmbientLight(AmbientLight ambientLight) {
            this.ambientLight = ambientLight;
            return this;
        }

        public Scene build() {
            Scene scene = new Scene(this.name);
            scene._camera = this.camera;
            scene._distance = this.distance;
            scene._background = this.background;
            scene._ambientLight = this.ambientLight;
            validateUserObject(scene);
            return scene;
        }

        private void validateUserObject(Scene scene) {
            //Do some basic validations to check
            //if user object does not break any assumption of system
        }
    }
}