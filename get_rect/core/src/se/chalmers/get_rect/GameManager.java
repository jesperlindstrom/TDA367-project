package se.chalmers.get_rect;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.IInputAdapter;
import se.chalmers.get_rect.log.GameLog;
import se.chalmers.get_rect.states.*;

public class GameManager implements IGame {
    private IGraphicsAdapter graphicsAdapter;
    private IInputAdapter inputAdapter;
    private StateManager stateManager;
    private GameLog gameLog;

    /**
     * Create a new game manager
     * @param graphicsAdapter A graphics adapter
     * @param inputAdapter An input adapter
     */
    public GameManager(IGraphicsAdapter graphicsAdapter, IInputAdapter inputAdapter) {
        this.graphicsAdapter = graphicsAdapter;
        this.inputAdapter = inputAdapter;
        this.gameLog = new GameLog();
        this.stateManager = new StateManager(this);
    }

    /**
     * Tell current state to draw
     */
    public void draw() {
        stateManager.getState().draw();
    }

    /**
     * Tell current state to update
     * @param delta Time since last draw
     */
    public void update(long delta) {
        stateManager.getState().update(delta);
    }

    /**
     * Get the graphics instance
     * @return Graphics adapter
     */
    @Override
    public IGraphicsAdapter getGraphics() {
        return graphicsAdapter;
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
    public StateManager getStateManager() {
        return stateManager;
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