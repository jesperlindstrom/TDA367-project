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
    @Inject @Named("Player") private IModel player;
    @Inject private IRectangleFactoryAdapter rectangleFactory;
    @Inject private IAudioManagerAdapter audioManager;

    public IEntity make(String enemyType, Point position) {
        if (enemyType.equals("zombie"))
            return makeZombie(position);

        if (enemyType.equals("demon"))
            return makeDemon(position);

        if (enemyType.equals("broccolighost"))
            return makeGhost(position);

        throw new EntityNotFoundException("enemy", enemyType);
    }

    private IEntity makeZombie(Point position) {
        Zombie model = new Zombie(position, rectangleFactory, player);
        IView view = new ZombieView(model, audioManager);

        return new Entity(model, view);
    }
    private IEntity makeDemon(Point position) {
        Demon model = new Demon(position, rectangleFactory, player);
        IView view = new DemonView(model, audioManager);

        return new Entity(model, view);
    }

    private IEntity makeGhost(Point position) {
        GhostBroccoli model = new GhostBroccoli(position, rectangleFactory, player);
        IView view = new GhostBroccoliView(model);

        return new Entity(model, view);
    }
}