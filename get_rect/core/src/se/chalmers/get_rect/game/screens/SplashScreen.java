package se.chalmers.get_rect.game.screens;

import se.chalmers.get_rect.IGame;
import se.chalmers.get_rect.adapters.IAssetManagerAdapter;
import se.chalmers.get_rect.adapters.ICameraAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.states.StateManager;

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
        screenManager = game.getScreenManager();
        camera = game.getCameraFactory().make(1920, 1080);
        camera.translate(1920/2, 1080/2);
    }

    @Override
    public void enteringState(String previousStateName) {
        System.out.println("Entering SplashScreen");
        assetManager.loadTexture("img/splash/splash_bg.jpg");
        assetManager.loadTexture("img/splash/loading_fill.png");
    }

    @Override
    public void leavingState(String nextStateName) {
        System.out.println("Leaving SplashScreen");
    }

    @Override
    public void update(long delta) {
        if (assetManager.update() && !addedAssets) {
            addedAssets = true;
            loadTextures();
        }

        if (progressValue < 1.0) {
            progressValue = assetManager.getProgress();
        } else if (progressValue >= 4.35) {
            screenManager.set("game");
        } else if (progressValue >= 1.0 && didStop) {
            progressValue += 0.015;
            stop = false;
        } else if (progressValue >= 1.0 && !didStop) {
            stop = true;
            stopTimer++;

            if (stopTimer == 100) {
                stop = false;
                didStop = true;
            }
        }

        camera.update();
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        camera.draw(graphics);

        if (addedAssets) {
            graphics.draw("img/splash/splash_bg.jpg", 0, 0, 1920, 1080);
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
     * Method for loading textures
     */
    private void loadTextures() {
        // TestScene
        assetManager.loadTexture("img/backgrounds/background.png");

        // Player
        assetManager.loadTexture("img/entities/player/playerTwoLeg.png");
        assetManager.loadTexture("img/entities/player/playerOneLeg.png");

        // Enemies
        assetManager.loadTexture("img/entities/zombies/zombie.png");
        assetManager.loadTexture("img/entities/zombies/zombieOpen.png");

        // NPCs
        assetManager.loadTexture("img/entities/sawmill/sawmill-express.png");

        // In-game menu
        assetManager.loadTexture("img/backgrounds/menuShader.png");
        assetManager.loadTexture("img/scenes/menuBackground.png");
        assetManager.loadTexture("img/scenes/exitButton.png");
        assetManager.loadTexture("img/scenes/continueButton.png");
    }
}