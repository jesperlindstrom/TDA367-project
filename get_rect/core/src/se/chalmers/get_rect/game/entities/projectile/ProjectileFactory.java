package se.chalmers.get_rect.game.entities.projectile;

import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
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

    public IPhysicsEntity make(Point point, Point velocity, int damage, IModel owner) {
        Projectile model = new Projectile(point, velocity, damage, rectangleFactory, owner);
        IView view = new ProjectileView(model);

        return new PhysicsEntity(model, view);
    }
}
