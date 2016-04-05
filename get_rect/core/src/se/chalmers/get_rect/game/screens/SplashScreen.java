package se.chalmers.get_rect.game.screens;

import se.chalmers.get_rect.IGame;
import se.chalmers.get_rect.adapters.IAssetManagerAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.states.StateManager;

public class SplashScreen implements IScreen {
    private IAssetManagerAdapter assetManager;
    private StateManager<IScreen> screenManager;

    public SplashScreen(IGame game) {
        System.out.println("SplashScreen is initialized");

        assetManager = game.getAssetManager();
        screenManager = game.getScreenManager();
    }

    @Override
    public void enteringState(String previousStateName) {
        System.out.println("Entering SplashScreen");
        assetManager.loadTexture("data/zombie.png");
    }

    @Override
    public void leavingState(String nextStateName) {
        System.out.println("Leaving SplashScreen");
    }

    @Override
    public void update(long delta) {
        if (assetManager.update()) {
            System.out.println("ASSETS LOADED!");
            screenManager.set("game");
        }

        float progress = assetManager.getProgress();
        System.out.println("Loading progress: " + progress);
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {

    }
}