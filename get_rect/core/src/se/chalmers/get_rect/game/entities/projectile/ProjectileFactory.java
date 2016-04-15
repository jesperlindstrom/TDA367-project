package se.chalmers.get_rect.game.entities.projectile;

import se.chalmers.get_rect.IGame;
import se.chalmers.get_rect.adapters.IRectangleAdapter;
import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.IPhysicsEntity;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.entities.PhysicsEntity;
import se.chalmers.get_rect.utilities.Point;

public class ProjectileFactory {
    private IRectangleFactoryAdapter rectangleFactory;
    private int damage;
    private int speed;

    public ProjectileFactory(IRectangleFactoryAdapter rectangleFactory){
        this.rectangleFactory = rectangleFactory;
    }

    public IPhysicsEntity make(int x, int y){
        Point point = new Point(x, y);
        return make(point);
    }
    public IPhysicsEntity make(Point point){
        Projectile model = new Projectile(point, rectangleFactory);
        IView view = new ProjectileView(model);

        return new PhysicsEntity(model, view);
    }
}
