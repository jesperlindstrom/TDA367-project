package se.chalmers.get_rect.game.entities.overlays.debug.player;

import se.chalmers.get_rect.GameConfig;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.utilities.Point;

public class PlayerView implements IView {
    private IPhysicsModel player;

    public PlayerView(IPhysicsModel player) {
        this.player = player;
    }

    public Point draw(IGraphicsAdapter graphics, Point position) {
        if (GameConfig.SHOW_POS) {
            position = position.addY(-20);
            graphics.drawText(player.getPosition().toString(), position);
        }

        return position;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        draw(graphics, new Point(0, 0));
    }
}
