package se.chalmers.get_rect;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.IInputAdapter;
import se.chalmers.get_rect.game.screens.GameScreen;
import se.chalmers.get_rect.game.screens.IScreen;
import se.chalmers.get_rect.game.screens.SplashScreen;
import se.chalmers.get_rect.game.screens.StartMenuScreen;
import se.chalmers.get_rect.log.GameLog;
import se.chalmers.get_rect.states.*;

public class GameManager implements IGame {
    private IGraphicsAdapter graphicsAdapter;
    private IInputAdapter inputAdapter;
    private GameLog gameLog;
    private StateManager<IScreen> screenManager;

    /**
     * Create a new game manager
     * @param graphicsAdapter A graphics adapter
     * @param inputAdapter An input adapter
     */
    public GameManager(IGraphicsAdapter graphicsAdapter, IInputAdapter inputAdapter) {
        // Store game engine adapters
        this.graphicsAdapter = graphicsAdapter;
        this.inputAdapter = inputAdapter;

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
        graphicsAdapter.clear();
        screenManager.getState().draw(graphicsAdapter);
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
        return inputAdapter;
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