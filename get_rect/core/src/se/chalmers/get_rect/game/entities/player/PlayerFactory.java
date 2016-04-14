package se.chalmers.get_rect.game.entities.player;

import se.chalmers.get_rect.IGame;
import se.chalmers.get_rect.adapters.IInputAdapter;
import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.IPhysicsEntity;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.entities.PhysicsEntity;
import se.chalmers.get_rect.utilities.Point;

public class PlayerFactory {
    private PlayerController playerController;
    private IRectangleFactoryAdapter rectangleFactory;
    /**
     * Initialize a new player factory.
     * @param game
     */
    public PlayerFactory(PlayerController playerController, IGame game){
        this.rectangleFactory = game.getRectangleFactory();
        this.playerController = playerController;

    }

    /**
     * Maker for a new player.
     * @return
     */
    public PhysicsEntity make(Point position) {
        Player model = new Player(new Point(position), rectangleFactory);
        IView view = new PlayerView(model);
        playerController.setPlayer(model);

        return new PhysicsEntity(model,view);
    }
}
