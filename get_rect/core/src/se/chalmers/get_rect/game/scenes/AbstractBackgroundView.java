package se.chalmers.get_rect.game.scenes;

import se.chalmers.get_rect.GameConfig;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.CameraManager;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.utilities.Point;

public abstract class AbstractBackgroundView implements IView {
    private CameraManager camera;
    private String imagePath;

    protected AbstractBackgroundView(CameraManager camera, String imagePath) {
        this.camera = camera;
        this.imagePath = imagePath;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        Point pos = camera.getPosition();
        graphics.draw(imagePath, pos, GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT, pos);
    }
}
