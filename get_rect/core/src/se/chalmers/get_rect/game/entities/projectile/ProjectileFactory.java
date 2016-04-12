package se.chalmers.get_rect.game.entities.projectile;

import se.chalmers.get_rect.IGame;
import se.chalmers.get_rect.adapters.IRectangleAdapter;
import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.utilities.Point;

public class ProjectileFactory {
    private IRectangleFactoryAdapter rectangleFactory;
    private int damage;
    private int speed;

    public ProjectileFactory(IRectangleFactoryAdapter rectangleFactory){
        this.rectangleFactory = rectangleFactory;
    }

    public ProjectileController make(int x, int y){
        Point point = new Point(x, y);
        return make(point);
    }
    public ProjectileController make(Point point){
        Projectile model = new Projectile(point, rectangleFactory);
        IView view = new ProjectileView(model);

        return new ProjectileController(model, view);
    }
}
