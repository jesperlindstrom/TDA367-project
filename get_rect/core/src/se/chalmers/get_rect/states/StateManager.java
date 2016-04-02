package se.chalmers.get_rect.states;

import java.util.HashMap;
import java.util.Map;

public class StateManager<T extends IState> {
    private Map<String, T> states;
    private String currentState;

    public StateManager() {
        this.states = new HashMap<>();
    }

    /**
     * Register a state
     * @param key The state name
     * @param state The state
     */
    public void add(String key, T state) {
        states.put(key, state);
    }

    /**
     * Switch to another state
     * @param stateName The state name
     */
    public void set(String stateName) {
        if (!states.containsKey(stateName)) {
            // todo: throw some "State not found" exception?
            return;
        }

        // Keep a copy of the previous state name
        String oldState = currentState;

        // Tell the current state it's being replaced
        if (oldState != null) {
            getState().leavingState(stateName);
        }

        // Change to the new state
        currentState = stateName;

        // Tell the new state it's becoming active
        getState().enteringState(oldState);
    }

    /**
     * Get the current state
     * @return The currently active IState
     */
    public T getState() {
        return states.get(currentState);
    }
}