package se.chalmers.get_rect.game;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Names;
import se.chalmers.get_rect.game.entities.IPhysicsEntity;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.entities.player.PlayerController;
import se.chalmers.get_rect.game.entities.player.PlayerFactory;
import se.chalmers.get_rect.game.entities.projectile.ProjectileFactory;

public class GameModule extends AbstractModule {
    private IPhysicsEntity playerEntity;

    public GameModule() {
        playerController = new PlayerController(input);
        ProjectileFactory projectileFactory = new ProjectileFactory(rectangleFactory);
        PlayerFactory playerFactory = new PlayerFactory(rectangleFactory, projectileFactory);

        return playerFactory.make(playerController);

        this.playerEntity = playerEntity;
    }

    @Override
    protected void configure() {
        bind(ProjectileFactory.class).to(ProjectileFactory.class);
        bind(IPhysicsEntity.class).annotatedWith(Names.named("Player")).toInstance(playerEntity);
        bind(IPhysicsModel.class).annotatedWith(Names.named("Player")).toInstance(playerEntity.getModel());
    }
}
