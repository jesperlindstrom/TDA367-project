package se.chalmers.get_rect;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.IInputAdapter;

public interface IGame {
    IGraphicsAdapter getGraphics();
    IInputAdapter getInput();
}
