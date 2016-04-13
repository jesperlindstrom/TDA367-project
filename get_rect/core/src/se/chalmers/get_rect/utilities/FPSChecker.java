package se.chalmers.get_rect.utilities;

import se.chalmers.get_rect.GameConfig;
import se.chalmers.get_rect.IGame;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.CameraManager;
import se.chalmers.get_rect.game.entities.player.PlayerController;

/**
 * This will print your updates or draws per second.
 *
 * If you want to use this do following:
 * Add an FPSChecker to a class with a draw or update method.
 * Put this in the variables
 *
 * private FPSChecker fps = new FPSChecker("[name of file]");
 *
 * and then add
 * fps.update(delta);
 * fps.draw(saker)
 *
 * to the draw or update in the file.
 *
 */
public class FPSChecker {

    private PlayerController playerController;
    private int currentFPS;
    private int FPS;
    private int updates;
    private int lowestFPS = 1000;
    private static final int graphicsUpdatePerSecond = 4;
    private double updatesInTime;
    private String name;
    private double delta;
    private double timeForLowest;

    public FPSChecker(String name, PlayerController playerController) {
        this.name = name;
        this.playerController = playerController;
    }


    public void update(double delta) {
        this.delta = delta;
        currentFPS = (int)(10/delta);
        updatesInTime += delta/10;
        timeForLowest += delta/10;
        updates++;



        if (currentFPS < lowestFPS || timeForLowest > 10) {
            lowestFPS = currentFPS;
            timeForLowest = 0;
        }

        if (updatesInTime > 0.5) {
            FPS = (int)(updates/updatesInTime);
            updates = 0;
            updatesInTime = 0;

        }

    }

    public void update(double delta, CameraManager camera) {
        update(delta);
    }


    public void draw(IGraphicsAdapter graphics) {
        draw(graphics, new Point(0, 0));
    }

    public void draw(IGraphicsAdapter graphics, Point point) {
        point = point.addY(1095);

        if (GameConfig.SHOW_FPS) {
            point = point.addY(-20);
            graphics.drawText("= " + FPS, point.addX(30));
            graphics.drawText("FPS ", point);
        }

        if (GameConfig.SHOW_LOWESTFPS) {
            point = point.addY(-20);
            graphics.drawText("= " + lowestFPS, point.addX(80));
            graphics.drawText("lowestFPS ", point);
        }
        
        if (GameConfig.SHOW_DELTA) {
            point = point.addY(-20);
            graphics.drawText("= " + delta, point.addX(50));
            graphics.drawText("delta ", point);
        }
        // TODO: 2016-04-13 where is player pos? o.0
        if (true) {
            point = point.addY(-20);
            graphics.drawText(playerController.getPosition().toString(), point.addY(10));
        }


    }
}
