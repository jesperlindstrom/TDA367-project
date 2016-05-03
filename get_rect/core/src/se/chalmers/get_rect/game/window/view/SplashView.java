package se.chalmers.get_rect.game.window.view;

import se.chalmers.get_rect.GameConfig;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.CameraManager;
import se.chalmers.get_rect.game.entities.AbstractView;
import se.chalmers.get_rect.game.window.model.SplashModel;
import se.chalmers.get_rect.utilities.Point;

public class SplashView extends AbstractView {

    private Point cameraPos;
    private SplashModel model;

    public SplashView(SplashModel model, CameraManager camera) {
        this.model = model;
        this.cameraPos = camera.getPosition();
    }


    @Override
    public void draw(IGraphicsAdapter graphics) {

        if (model.getAddedAssets()) {
            graphics.draw("img/splash/splash_bg.jpg", cameraPos, GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);
            int progressWidth;

            if (model.getStop()) {
                progressWidth = 578;
            } else {
                progressWidth = (int) (578 * model.getProgressValue());
            }

            graphics.draw("img/splash/loading_fill.png", cameraPos.add(new Point(768, 128)), progressWidth, 60);

            if (model.getDidStop()) {
                graphics.drawText("LOL JK xD", 1300, 200);
            }

            if (model.getProgressValue() > 2.3) {
                int secondProgressWidth = (int) (300 * (model.getProgressValue() - 2.3));
                graphics.draw("img/splash/loading_fill.png", 0, 128, secondProgressWidth, 60);
            }
        }
    }
}
