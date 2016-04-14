package se.chalmers.get_rect.game.entities.enemies.zombie;

import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.entities.IPhysicsEntity;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.entities.PhysicsEntity;
import se.chalmers.get_rect.game.entities.player.PlayerController;
import se.chalmers.get_rect.utilities.Point;

public class ZombieFactory {

    private IModel player;
    private IRectangleFactoryAdapter rectangleFactory;

    public ZombieFactory(IModel player, IRectangleFactoryAdapter rectangleFactory) {
        this.player = player;
        this.rectangleFactory = rectangleFactory;
    }

    public IPhysicsEntity make() {
        return make(0, 0);
    }

    public IPhysicsEntity make(ZombieDataStore data) {
        return make(data.getX(), data.getY());
    }

    public IPhysicsEntity make(int x, int y) {
        Zombie model = new Zombie(new Point(x, y), rectangleFactory, player);
        IView view = new ZombieView(model);

        return new PhysicsEntity(model, view) {
        };
    }
}