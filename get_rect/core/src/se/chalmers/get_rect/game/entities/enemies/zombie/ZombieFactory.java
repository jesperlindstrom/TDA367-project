package se.chalmers.get_rect.game.entities.enemies.zombie;

import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.utilities.Point;

public class ZombieFactory {

    private IPhysicsModel player;
    private IRectangleFactoryAdapter rectangleFactory;

    public ZombieFactory(IPhysicsModel player, IRectangleFactoryAdapter rectangleFactory) {
        this.player = player;
        this.rectangleFactory = rectangleFactory;
    }

    public IPhysicsEntity make(ZombieDataStore data) {
        return make(data.getPosition());
    }

    public IPhysicsEntity make(Point position) {
        Zombie model = new Zombie(position, rectangleFactory, player);
        IView view = new ZombieView(model);

        return new PhysicsEntity(model, view);
    }
}