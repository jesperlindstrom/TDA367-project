package se.chalmers.get_rect.game.entities.item.projectile;

import com.google.inject.Inject;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.utilities.Point;

public class ProjectileFactory {
    @Inject private IRectangleFactoryAdapter rectangleFactory;

    public IEntity make(String type, Point point, Point velocity, int damage, IModel owner) {
        if (type.equals("bullet"))
            return makeBullet(point, velocity, damage, owner);

        if (type.equals("arcane"))
            return makeMagic(point, velocity, damage, owner);

        if (type.equals("fireMagic"))
            return makeFireMagic(point, velocity, damage, owner, false);

        if (type.equals("fireMagicCluster")) {
            return makeFireMagic(point, velocity, damage, owner, true);
        }

        if (type.equals("rock")) {
            return makeRock(point, velocity, damage, owner);
        }

        throw new EntityNotFoundException("projectile", type);
    }

    private IEntity makeBullet(Point point, Point velocity, int damage, IModel owner) {
        Projectile model = new Projectile(point, velocity, damage, rectangleFactory, owner, false);
        IView view = new BulletProjectileView(model);
        return new Entity(model, view);
    }

    private IEntity makeMagic(Point point, Point velocity, int damage, IModel owner) {
        Projectile model = new Projectile(point, velocity, damage, rectangleFactory, owner, false);
        IView view = new ArcaneMagicProjectileView(model);
        return new Entity(model, view);
    }

    private IEntity makeFireMagic(Point point, Point velocity, int damage, IModel owner, boolean cluster) {
        Projectile model;

        if (cluster) {
            model = new Projectile(point, velocity, damage / 2, rectangleFactory, owner, true);
        } else {
            model = new Projectile(point, velocity, damage, rectangleFactory, owner, true, this, "fireMagicCluster");
        }

        IView view = new FireMagicProjectileView(model);
        return new Entity(model, view);
    }
    private IEntity makeRock(Point point, Point velocity, int damage, IModel owner) {
        Projectile model = new Projectile(point, velocity, damage, rectangleFactory, owner, true);
        IView view = new RockProjectileView(model);
        return new Entity(model, view);
    }
}
