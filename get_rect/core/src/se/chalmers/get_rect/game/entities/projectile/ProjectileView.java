package se.chalmers.get_rect.game.entities.projectile;

import se.chalmers.get_rect.game.entities.AbstractStaticView;
import se.chalmers.get_rect.game.entities.IModel;

public class ProjectileView extends AbstractStaticView {
    public ProjectileView(IModel model) {
        super(model, "img/entities/projectiles/bullet.png");
    }
}