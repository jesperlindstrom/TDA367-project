package se.chalmers.get_rect.game.window.model;

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
    private String error = null;

    public SplashModel(IAssetManagerAdapter assetManager, StateManager<? extends IState> windowManager) {
        this.assetManager = assetManager;
        this.windowManager = windowManager;
    }

    public void preload() {
        assetManager.loadTexture("img/splash/splash_logo.png");
        assetManager.loadTexture("img/splash/progress_bar_bg.png");
        assetManager.loadTexture("img/splash/loading_fill.png");
    }

    private void loadAssets() {
        try {
            assetManager.loadTextureDir("img");
            assetManager.loadSoundsDir("sounds");
            assetManager.loadMusicDir("music");
        } catch (FileNotFoundException e) {
            error = e.getMessage();
        }
    }

    public void update(double delta) {
        if (assetManager.update() && !addedAssets) {
            addedAssets = true;
            loadAssets();
        }

        if (progressValue < 1.0) {
            progressValue = assetManager.getProgress();
        } else if (progressValue >= 1.0) {
            windowManager.set(GameConfig.MAIN_MENU);
        }
    }

    public double getProgressValue() {
        return progressValue;
    }

    public boolean getAddedAssets() {
        return addedAssets;
    }

    public String getError() {
        return error;
    }

    public void setProgress(double progressValue) {
        this.progressValue = progressValue;
    }
}
