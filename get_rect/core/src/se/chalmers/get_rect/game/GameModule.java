package se.chalmers.get_rect.game;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.entities.enemies.EnemyRepository;
import se.chalmers.get_rect.game.entities.npc.NpcRepository;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.game.window.model.IGameControl;
import se.chalmers.get_rect.game.entities.worldObjects.WorldObjectRepository;
import se.chalmers.get_rect.game.input.GameInput;
import se.chalmers.get_rect.game.quests.QuestManager;
import se.chalmers.get_rect.game.world.IWorld;
import se.chalmers.get_rect.game.window.controller.IWindowController;
import se.chalmers.get_rect.states.StateManager;

public class GameModule extends AbstractModule {
    private IEntity playerEntity;
    private EntityCamera camera;
    private IGameControl game;
    private GameInput gameInput;
    private QuestManager questManager;

    public GameModule(IEntity playerEntity, EntityCamera camera, IGameControl game, GameInput gameInput, QuestManager questManager) {
        this.playerEntity = playerEntity;
        this.camera = camera;
        this.game = game;
        this.gameInput = gameInput;
        this.questManager = questManager;
    }

    @Override
    protected void configure() {
        // Player
        bind(IEntity.class).annotatedWith(Names.named("Player")).toInstance(playerEntity);
        bind(IModel.class).annotatedWith(Names.named("Player")).toInstance(playerEntity.getModel());
        bind(IPhysicsModel.class).annotatedWith(Names.named("Player")).toInstance((IPhysicsModel) playerEntity.getModel());
        bind(Player.class).toInstance((Player) playerEntity.getModel());

        bind(GameInput.class).toInstance(gameInput);
        bind(ICamera.class).toInstance(camera);
        bind(EntityCamera.class).toInstance(camera);
        bind(IGameControl.class).toInstance(game);
        bind(QuestManager.class).toInstance(questManager);

        // SceneManager and WindowManager
        StateManager<IWorld> sceneManager = new StateManager<>();
        bind(new TypeLiteral<StateManager<IWorld>>() {}).toInstance(sceneManager);
        bind(StateManager.class).annotatedWith(Names.named("World")).toInstance(sceneManager);
        StateManager<IWindowController> windowManager = new StateManager<>();
        bind(new TypeLiteral<StateManager<IWindowController>>() {}).toInstance(windowManager);
        bind(StateManager.class).annotatedWith(Names.named("Window")).toInstance(windowManager);

        bind(ErrorHandler.class).toInstance(new ErrorHandler(windowManager));

        // Repositories
        TypeLiteral repository = new TypeLiteral<IRepository<IEntity>>() {};
        bind(repository).annotatedWith(Names.named("worldObject")).to(WorldObjectRepository.class);
        bind(repository).annotatedWith(Names.named("npc")).to(NpcRepository.class);
        bind(repository).annotatedWith(Names.named("enemy")).to(EnemyRepository.class);
    }
}
