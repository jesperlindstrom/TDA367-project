package se.chalmers.get_rect.game.entities.player;

import com.google.inject.Inject;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.entities.projectile.ProjectileFactory;
import se.chalmers.get_rect.game.entities.PhysicsEntity;

public class PlayerFactory {
    private IRectangleFactoryAdapter rectangleFactory;
    private ProjectileFactory projectileFactory;

    /**
     * Initialize a new player factory.
     * @param game
     */
    @Inject
    public PlayerFactory(IRectangleFactoryAdapter rectangleFactory, ProjectileFactory projectileFactory) {
        this.rectangleFactory = rectangleFactory;
    }

    /**
     * Maker for a new player.
     * @return
     */
    public PhysicsEntity make(PlayerController controller) {
        Player model = new Player(rectangleFactory, projectileFactory);
        IView view = new PlayerView(model);

        controller.setPlayer(model);

        return new PhysicsEntity(model,view);
    }
}
