package se.chalmers.get_rect.game.entities.player;

import se.chalmers.get_rect.IGame;
import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.entities.projectile.ProjectileFactory;
import se.chalmers.get_rect.game.entities.PhysicsEntity;

public class PlayerFactory {
    private PlayerController playerController;
    private IRectangleFactoryAdapter rectangleFactory;
    private ProjectileFactory projectileFactory;
    /**
     * Initialize a new player factory.
     * @param game
     */

    public PlayerFactory(PlayerController playerController, IGame game, ProjectileFactory projectilFactory){
        this.rectangleFactory = game.getRectangleFactory();
        this.playerController = playerController;
        this.projectileFactory = projectilFactory;

    }

    /**
     * Maker for a new player.
     * @return
     */
    public PhysicsEntity make() {
        Player model = new Player(rectangleFactory);
        IView view = new PlayerView(model);
        playerController.setPlayer(model);
        return new PhysicsEntity(model,view);
    }
}
