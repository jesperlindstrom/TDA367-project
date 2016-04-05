package se.chalmers.get_rect.game.entities.player;

import se.chalmers.get_rect.game.entities.IView;

public class PlayerFactory {
    public PlayerController make() {
        Player model = new Player();
        IView view = new PlayerView(model);

        return new PlayerController(model, view);
    }
}
