package se.chalmers.get_rect.game.scenes;

import se.chalmers.get_rect.game.GameConfig;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.CameraManager;
import se.chalmers.get_rect.game.entities.AbstractView;
import se.chalmers.get_rect.utilities.Point;

public abstract class AbstractBackgroundView extends AbstractView {
    private CameraManager camera;
    private String sceneImage;
    private String skyboxImage;
    private static final int DRAW_PRIORITY = -1;

    protected AbstractBackgroundView(CameraManager camera, String sceneImage) {
        super();
        this.camera = camera;
        this.sceneImage = sceneImage;
    }

    protected AbstractBackgroundView(CameraManager camera, String sceneImage, String skyboxImage) {
        this.camera = camera;
        this.sceneImage = sceneImage;
        this.skyboxImage = skyboxImage;
    }

    @Override
    public int getDrawPriority() {
        return DRAW_PRIORITY;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        Point pos = camera.getPosition();

        if (skyboxImage != null) {
            graphics.draw(skyboxImage, pos, GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT, pos);
        }

        graphics.draw(sceneImage, pos, GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT, pos);
    }
}
