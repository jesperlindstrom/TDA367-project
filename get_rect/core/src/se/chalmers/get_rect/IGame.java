package se.chalmers.get_rect;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.IInputAdapter;
import se.chalmers.get_rect.log.GameLog;

public interface IGame {
    IGraphicsAdapter getGraphics();
    IInputAdapter getInput();
    GameLog getGameLog();
}
