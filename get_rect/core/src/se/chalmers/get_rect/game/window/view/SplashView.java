package se.chalmers.get_rect.game.window.view;

import se.chalmers.get_rect.game.GameConfig;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractView;
import se.chalmers.get_rect.game.entities.ICamera;
import se.chalmers.get_rect.game.window.model.SplashModel;
import se.chalmers.get_rect.utilities.Point;

public class SplashView implements IWindowView{
    private Point cameraPos;
    private SplashModel model;

    public SplashView(SplashModel model, ICamera camera) {
        this.model = model;
        this.cameraPos = camera.getAdapter().getPosition();
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        if (!model.getAddedAssets()) return;

        Point backgroundPos = cameraPos.add(-1920/2, -1080/2);
        graphics.draw("img/splash/splash_bg.jpg", backgroundPos, GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);

        int progressWidth = (int) (578 * model.getProgressValue());
        graphics.draw("img/splash/loading_fill.png", backgroundPos.add(768, 128), progressWidth, 60);
    }
}
