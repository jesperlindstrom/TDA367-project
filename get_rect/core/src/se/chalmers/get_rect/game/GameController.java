package se.chalmers.get_rect.game;

import com.google.inject.Inject;
import se.chalmers.get_rect.adapters.*;
import se.chalmers.get_rect.event.Event;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.game.entities.player.PlayerRepository;
import se.chalmers.get_rect.game.input.Actions;
import se.chalmers.get_rect.game.input.GameInput;
import se.chalmers.get_rect.game.entities.player.PlayerController;
import se.chalmers.get_rect.game.quests.QuestManager;
import se.chalmers.get_rect.game.quests.QuestRepository;
import se.chalmers.get_rect.game.quests.data.IQuest;
import se.chalmers.get_rect.game.world.IWorld;
import se.chalmers.get_rect.game.world.WorldFactory;
import se.chalmers.get_rect.game.window.controller.IWindowController;
import se.chalmers.get_rect.game.window.WindowFactory;
import se.chalmers.get_rect.states.*;
import se.chalmers.get_rect.utilities.Point;

import java.io.FileNotFoundException;
import java.util.List;

public class GameController {
    @Inject private IGraphicsAdapter graphics;
    @Inject private GameInput gameInput;
    @Inject private StateManager<IWorld> sceneManager;
    @Inject private StateManager<IWindowController> windowManager;
    @Inject private PlayerController playerController;
    @Inject private Player player;
    @Inject private PlayerRepository playerRepository;
    @Inject private WorldFactory worldFactory;
    @Inject private WindowFactory windowFactory;
    @Inject private IAudioManagerAdapter audioManager;
    @Inject private QuestManager questManager;
    @Inject private QuestRepository questRepository;

    private boolean muted = false;
    private static final String imgPath = "img/extras/";

    public void draw() {
        if (sceneManager.getState() != null) {
            sceneManager.getState().draw(graphics);
        }

        if (windowManager.getState() != null) {
            windowManager.getState().draw(graphics);
        }
        if(muted) {
            graphics.draw(imgPath + "noSound.png", new Point(700, 100));
        }
    }

    public void setup() {
        sceneManager.add(GameConfig.TEST, worldFactory.make("test"));
        sceneManager.add(GameConfig.HORSALSVAGEN, worldFactory.make("horsalsvagen"));
        sceneManager.add(GameConfig.HUBBEN, worldFactory.make("hubben"));

        windowManager.add(GameConfig.SPLASH, windowFactory.make("splash"));
        windowManager.add(GameConfig.MAIN_MENU, windowFactory.make("mainMenu"));
        windowManager.add(GameConfig.INGAME_MENU, windowFactory.make("inGameMenu"));
        windowManager.add(GameConfig.INVENTORY, windowFactory.make("inventory"));

        // Set the active state
        windowManager.set(GameConfig.SPLASH);

        // Add various listeners
        player.addListener(this::onPlayerDeath);
        sceneManager.addListener((e) -> save());
    }

    private void onPlayerDeath(Event e) {
        if (e.getAction().equals("died")) {
            try {
                playerRepository.load();
            } catch (FileNotFoundException f){
                System.out.println(f.getMessage());
                startNew();
            }
            sceneManager.set(GameConfig.HUBBEN);
        }
    }

    /**
     * Tell current state to update
     * @param delta Time since last drawIcon
     */
    public void update(double delta) {

        if (windowManager.getCurrentStateKey() != GameConfig.SPLASH && windowManager.getCurrentStateKey() != GameConfig.MAIN_MENU) {
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
            questManager.setQuests(questRepository.getAll());
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        sceneManager.set(GameConfig.HUBBEN);
        resume();
    }

    public void save() {
        try {
            System.out.println("called from save");
            // Save player details and weapons
            playerRepository.save();

            // Save quest status
            List<IQuest> quests = questManager.getAll();
            questRepository.save(quests);
        } catch (FileNotFoundException e){
            // todo: handle this
            System.out.println(e.getMessage());
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