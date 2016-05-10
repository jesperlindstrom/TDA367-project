package se.chalmers.get_rect.game.scenes;

public class SceneNotFoundException extends RuntimeException {
    public SceneNotFoundException(String name) {
        super("There is no scene with name \"" + name);
    }
}
