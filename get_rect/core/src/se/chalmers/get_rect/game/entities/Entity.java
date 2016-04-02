package se.chalmers.get_rect.game.entities;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;

public class Entity<T extends IModel> implements IEntity {
    private T model;
    private IView<T> view;
    private IController<T> controller;

    public Entity(T model, IView<T> view, IController<T> controller) {
        this.model = model;
        this.view = view;
        this.controller = controller;

        view.setModel(model);
        controller.setModel(model);
    }

    @Override
    public void update(long delta) {
        controller.update(delta);
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        view.draw(graphics);
    }
}
