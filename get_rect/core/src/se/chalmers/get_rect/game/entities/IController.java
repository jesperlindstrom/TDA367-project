package se.chalmers.get_rect.game.entities;

public interface IController<T extends IModel> {
    void update(long delta);
    void setModel(T model);
}
