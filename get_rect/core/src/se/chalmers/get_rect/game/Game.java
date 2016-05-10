package se.chalmers.get_rect.game;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import se.chalmers.get_rect.adapters.*;
import se.chalmers.get_rect.game.entities.EntityCamera;
import se.chalmers.get_rect.game.entities.IPhysicsEntity;
import se.chalmers.get_rect.game.entities.player.PlayerController;
import se.chalmers.get_rect.game.entities.player.PlayerFactory;
import se.chalmers.get_rect.game.scenes.IScene;
import se.chalmers.get_rect.game.scenes.horsalsvagen.HorsalsvagenScene;
import se.chalmers.get_rect.game.scenes.test.TestScene;
import se.chalmers.get_rect.game.entities.window.controller.IWindowController;
import se.chalmers.get_rect.game.entities.window.WindowFactory;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.states.*;

public class Game implements IGame {
    private Injector injector;
    private StateManager<IScene> sceneManager;
    private StateManager<IWindowController> windowManager;
    private PlayerController playerController;
    private EntityCamera cameraManager;
    private IGraphicsAdapter graphics;
    private IInputAdapter input;
    private IGameLoopAdapter gameLoop;
    private IAssetManagerAdapter assetManager;
    private IRectangleFactoryAdapter rectangleFactory;
    private boolean paused = true;


    @Inject
    public Game(Injector rootInjector, IGraphicsAdapter graphics, IInputAdapter input, IGameLoopAdapter gameLoop, IAssetManagerAdapter assetManager, IRectangleFactoryAdapter rectangleFactory, ICameraFactoryAdapter cameraFactory) {
        this.gameLoop = gameLoop;
        this.graphics = graphics;
        this.assetManager = assetManager;
        this.input = input;
        this.rectangleFactory = rectangleFactory;

        PlayerFactory playerFactory = rootInjector.getInstance(PlayerFactory.class);
        playerController = rootInjector.getInstance(PlayerController.class);
        IPhysicsEntity player = playerFactory.make(playerController);

        cameraManager = new EntityCamera(cameraFactory, player.getModel());
        injector = rootInjector.createChildInjector(new GameModule(player, cameraManager, this));

        sceneManager = injector.getInstance(Key.get(new TypeLiteral<StateManager<IScene>>() {}));
        windowManager = injector.getInstance(Key.get(new TypeLiteral<StateManager<IWindowController>>() {}));

        addComponents(); //todo: find a better name

        // Set the active state
        windowManager.set(GameConfig.SPLASH);
    }

    /**
     * Tell current state to draw
     */
    public void draw() {
        cameraManager.draw(graphics);
        graphics.clear();
        graphics.start();
        if (sceneManager.getState() != null) {
            sceneManager.getState().draw(graphics);
        }
        if (paused) {
            windowManager.getState().draw(graphics);
        }
        graphics.end();
    }

    private void addComponents() {
        // todo: factory for scenes?

        // Register scenes
        sceneManager.add(GameConfig.TEST, injector.getInstance(TestScene.class));
        sceneManager.add(GameConfig.HORSALSVAGEN, injector.getInstance(HorsalsvagenScene.class));
        // todo: this is bad and Sune should feel bad
        sceneManager.add(GameConfig.NULL, null);

        WindowFactory window = injector.getInstance(WindowFactory.class);
        windowManager.add(GameConfig.SPLASH, window.make("splash"));
        windowManager.add(GameConfig.MAIN_MENU, window.make("mainMenu"));
        windowManager.add(GameConfig.INGAME_MENU, window.make("inGameMenu"));
    }

    public StateManager<IWindowController> getWindowManager() {
        return windowManager;
    }

    public StateManager<IScene> getSceneManager() {
        return sceneManager;
    }

    /**
     * Tell current state to update
     * @param delta Time since last draw
     */
    public void update(double delta) {

        handleInput();
        // Will update the menu if it is active and pause the current scene.
        if (paused) {
            windowManager.getState().update(delta);
        } else {
            playerController.update();
            if (sceneManager.getState() != null) {
                sceneManager.getState().update(delta);
            }
        }
        cameraManager.update(delta);
    }

    private void handleInput() {
        if (input.isKeyJustPressed(IInputAdapter.Keys.ESC)) {
            if (paused) {
                resume();
            } else {
                windowManager.set(GameConfig.INGAME_MENU);
                paused = true;
            }
        }
    }

    /**
     * Get the input instance
     * @return Input adapter
     */
    @Override
    public IInputAdapter getInput() {
        return input;
    }

    /**
     * Get the assetManager instance
     * @return assetManager adapter
     */
    @Override
    public IAssetManagerAdapter getAssetManager() {
        return assetManager;
    }

    /**
     * Get the rectangle factory
     * @return rectangleFactory adapter
     */
    @Override
    public IRectangleFactoryAdapter getRectangleFactory() {
        return rectangleFactory;
    }

    @Override
    public EntityCamera getCameraManager() {
        return cameraManager;
    }

    @Override
    public void exit() {
        assetManager.dispose();
        gameLoop.exit();
    }

    public void exitToMainMenu() {
        sceneManager.set(GameConfig.NULL);
        windowManager.set(GameConfig.MAIN_MENU);
    }

    @Override
    public void load() {
        sceneManager.set(GameConfig.HORSALSVAGEN);
        resume();
    }

    @Override
    public void save() {

    }

    @Override
    public void resume() {
        paused = false;
    }

    @Override
    public void startNew() {
        sceneManager.set(GameConfig.HORSALSVAGEN);
        resume();
    }

    @Override
    public boolean loadAvailable() {
        return true;
    }


}