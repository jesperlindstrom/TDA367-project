package se.chalmers.get_rect.game.scenes;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.IController;
import se.chalmers.get_rect.game.entities.IPhysicsController;
import se.chalmers.get_rect.game.entities.player.PlayerController;

public class StudentUnionHouseScene implements IScene {

    private PlayerController playerController;

    public StudentUnionHouseScene(PlayerController playerController) {
        this.playerController = playerController;
    }

    @Override
    public void update(long delta) {

    }

    @Override
    public void draw(IGraphicsAdapter graphics) {

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
