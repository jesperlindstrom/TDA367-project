package se.chalmers.get_rect.game.screens;

import se.chalmers.get_rect.IGame;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.scenes.AuditoriumStreetScene;
import se.chalmers.get_rect.game.scenes.EDITScene;
import se.chalmers.get_rect.game.scenes.IScene;
import se.chalmers.get_rect.game.scenes.StudentUnionHouseScene;
import se.chalmers.get_rect.states.StateManager;


public class GameScreen implements IScreen {
    private StateManager<IScene> sceneManager;

    public GameScreen(IGame game) {
        System.out.println("GameScreen is initialized");

        // Create the scene manager
        sceneManager = new StateManager<>();

        // Register scenes
        sceneManager.add("auditoriumStreet", new AuditoriumStreetScene());
        sceneManager.add("EDIT", new EDITScene());
        sceneManager.add("studentUnionHouse", new StudentUnionHouseScene());

        // Set starting scene
        sceneManager.set("auditoriumStreet");
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
        sceneManager.getState().update(delta);
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        sceneManager.getState().draw(graphics);
    }
}
