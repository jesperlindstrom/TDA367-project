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
import se.chalmers.get_rect.game.scenes.*;
import se.chalmers.get_rect.game.scenes.menu.MenuController;
import se.chalmers.get_rect.game.scenes.menu.MenuModel;
import se.chalmers.get_rect.states.StateManager;
import se.chalmers.get_rect.utilities.debug.Debugger;


public class GameScreen implements IScreen {
    private StateManager<IScene> sceneManager;
    private CameraManager cameraManager;
    private IInputAdapter input;
    private MenuController menu;
    private boolean menuActive;
    private Debugger debugger;
    private PlayerController playerController;
    private IGame game;

    public GameScreen(IGame game) {
        System.out.println("GameScreen is initialized");

        // get reference to game
        this.game = game;

        input = game.getInput();
        sceneManager = new StateManager<>();

        // Initialize player
        IPhysicsEntity player = createPlayer(game.getRectangleFactory());

        // Create the CameraManager
        cameraManager = createCamera(game.getCameraFactory(), player.getModel());

        // Initialize debugger
        debugger = new Debugger(player.getModel(), cameraManager);

        // Add all scenes
        addScenes(player, game.getRectangleFactory(), debugger);

        // Sets menuActive to false
        menuActive = false;

        // Creates menu
        menu = new MenuController(this, input, cameraManager);

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

    private void addScenes(IPhysicsEntity player, IRectangleFactoryAdapter rectangleFactory, Debugger debugger) {
        // Register scenes
        sceneManager.add("test", new TestScene(player, rectangleFactory, cameraManager, debugger));

        // Set starting scene
        sceneManager.set("test");
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
        if (input.isKeyJustPressed(IInputAdapter.Keys.ESC)) {
            menuActive = !menuActive;
        }

        // Will update the menu if it is active and pause the current scene.
        if (menuActive) {
            menu.update();
        } else {
            playerController.update();
            sceneManager.getState().update(delta);
            cameraManager.update(delta);
        }

        debugger.update(delta);
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        cameraManager.draw(graphics);
        sceneManager.getState().draw(graphics);

        if (menuActive) {
            menu.draw(graphics);
        }

        debugger.draw(graphics);
    }

    public void exit() {
        game.exit();
    }

    public void setMenuActive(boolean value) {
        menuActive = value;
    }

}
