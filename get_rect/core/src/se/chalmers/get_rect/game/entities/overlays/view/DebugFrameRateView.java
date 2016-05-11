package se.chalmers.get_rect.game.entities.overlays.view;

import se.chalmers.get_rect.game.GameConfig;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.overlays.model.FrameRate;
import se.chalmers.get_rect.utilities.Point;

public class DebugFrameRateView {
    private FrameRate model;

    public DebugFrameRateView(FrameRate model) {
        this.model = model;
    }

    public Point draw(IGraphicsAdapter graphics, Point point) {
        int textOffset = -20;

        if (GameConfig.SHOW_FPS && !GameConfig.DISABLE_ALL) {
            point = point.addY(textOffset);
            graphics.drawText("FPS = " + model.getFps(), point);
        }

        if (GameConfig.SHOW_LOWESTFPS && !GameConfig.DISABLE_ALL) {
            point = point.addY(textOffset);
            graphics.drawText("lowestFPS(10sec) = " + model.getLowestFps(), point);
        }

        if (GameConfig.SHOW_DELTA && !GameConfig.DISABLE_ALL) {
            point = point.addY(textOffset);
            graphics.drawText("delta = " + model.getDelta(), point);
        }

        return point;
    }
}
