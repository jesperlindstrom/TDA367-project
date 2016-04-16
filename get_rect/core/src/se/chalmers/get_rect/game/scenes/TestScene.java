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
        for (Map.Entry<layer, EntityManager> entry : layers.entrySet()) {
            entry.getValue().update();
        }

        physics.update(delta);
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.draw("img/backgrounds/background.png", camera.getPosition(), GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT, camera.getPosition());

        for (Map.Entry<layer, EntityManager> entry : layers.entrySet()) {
            entry.getValue().draw(graphics);
        }
    }

    @Override
    public void enteringState(String previousStateName) {
        layers.clear();
        layers.put(layer.BACKGROUND, new EntityManager());
        layers.put(layer.FOREGROUND, new EntityManager());
        layers.put(layer.FOREGROUND_EFFECTS, new EntityManager());

        physics = new PhysicsEngine();
        physics.add(playerEntity.getModel());

        SceneLoader loader = new SceneLoader("test", playerEntity, rectangleFactory);

        try {
            loadForeground(loader);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }


        playerEntity.getModel().setPosition(new Point(1500, 150));

        WorldObjectFactory solidFactory = new WorldObjectFactory(rectangleFactory);
        for (int i = 0; i < 2; i++) {
            IPhysicsEntity entity = solidFactory.make(new Point(i*1000, 120), 1000);
            layers.get(layer.FOREGROUND).add(entity);
            physics.add(entity.getModel());
        }

       layers.get(layer.FOREGROUND).add(playerEntity);
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
