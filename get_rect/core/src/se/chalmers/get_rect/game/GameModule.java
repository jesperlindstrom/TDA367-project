package se.chalmers.get_rect.game;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.entities.enemies.EnemyRepository;
import se.chalmers.get_rect.game.entities.npc.NpcRepository;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.game.entities.worldObjects.WorldObjectRepository;
import se.chalmers.get_rect.game.scenes.IScene;
import se.chalmers.get_rect.game.entities.window.controller.IWindowController;
import se.chalmers.get_rect.states.StateManager;

public class GameModule extends AbstractModule {
    private IPhysicsEntity playerEntity;
    private EntityCamera camera;
    private IGame game;

    public GameModule(IPhysicsEntity playerEntity, EntityCamera camera, IGame game) {
        this.playerEntity = playerEntity;
        this.camera = camera;
        this.game = game;
    }

    @Override
    protected void configure() {
        // Player
        bind(IPhysicsEntity.class).annotatedWith(Names.named("Player")).toInstance(playerEntity);
        bind(IPhysicsModel.class).annotatedWith(Names.named("Player")).toInstance(playerEntity.getModel());
        bind(Player.class).toInstance((Player) playerEntity.getModel());

        bind(ICamera.class).toInstance(camera);
        bind(EntityCamera.class).toInstance(camera);
        bind(IGame.class).toInstance(game);

        // SceneManager and WindowManager
        bind(new TypeLiteral<StateManager<IScene>>() {}).toInstance(new StateManager<>());
        bind(new TypeLiteral<StateManager<IWindowController>>() {}).toInstance(new StateManager<>());

        // Repositories
        bind(IRepository.class).annotatedWith(Names.named("worldObject")).to(WorldObjectRepository.class);
        bind(IRepository.class).annotatedWith(Names.named("npc")).to(NpcRepository.class);
        bind(IRepository.class).annotatedWith(Names.named("enemy")).to(EnemyRepository.class);
    }
}
