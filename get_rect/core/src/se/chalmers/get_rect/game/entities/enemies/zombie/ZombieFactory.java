package se.chalmers.get_rect.game.entities.enemies.zombie;


import se.chalmers.get_rect.game.entities.IController;
import se.chalmers.get_rect.game.entities.IView;

public class ZombieFactory {
    public IController make() {
        Zombie model = new Zombie();
        IView view = new ZombieView(model);

        return new ZombieController(model, view);
    }
}
