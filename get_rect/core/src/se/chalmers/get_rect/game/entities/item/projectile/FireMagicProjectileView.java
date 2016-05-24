package se.chalmers.get_rect.game.entities.item.projectile;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractView;
import se.chalmers.get_rect.game.entities.IModel;

public class FireMagicProjectileView extends AbstractView {
    private IModel model;

    public FireMagicProjectileView(IModel model) {
        super(model);
        this.model = model;
    }
    private static final int DRAW_PRIORITY = 967;

    @Override
    public int getDrawPriority() {
        return DRAW_PRIORITY;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.draw("img/entities/projectiles/fire.png", model.getPosition().add(-8, -9));
    }
}
