package se.chalmers.get_rect.adapters;

import se.chalmers.get_rect.utilities.Point;

public interface IKeyboardInputAdapter {
    enum Keys{
        Q, W, E, A, S, D, X, H, M, SPACE, MOUSELEFT, MOUSERIGHT, LEFT_KEY, RIGHT_KEY, UP_KEY, DOWN_KEY, ENTER, ESC
    }
    Point getMousePosition();

    boolean isKeyPressed(Keys key);
    boolean isKeyJustPressed(Keys key);
    boolean isTranslatable(Keys key);

}
