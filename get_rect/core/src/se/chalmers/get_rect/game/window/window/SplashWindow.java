package se.chalmers.get_rect.game.window.window;

import com.google.inject.Inject;
import se.chalmers.get_rect.game.IGame;
import se.chalmers.get_rect.adapters.IAssetManagerAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.ICamera;
import se.chalmers.get_rect.game.window.IWindowController;
import se.chalmers.get_rect.game.window.model.SplashModel;
import se.chalmers.get_rect.game.window.view.SplashView;
import se.chalmers.get_rect.states.StateManager;

public class SplashWindow implements IWindowController {

    private SplashModel model;
    private SplashView view;
    private IAssetManagerAdapter assetManager;

    @Inject
    public SplashWindow(StateManager<IWindowController> windowManager, ICamera camera) {
        System.out.println("SplashScreen is initialized");

        this.model = new SplashModel(assetManager, windowManager);
        this.view = new SplashView(model, camera);

    }

    @Override
    public void enteringState(Integer previousStateName) {
        System.out.println("Entering SplashScreen");
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

