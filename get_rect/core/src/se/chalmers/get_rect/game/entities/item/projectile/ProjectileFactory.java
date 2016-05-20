package se.chalmers.get_rect.game.entities.item.projectile;

import com.google.inject.Inject;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.entities.IPhysicsEntity;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.entities.PhysicsEntity;
import se.chalmers.get_rect.utilities.Point;

public class ProjectileFactory {
    @Inject private IRectangleFactoryAdapter rectangleFactory;

    public IPhysicsEntity makeBullet(Point point, Point velocity, int damage, IModel owner) {
        Projectile model = new Projectile(point, velocity, damage, rectangleFactory, owner);
        IView view = new BulletView(model);
        return new PhysicsEntity(model, view);
    }

    public IPhysicsEntity makeMagic(Point point, Point velocity, int damage, IModel owner) {
        Projectile model = new Projectile(point, velocity, damage, rectangleFactory, owner);
        IView view = new MagicView(model);
        return new PhysicsEntity(model, view);
    }
}
