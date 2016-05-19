package se.chalmers.get_rect.game;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.entities.enemies.EnemyRepository;
import se.chalmers.get_rect.game.entities.npc.NpcRepository;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.game.entities.window.model.IGameControl;
import se.chalmers.get_rect.game.entities.worldObjects.WorldObjectRepository;
import se.chalmers.get_rect.game.input.GameInput;
import se.chalmers.get_rect.game.scenes.IScene;
import se.chalmers.get_rect.game.entities.window.controller.IWindowController;
import se.chalmers.get_rect.states.StateManager;

import java.lang.reflect.Type;

public class GameModule extends AbstractModule {
    private IPhysicsEntity playerEntity;
    private EntityCamera camera;
    private IGameControl game;
    private GameInput gameInput;

    public GameModule(IPhysicsEntity playerEntity, EntityCamera camera, IGameControl game, GameInput gameInput) {
        this.playerEntity = playerEntity;
        this.camera = camera;
        this.game = game;
        this.gameInput = gameInput;
    }

    @Override
    protected void configure() {
        // Player
        bind(IPhysicsEntity.class).annotatedWith(Names.named("Player")).toInstance(playerEntity);
        bind(IPhysicsModel.class).annotatedWith(Names.named("Player")).toInstance(playerEntity.getModel());
        bind(Player.class).toInstance((Player) playerEntity.getModel());

        bind(GameInput.class).toInstance(gameInput);
        bind(ICamera.class).toInstance(camera);
        bind(EntityCamera.class).toInstance(camera);
        bind(IGameControl.class).toInstance(game);

        // SceneManager and WindowManager
        StateManager<IScene> sceneManager = new StateManager<>();
        bind(new TypeLiteral<StateManager<IScene>>() {}).toInstance(sceneManager);
        bind(StateManager.class).annotatedWith(Names.named("Scene")).toInstance(sceneManager);
        StateManager<IWindowController> windowManager = new StateManager<>();
        bind(new TypeLiteral<StateManager<IWindowController>>() {}).toInstance(windowManager);
        bind(StateManager.class).annotatedWith(Names.named("Window")).toInstance(windowManager);

        // Repositories

        TypeLiteral repository = new TypeLiteral<IRepository<IPhysicsEntity>>() {};
        bind(repository).annotatedWith(Names.named("worldObject")).to(WorldObjectRepository.class);
        bind(repository).annotatedWith(Names.named("npc")).to(NpcRepository.class);
        bind(repository).annotatedWith(Names.named("enemy")).to(EnemyRepository.class);
    }
}
