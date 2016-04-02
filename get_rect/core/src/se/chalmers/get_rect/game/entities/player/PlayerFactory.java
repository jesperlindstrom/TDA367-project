package se.chalmers.get_rect.game.entities.player;

import se.chalmers.get_rect.game.entities.Entity;
import se.chalmers.get_rect.game.entities.IController;
import se.chalmers.get_rect.game.entities.IView;

public class PlayerFactory {
    public Entity<Player> make() {
        Player model = new Player();
        IView<Player> view = new PlayerView();
        IController<Player> controller = new PlayerController();

        return new Entity<>(model, view, controller);
    }
}
