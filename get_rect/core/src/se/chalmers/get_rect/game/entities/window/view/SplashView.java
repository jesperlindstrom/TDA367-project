package se.chalmers.get_rect.game.entities.window.view;

import se.chalmers.get_rect.game.GameConfig;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractView;
import se.chalmers.get_rect.game.entities.ICamera;
import se.chalmers.get_rect.game.entities.window.model.SplashModel;
import se.chalmers.get_rect.utilities.Point;

public class SplashView extends AbstractView {
    private Point cameraPos;
    private SplashModel model;

    /**
     * cameraPos adjusts to the current screen in use and fits the picture to the middle
     * @param model
     * @param camera
     */

    public SplashView(SplashModel model, ICamera camera) {
        this.model = model;
        this.cameraPos = camera.getAdapter().getPosition();
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        if (model.getAddedAssets()) {
            Point backgroundPos = cameraPos.add(-1920/2, -1080/2);
            graphics.draw("img/splash/splash_bg.jpg", backgroundPos, GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);
            int progressWidth;

            if (model.getStop()) {
                progressWidth = 578;
            } else {
                progressWidth = (int) (578 * model.getProgressValue());
            }

            graphics.draw("img/splash/loading_fill.png", backgroundPos.add(768, 128), progressWidth, 60);

            if (model.getDidStop()) {
                graphics.drawText("LOL JK xD", backgroundPos.add(1300, 200));
            }

            if (model.getProgressValue() > 2.3) {
                int secondProgressWidth = (int) (300 * (model.getProgressValue() - 2.3));
                graphics.draw("img/splash/loading_fill.png", backgroundPos.addY(128), secondProgressWidth, 60);
            }
        }
    }
}
