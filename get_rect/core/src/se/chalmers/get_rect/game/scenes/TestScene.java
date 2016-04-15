package se.chalmers.get_rect.game.scenes;

import se.chalmers.get_rect.GameConfig;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.CameraManager;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.entities.npc.Floor100;
import se.chalmers.get_rect.game.entities.npc.NpcFactory;
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
    private Map<layer, EntityManager> entityManagerMap;

    public TestScene(IPhysicsEntity playerEntity, IRectangleFactoryAdapter rectangleFactory, CameraManager camera) {
        this.playerEntity = playerEntity;
        this.rectangleFactory = rectangleFactory;
        this.camera = camera;
    }

    @Override
    public void update(double delta) {
        entityManagerMap.get(layer.BACKGROUND).update();
        entityManagerMap.get(layer.FOREGROUND).update();
        physics.update(delta);
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.draw("img/backgrounds/background.png", camera.getPosition(), GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT, camera.getPosition());

        entityManagerMap.get(layer.BACKGROUND).draw(graphics);
        entityManagerMap.get(layer.FOREGROUND).draw(graphics);

    }

    @Override
    public void enteringState(String previousStateName) {
        entityManagerMap = new HashMap<>();
        entityManagerMap.put(layer.BACKGROUND, new EntityManager());
        entityManagerMap.put(layer.FOREGROUND, new EntityManager());
        physics = new PhysicsEngine();
        physics.add(playerEntity.getModel());

        SceneLoader loader = new SceneLoader("test", playerEntity, rectangleFactory);
/*
        try {
            loadZombies(loader);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        */
        playerEntity.getModel().setPosition(new Point(200, 95));
        Floor100 floor100 = new Floor100(new Point(150,90),rectangleFactory);
        physics.add(floor100);
/*
        for (int i = 0; i < 100; i++) {
            Floor100 floor = new Floor100(new Point(i * 100, 90), rectangleFactory);
            physics.add(floor);
        }
    /*
        NpcFactory sawmillFactory = new NpcFactory(rectangleFactory);

        for (int i = 1; i < 100; i++) {
            IPhysicsEntity sm = sawmillFactory.make(new Point(1100+i*100, 50));
            entityManagerMap.get(layer.FOREGROUND).add(sm);
            physics.add(sm.getModel());
        }*/

        entityManagerMap.get(layer.FOREGROUND).add(playerEntity);
    }

    private void loadZombies(SceneLoader loader) throws FileNotFoundException {
        for (IPhysicsEntity entity : loader.getZombies()) {
            entityManagerMap.get(layer.FOREGROUND).add(entity);
            physics.add(entity.getModel());
        }
    }

    @Override
    public void leavingState(String nextStateName) {

    }


    @Override
    public void addEntity(layer layer, IEntity entity) {
        entityManagerMap.get(layer).add(entity);
    }

    @Override
    public void addPhysicsEntity(layer layer, IPhysicsEntity entity) {
        entityManagerMap.get(layer).add(entity);
        physics.add(entity.getModel());
    }
}
