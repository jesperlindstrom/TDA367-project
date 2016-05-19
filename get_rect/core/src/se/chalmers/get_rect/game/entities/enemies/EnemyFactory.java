package se.chalmers.get_rect.game.entities.enemies;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import se.chalmers.get_rect.adapters.IAssetManagerAdapter;
import se.chalmers.get_rect.adapters.IAudioManagerAdapter;
import se.chalmers.get_rect.game.entities.enemies.model.Demon;
import se.chalmers.get_rect.game.entities.enemies.model.GhostBroccoli;
import se.chalmers.get_rect.game.entities.enemies.view.DemonView;
import se.chalmers.get_rect.game.entities.enemies.view.GhostBroccoliView;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.entities.enemies.model.Zombie;
import se.chalmers.get_rect.game.entities.enemies.view.ZombieView;
import se.chalmers.get_rect.utilities.Point;

public class EnemyFactory {
    @Inject @Named("Player") private IPhysicsModel player;
    @Inject private IRectangleFactoryAdapter rectangleFactory;
    @Inject private IAudioManagerAdapter audioManager;

    public IPhysicsEntity make(String enemyType, Point position) {
        if (enemyType.equals("zombie"))
            return makeZombie(position);
        if (enemyType.equals("demon"))
            return makeDemon(position);
        if (enemyType.equals("broccolighost"))
            return makeGhost(position);
        throw new EntityNotFoundException("enemy", enemyType);
    }

    private IPhysicsEntity makeZombie(Point position) {
        Zombie model = new Zombie(position, rectangleFactory, player);
        IView view = new ZombieView(model);

        return new PhysicsEntity(model, view);
    }
    private IPhysicsEntity makeDemon(Point position) {
        Demon model = new Demon(position, rectangleFactory, player);
        IView view = new DemonView(model, audioManager);

        return new PhysicsEntity(model, view);
    }

    private IPhysicsEntity makeGhost(Point position) {
        GhostBroccoli model = new GhostBroccoli(position, rectangleFactory, player);
        IView view = new GhostBroccoliView(model);

        return new PhysicsEntity(model, view);
    }
}