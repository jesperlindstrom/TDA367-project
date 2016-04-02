package se.chalmers.get_rect.game.entities.player;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.ISolidEntity;

class PlayerView implements ISolidEntity.View {
    private Player model;

    public PlayerView(Player model) {
        this.model = model;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {

    }
}
