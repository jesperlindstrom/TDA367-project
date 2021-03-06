package se.chalmers.get_rect.game.entities.overlays.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractView;
import se.chalmers.get_rect.game.entities.IEntity;
import se.chalmers.get_rect.game.entities.IInteractableModel;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.physics.IRectangleAdapter;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.utilities.StringWrapper;

public class DialogView extends AbstractView {
    private Player player;
    private static final int DRAW_PRIORITY = 9;


    public DialogView(Player player) {
        super(player);
        this.player = player;
    }

    @Override
    public int getDrawPriority() {
        return DRAW_PRIORITY;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        if (player.getCurrentNpc() == null)return;
        if (!player.getCurrentNpc().isDialogVisible())return;
        Point position = new Point(getUpperLeftCorner().addX(100));
        graphics.draw("img/interact/bubble.png", position);
        graphics.drawText(player.getCurrentNpc().getDialog(), position.add(20,85), IGraphicsAdapter.Colors.BLACK);
    }

    private Point getUpperLeftCorner() {
        IRectangleAdapter boundingBox = player.getCurrentNpc().getBoundingBox();
        Point point = new Point(boundingBox.getPosition());
        point = point.add((int)boundingBox.getWidth()/2 - 40, (int)boundingBox.getHeight()); // -40 due to e.png's size
        return point;
    }
}
