package se.chalmers.get_rect.utilities.debug;

import se.chalmers.get_rect.GameConfig;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.utilities.Point;

class FpsHandler {
    private int currentFPS;
    private int FPS;
    private int updates;
    private int lowestFPS = 1000;
    private double updatesInTime;
    private double delta;
    private double timeForLowest;

    public void update(double delta) {
        currentFPS = (int)(10/delta);
        this.delta = delta;

        if (GameConfig.SHOW_FPS) {
            updates++;
            updatesInTime += delta/10;

            if (updatesInTime > 0.5) {
                FPS = (int)(updates/updatesInTime);
                updates = 0;
                updatesInTime = 0;
            }
        }

        if (GameConfig.SHOW_LOWESTFPS) {
            timeForLowest += delta/10;

            if (currentFPS < lowestFPS || timeForLowest > 10) {
                lowestFPS = currentFPS;
                timeForLowest = 0;
            }
        }
    }

    public Point draw(IGraphicsAdapter graphics, Point point) {
        int textOffset = -20;

        if (GameConfig.SHOW_FPS) {
            point = point.addY(textOffset);
            graphics.drawText("FpsHandler = " + FPS, point);
        }

        if (GameConfig.SHOW_LOWESTFPS) {
            point = point.addY(textOffset);
            graphics.drawText("lowestFPS(10sec) = " + lowestFPS, point);
        }

        if (GameConfig.SHOW_DELTA) {
            point = point.addY(textOffset);
            graphics.drawText("delta = " + delta, point);
        }

        return point;
    }
}
