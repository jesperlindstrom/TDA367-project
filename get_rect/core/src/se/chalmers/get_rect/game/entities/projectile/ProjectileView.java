package se.chalmers.get_rect.game.entities.projectile;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.IView;

public class ProjectileView implements IView{
    private Projectile projectile;

    public ProjectileView(Projectile projectile) {
        this.projectile = projectile;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.draw("img/entities/projectiles/bullet.png", projectile.getPosition());
    }
}
