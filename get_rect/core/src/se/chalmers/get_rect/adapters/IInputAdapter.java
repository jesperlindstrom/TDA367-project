package se.chalmers.get_rect.adapters;

public interface IInputAdapter {
    enum Keys{
        Q, W, E, A, S, D, X, H, SPACE, MOUSELEFT, MOUSERIGHT, LEFTKEY, RIGHTKEY, UPKEY, DOWNKEY, ENTER, ESC
    }
    boolean isKeyPressed(Keys key);
    boolean isKeyJustPressed(Keys key);
    boolean isTranslatable(Keys key);

}
