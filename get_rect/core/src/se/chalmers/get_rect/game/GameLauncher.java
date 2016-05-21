package se.chalmers.get_rect.game;

import com.google.inject.Inject;
import com.google.inject.Injector;
import se.chalmers.get_rect.adapters.*;
import se.chalmers.get_rect.game.entities.EntityCamera;
import se.chalmers.get_rect.game.entities.IEntity;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.entities.window.model.IGameControl;
import se.chalmers.get_rect.game.input.GameInput;
import se.chalmers.get_rect.game.entities.player.PlayerFactory;
import se.chalmers.get_rect.game.quests.QuestManager;
import se.chalmers.get_rect.game.quests.QuestRepository;

public class GameLauncher implements IGameControl {
    @Inject private IGameLoopAdapter gameLoop;
    @Inject private IAssetManagerAdapter assetManager;
    @Inject private IGraphicsAdapter graphics;

    private Game game;
    private EntityCamera cameraManager;

    @Inject
    public GameLauncher(Injector rootInjector, ICameraFactoryAdapter cameraFactory, IKeyboardInputAdapter keyboard, IControllerInputAdapter controller, QuestRepository questRepository) {
        GameInput input = new GameInput(keyboard, controller);

        PlayerFactory playerFactory = rootInjector.getInstance(PlayerFactory.class);
        IEntity player = playerFactory.make();

        cameraManager = new EntityCamera(cameraFactory, (IPhysicsModel) player.getModel());

        QuestManager questManager = new QuestManager(questRepository);
        Injector injector = rootInjector.createChildInjector(new GameModule(player, cameraManager, this, input, questManager));

        game = injector.getInstance(Game.class);

        game.setup();
    }

    /**
     * Tell current state to drawIcon
     */
    public void draw() {
        graphics.clear();
        graphics.start();
        cameraManager.draw(graphics);
        game.draw();
        graphics.end();
    }

    /**
     * Tell current state to update
     * @param delta Time since last drawIcon
     */
    public void update(double delta) {
        game.update(delta);
        cameraManager.update(delta);
    }

    @Override
    public void exit() {
        assetManager.dispose();
        gameLoop.exit();
    }

    @Override
    public void load() {
        game.load();
    }

    @Override
    public void save() {
        game.save();
    }

    @Override
    public void resume() {
        game.resume();
    }

    @Override
    public void startNew() {
        game.startNew();
    }

    @Override
    public boolean loadAvailable() {
        return game.loadAvailable();
    }
}