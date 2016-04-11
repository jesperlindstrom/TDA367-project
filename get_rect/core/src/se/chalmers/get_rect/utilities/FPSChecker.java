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
 * fps.update();
 * to the draw or update in the file.
 *
 */
public class FPSChecker {
    private int FPS;
    private int AVG;
    private String name;

    public FPSChecker(String name) {
        timer();
        this.name = name;
    }

    private void timer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                AVG = FPS*2;
                FPS = 0;

                timer();
            }
        }, 500);
    }

    public void update() {
        FPS++;
    }

    public void update(IGraphicsAdapter graphicsAdapter) {
        FPS++;
        draw(graphicsAdapter);
    }

    public void update(IGraphicsAdapter graphicsAdapter, CameraManager camera) {
        FPS++;
        draw(graphicsAdapter, camera.getCenterPosition().add(new Point(-500, 700)));
    }


    private void draw(IGraphicsAdapter graphics) {
        graphics.drawText("FPS = " + AVG, 500, 500);
    }

    private void draw(IGraphicsAdapter graphics, Point point) {
        graphics.drawText("FPS = " + AVG, point);
    }
}
