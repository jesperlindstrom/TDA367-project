package se.chalmers.get_rect.game.entities.player;

import com.google.inject.Inject;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.entities.projectile.ProjectileFactory;
import se.chalmers.get_rect.game.entities.PhysicsEntity;

public class PlayerFactory {
    @Inject private IRectangleFactoryAdapter rectangleFactory;
    @Inject private ProjectileFactory projectileFactory;

    public PhysicsEntity make() {
        Player model = new Player(rectangleFactory, projectileFactory);
        IView view = new PlayerView(model);

        return new PhysicsEntity(model,view);
    }
}
