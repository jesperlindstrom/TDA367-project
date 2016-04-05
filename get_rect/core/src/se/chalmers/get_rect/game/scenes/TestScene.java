package se.chalmers.get_rect.game.scenes;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.player.PlayerController;


public class TestScene implements IScene {
    private PlayerController playerController;

    public TestScene(PlayerController playerController) {
        this.playerController = playerController;
    }

    @Override
    public void update(long delta) {
        playerController.update(delta);
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.start();
        graphics.draw("data/background.png", 0, 0);
        playerController.draw(graphics);
        graphics.end();
    }

    @Override
    public void enteringState(String previousStateName) {

    }

    @Override
    public void leavingState(String nextStateName) {

    }

}
