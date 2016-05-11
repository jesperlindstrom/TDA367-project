package se.chalmers.get_rect.game.entities.player;

import com.google.inject.Inject;
import se.chalmers.get_rect.game.entities.item.ItemFactory;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.entities.item.damageBoxes.ProjectileFactory;
import se.chalmers.get_rect.game.entities.PhysicsEntity;

public class PlayerFactory {
    @Inject private IRectangleFactoryAdapter rectangleFactory;
    @Inject private ProjectileFactory projectileFactory;
    @Inject private ItemFactory itemFactory;

    public PhysicsEntity make() {
        Player model = new Player(rectangleFactory, projectileFactory, itemFactory);
        IView view = new PlayerView(model);

        return new PhysicsEntity(model,view);
    }
}
