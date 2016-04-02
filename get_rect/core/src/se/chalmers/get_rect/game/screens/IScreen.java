package se.chalmers.get_rect.game.screens;

import se.chalmers.get_rect.IGame;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.states.IState;

public interface IScreen extends IState {
    void update(long delta);
    void draw(IGraphicsAdapter graphics);
}
