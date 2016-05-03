package se.chalmers.get_rect.game.entities.overlays.view;

import se.chalmers.get_rect.GameConfig;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractView;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.utilities.Point;

public class PlayerView extends AbstractView {
    private IPhysicsModel player;
    private static final int DRAW_PRIORITY = 35;
    public PlayerView(IPhysicsModel player) {
        super();
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
    public int getDrawPriority() {
        return DRAW_PRIORITY;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        draw(graphics, new Point(0, 0));
    }
}
