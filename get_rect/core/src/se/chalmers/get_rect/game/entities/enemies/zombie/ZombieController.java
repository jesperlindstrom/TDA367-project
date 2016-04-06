package se.chalmers.get_rect.game.entities.enemies.zombie;


import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.IPhysicsController;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.entities.player.PlayerController;

public class ZombieController implements IPhysicsController {
    private Zombie model;
    private IView view;
    private PlayerController playerController;
    private int pace;

    public ZombieController(Zombie model, IView view, PlayerController playerController) {
        this.model = model;
        this.view = view;
        this.playerController = playerController;
        pace = (int)(Math.random()*5 + 1);
    }
    @Override
    public void update(long delta) {
        int X = model.getX();
        int Y = model.getY();

        if (X-playerController.getxCoord() < 0) {
            X = X + pace;
        } else {
            X = X - pace;
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
