package se.chalmers.get_rect.game.entities.projectile;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.IView;

public class ProjectileView implements IView{
    private String currentImagePath;
    private Projectile projectile;

    public ProjectileView(Projectile projectile){
        this.projectile = projectile;
        this.currentImagePath = "data/bullet.png";
    }
    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.draw(currentImagePath, projectile.getPosition());
    }
}
