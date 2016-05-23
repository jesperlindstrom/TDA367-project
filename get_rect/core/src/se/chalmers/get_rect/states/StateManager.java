package se.chalmers.get_rect.states;

import se.chalmers.get_rect.event.EventSource;
import se.chalmers.get_rect.event.IEventListener;
import se.chalmers.get_rect.event.IEventSource;

import java.util.HashMap;
import java.util.Map;

public class StateManager<V extends IState> implements IEventSource {
    private EventSource event = new EventSource();
    private Map<Integer, V> states;
    private Integer currentState;

    public StateManager() {
        this.states = new HashMap<>();
    }

    public void add(Integer key, V state) {
        states.put(key, state);
    }

    public void set(Integer stateName) {
        if (!states.containsKey(stateName)) {
            throw new StateNotFoundException("Could not find a state with ID:" + stateName);
        } else {

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

            event.triggerEvent("state", stateName.toString());
        }
    }

    public V getState() {
        if (currentState == null) return null;
        return getState(currentState);
    }

    public V getState(int key) {
        return states.get(key);
    }

    public void clearState() {
        currentState = null;
    }

    public Integer getCurrentStateKey() {
        if (currentState == null) return -1;
        return currentState;
    }

    @Override
    public void addListener(IEventListener o) {
        event.addListener(o);
    }

    @Override
    public void removeListener(IEventListener o) {
        event.removeListener(o);
    }
}