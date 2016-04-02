package se.chalmers.get_rect.states;

public interface IState {
    void enteringState(String previousStateName);
    void leavingState(String nextStateName);
}
