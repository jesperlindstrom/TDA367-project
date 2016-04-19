package se.chalmers.get_rect.game.screens;

import se.chalmers.get_rect.GameConfig;
import se.chalmers.get_rect.IGame;
import se.chalmers.get_rect.adapters.ICameraAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.gui.IWindow;
import se.chalmers.get_rect.game.gui.mainMenu.mainMenuOverlay;

public class StartMenuScreen implements IScreen {

    private IWindow gameMenu;
    private ICameraAdapter camera;

    public StartMenuScreen(IGame game) {
        System.out.println("StartMenuScreen is initialized");
        camera = game.getCameraFactory().make(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);
        gameMenu = new mainMenuOverlay(game, camera);
    }

    @Override
    public void enteringState(String previousStateName) {
        System.out.println("Entering StartMenuScreen");
        gameMenu.enteringState(previousStateName);
    }

    @Override
    public void leavingState(String nextStateName) {
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