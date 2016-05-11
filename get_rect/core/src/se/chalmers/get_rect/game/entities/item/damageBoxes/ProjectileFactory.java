package se.chalmers.get_rect.game.entities.item.damageBoxes;

import com.google.inject.Inject;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.entities.IPhysicsEntity;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.entities.PhysicsEntity;
import se.chalmers.get_rect.game.entities.item.damageBoxes.model.Projectile;
import se.chalmers.get_rect.game.entities.item.damageBoxes.view.ProjectileView;
import se.chalmers.get_rect.utilities.Point;

public class ProjectileFactory {
    @Inject private IRectangleFactoryAdapter rectangleFactory;

    public IPhysicsEntity make(Point point, Point velocity, int damage, IModel owner) {
        Projectile model = new Projectile(point, velocity, damage, rectangleFactory, owner);
        IView view = new ProjectileView(model);

        return new PhysicsEntity(model, view);
    }
}
