package se.chalmers.get_rect.game.scenes;

import se.chalmers.get_rect.game.IGameComponent;

import se.chalmers.get_rect.game.entities.IEntity;
import se.chalmers.get_rect.game.entities.IPhysicsEntity;
import se.chalmers.get_rect.states.IState;

public interface IScene extends IGameComponent, IState {
    void addEntity(layer layer, IEntity entity);
    void addPhysicsEntity(layer layer, IPhysicsEntity entity);

    enum layer {
        FOREGROUND, BACKGROUND, FOREGROUND_EFFECTS, BACKGROUND_EFFECTS
    }
}
