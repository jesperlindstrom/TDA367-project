package se.chalmers.get_rect.game.entities.player;

import se.chalmers.get_rect.game.entities.IController;
import se.chalmers.get_rect.game.entities.IView;

public class PlayerFactory {
    public IController make() {
        Player model = new Player();
        IView view = new PlayerView(model);

        return new PlayerController(model, view);
    }
}
