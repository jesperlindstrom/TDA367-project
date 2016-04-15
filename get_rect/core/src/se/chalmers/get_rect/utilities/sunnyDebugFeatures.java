package se.chalmers.get_rect.utilities;

import se.chalmers.get_rect.GameConfig;
import se.chalmers.get_rect.IGame;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.CameraManager;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.entities.player.PlayerController;

/**
 *  class for debugging stats
 *  set booleans in GameConfig
 *  and it will print values in
 *  the top left corner
 */

public class sunnyDebugFeatures {

    private IPhysicsModel playerController;
    private int currentFPS;
    private int FPS;
    private int updates;
    private int lowestFPS = 1000;
    private double updatesInTime;
    private double delta;
    private double timeForLowest;

    public sunnyDebugFeatures(IPhysicsModel playerController) {
        this.playerController = playerController;
    }


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

    public void draw(IGraphicsAdapter graphics, Point point) {
        point = point.addY(1095);
        int textOffset = -20;

        if (GameConfig.SHOW_FPS) {
            point = point.addY(textOffset);
            graphics.drawText("FPS = " + FPS, point);
        }

        if (GameConfig.SHOW_LOWESTFPS) {
            point = point.addY(textOffset);
            graphics.drawText("lowestFPS(10sec) = " + lowestFPS, point);
        }
        
        if (GameConfig.SHOW_DELTA) {
            point = point.addY(textOffset);
            graphics.drawText("delta = " + delta, point);
        }

        if (GameConfig.SHOW_POS) {
            point = point.addY(textOffset);
            graphics.drawText(playerController.getPosition().toString(), point);
        }
    }
}
