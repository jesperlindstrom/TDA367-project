package se.chalmers.get_rect.states;

import se.chalmers.get_rect.IGame;

import java.util.HashMap;
import java.util.Map;

public class StateManager {
    public enum State { SPLASH, GAME, MENU };
    private static State DEFAULT_STATE = State.GAME;
    private Map<State, IState> states;
    private IState currentState;
    private IGame game;

    public StateManager(IGame game) {
        this.game = game;
        this.states = new HashMap<State, IState>();

        loadStates();
        initialize();

        // Load the default state
        setState(DEFAULT_STATE);
    }

    /**
     * Create the states
     */
    public void loadStates() {
        states.put(State.SPLASH, new SplashState());
        states.put(State.GAME, new GameState());
        states.put(State.MENU, new StartMenuState());
    }

    /**
     * Initialize all states
     */
    public void initialize() {
        for (State state : State.values()) {
            states.get(state).initialize(game);
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
     * Get the current state
     * @return The currently active IState
     */
    public IState getState() {
        return currentState;
    }
}