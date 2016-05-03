package se.chalmers.get_rect.game.entities;

public class Entity implements IEntity {
    private IModel model;
    private IView view;

    public Entity(IModel model, IView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public IModel getModel() {
        return model;
    }

    @Override
    public IView getView() {
        return view;
    }
}
