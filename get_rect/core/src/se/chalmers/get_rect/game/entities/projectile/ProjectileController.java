package se.chalmers.get_rect.game.entities.projectile;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.IRectangleAdapter;
import se.chalmers.get_rect.game.entities.IPhysicsController;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.entities.player.PlayerController;
import se.chalmers.get_rect.game.scenes.IScene;
import se.chalmers.get_rect.physics.ISolidObject;
import se.chalmers.get_rect.utilities.Point;

public class ProjectileController implements IPhysicsController {
    private Projectile projectile;
    private IView view;
    private int speed;
    private int damage;
    private IScene scene;
    private int direction;

    public ProjectileController(Projectile projectile, IView view){
        this.projectile = projectile;
        this.view = view;
        //this.speed = speed;
        //this.damage = damage;
    }

    @Override
    public void setScene(IScene scene) {
        this.scene = scene;
    }

    @Override
    public void update(double delta) {
        Point modelPosition = projectile.getPosition();
        int x = modelPosition.getX();
        int y = modelPosition.getY();
        x = x + direction;
        projectile.setPosition(x, y);
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        view.draw(graphics);
    }

    @Override
    public IRectangleAdapter getBoundingBox() {
        return projectile.getBoundingBox();
    }

    @Override
    public void onCollision(ISolidObject otherObject) {

    }
    public void setAngle(boolean isLeft){
        if (isLeft){
            direction = -10;
        }
        else {
            direction = 10;
        }
    }
}
