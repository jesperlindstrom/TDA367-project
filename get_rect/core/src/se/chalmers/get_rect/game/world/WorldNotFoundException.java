package se.chalmers.get_rect.game.world;

public class WorldNotFoundException extends RuntimeException {
    public WorldNotFoundException(String name) {
        super("There is no scene with name \"" + name);
    }
}
