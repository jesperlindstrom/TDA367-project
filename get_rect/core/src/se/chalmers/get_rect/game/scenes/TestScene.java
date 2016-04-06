package se.chalmers.get_rect.game.scenes;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.EntityManager;
import se.chalmers.get_rect.game.entities.player.PlayerController;
import se.chalmers.get_rect.game.loaders.SceneLoader;

import java.io.FileNotFoundException;

public class TestScene implements IScene {

    private PlayerController playerController;
    private EntityManager background;
    private EntityManager foreground;

    public TestScene(PlayerController playerController) {
        this.playerController = playerController;

    }

    @Override
    public void update(long delta) {
        background.update(delta);
        foreground.update(delta);
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.start();

        // todo: move to some background thing
        graphics.draw("data/background.png", 0, 0);

        background.draw(graphics);
        foreground.draw(graphics);

        graphics.end();
    }

    @Override
    public void enteringState(String previousStateName) {
        background = new EntityManager();
        foreground = new EntityManager();
        foreground.add(playerController);

        SceneLoader loader = new SceneLoader("test", playerController);

        try {
            foreground.addAll(loader.getZombies());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        playerController.setPosition(200, 90);
    }

    @Override
    public void leavingState(String nextStateName) {

    }

}
