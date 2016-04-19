package se.chalmers.get_rect.game.entities;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;

public interface IEntity {
    IModel getModel();
    void update(double delta);
    void draw(IGraphicsAdapter g);
}