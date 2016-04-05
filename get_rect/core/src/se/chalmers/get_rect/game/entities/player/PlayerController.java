package se.chalmers.get_rect.game.entities.player;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.IController;
import se.chalmers.get_rect.game.entities.IView;

class PlayerController implements IController {
    private Player model;
    private IView view;

    public PlayerController(Player player, IView view) {
        this.model = player;
        this.view = view;
    }

    @Override
    public void update(long delta) {

    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        view.draw(graphics);
    }
}