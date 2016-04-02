package se.chalmers.get_rect;

import se.chalmers.get_rect.adapters.IAssetManagerAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.IInputAdapter;
import se.chalmers.get_rect.game.screens.GameScreen;
import se.chalmers.get_rect.game.screens.IScreen;
import se.chalmers.get_rect.game.screens.SplashScreen;
import se.chalmers.get_rect.game.screens.StartMenuScreen;
import se.chalmers.get_rect.log.GameLog;
import se.chalmers.get_rect.states.*;

public class GameManager implements IGame {
    private IGraphicsAdapter graphics;
    private IInputAdapter input;
    private IAssetManagerAdapter assetManager;
    private GameLog gameLog;
    private StateManager<IScreen> screenManager;

    /**
     * Create a new game manager
     * @param graphics A graphics adapter
     * @param input An input adapter
     * @param assetManager An assetManager adapter
     */
    public GameManager(IGraphicsAdapter graphics, IInputAdapter input, IAssetManagerAdapter assetManager) {
        // Store game engine adapters
        this.graphics = graphics;
        this.input = input;
        this.assetManager = assetManager;

        // Initialize components
        gameLog = new GameLog();
        screenManager = new StateManager<>();

        // Add screens
        screenManager.add("splash", new SplashScreen(this));
        screenManager.add("startMenu", new StartMenuScreen(this));
        screenManager.add("game", new GameScreen(this));

        // Set the active state
        screenManager.set("splash");
    }

    /**
     * Tell current state to draw
     */
    public void draw() {
        graphics.clear();
        screenManager.getState().draw(graphics);
    }

    /**
     * Tell current state to update
     * @param delta Time since last draw
     */
    public void update(long delta) {
        screenManager.getState().update(delta);
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
     * Get the state manager instance
     * @return State manager
     */
    @Override
    public StateManager<IScreen> getScreenManager() {
        return screenManager;
    }

    /**
     * Get the log instance
     * @return Game log
     */
    @Override
    public GameLog getGameLog() {
        return gameLog;
    }
}