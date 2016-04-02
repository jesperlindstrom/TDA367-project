package se.chalmers.get_rect.states;

public class StateNotFoundException extends RuntimeException {
    public StateNotFoundException(String stateName) {
        super("There is no state with key \"" + stateName + "\"");
    }
}
