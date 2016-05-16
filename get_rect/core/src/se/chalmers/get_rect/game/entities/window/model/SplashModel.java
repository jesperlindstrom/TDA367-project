package se.chalmers.get_rect.game.entities.window.model;

import se.chalmers.get_rect.game.GameConfig;
import se.chalmers.get_rect.adapters.IAssetManagerAdapter;
import se.chalmers.get_rect.states.IState;
import se.chalmers.get_rect.states.StateManager;

import java.io.FileNotFoundException;

public class SplashModel {
    private IAssetManagerAdapter assetManager;
    private StateManager<? extends IState> windowManager;
    private boolean addedAssets = false;
    private double progressValue = 0.0;
    private boolean stop = false;
    private boolean didStop = false;
    private int stopTimer = 0;

    public SplashModel(IAssetManagerAdapter assetManager, StateManager<? extends IState> windowManager) {
        this.assetManager = assetManager;
        this.windowManager = windowManager;
    }

    public void preload() {
        assetManager.loadTexture("img/splash/splash_bg.jpg");
        assetManager.loadTexture("img/splash/loading_fill.png");
    }

    private void loadAssets() {
        try {
            assetManager.loadTextureDir("img");
            assetManager.loadSoundsDir("sounds");
            assetManager.loadMusicDir("music");
        } catch (FileNotFoundException e) {
            // todo: show an actual error
            System.out.println(e.getMessage());
        }
    }

    public void update(double delta) {
        if (assetManager.update() && !addedAssets) {
            addedAssets = true;
            loadAssets();
        }

        if (progressValue < 1.0) {
            progressValue = assetManager.getProgress();
        } else if (progressValue >= 4.35) {
            windowManager.set(GameConfig.MAIN_MENU);
        } else if (progressValue >= 1.0 && didStop) {
            progressValue += 0.015;
            stop = false;
        } else if (progressValue >= 1.0 && !didStop) {
            if (!GameConfig.SPLASH_SCREEN_TROLL) {
                windowManager.set(GameConfig.MAIN_MENU);
                return;
            }

            stop = true;
            stopTimer++;

            if (stopTimer == 100) {
                stop = false;
                didStop = true;
            }
        }
    }

    public double getProgressValue() {
        return progressValue;
    }

    public boolean getStop() {
        return stop;
    }

    public boolean getDidStop() {
        return didStop;
    }

    public boolean getAddedAssets() {
        return addedAssets;
    }
}
