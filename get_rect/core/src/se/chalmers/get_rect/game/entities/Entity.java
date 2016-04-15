package se.chalmers.get_rect.game.entities;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;

public class Entity implements IEntity {
    private IModel model;
    private IView view;

    public Entity(IModel model, IView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void update() {
        model.update();
    }

    @Override
    public void draw(IGraphicsAdapter g) {
        view.draw(g);
    }

    @Override
    public IModel getModel() {
        return model;
    }
}
