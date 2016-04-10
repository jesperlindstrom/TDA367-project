package se.chalmers.get_rect.game.scenes;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.EntityManager;
import se.chalmers.get_rect.game.entities.IPhysicsController;
import se.chalmers.get_rect.game.entities.player.PlayerController;
import se.chalmers.get_rect.game.loaders.SceneLoader;
import se.chalmers.get_rect.physics.FrostbiteEngine;
import se.chalmers.get_rect.physics.IPhysicsEngine;

import java.io.FileNotFoundException;

public class TestScene implements IScene {

    private PlayerController playerController;
    private IRectangleFactoryAdapter rectangleFactory;
    private EntityManager background;
    private EntityManager foreground;
    private IPhysicsEngine physics;

    public TestScene(PlayerController playerController, IRectangleFactoryAdapter rectangleFactory) {
        this.playerController = playerController;
        this.rectangleFactory = rectangleFactory;
    }

    @Override
    public void update(long delta) {
        background.update(delta);
        foreground.update(delta);
        physics.update(delta);
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {

        // todo: move to some background thing
        graphics.draw("img/backgrounds/background.png", 0, 0);

        background.draw(graphics);
        foreground.draw(graphics);

    }

    @Override
    public void enteringState(String previousStateName) {
        background = new EntityManager();
        foreground = new EntityManager();
        physics = new FrostbiteEngine();

        physics.add(playerController);

        SceneLoader loader = new SceneLoader("test", playerController, rectangleFactory);

        try {
            loadZombies(loader);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        playerController.setPosition(200, 90);
        foreground.add(playerController);
    }

    private void loadZombies(SceneLoader loader) throws FileNotFoundException {
        for (IPhysicsController entity : loader.getZombies()) {
            foreground.add(entity);
            physics.add(entity);
        }
    }

    @Override
    public void leavingState(String nextStateName) {

    }
}
