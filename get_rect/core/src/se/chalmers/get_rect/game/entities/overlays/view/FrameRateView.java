package se.chalmers.get_rect.game.entities.overlays.view;

import se.chalmers.get_rect.GameConfig;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractView;
import se.chalmers.get_rect.game.entities.overlays.model.FrameRate;
import se.chalmers.get_rect.utilities.Point;

public class FrameRateView extends AbstractView {
    private FrameRate model;
    private static final int DRAW_PRIORITY = 5;

    public FrameRateView(FrameRate model) {
        this.model = model;
    }

    public Point draw(IGraphicsAdapter graphics, Point point) {
        int textOffset = -20;

        if (GameConfig.SHOW_FPS) {
            point = point.addY(textOffset);
            graphics.drawText("FPS = " + model.getFps(), point);
        }

        if (GameConfig.SHOW_LOWESTFPS) {
            point = point.addY(textOffset);
            graphics.drawText("lowestFPS(10sec) = " + model.getLowestFps(), point);
        }

        if (GameConfig.SHOW_DELTA) {
            point = point.addY(textOffset);
            graphics.drawText("delta = " + model.getDelta(), point);
        }

        return point;
    }

    @Override
    public int getDrawPriority() {
        return DRAW_PRIORITY;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        draw(graphics, new Point(0, 0));
    }
}
