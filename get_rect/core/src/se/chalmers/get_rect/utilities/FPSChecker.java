package se.chalmers.get_rect.utilities;

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
                System.out.println(name + " | FPS = " + FPS);
                FPS = 0;
                timer();
            }
        }, 1000);
    }

    public void update() {
        FPS++;
    }
}
