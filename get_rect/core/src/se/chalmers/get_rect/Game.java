package se.chalmers.get_rect;

import se.chalmers.get_rect.adapters.*;
import se.chalmers.get_rect.game.CameraManager;
import se.chalmers.get_rect.game.IGame;
import se.chalmers.get_rect.game.IScene;
import se.chalmers.get_rect.game.entities.IPhysicsEntity;
import se.chalmers.get_rect.game.entities.player.PlayerController;
import se.chalmers.get_rect.game.entities.player.PlayerFactory;
import se.chalmers.get_rect.game.entities.projectile.ProjectileFactory;
import se.chalmers.get_rect.game.scenes.horsalsvagen.HorsalsvagenScene;
import se.chalmers.get_rect.game.scenes.test.TestScene;
import se.chalmers.get_rect.game.window.IWindow;
import se.chalmers.get_rect.game.window.window.SplashWindow;
import se.chalmers.get_rect.game.window.window.inGameMenuWindow;
import se.chalmers.get_rect.game.window.window.mainMenuWindow;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.states.*;

public class Game implements IGame {
    private IGraphicsAdapter graphics;
    private IInputAdapter input;
    private IAssetManagerAdapter assetManager;
    private IGameLoopAdapter gameLoop;
    private IRectangleFactoryAdapter rectangleFactory;
    private StateManager<IScene> sceneManager = new StateManager<>();
    private StateManager<IWindow> windowManager = new StateManager<>();
    private PlayerController playerController;
    private CameraManager cameraManager;
    private boolean paused = true;

    private static final int SPLASH = 11;
    private static final int MAIN_MENU = 12;
    private static final int GAME = 13;

    private static final int NULL = 20;
    private static final int HORSALSLANGAN = 21;
    private static final int TEST = 22;


    private static final int INGAME_MENU = 31;

    /**
     * Initialize a new RPG game
     * @param graphics Graphics adapter
     * @param input Input adapter
     * @param assetManager AssetManager adapter
     * @param cameraFactory CameraFactory adapter
     * @param gameLoop GameLoop adapter
     * @param rectangleFactory RectangleFactory adapter
     */
    public Game(IGraphicsAdapter graphics, IInputAdapter input, IAssetManagerAdapter assetManager, ICameraFactoryAdapter cameraFactory, IGameLoopAdapter gameLoop, IRectangleFactoryAdapter rectangleFactory) {
        // Store game engine adapters
        this.graphics = graphics;
        this.input = input;
        this.assetManager = assetManager;
        this.gameLoop = gameLoop;
        this.rectangleFactory = rectangleFactory;

        IPhysicsEntity player = createPlayer(rectangleFactory);

        this.cameraManager = new CameraManager(cameraFactory.make(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT), player.getModel());

        addComponents(player); //todo: find a better name
        // Set the active state
        windowManager.set(SPLASH);
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

    private IPhysicsEntity createPlayer(IRectangleFactoryAdapter rectangleFactory) {
        playerController = new PlayerController(input);
        ProjectileFactory projectileFactory = new ProjectileFactory(rectangleFactory);
        PlayerFactory playerFactory = new PlayerFactory(playerController, rectangleFactory, projectileFactory);

        return playerFactory.make();
    }

    private void addComponents(IPhysicsEntity player) {
        // Register scenes
        sceneManager.add(TEST, new TestScene(player, rectangleFactory, cameraManager, sceneManager));
        sceneManager.add(HORSALSLANGAN, new HorsalsvagenScene(player, rectangleFactory, cameraManager, sceneManager));
        sceneManager.add(NULL, null);

        windowManager.add(SPLASH, new SplashWindow(this));
        windowManager.add(MAIN_MENU, new mainMenuWindow(this, cameraManager));
        windowManager.add(INGAME_MENU, new inGameMenuWindow(this, input, cameraManager));
    }

    public StateManager<IWindow> getWindowManager() {
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
                windowManager.set(INGAME_MENU);
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
    public CameraManager getCameraManager() {
        return cameraManager;
    }

    @Override
    public void exit() {
        assetManager.dispose();
        gameLoop.exit();
    }

    public void exitToMainMenu() {
        sceneManager.set(NULL);
        windowManager.set(MAIN_MENU);
    }

    @Override
    public void load() {
        sceneManager.set(HORSALSLANGAN);
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
        sceneManager.set(HORSALSLANGAN);
        resume();
    }

    @Override
    public boolean loadAvailable() {
        return true;
    }


}