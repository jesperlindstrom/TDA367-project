package se.chalmers.get_rect.game.entities.enemies;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.entities.enemies.model.Zombie;
import se.chalmers.get_rect.game.entities.enemies.view.ZombieView;
import se.chalmers.get_rect.utilities.Point;

public class EnemyFactory {
    @Inject @Named("Player") private IPhysicsModel player;
    @Inject private IRectangleFactoryAdapter rectangleFactory;

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