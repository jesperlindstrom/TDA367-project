package se.chalmers.get_rect.game.entities.projectile.view;

import se.chalmers.get_rect.game.entities.AbstractStaticView;
import se.chalmers.get_rect.game.entities.IModel;

public class ProjectileView extends AbstractStaticView {
    public ProjectileView(IModel model) {
        super(model, "img/entities/projectiles/bullet.png");
    }
    private static final int DRAW_PRIORITY = 967;

    @Override
    public int getDrawPriority() {
        return DRAW_PRIORITY;
    }
}