package se.chalmers.get_rect.game.scenes;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.player.PlayerController;

public class AuditoriumStreetScene implements IScene {
    private PlayerController player;

    public AuditoriumStreetScene(PlayerController player) {
        this.player = player;

    }

    @Override
    public void update(long delta) {
        player.update(delta);
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.start();
        player.draw(graphics);
        graphics.end();
    }

    @Override
    public void enteringState(String previousStateName) {

    }

    @Override
    public void leavingState(String nextStateName) {

    }
}
