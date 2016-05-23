package se.chalmers.get_rect.game.world;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.IEntityHolder;
import se.chalmers.get_rect.states.IState;

public interface IWorld extends IState, IEntityHolder {
    void update(double delta);
    void draw(IGraphicsAdapter graphics);
}
