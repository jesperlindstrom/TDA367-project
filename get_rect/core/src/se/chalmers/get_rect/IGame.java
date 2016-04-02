package se.chalmers.get_rect;

import se.chalmers.get_rect.adapters.IInputAdapter;
import se.chalmers.get_rect.game.screens.IScreen;
import se.chalmers.get_rect.log.GameLog;
import se.chalmers.get_rect.states.StateManager;

public interface IGame {
    IInputAdapter getInput();
    GameLog getGameLog();
    StateManager<IScreen> getScreenManager();
}
