package se.chalmers.get_rect.game.scenes;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.Entity;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.game.entities.player.PlayerFactory;

public class AuditoriumStreetScene implements IScene {
    private Entity<Player> player;

    public AuditoriumStreetScene() {
        PlayerFactory playerFactory = new PlayerFactory();
        player = playerFactory.make();
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
