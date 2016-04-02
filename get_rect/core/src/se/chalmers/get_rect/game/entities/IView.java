package se.chalmers.get_rect.game.entities;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;

public interface IView<T extends IModel> {
    void draw(IGraphicsAdapter graphics);
    void setModel(T model);
}