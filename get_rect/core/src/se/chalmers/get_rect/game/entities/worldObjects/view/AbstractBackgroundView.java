package se.chalmers.get_rect.game.entities.worldObjects.view;

import se.chalmers.get_rect.game.GameConfig;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractView;
import se.chalmers.get_rect.game.entities.ICamera;
import se.chalmers.get_rect.utilities.Point;

public abstract class AbstractBackgroundView extends AbstractView {
    private ICamera camera;
    private String sceneImage;
    private String skyboxImage;
    private static final int DRAW_PRIORITY = -1;

    protected AbstractBackgroundView(ICamera camera, String sceneImage) {
        super();
        this.camera = camera;
        this.sceneImage = sceneImage;
    }

    protected AbstractBackgroundView(ICamera camera, String sceneImage, String skyboxImage) {
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
            graphics.draw(skyboxImage, pos, camera.getAdapter().getWidth(), camera.getAdapter().getHeight(), pos);
        }

        graphics.draw(sceneImage, pos, camera.getAdapter().getWidth(), camera.getAdapter().getHeight(), pos);
    }
}
