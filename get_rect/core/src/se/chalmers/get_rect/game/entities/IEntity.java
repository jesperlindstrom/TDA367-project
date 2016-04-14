package se.chalmers.get_rect.game.entities;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;

public interface IEntity {
    void update();
    void draw(IGraphicsAdapter g);
    IModel getModel();
}
