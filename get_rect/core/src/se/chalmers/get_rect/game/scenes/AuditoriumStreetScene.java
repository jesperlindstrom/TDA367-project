package se.chalmers.get_rect.game.scenes;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.IController;
import se.chalmers.get_rect.game.entities.enemies.zombie.ZombieFactory;
import se.chalmers.get_rect.game.entities.player.PlayerController;
import se.chalmers.get_rect.game.entities.player.PlayerFactory;


public class AuditoriumStreetScene implements IScene {
    private PlayerController player;
    private IController zombie;

    public AuditoriumStreetScene(PlayerController player) {
        this.player = player;

        ZombieFactory zombieFactory = new ZombieFactory();
        zombie = zombieFactory.make();
    }

    @Override
    public void update(long delta) {
        player.update(delta);
        zombie.update(delta);
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.start();
        player.draw(graphics);
        zombie.draw(graphics);
        graphics.end();
    }

    @Override
    public void enteringState(String previousStateName) {

    }

    @Override
    public void leavingState(String nextStateName) {

    }
}
