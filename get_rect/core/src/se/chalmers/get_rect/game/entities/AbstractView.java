package se.chalmers.get_rect.game.entities;

public abstract class AbstractView implements IView{

    private IModel model;

    protected AbstractView() {}
    protected AbstractView(IModel model) {
        this.model = model;
    }

    @Override
    public boolean shouldBeRemoved() {
        return model != null && model.shouldBeRemoved();
    }

    @Override
    public int getDrawPriority() {
        return 0;
    }
}
