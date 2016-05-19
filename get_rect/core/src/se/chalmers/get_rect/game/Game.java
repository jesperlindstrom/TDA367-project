package se.chalmers.get_rect.game;

import com.google.inject.Inject;
import se.chalmers.get_rect.adapters.*;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.game.entities.player.PlayerRepository;
import se.chalmers.get_rect.game.input.Actions;
import se.chalmers.get_rect.game.input.GameInput;
import se.chalmers.get_rect.game.entities.player.PlayerController;
import se.chalmers.get_rect.game.scenes.IScene;
import se.chalmers.get_rect.game.scenes.SceneFactory;
import se.chalmers.get_rect.game.entities.window.controller.IWindowController;
import se.chalmers.get_rect.game.entities.window.WindowFactory;
import se.chalmers.get_rect.states.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;

public class Game {
    @Inject private IGraphicsAdapter graphics;
    @Inject private GameInput gameInput;
    @Inject private StateManager<IScene> sceneManager;
    @Inject private StateManager<IWindowController> windowManager;
    @Inject private PlayerController playerController;
    @Inject private Player player;
    @Inject private PlayerRepository playerRepository;
    @Inject private SceneFactory sceneFactory;
    @Inject private WindowFactory windowFactory;
    @Inject private IAudioManagerAdapter audioManager;

    private boolean muted = false;

    public void draw() {
        if (sceneManager.getState() != null) {
            sceneManager.getState().draw(graphics);
        }

        if (windowManager.getState() != null) {
            windowManager.getState().draw(graphics);
        }
    }

    public void setup() {
        sceneManager.add(GameConfig.TEST, sceneFactory.make("test"));
        sceneManager.add(GameConfig.HORSALSVAGEN, sceneFactory.make("horsalsvagen"));
        sceneManager.add(GameConfig.HUBBEN, sceneFactory.make("hubben"));

        windowManager.add(GameConfig.SPLASH, windowFactory.make("splash"));
        windowManager.add(GameConfig.MAIN_MENU, windowFactory.make("mainMenu"));
        windowManager.add(GameConfig.INGAME_MENU, windowFactory.make("inGameMenu"));
        windowManager.add(GameConfig.INVENTORY, windowFactory.make("inventory"));

        // Set the active state
        windowManager.set(GameConfig.SPLASH);

        player.addListener((e) -> {
            System.out.println(e);
            if (e.getAction().equals("died")) {
                try {
                    playerRepository.load();
                } catch (FileNotFoundException f){
                    System.out.println(f.getMessage());
                    startNew();
                }
                sceneManager.set(GameConfig.HUBBEN);
                player.refillHealth();
            }
        });
    }

    /**
     * Tell current state to update
     * @param delta Time since last drawIcon
     */
    public void update(double delta) {
        if (windowManager.getCurrentStateKey() != GameConfig.SPLASH) {
            handleInput();
        }

        // Will update the menu if it is active and pause the current scene.
        if (windowManager.getState() != null) {
            windowManager.getState().update(delta);
        } else {
            playerController.update();

            if (sceneManager.getState() != null) {
                sceneManager.getState().update(delta);
            }
        }
    }

    private void handleInput() {
        if (gameInput.isKeyJustPressed(Actions.MENU)) {
            if (windowManager.getState() == null) {
                windowManager.set(GameConfig.INGAME_MENU);
            } else {
                resume();
            }
        }
        if (gameInput.isKeyJustPressed(Actions.EXIT_MENU)) {
            if (windowManager.getState() != null)
                resume();
        }
        if (gameInput.isKeyJustPressed(Actions.MUTE)) {
            if (!muted) {
                audioManager.mute();
                muted = true;
            } else {
                audioManager.unmute();
                muted = false;
            }
        }
    }


    public void load() {
        try {
            playerRepository.load();
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        sceneManager.set(GameConfig.HUBBEN);
        resume();
    }

    public void save() {
        try {
            playerRepository.save();
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public void resume() {
        windowManager.clearState();
    }

    public void startNew() {

        try {
            playerRepository.reset();
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        sceneManager.set(GameConfig.HUBBEN);
        resume();
    }

    public boolean loadAvailable() {
        return playerRepository.hasFile();
    }
}