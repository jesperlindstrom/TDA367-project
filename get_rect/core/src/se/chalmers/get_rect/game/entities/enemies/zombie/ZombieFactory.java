package se.chalmers.get_rect.game.entities.enemies.zombie;

import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.entities.player.PlayerController;
import se.chalmers.get_rect.utilities.Point;

public class ZombieFactory {

    private PlayerController playerController;

    public ZombieFactory(PlayerController playerController) {
        this.playerController = playerController;
    }

    public ZombieController make() {
        return make(0, 0);
    }
    public ZombieController make(ZombieDataStore data) {
        return make(data.getX(), data.getY());
    }

    public ZombieController make(int x, int y) {
        Zombie model = new Zombie(new Point(x, y));
        IView view = new ZombieView(model);

        return new ZombieController(model, view, playerController);
    }
}