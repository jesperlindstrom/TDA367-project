package se.chalmers.get_rect.game;

import com.google.inject.Inject;
import se.chalmers.get_rect.adapters.*;
import se.chalmers.get_rect.game.entities.player.PlayerController;
import se.chalmers.get_rect.game.scenes.IScene;
import se.chalmers.get_rect.game.scenes.SceneFactory;
import se.chalmers.get_rect.game.entities.window.controller.IWindowController;
import se.chalmers.get_rect.game.entities.window.WindowFactory;
import se.chalmers.get_rect.states.*;

public class Game {
    @Inject private IGraphicsAdapter graphics;
    @Inject private IInputAdapter input;
    @Inject private StateManager<IScene> sceneManager;
    @Inject private StateManager<IWindowController> windowManager;
    @Inject private PlayerController playerController;
    @Inject private SceneFactory sceneFactory;
    @Inject private WindowFactory windowFactory;

    private boolean paused = true;


    public void draw() {
        if (sceneManager.getState() != null) {
            sceneManager.getState().draw(graphics);
        }

        if (paused) {
            windowManager.getState().draw(graphics);
        }
    }

    public void setup() {
        sceneManager.add(GameConfig.TEST, sceneFactory.make("test"));
        sceneManager.add(GameConfig.HORSALSVAGEN, sceneFactory.make("horsalsvagen"));
        sceneManager.add(GameConfig.HUBBEN, sceneFactory.make("hubben"));

        // todo: this is bad and Sune should feel bad
        sceneManager.add(GameConfig.NULL, null);

        windowManager.add(GameConfig.SPLASH, windowFactory.make("splash"));
        windowManager.add(GameConfig.MAIN_MENU, windowFactory.make("mainMenu"));
        windowManager.add(GameConfig.INGAME_MENU, windowFactory.make("inGameMenu"));

        // Set the active state
        windowManager.set(GameConfig.SPLASH);
    }

    /**
     * Tell current state to update
     * @param delta Time since last draw
     */
    public void update(double delta) {
        if (!windowManager.getState().equals(windowManager.getState(GameConfig.SPLASH))) {
            handleInput();
        }

        // Will update the menu if it is active and pause the current scene.
        if (paused) {
            windowManager.getState().update(delta);
        } else {
            playerController.update();

            if (sceneManager.getState() != null) {
                sceneManager.getState().update(delta);
            }
        }
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

    public void load() {
        sceneManager.set(GameConfig.HORSALSVAGEN);
        resume();
    }

    public void save() {

    }

    public void resume() {
        paused = false;
    }

    public void startNew() {
        sceneManager.set(GameConfig.HORSALSVAGEN);
        resume();
    }

    public boolean loadAvailable() {
        return true;
    }
}