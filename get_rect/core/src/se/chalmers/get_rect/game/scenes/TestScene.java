package se.chalmers.get_rect.game.scenes;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.IController;
import se.chalmers.get_rect.game.entities.player.PlayerController;
import se.chalmers.get_rect.game.loaders.SceneLoader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class TestScene implements IScene {
    private List<IController> entities;

    public TestScene(PlayerController playerController) {
        entities = new ArrayList<>();
        entities.add(playerController);

        SceneLoader loader = new SceneLoader("test");

        try {
            entities.addAll(loader.getZombies());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(long delta) {
        for (IController entity : entities) {
            entity.update(delta);
        }
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.start();
        graphics.draw("data/background.png", 0, 0);

        for (IController entity : entities) {
            entity.draw(graphics);
        }

        graphics.end();
    }

    @Override
    public void enteringState(String previousStateName) {

    }

    @Override
    public void leavingState(String nextStateName) {

    }

}
