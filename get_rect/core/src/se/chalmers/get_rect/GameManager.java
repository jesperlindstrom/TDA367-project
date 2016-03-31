package se.chalmers.get_rect;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.IInputAdapter;
import se.chalmers.get_rect.log.GameLog;
import se.chalmers.get_rect.states.*;

import java.util.HashMap;
import java.util.Map;

public class GameManager implements IGame {
    private static State DEFAULT_STATE = State.GAME;
    private IGraphicsAdapter graphicsAdapter;
    private IInputAdapter inputAdapter;
    private Map<State, IState> states;
    private IState currentState;
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

        states = new HashMap<State, IState>();

        loadStates();
        initialize();

        // Load the default state
        setState(DEFAULT_STATE);
    }

    /**
     * Create all states
     */
    private void loadStates() {
        states.put(State.SPLASH, new SplashState());
        states.put(State.GAME, new GameState());
        states.put(State.MENU, new StartMenuState());
    }

    /**
     * Initialize all states
     */
    private void initialize() {
        for (State state : State.values()) {
            states.get(state).initialize(this);
        }
    }


    /**
     * Switch to another state
     * @param state The new state
     */
    public void setState(State state) {
        IState nextState = states.get(state);

        if (nextState != null) {
            currentState = nextState;
            currentState.show();
        }
    }

    /**
     * Tell current state to draw
     */
    public void draw() {
        currentState.draw();
    }

    /**
     * Tell current state to update
     * @param delta Time since last draw
     */
    public void update(long delta) {
        currentState.update(delta);
    }

    @Override
    public IGraphicsAdapter getGraphics() {
        return graphicsAdapter;
    }

    @Override
    public IInputAdapter getInput() {
        return inputAdapter;
    }

    /**
     * Getter for GameLog
     * @return
     */
    @Override
    public GameLog getGameLog() {
        return gameLog;
    }
}
