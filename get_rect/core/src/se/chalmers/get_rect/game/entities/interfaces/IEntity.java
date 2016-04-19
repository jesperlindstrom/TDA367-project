package se.chalmers.get_rect.game.entities.interfaces;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;

public interface IEntity {
    IModel getModel();
    void update();
    void draw(IGraphicsAdapter g);
}