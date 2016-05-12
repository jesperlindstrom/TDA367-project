package se.chalmers.get_rect.game.entities.overlays.view;

import se.chalmers.get_rect.game.GameConfig;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.utilities.Point;

public class DebugPlayerView {
    private IPhysicsModel player;

    public DebugPlayerView(IPhysicsModel player) {
        super();
        this.player = player;
    }

    public Point draw(IGraphicsAdapter graphics, Point position) {
        if (GameConfig.SHOW_POS && !GameConfig.DISABLE_ALL) {
            position = position.addY(-20);
            graphics.drawText(player.getPosition().toString(), position);
        }

        return position;
    }
}
