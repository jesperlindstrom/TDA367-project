package se.chalmers.get_rect.game.entities.enemies.zombie;

import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.entities.player.PlayerController;
import se.chalmers.get_rect.utilities.Point;

public class ZombieFactory {

    private PlayerController playerController;
    private IRectangleFactoryAdapter rectangleFactory;

    public ZombieFactory(PlayerController playerController, IRectangleFactoryAdapter rectangleFactory) {
        this.playerController = playerController;
        this.rectangleFactory = rectangleFactory;
    }

    public ZombieController make() {
        return make(0, 0);
    }

    public ZombieController make(ZombieDataStore data) {
        return make(data.getX(), data.getY());
    }

    public ZombieController make(int x, int y) {
        Zombie model = new Zombie(new Point(x, y), rectangleFactory);
        IView view = new ZombieView(model);

        return new ZombieController(model, view, playerController);
    }
}