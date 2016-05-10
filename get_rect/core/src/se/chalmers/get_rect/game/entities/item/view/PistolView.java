package se.chalmers.get_rect.game.entities.item.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractView;
import se.chalmers.get_rect.game.entities.IModel;

public class PistolView extends AbstractView {

    private IModel model;

    public PistolView(IModel model) {
        this.model = model;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.draw("img/items/potistol.png", model.getPosition().add(2, -18), 30, 20);
    }
}