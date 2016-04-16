package se.chalmers.get_rect.game.scenes;

import se.chalmers.get_rect.GameConfig;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.CameraManager;
import se.chalmers.get_rect.game.entities.EntityManager;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.entities.worldObjects.floor.WorldObjectFactory;
import se.chalmers.get_rect.game.loaders.SceneLoader;
import se.chalmers.get_rect.physics.frostbite.PhysicsEngine;
import se.chalmers.get_rect.physics.IPhysicsEngine;
import se.chalmers.get_rect.utilities.Point;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class TestScene implements IScene {
    private IPhysicsEntity playerEntity;
    private IRectangleFactoryAdapter rectangleFactory;
    private IPhysicsEngine physics;
    private CameraManager camera;
    private Map<layer, EntityManager> layers;

    public TestScene(IPhysicsEntity playerEntity, IRectangleFactoryAdapter rectangleFactory, CameraManager camera) {
        this.playerEntity = playerEntity;
        this.rectangleFactory = rectangleFactory;
        this.camera = camera;
        layers = new HashMap<>();
    }

    @Override
    public void update(double delta) {
        layers.forEach((k, v) -> v.update());
        physics.update(delta);
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.draw("img/backgrounds/background.png", camera.getPosition(), GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT, camera.getPosition());
        layers.forEach((k, v) -> v.draw(graphics));
    }

    @Override
    public void enteringState(String previousStateName) {
        createLayers();
        physics = new PhysicsEngine();
        SceneLoader loader = new SceneLoader("test", playerEntity, rectangleFactory);

        try {
            loadBackground(loader);
            loadForeground(loader);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        addPlayerAtPosition(1500, 150);
    }

    private void createLayers() {
        layers.clear();
        layers.put(layer.BACKGROUND, new EntityManager());
        layers.put(layer.FOREGROUND, new EntityManager());
        layers.put(layer.FOREGROUND_EFFECTS, new EntityManager());
    }

    private void addPlayerAtPosition(int x, int y) {
        playerEntity.getModel().setPosition(new Point(x, y));
        addPhysicsEntity(layer.FOREGROUND, playerEntity);
    }

    private void loadBackground(SceneLoader loader) throws FileNotFoundException {
        for (IPhysicsEntity entity : loader.getBackground()) {
            addPhysicsEntity(layer.BACKGROUND, entity);
        }
    }

    private void loadForeground(SceneLoader loader) throws FileNotFoundException {
        for (IPhysicsEntity entity : loader.getForeground()) {
            addPhysicsEntity(layer.FOREGROUND, entity);
        }
    }

    @Override
    public void leavingState(String nextStateName) {

    }


    @Override
    public void addEntity(layer layer, IEntity entity) {
        layers.get(layer).add(entity);
    }

    @Override
    public void addPhysicsEntity(layer layer, IPhysicsEntity entity) {
        layers.get(layer).add(entity);
        physics.add(entity.getModel());
    }
}
