package se.chalmers.get_rect.game.entities;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entity, String type) {
        super("There is no \"" + entity + "\" with type \"" + type + "\"");
    }
}