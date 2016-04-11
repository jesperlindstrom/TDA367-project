package se.chalmers.get_rect.game.scenes;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.CameraManager;
import se.chalmers.get_rect.game.entities.EntityManager;
import se.chalmers.get_rect.game.entities.IController;
import se.chalmers.get_rect.game.entities.IPhysicsController;
import se.chalmers.get_rect.game.entities.npc.NpcFactory;
import se.chalmers.get_rect.game.entities.player.PlayerController;
import se.chalmers.get_rect.game.loaders.SceneLoader;
import se.chalmers.get_rect.physics.FrostbiteEngine;
import se.chalmers.get_rect.physics.IPhysicsEngine;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class TestScene implements IScene {

    private PlayerController playerController;
    private IRectangleFactoryAdapter rectangleFactory;
    private IPhysicsEngine physics;
    private CameraManager camera;
    private Map<layer, EntityManager> entityManagerMap;

    public TestScene(PlayerController playerController, IRectangleFactoryAdapter rectangleFactory, CameraManager camera) {
        this.playerController = playerController;
        this.rectangleFactory = rectangleFactory;
        this.camera = camera;
    }

    @Override
    public void update(long delta) {
        entityManagerMap.get(layer.BACKGROUND).update(delta);
        entityManagerMap.get(layer.FOREGROUND).update(delta);
        physics.update(delta);
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {

        graphics.draw("img/backgrounds/background.png", camera.getPosition(), 1920, 1080, camera.getPosition());

        entityManagerMap.get(layer.BACKGROUND).draw(graphics);
        entityManagerMap.get(layer.FOREGROUND).draw(graphics);

    }

    @Override
    public void enteringState(String previousStateName) {
        entityManagerMap = new HashMap<>();
        entityManagerMap.put(layer.BACKGROUND, new EntityManager());
        entityManagerMap.put(layer.FOREGROUND, new EntityManager());
        physics = new FrostbiteEngine();
        physics.add(playerController);

        SceneLoader loader = new SceneLoader("test", playerController, rectangleFactory);

        try {
            loadZombies(loader);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        playerController.setPosition(200, 90);
        NpcFactory sawmillFactory = new NpcFactory(rectangleFactory);
        entityManagerMap.get(layer.FOREGROUND).add(sawmillFactory.make(1700, 50));

        entityManagerMap.get(layer.FOREGROUND).add(playerController);

    }

    private void loadZombies(SceneLoader loader) throws FileNotFoundException {
        for (IPhysicsController entity : loader.getZombies()) {
            entityManagerMap.get(layer.FOREGROUND).add(entity);
            physics.add(entity);
        }
    }

    @Override
    public void leavingState(String nextStateName) {

    }


    @Override
    public void addEntity(layer layer, IController controller) {
        entityManagerMap.get(layer).add(controller);
    }
}
