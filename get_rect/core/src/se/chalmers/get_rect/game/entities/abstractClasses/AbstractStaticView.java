package se.chalmers.get_rect.game.entities.abstractClasses;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.interfaces.IModel;
import se.chalmers.get_rect.game.entities.interfaces.IView;

public abstract class AbstractStaticView implements IView {
    private IModel model;
    private String image;

    protected AbstractStaticView(IModel model, String image) {
        this.model = model;
        this.image = image;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.draw(image, model.getPosition());
    }
}
