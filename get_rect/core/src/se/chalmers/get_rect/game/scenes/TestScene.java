package se.chalmers.get_rect.game.scenes;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.IController;
import se.chalmers.get_rect.game.entities.enemies.zombie.ZombieFactory;
import se.chalmers.get_rect.game.entities.player.PlayerController;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


public class TestScene implements IScene {
    private PlayerController playerController;
    private List<IController> enemies;

    public TestScene(PlayerController playerController) {
        this.playerController = playerController;
    }

    @Override
    public void update(long delta) {
        playerController.update(delta);
        for (IController entity: enemies) {
            entity.update(delta);
        }

    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.start();
        graphics.draw("data/background.png", 0, 0);
        playerController.draw(graphics);
        for (IController entity: enemies) {
            entity.draw(graphics);
        }
        graphics.end();
    }

    @Override
    public void enteringState(String previousStateName) {
        playerController.setPosition(5, 90);
        enemies = new ArrayList<IController>();
        ZombieFactory zombieFactory = new ZombieFactory();
        enemies.add(zombieFactory.make(60, 90));
    }

    @Override
    public void leavingState(String nextStateName) {

    }

}
