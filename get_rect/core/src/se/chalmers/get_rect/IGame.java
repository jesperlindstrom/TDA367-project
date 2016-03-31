package se.chalmers.get_rect;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.IInputAdapter;

public interface IGame {
    enum State { SPLASH, GAME, MENU };
    IGraphicsAdapter getGraphics();
    IInputAdapter getInput();
    void setState(State state);
}
