package se.chalmers.get_rect.game.entities.projectile;

import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.IPhysicsEntity;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.entities.PhysicsEntity;
import se.chalmers.get_rect.utilities.Point;

public class ProjectileFactory {
    private IRectangleFactoryAdapter rectangleFactory;

    public ProjectileFactory(IRectangleFactoryAdapter rectangleFactory){
        this.rectangleFactory = rectangleFactory;
    }

    public IPhysicsEntity make(Point point, Point velocity){
        Projectile model = new Projectile(point, velocity, rectangleFactory);
        IView view = new ProjectileView(model);

        return new PhysicsEntity(model, view);
    }
}
