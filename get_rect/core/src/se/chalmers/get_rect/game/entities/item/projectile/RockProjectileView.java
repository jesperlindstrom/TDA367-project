package se.chalmers.get_rect.game.entities.item.projectile;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractView;
import se.chalmers.get_rect.game.entities.IModel;

public class RockProjectileView extends AbstractView {
    private IModel model;

    public RockProjectileView(IModel model) {
        super(model);
        this.model = model;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.draw("img/items/rock.png", model.getPosition());
    }

    @Override
    public int getDrawPriority() {
        return 7;
    }
}
