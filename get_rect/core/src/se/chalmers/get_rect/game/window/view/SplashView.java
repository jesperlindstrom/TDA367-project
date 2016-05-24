package se.chalmers.get_rect.game.window.view;

import se.chalmers.get_rect.adapters.ICameraAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.ICamera;
import se.chalmers.get_rect.game.window.model.SplashModel;
import se.chalmers.get_rect.utilities.Point;

public class SplashView implements IWindowView {
    private static final int BAR_WIDTH = 578;
    private static final int BARS_OFFSET = -55;
    private static final int BARS_OFFSET_Y = -250;
    private static final int BAR_BG_OFFSET = -350 + BARS_OFFSET;
    private static final int BAR_FILL_OFFSET = -145 + BARS_OFFSET;
    private SplashModel model;
    private ICameraAdapter camera;

    public SplashView(SplashModel model, ICamera camera) {
        this.model = model;
        this.camera = camera.getAdapter();
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        if (!model.getAddedAssets()) return;

        Point centerPos = camera.getPosition();

        graphics.draw("img/splash/splash_logo.png", centerPos.add(-300, BARS_OFFSET_Y+100));

        graphics.draw("img/splash/progress_bar_bg.png", centerPos.add(BAR_BG_OFFSET, BARS_OFFSET_Y+12));
        int progressWidth = (int) (BAR_WIDTH * model.getProgressValue());
        graphics.draw("img/splash/loading_fill.png", centerPos.add(BAR_FILL_OFFSET, BARS_OFFSET_Y), progressWidth, 60);
    }
}
