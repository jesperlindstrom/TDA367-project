package se.chalmers.get_rect.adapters;

import se.chalmers.get_rect.utilities.Point;

public interface IControllerInputAdapter {
    enum Keys {
        X, Y, A, B, START, SELECT, LB, RB,
        DPAD_LEFT, DPAD_RIGHT, DPAD_UP, DPAD_DOWN, DPAD_CENTER,
        L_LEFT, L_RIGHT, L_UP, L_DOWN,
        R_LEFT, R_RIGHT, R_UP, R_DOWN,
        L_BUMPER, R_BUMPER
    }

    boolean isKeyPressed(Keys key);
    boolean isKeyJustPressed(Keys key);
    Point getDirection();

}
