package se.chalmers.get_rect.adapters;

/**
 * Created by simsund on 2016-03-31.
 */
public interface IInputAdapter {
    enum Keys{
        Q, W, E, A, S, D, SPACE, MOUSELEFT, MOUSERIGHT, LEFTKEY, RIGHTKEY, UPKEY, DOWNKEY, ENTER, ESC
    }
    boolean isKeyPressed(Keys key);
    boolean isTranslateable(Keys key);

}