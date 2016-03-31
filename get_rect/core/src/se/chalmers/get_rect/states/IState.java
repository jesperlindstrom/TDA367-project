package se.chalmers.get_rect.states;

import se.chalmers.get_rect.IGame;

public interface IState {
    void initialize(IGame game);
    void show();
    void draw();
    void update(long delta);
}
