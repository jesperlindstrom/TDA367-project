package se.chalmers.get_rect.game.screens;

import se.chalmers.get_rect.GameConfig;
import se.chalmers.get_rect.game.IGame;
import se.chalmers.get_rect.adapters.ICameraAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.IScreen;
import se.chalmers.get_rect.game.window.IWindow;
import se.chalmers.get_rect.game.window.mainMenu.mainMenuWindow;

public class StartMenuScreen implements IScreen {

    private IWindow gameMenu;
    private ICameraAdapter camera;

    public StartMenuScreen(IGame game) {
        System.out.println("StartMenuScreen is initialized");
        camera = game.getCameraFactory().make(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);
        gameMenu = new mainMenuWindow(game, camera);
    }

    @Override
    public void enteringState(Integer previousStateName) {
        System.out.println("Entering StartMenuScreen");
        gameMenu.enteringState(previousStateName);
    }

    @Override
    public void leavingState(Integer nextStateName) {
        System.out.println("Leaving StartMenuScreen");
    }

    @Override
    public void update(double delta) {
        gameMenu.update(delta);
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        gameMenu.draw(graphics);
    }
}