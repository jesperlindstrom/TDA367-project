package se.chalmers.get_rect.game.screens;

import se.chalmers.get_rect.IGame;
import se.chalmers.get_rect.adapters.ICameraAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.CameraManager;
import se.chalmers.get_rect.adapters.IInputAdapter;
import se.chalmers.get_rect.game.entities.player.PlayerController;
import se.chalmers.get_rect.game.entities.player.PlayerFactory;
import se.chalmers.get_rect.game.scenes.*;
import se.chalmers.get_rect.game.scenes.menu.MenuScene;
import se.chalmers.get_rect.states.StateManager;
import se.chalmers.get_rect.utilities.FPSChecker;


public class GameScreen implements IScreen {
    private StateManager<IScene> sceneManager;
    private CameraManager cameraManager;
    private IInputAdapter input;
    private MenuScene menu;
    private boolean menuActive;
    private FPSChecker fps = new FPSChecker("GameScreen");

    public GameScreen(IGame game) {
        this.input = game.getInput();
        System.out.println("GameScreen is initialized");

        // Create the scene manager
        sceneManager = new StateManager<>();

        //Initialize player
        PlayerFactory playerFactory = new PlayerFactory(game);
        PlayerController playerController = playerFactory.make(0,0);

        // Create the CameraManager
        ICameraAdapter camera = game.getCameraFactory().make(1920, 1080);
        cameraManager = new CameraManager(camera, playerController);

        // Register scenes
        sceneManager.add("auditoriumStreet", new AuditoriumStreetScene(playerController));
        sceneManager.add("EDIT", new EDITScene(playerController));
        sceneManager.add("studentUnionHouse", new StudentUnionHouseScene(playerController));
        sceneManager.add("test", new TestScene(playerController, game.getRectangleFactory(), cameraManager));


        // Set starting scene
        sceneManager.set("test");

        // Sets menuActive to false
        menuActive = false;

        menu = new MenuScene(input, cameraManager);
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
        fps.update(delta);
        if (input.isKeyJustPressed(IInputAdapter.Keys.ESC)) {
            menuActive = !menuActive;
        }

        // Will update the menu if it is active and pause the current scene.
        if (menuActive) {
            menu.update(delta);
        } else {
            sceneManager.getState().update(delta);
            cameraManager.update(delta);
        }
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        cameraManager.draw(graphics);
        sceneManager.getState().draw(graphics);

        if (menuActive) {
            menu.draw(graphics);
        }

        fps.draw(graphics, cameraManager.getPosition());
    }

}
