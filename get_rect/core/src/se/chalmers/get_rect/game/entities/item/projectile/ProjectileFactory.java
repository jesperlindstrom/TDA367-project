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

    public IPhysicsEntity make(String type, Point point, Point velocity, int damage, IModel owner) {
        System.out.println("making " + type);
        if (type.equals("bullet"))
            return makeBullet(point, velocity, damage, owner);

        if (type.equals("magic"))
            return makeMagic(point, velocity, damage, owner);

        if (type.equals("fireMagic"))
            return makeFireMagic(point, velocity, damage, owner, false);

        if (type.equals("fireMagicCluster")) {
            return makeFireMagic(point, velocity, damage, owner, true);
        }

        throw new EntityNotFoundException("projectile", type);
    }

    private IPhysicsEntity makeBullet(Point point, Point velocity, int damage, IModel owner) {
        Projectile model = new Projectile(point, velocity, damage, rectangleFactory, owner, false);
        IView view = new BulletView(model);
        return new PhysicsEntity(model, view);
    }

    private IPhysicsEntity makeMagic(Point point, Point velocity, int damage, IModel owner) {
        Projectile model = new Projectile(point, velocity, damage, rectangleFactory, owner, false);
        IView view = new MagicView(model);
        return new PhysicsEntity(model, view);
    }

    private IPhysicsEntity makeFireMagic(Point point, Point velocity, int damage, IModel owner, boolean cluster) {
        Projectile model;

        if (cluster) {
            model = new Projectile(point, velocity, damage / 2, rectangleFactory, owner, true);
        } else {
            model = new Projectile(point, velocity, damage, rectangleFactory, owner, true, this, "fireMagicCluster");
        }

        IView view = new FireMagicView(model);
        return new PhysicsEntity(model, view);
    }
}
