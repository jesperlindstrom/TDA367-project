package se.chalmers.get_rect.game.entities.enemies;

import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.entities.enemies.model.Zombie;
import se.chalmers.get_rect.game.entities.enemies.view.ZombieView;
import se.chalmers.get_rect.utilities.Point;

public class EnemyFactory {

    private IPhysicsModel player;
    private IRectangleFactoryAdapter rectangleFactory;

    public EnemyFactory(IPhysicsModel player, IRectangleFactoryAdapter rectangleFactory) {
        this.player = player;
        this.rectangleFactory = rectangleFactory;
    }

    public IPhysicsEntity make(EnemyDataStore data) {
        return make(data.getType(), data.getPosition());
    }

    public IPhysicsEntity make(String enemyType, Point position) {
        if (enemyType.equals("zombie"))
            return makeZombie(position);

        throw new EntityNotFoundException("enemy", enemyType);
    }

    private IPhysicsEntity makeZombie(Point position) {
        Zombie model = new Zombie(position, rectangleFactory, player);
        IView view = new ZombieView(model);

        return new PhysicsEntity(model, view);
    }
}