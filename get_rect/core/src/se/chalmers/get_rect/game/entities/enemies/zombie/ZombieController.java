package se.chalmers.get_rect.game.entities.enemies.zombie;


import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.IPhysicsController;
import se.chalmers.get_rect.game.entities.IView;

public class ZombieController implements IPhysicsController {
    private Zombie model;
    private IView view;

    public ZombieController(Zombie zombie, IView view) {
        this.model = model;
        this.view = view;
    }
    @Override
    public void update(long delta) {

    }

    @Override
    public void draw(IGraphicsAdapter graphics) {view.draw(graphics);}

    @Override
    public void getBoundingBox() {

    }
}
