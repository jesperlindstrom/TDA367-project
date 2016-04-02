package se.chalmers.get_rect.game.entities.player;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.ISolidEntity;

public class PlayerEntity implements ISolidEntity {
    private Player model;
    private PlayerView view;

    public PlayerEntity() {
        model = new Player();
        view = new PlayerView(model);
    }

    @Override
    public void update(long delta) {

    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        view.draw(graphics);
    }

    @Override
    public void getBoundingBox() {

    }
}
