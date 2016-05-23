package se.chalmers.get_rect.game.window;

public class WindowNotFoundException extends RuntimeException{
    public WindowNotFoundException(String name) {
        super("There is no world with name \"" + name);
    }
}
