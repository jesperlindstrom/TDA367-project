package se.chalmers.get_rect.game.entities.projectile;

import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.entities.EntityNotFoundException;
import se.chalmers.get_rect.game.entities.IPhysicsEntity;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.entities.PhysicsEntity;
import se.chalmers.get_rect.game.entities.projectile.model.Projectile;
import se.chalmers.get_rect.game.entities.projectile.view.ProjectileView;
import se.chalmers.get_rect.utilities.Point;

public class ProjectileFactory {
    private IRectangleFactoryAdapter rectangleFactory;

    public ProjectileFactory(IRectangleFactoryAdapter rectangleFactory){
        this.rectangleFactory = rectangleFactory;
    }

    public IPhysicsEntity make(String type, Point point, Point velocity, IModel owner){
        if (type.equals("normal"))
            return makeProjectile(point, velocity, owner);

        if (type.equals("melee"))
            return makeMeleeProjectile(point, velocity, owner);

        throw new EntityNotFoundException("Projectile", type);
    }

    private IPhysicsEntity makeProjectile(Point point, Point velocity, IModel owner) {
        Projectile model = new Projectile(point, velocity, rectangleFactory, owner, false);
        IView view = new ProjectileView(model);

        return new PhysicsEntity(model, view);
    }

    private IPhysicsEntity makeMeleeProjectile(Point point, Point velocity, IModel owner){
        Projectile model = new Projectile(point, velocity, rectangleFactory, owner, true);
        IView view = new ProjectileView(model);

        return new PhysicsEntity(model, view);
    }
}
