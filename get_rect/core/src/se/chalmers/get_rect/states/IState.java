package se.chalmers.get_rect.states;

public interface IState {
    void enteringState(Integer previousStateName);
    void leavingState(Integer nextStateName);
}
