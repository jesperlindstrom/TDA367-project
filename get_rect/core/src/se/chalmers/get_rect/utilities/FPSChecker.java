package se.chalmers.get_rect.utilities;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.CameraManager;

import java.util.Timer;
import java.util.TimerTask;

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
    private int currentFPS;
    private int FPS;
    private int updates;
    private int lowestFPS = 1000;
    private static final int graphicsUpdatePerSecond = 4;
    private double updatesInTime;
    private String name;
    private boolean show;
    private boolean showLowest;
    private double delta;

    public FPSChecker(String name) {
        this.name = name;
        showLowest = true;
    }


    public void update(double delta) {
        this.delta = delta;
        currentFPS = (int)(10/delta);
        updatesInTime += delta/10;
        updates++;



        if (currentFPS < lowestFPS) {
            lowestFPS = currentFPS;
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
        point = point.addY(1070);
        graphics.drawText("= " + FPS, point.addX(30));
        graphics.drawText("FPS ", point);
        if (showLowest) {
            point = point.addY(-40);
            graphics.drawText("= " + lowestFPS, point.addX(80));
            graphics.drawText("lowestFPS ", point);
        }
        point = point.addY(-40);
        graphics.drawText("= " + delta, point.addX(50));
        graphics.drawText("delta ", point);

    }
}
