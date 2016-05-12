package se.chalmers.get_rect.states;

import java.util.HashMap;
import java.util.Map;

public class StateManager<V extends IState> {
    private Map<Integer, V> states;
    private Integer currentState;

    public StateManager() {
        this.states = new HashMap<>();
    }

    /**
     * Register a state
     * @param key The state name
     * @param state The state
     */
    public void add(Integer key, V state) {
        states.put(key, state);
    }

    /**
     * Switch to another state
     * @param stateName The state name
     */
    public void set(Integer stateName) {
        if (!states.containsKey(stateName)) {
            throw new StateNotFoundException("Could not find a state with ID:" + stateName);
        }

        // Keep a copy of the previous state name
        Integer oldState = currentState;

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
    public V getState() {
        return states.get(currentState);
    }

    public V getState(int key) {
        return states.get(key);
    }
}