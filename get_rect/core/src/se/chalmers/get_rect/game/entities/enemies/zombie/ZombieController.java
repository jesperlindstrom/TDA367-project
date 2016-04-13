package se.chalmers.get_rect.game.entities.enemies.zombie;


import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.IRectangleAdapter;
import se.chalmers.get_rect.game.entities.IPhysicsController;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.entities.player.PlayerController;
import se.chalmers.get_rect.game.scenes.IScene;
import se.chalmers.get_rect.physics.ISolidObject;
import se.chalmers.get_rect.utilities.Point;

public class ZombieController implements IPhysicsController {
    private Zombie model;
    private IView view;
    private PlayerController playerController;
    private int pace;

    public ZombieController(Zombie model, IView view, PlayerController playerController) {
        this.model = model;
        this.view = view;
        this.playerController = playerController;
        pace = (int)(Math.random()*5 + 1)-2;
    }
    @Override
    public void update(double delta) {
        Point modelPosition = model.getPosition();

        int x = modelPosition.getX();
        int y = modelPosition.getY();

        if (x-playerController.getPosition().getX()+40 < 0) {
            x = x + pace;
        } else {
            x = x - pace;
        }

        if(y-playerController.getPosition().getY() < 0) {
            y = y + pace;
        } else {
            y = y - pace;
        }

        model.setPosition(x, y);
        //implement AI

    }

    @Override
    public void draw(IGraphicsAdapter graphics) {view.draw(graphics);}

    @Override
    public IRectangleAdapter getBoundingBox() {
        return model.getBoundingBox();
    }

    @Override
    public void onCollision(ISolidObject otherObject) {

    }

    @Override
    public void setScene(IScene scene) {
        
    }
}
