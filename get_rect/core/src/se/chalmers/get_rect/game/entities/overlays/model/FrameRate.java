package se.chalmers.get_rect.game.entities.overlays.model;

import se.chalmers.get_rect.game.GameConfig;

public class FrameRate {
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

    public int getFps() {
        return FPS;
    }

    public int getLowestFps() {
        return lowestFPS;
    }

    public double getDelta() {
        return delta;
    }
}