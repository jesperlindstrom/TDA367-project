package se.chalmers.get_rect.game.screens;

import se.chalmers.get_rect.IGame;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.IInputAdapter;
import se.chalmers.get_rect.game.entities.player.PlayerController;
import se.chalmers.get_rect.game.entities.player.PlayerFactory;
import se.chalmers.get_rect.game.scenes.AuditoriumStreetScene;
import se.chalmers.get_rect.game.scenes.EDITScene;
import se.chalmers.get_rect.game.scenes.IScene;
import se.chalmers.get_rect.game.scenes.StudentUnionHouseScene;
import se.chalmers.get_rect.game.scenes.*;
import se.chalmers.get_rect.states.StateManager;


public class GameScreen implements IScreen {
    private StateManager<IScene> sceneManager;
    private PlayerController playerController;
    private IInputAdapter input;
    private IScene menu;

    public GameScreen(IGame game) {
        this.input = game.getInput();
        System.out.println("GameScreen is initialized");

        menu = new MenuScene(input);

        // Create the scene manager
        sceneManager = new StateManager<>();


        //Initialize player
        PlayerFactory playerFactory = new PlayerFactory(game.getInput());
        playerController = playerFactory.make(0,0);


        // Register scenes
        sceneManager.add("auditoriumStreet", new AuditoriumStreetScene(playerController));
        sceneManager.add("EDIT", new EDITScene(playerController));
        sceneManager.add("studentUnionHouse", new StudentUnionHouseScene(playerController));
        sceneManager.add("test", new TestScene(playerController));


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

    @Override
    public void update(long delta) {
        if (input.isKeyPressed(IInputAdapter.Keys.ESC)) {

        }
        sceneManager.getState().update(delta);
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        sceneManager.getState().draw(graphics);
    }
}
