package se.chalmers.get_rect.game;

import se.chalmers.get_rect.game.entities.IEntity;
import se.chalmers.get_rect.states.IState;

public interface IScene extends IGameComponent, IState{
    void add(IEntity entity);
}
