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
        if (model != null) {
            model.update();
        }
    }

    @Override
    public void draw(IGraphicsAdapter g) {
        if (view != null) {
            view.draw(g);
        }
    }

    @Override
    public IModel getModel() {
        return model;
    }
}
