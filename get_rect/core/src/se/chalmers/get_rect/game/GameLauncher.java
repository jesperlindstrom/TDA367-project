package se.chalmers.get_rect.game;

import com.google.inject.Inject;
import com.google.inject.Injector;
import se.chalmers.get_rect.adapters.*;
import se.chalmers.get_rect.game.entities.EntityCamera;
import se.chalmers.get_rect.game.entities.IEntity;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.entities.item.WeaponFactory;
import se.chalmers.get_rect.game.entities.item.WeaponRepository;
import se.chalmers.get_rect.game.window.model.IGameControl;
import se.chalmers.get_rect.game.input.GameInput;
import se.chalmers.get_rect.game.entities.player.PlayerFactory;
import se.chalmers.get_rect.game.quests.QuestManager;

public class GameLauncher implements IGameControl {
    @Inject private IGameLoopAdapter gameLoop;
    @Inject private IAssetManagerAdapter assetManager;
    @Inject private IGraphicsAdapter graphics;

    private GameController gameController;
    private EntityCamera cameraManager;

    @Inject
    public GameLauncher(Injector rootInjector, ICameraFactoryAdapter cameraFactory, IKeyboardInputAdapter keyboard, IControllerInputAdapter controller, WeaponFactory weaponFactory) {
        GameInput input = new GameInput(keyboard, controller);

        PlayerFactory playerFactory = rootInjector.getInstance(PlayerFactory.class);
        IEntity player = playerFactory.make();

        cameraManager = new EntityCamera(cameraFactory, (IPhysicsModel) player.getModel());
        WeaponRepository weaponRepository = new WeaponRepository(weaponFactory);

        QuestManager questManager = new QuestManager();
        Injector injector = rootInjector.createChildInjector(new GameModule(player, cameraManager, this, input, questManager, weaponRepository));

        gameController = injector.getInstance(GameController.class);

        gameController.setup();
    }

    /**
     * Tell current state to drawIcon
     */
    public void draw() {
        graphics.clear();
        graphics.start();
        cameraManager.draw(graphics);
        gameController.draw();
        graphics.end();
    }

    /**
     * Tell current state to update
     * @param delta Time since last drawIcon
     */
    public void update(double delta) {
        gameController.update(delta);
        cameraManager.update(delta);
    }

    @Override
    public void exit() {
        assetManager.dispose();
        gameLoop.exit();
    }

    @Override
    public void load() {
        gameController.load();
    }

    @Override
    public void save() {
        gameController.save();
    }

    @Override
    public void resume() {
        gameController.resume();
    }

    @Override
    public void startNew() {
        gameController.startNew();
    }

    @Override
    public boolean loadAvailable() {
        return gameController.loadAvailable();
    }
}