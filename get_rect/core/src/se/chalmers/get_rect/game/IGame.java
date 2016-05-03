package se.chalmers.get_rect.game;

import se.chalmers.get_rect.adapters.*;
import se.chalmers.get_rect.game.window.IWindow;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.states.StateManager;

public interface IGame {
    IInputAdapter getInput();
    IAssetManagerAdapter getAssetManager();
    IRectangleFactoryAdapter getRectangleFactory();
    CameraManager getCameraManager();
    StateManager<IWindow> getWindowManager();
    StateManager<IScene> getSceneManager();
    void exit();
    void exitToMainMenu();
    void load();
    void save();
    void resume();
    void startNew();
    boolean loadAvailable();

}
