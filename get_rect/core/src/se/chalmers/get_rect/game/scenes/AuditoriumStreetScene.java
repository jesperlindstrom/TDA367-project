package se.chalmers.get_rect.game.scenes;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.IController;
import se.chalmers.get_rect.game.entities.IPhysicsController;
import se.chalmers.get_rect.game.entities.enemies.zombie.ZombieFactory;
import se.chalmers.get_rect.game.entities.player.PlayerController;
import se.chalmers.get_rect.game.entities.player.PlayerFactory;


public class AuditoriumStreetScene implements IScene {
    private PlayerController player;

    public AuditoriumStreetScene(PlayerController player) {
        this.player = player;
    }

    @Override
    public void update(double delta) {

    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.start();

        graphics.end();
    }

    @Override
    public void enteringState(String previousStateName) {

    }

    @Override
    public void leavingState(String nextStateName) {

    }

    @Override
    public void addEntity(layer layer, IPhysicsController physicsController) {

    }
}
