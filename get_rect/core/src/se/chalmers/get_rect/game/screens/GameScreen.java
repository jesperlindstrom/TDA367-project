package se.chalmers.get_rect.game.screens;

import se.chalmers.get_rect.GameConfig;
import se.chalmers.get_rect.IGame;
import se.chalmers.get_rect.adapters.*;
import se.chalmers.get_rect.game.CameraManager;
import se.chalmers.get_rect.game.entities.IPhysicsEntity;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.entities.player.PlayerController;
import se.chalmers.get_rect.game.entities.player.PlayerFactory;
import se.chalmers.get_rect.game.entities.projectile.ProjectileFactory;
import se.chalmers.get_rect.game.gui.IWindow;
import se.chalmers.get_rect.game.gui.inGameMenu.inGameMenuWindow;
import se.chalmers.get_rect.game.scenes.*;
import se.chalmers.get_rect.game.scenes.test.TestScene;
import se.chalmers.get_rect.states.StateManager;


public class GameScreen implements IScreen {
    private StateManager<IScene> sceneManager = new StateManager<>();
    private StateManager<IWindow> overlayManager = new StateManager<>();
    private CameraManager cameraManager;
    private IInputAdapter input;
    private PlayerController playerController;
    private IGame game;
    private boolean pause = false;

    public GameScreen(IGame game) {
        System.out.println("GameScreen is initialized");

        // get reference to game
        this.game = game;

        input = game.getInput();

        // Initialize player
        IPhysicsEntity player = createPlayer(game.getRectangleFactory());

        // Create the CameraManager
        cameraManager = createCamera(game.getCameraFactory(), player.getModel());

        // Add all scenes
        addScenes(player, game.getRectangleFactory());

        // Add GUI windows
        addWindows();
    }

    private CameraManager createCamera(ICameraFactoryAdapter cameraFactory, IPhysicsModel entity) {
        ICameraAdapter camera = cameraFactory.make(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);
        return new CameraManager(camera, entity);
    }

    private IPhysicsEntity createPlayer(IRectangleFactoryAdapter rectangleFactory) {
        playerController = new PlayerController(input);
        ProjectileFactory projectileFactory = new ProjectileFactory(rectangleFactory);
        PlayerFactory playerFactory = new PlayerFactory(playerController, rectangleFactory, projectileFactory);

        return playerFactory.make();
    }

    private void addScenes(IPhysicsEntity player, IRectangleFactoryAdapter rectangleFactory) {
        // Register scenes
        sceneManager.add("test", new TestScene(player, rectangleFactory, cameraManager));

        // Set starting scene
        sceneManager.set("test");
    }

    private void addWindows() {
        overlayManager.add("inGameMenu", new inGameMenuWindow(this, input, cameraManager));
    }

    @Override
    public void enteringState(String previousStateName) {
        System.out.println("Entering GameScreen");
    }

    @Override
    public void leavingState(String nextStateName) {
        System.out.println("Leaving GameScreen");
    }

    /**
     * Will set update for the correct scene
     * and check if the menu button is pressed
     * @param delta time since last draw.
     */
    @Override
    public void update(double delta) {
        handleInput();

        // Will update the menu if it is active and pause the current scene.
        if (pause) {
            overlayManager.getState().update(delta);
        } else {
            playerController.update();
            sceneManager.getState().update(delta);
            cameraManager.update(delta);
        }
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        cameraManager.draw(graphics);
        sceneManager.getState().draw(graphics);

        if (pause) {
            overlayManager.getState().draw(graphics);
        }
    }

    private void handleInput() {
        if (input.isKeyJustPressed(IInputAdapter.Keys.ESC)) {
            if (pause) {
                resume();
            } else {
                openWindow("inGameMenu");
            }
        }
    }

    public void openWindow(String name) {
        overlayManager.set(name);
        pause();
    }

    public void exit() {
        game.exit();
    }

    public void pause() {
        pause = true;
    }

    public void resume() {
        pause = false;
    }
}
