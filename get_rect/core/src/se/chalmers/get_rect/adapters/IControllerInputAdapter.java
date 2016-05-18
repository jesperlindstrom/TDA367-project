package se.chalmers.get_rect.adapters;

import se.chalmers.get_rect.utilities.Point;

public interface IControllerInputAdapter {
    enum Keys {
        X, Y, A, B, LEFT, RIGHT, UP, DOWN,
    }

    boolean isKeyPressed(Keys key);
    boolean isKeyJustPressed(Keys key);
    Point getDirection();

}
