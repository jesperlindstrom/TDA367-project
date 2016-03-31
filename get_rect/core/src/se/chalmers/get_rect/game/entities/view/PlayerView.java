package se.chalmers.get_rect.game.entities.view;

import se.chalmers.get_rect.game.entities.model.Player;

public class PlayerView implements IView {
    private Player model;

    public PlayerView(Player model) {
        this.model = model;
    }

    @Override
    public void draw() {

    }
}
