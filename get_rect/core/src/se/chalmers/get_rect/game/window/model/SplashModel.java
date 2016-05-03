package se.chalmers.get_rect.game.window.model;

import se.chalmers.get_rect.GameConfig;
import se.chalmers.get_rect.adapters.IAssetManagerAdapter;
import se.chalmers.get_rect.game.IScene;
import se.chalmers.get_rect.game.window.IWindow;
import se.chalmers.get_rect.states.StateManager;

import java.io.FileNotFoundException;

public class SplashModel{

    private IAssetManagerAdapter assetManager;
    private StateManager<IWindow> windowManager;
    private boolean addedAssets = false;
    private double progressValue = 0.0;
    private boolean stop = false;
    private boolean didStop = false;
    private int stopTimer = 0;


    public SplashModel(IAssetManagerAdapter assetManager, StateManager<IWindow> windowManager) {
        this.assetManager = assetManager;
        this.windowManager = windowManager;
    }

    private void loadAssets() {
        try {
            assetManager.loadTextureDir("img");
            assetManager.loadSoundsDir("sounds");
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
            windowManager.set(12);
        } else if (progressValue >= 1.0 && didStop) {
            progressValue += 0.015;
            stop = false;
        } else if (progressValue >= 1.0 && !didStop) {
            if (!GameConfig.SPLASH_SCREEN_TROLL) {
                windowManager.set(12);
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
