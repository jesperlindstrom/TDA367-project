package se.chalmers.get_rect.game.entities.enemies.zombie;

import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.entities.player.PlayerController;

public class ZombieFactory {

    private PlayerController playerController;

    public ZombieFactory(PlayerController playerController) {
        this.playerController = playerController;
    }

    public ZombieController make() {
        return make(0, 0);
    }

    public ZombieController make(int x, int y) {
        Zombie model = new Zombie(x, y);
        IView view = new ZombieView(model);

        return new ZombieController(model, view, playerController);
    }
}