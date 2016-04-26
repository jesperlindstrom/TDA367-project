package se.chalmers.get_rect.game.scenes;

import se.chalmers.get_rect.game.entities.IEntity;
import se.chalmers.get_rect.game.entities.IPhysicsEntity;

public interface IEntityHolder {
    enum layer {
        OVERLAY_UI, FOREGROUND, BACKGROUND, FOREGROUND_EFFECTS, BACKGROUND_EFFECTS
    }

    void addEntity(layer layer, IEntity entity);
    void addPhysicsEntity(layer layer, IPhysicsEntity entity);
}
