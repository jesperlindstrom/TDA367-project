package se.chalmers.get_rect.game.window;

public class WindowNotFoundException extends RuntimeException {
    public WindowNotFoundException(String type) {
        super("There is no window with type \"" + type);
    }
}