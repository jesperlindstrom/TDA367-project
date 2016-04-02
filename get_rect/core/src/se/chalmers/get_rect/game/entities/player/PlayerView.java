package se.chalmers.get_rect.game.entities.player;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.IView;

class PlayerView implements IView<Player> {
    private Player model;

    @Override
    public void setModel(Player model) {
        this.model = model;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        graphics.draw("data/badlogic.jpg", 0, 0);
    }
}