package se.chalmers.get_rect.game.scenes;

import se.chalmers.get_rect.GameConfig;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.CameraManager;
import se.chalmers.get_rect.game.entities.EntityManager;
import se.chalmers.get_rect.game.entities.npc.NpcFactory;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.entities.solidStuff.floor.Floor;
import se.chalmers.get_rect.game.entities.solidStuff.floor.SolidFactory;
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

        entityManagerMap.get(layer.FOREGROUND_EFFECTS).update();
        entityManagerMap.get(layer.BACKGROUND).update();
        entityManagerMap.get(layer.FOREGROUND).update();

        physics.update(delta);
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {

        graphics.draw("img/backgrounds/background.png", camera.getPosition(), GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT, camera.getPosition());

        entityManagerMap.get(layer.BACKGROUND).draw(graphics);
        entityManagerMap.get(layer.FOREGROUND).draw(graphics);
        entityManagerMap.get(layer.FOREGROUND_EFFECTS).draw(graphics);

    }

    @Override
    public void enteringState(String previousStateName) {
        entityManagerMap = new HashMap<>();
        entityManagerMap.put(layer.BACKGROUND, new EntityManager());
        entityManagerMap.put(layer.FOREGROUND, new EntityManager());
        entityManagerMap.put(layer.FOREGROUND_EFFECTS, new EntityManager());

        physics = new PhysicsEngine();
        physics.add(playerEntity.getModel());

        SceneLoader loader = new SceneLoader("test", playerEntity, rectangleFactory);

        try {
            loadZombies(loader);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }


        playerEntity.getModel().setPosition(new Point(1500, 150));

        SolidFactory solidFactory = new SolidFactory(rectangleFactory);
        for (int i = 0; i < 2; i++) {
            IPhysicsEntity entity = solidFactory.make(new Point(i*1000, 120), 1000);
            entityManagerMap.get(layer.FOREGROUND).add(entity);
            physics.add(entity.getModel());
        }

        NpcFactory sawmillFactory = new NpcFactory(rectangleFactory);
/*
        for (int i = 1; i < 2; i++) {
            IPhysicsEntity sm = sawmillFactory.make(new Point(1100+i*100, 95));
>>>>>>> feature-frostbite-2
            entityManagerMap.get(layer.FOREGROUND).add(sm);
            physics.add(sm.getModel());
        }
<<<<<<< HEAD
        entityManagerMap.get(layer.FOREGROUND).add(playerController);
=======
*/
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
