package se.chalmers.get_rect.adapters;

import se.chalmers.get_rect.utilities.Point;

public interface IInputAdapter {
    enum Keys{
        Q, W, E, A, S, D, X, H, M, SPACE, MOUSELEFT, MOUSERIGHT, LEFTKEY, RIGHTKEY, UPKEY, DOWNKEY, ENTER, ESC
    }
    Point getMousePosition();

    boolean isKeyPressed(Keys key);
    boolean isKeyJustPressed(Keys key);
    boolean isTranslatable(Keys key);

}
