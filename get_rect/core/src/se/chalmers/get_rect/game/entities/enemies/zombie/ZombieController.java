package se.chalmers.get_rect.game.entities.enemies.zombie;


import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.IPhysicsController;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.entities.player.PlayerController;
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
    public void update(long delta) {
        Point modelPosition = model.getPosition();

        if (X-playerController.getxCoord()+40 < 0) {
            X = X + pace;
        } else {
            X = X - pace;
        }

        if(Y-playerController.getyCoord() < 0) {
            Y = Y + pace;
        } else {
            Y = Y - pace;
        }

        model.setPosition(X, Y);
        //implement AI

    }

    @Override
    public void draw(IGraphicsAdapter graphics) {view.draw(graphics);}

    @Override
    public void getBoundingBox() {

    }
}
