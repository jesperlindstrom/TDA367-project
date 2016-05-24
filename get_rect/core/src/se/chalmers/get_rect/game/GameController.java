package se.chalmers.get_rect.game;

import com.google.inject.Inject;
import se.chalmers.get_rect.adapters.*;
import se.chalmers.get_rect.event.Event;
import se.chalmers.get_rect.game.entities.ICamera;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.game.entities.player.PlayerRepository;
import se.chalmers.get_rect.game.input.Actions;
import se.chalmers.get_rect.game.input.GameInput;
import se.chalmers.get_rect.game.entities.player.PlayerController;
import se.chalmers.get_rect.game.quests.QuestManager;
import se.chalmers.get_rect.game.quests.QuestRepository;
import se.chalmers.get_rect.game.quests.data.IQuest;
import se.chalmers.get_rect.game.window.model.ErrorWindow;
import se.chalmers.get_rect.game.world.IWorld;
import se.chalmers.get_rect.game.world.WorldFactory;
import se.chalmers.get_rect.game.window.controller.IWindowController;
import se.chalmers.get_rect.game.window.WindowFactory;
import se.chalmers.get_rect.states.*;
import se.chalmers.get_rect.utilities.Point;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class GameController {
    @Inject private IGraphicsAdapter graphics;
    @Inject private GameInput gameInput;
    @Inject private StateManager<IWorld> worldManager;
    @Inject private StateManager<IWindowController> windowManager;
    @Inject private PlayerController playerController;
    @Inject private Player player;
    @Inject private PlayerRepository playerRepository;
    @Inject private WorldFactory worldFactory;
    @Inject private WindowFactory windowFactory;
    @Inject private IAudioManagerAdapter audioManager;
    @Inject private QuestManager questManager;
    @Inject private QuestRepository questRepository;
    @Inject private ICamera camera;
    @Inject private ErrorHandler error;

    private boolean muted = false;
    private static final String imgPath = "img/extras/";

    public void draw() {
        if (worldManager.getState() != null) {
            worldManager.getState().draw(graphics);
        }

        if (windowManager.getState() != null) {
            windowManager.getState().draw(graphics);
        }
        if(muted) {
            graphics.draw(imgPath + "noSound.png", new Point((int)(camera.getAdapter().getWidth())/3, (int)(camera.getAdapter().getHeight())/6));
        }
    }

    public void setup() {
        worldManager.add(GameConfig.TEST, worldFactory.make("test"));
        worldManager.add(GameConfig.HORSALSVAGEN, worldFactory.make("horsalsvagen"));
        worldManager.add(GameConfig.HUBBEN, worldFactory.make("hubben"));

        windowManager.add(GameConfig.SPLASH, windowFactory.makeSplash());
        windowManager.add(GameConfig.MAIN_MENU, windowFactory.makeMainMenu());
        windowManager.add(GameConfig.INGAME_MENU, windowFactory.makeInGameMenu());
        windowManager.add(GameConfig.INVENTORY, windowFactory.makeInventory());
        windowManager.add(GameConfig.ERROR_WINDOW, windowFactory.makeErrorWindow());

        // Set the active state
        windowManager.set(GameConfig.SPLASH);

        // Add various listeners
        player.addListener(this::onPlayerDeath);
    }

    private void onPlayerDeath(Event e) {
        if (e.getAction().equals("died")) {
            try {
                playerRepository.load();
            } catch (FileNotFoundException f){
                startNew();
            }
            worldManager.set(GameConfig.HUBBEN);
        }
    }

    /**
     * Tell current state to update
     * @param delta Time since last drawIcon
     */
    public void update(double delta) {
        if (windowManager.getState() == null || windowManager.getState().allowsRegularInput()) {
            handleInput();
        }

        // Will update the menu if it is active and pause the current scene.
        if (windowManager.getState() != null) {
            windowManager.getState().update(delta);
        } else {
            playerController.update();

            if (worldManager.getState() != null) {
                worldManager.getState().update(delta);
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
        if (gameInput.isKeyJustPressed(Actions.RESPAWN)) {
            worldManager.set(GameConfig.HUBBEN);
        }
    }

    public void load() {
        try {
            playerRepository.load();
            questManager.setQuests(questRepository.getAll());
            worldManager.set(GameConfig.HUBBEN);
            resume();
        } catch (FileNotFoundException e){
            ((ErrorWindow)windowManager.getState(GameConfig.ERROR_WINDOW).getModel()).setMessage(e.getMessage());
            windowManager.set(GameConfig.ERROR_WINDOW);
        }
    }

    public void save() {
        try {
            // Save player details and weapons
            playerRepository.save();

            // Save quest status
            List<IQuest> quests = questManager.getAll();
            questRepository.save(quests);
        } catch (IOException e){
            error.showError(e.getMessage());
        }
    }

    public void resume() {
        windowManager.clearState();
    }

    public void startNew() {
        try {
            questRepository.reset();
            playerRepository.reset();
            questManager.setQuests(questRepository.getAll());
            worldManager.set(GameConfig.HUBBEN);
            resume();
        } catch (FileNotFoundException e){
            ((ErrorWindow)windowManager.getState(GameConfig.ERROR_WINDOW).getModel()).setMessage(e.getMessage());
            windowManager.set(GameConfig.ERROR_WINDOW);
        }
    }

    public boolean loadAvailable() {
        return playerRepository.hasFile();
    }
}