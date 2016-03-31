package se.chalmers.get_rect;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.IInputAdapter;
import se.chalmers.get_rect.log.GameLog;
import se.chalmers.get_rect.states.StateManager;

public interface IGame {
    IGraphicsAdapter getGraphics();
    IInputAdapter getInput();
    StateManager getStateManager();
    GameLog getGameLog();
}
