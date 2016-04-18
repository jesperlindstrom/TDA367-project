package se.chalmers.get_rect.utilities.debug;

import se.chalmers.get_rect.GameConfig;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.utilities.Point;

class PlayerHandler  {
    private IPhysicsModel player;

    public PlayerHandler(IPhysicsModel player) {
        this.player = player;
    }

    public Point draw(IGraphicsAdapter graphics, Point position) {
        if (GameConfig.SHOW_POS) {
            position = position.addY(-20);
            graphics.drawText(player.getPosition().toString(), position);
        }

        return position;
    }
}
