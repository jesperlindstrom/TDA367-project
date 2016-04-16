package se.chalmers.get_rect;

import se.chalmers.get_rect.adapters.*;
import se.chalmers.get_rect.game.screens.IScreen;
import se.chalmers.get_rect.states.StateManager;

public interface IGame {
    IInputAdapter getInput();
    IAssetManagerAdapter getAssetManager();
    IRectangleFactoryAdapter getRectangleFactory();
    StateManager<IScreen> getScreenManager();
    ICameraFactoryAdapter getCameraFactory();
    void exit();
}
