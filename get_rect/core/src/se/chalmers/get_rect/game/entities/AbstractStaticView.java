package se.chalmers.get_rect.game.entities;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;

public abstract class AbstractStaticView extends AbstractView {
    private String image;

    protected AbstractStaticView(IModel model, String image) {
        super(model);
        this.image = image;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.draw(image, getModel().getPosition());
    }
}
