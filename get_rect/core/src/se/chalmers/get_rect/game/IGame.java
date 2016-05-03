package se.chalmers.get_rect.game;

import se.chalmers.get_rect.adapters.*;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.states.StateManager;

public interface IGame {
    IInputAdapter getInput();
    IAssetManagerAdapter getAssetManager();
    IRectangleFactoryAdapter getRectangleFactory();
    StateManager<IScreen> getScreens();
    ICameraFactoryAdapter getCameraFactory();
    void exit();
    void load();
    void save();
    void startNew();
    boolean loadAvailable();

}
