package se.chalmers.get_rect.game.screens;

import se.chalmers.get_rect.GameConfig;
import se.chalmers.get_rect.game.IGame;
import se.chalmers.get_rect.adapters.IAssetManagerAdapter;
import se.chalmers.get_rect.adapters.ICameraAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.IScreen;
import se.chalmers.get_rect.states.StateManager;

import java.io.FileNotFoundException;

public class SplashScreen implements IScreen {
    private IAssetManagerAdapter assetManager;
    private StateManager<IScreen> screenManager;
    private ICameraAdapter camera;
    private boolean addedAssets = false;
    private double progressValue = 0.0;
    private boolean stop = false;
    private boolean didStop = false;
    private int stopTimer = 0;

    public SplashScreen(IGame game) {
        System.out.println("SplashScreen is initialized");

        assetManager = game.getAssetManager();
        screenManager = game.getScreens();
        camera = game.getCameraFactory().make(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);
        camera.translate(GameConfig.SCREEN_WIDTH/2, GameConfig.SCREEN_HEIGHT/2);
    }

    @Override
    public void enteringState(Integer previousStateName) {
        System.out.println("Entering SplashScreen");
        assetManager.loadTexture("img/splash/splash_bg.jpg");
        assetManager.loadTexture("img/splash/loading_fill.png");
    }

    @Override
    public void leavingState(Integer nextStateName) {
        System.out.println("Leaving SplashScreen");
    }

    @Override
    public void update(double delta) {
        if (assetManager.update() && !addedAssets) {
            addedAssets = true;
            loadAssets();
        }

        if (progressValue < 1.0) {
            progressValue = assetManager.getProgress();
        } else if (progressValue >= 4.35) {
            screenManager.set(12);
        } else if (progressValue >= 1.0 && didStop) {
            progressValue += 0.015;
            stop = false;
        } else if (progressValue >= 1.0 && !didStop) {
            if (!GameConfig.SPLASH_SCREEN_TROLL) {
                screenManager.set(12);
                return;
            }

            stop = true;
            stopTimer++;

            if (stopTimer == 100) {
                stop = false;
                didStop = true;
            }
        }

        camera.update(delta);
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        camera.draw(graphics);

        if (addedAssets) {
            graphics.draw("img/splash/splash_bg.jpg", 0, 0, GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);
            int progressWidth;

            if (stop) {
                progressWidth = 578;
            } else {
                progressWidth = (int) (578 * progressValue);
            }

            graphics.draw("img/splash/loading_fill.png", 768, 128, progressWidth, 60);

            if (didStop) {
                graphics.drawText("LOL JK xD", 1300, 200);
            }

            if (progressValue > 2.3) {
                int secondProgressWidth = (int)(300 * (progressValue - 2.3));
                graphics.draw("img/splash/loading_fill.png", 0, 128, secondProgressWidth, 60);
            }
        }
    }

    /**
     * Method for loading assets
     */
    private void loadAssets() {
        try {
            assetManager.loadTextureDir("img");
            assetManager.loadSoundsDir("sounds");
        } catch (FileNotFoundException e) {
            // todo: show an actual error
            System.out.println(e.getMessage());
        }
    }
}