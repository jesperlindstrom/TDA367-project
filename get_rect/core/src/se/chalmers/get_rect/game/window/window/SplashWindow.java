package se.chalmers.get_rect.game.window.window;

import se.chalmers.get_rect.game.IGame;
import se.chalmers.get_rect.adapters.IAssetManagerAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.window.IWindowController;
import se.chalmers.get_rect.game.window.model.SplashModel;
import se.chalmers.get_rect.game.window.view.SplashView;

public class SplashWindow implements IWindowController {

    private SplashModel model;
    private SplashView view;
    private IAssetManagerAdapter assetManager;

    public SplashWindow(IGame game) {
        System.out.println("SplashScreen is initialized");
        this.assetManager = game.getAssetManager();

        this.model = new SplashModel(assetManager, game.getWindowManager());
        this.view = new SplashView(model, game.getCameraManager());

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
        model.update(delta);
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        view.draw(graphics);
    }
}

