package se.chalmers.get_rect.game.entities.player;

import se.chalmers.get_rect.game.entities.IController;

public class PlayerController implements IController<Player> {
    private Player model;

    @Override
    public void setModel(Player model) {
        this.model = model;
    }

    @Override
    public void update(long delta) {

    }
}