package se.chalmers.get_rect.game.entities.overlays.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractView;
import se.chalmers.get_rect.game.entities.overlays.model.Debug;
import se.chalmers.get_rect.utilities.Point;

public class DebugView extends AbstractView {
    private Debug model;
    private FrameRateView frameRateView;
    private PhysicsView physicsView;
    private PlayerView playerView;
    private static final int DRAW_PRIORITY = 15;

    public DebugView(Debug model) {
        super(model);
        this.model = model;
        frameRateView = new FrameRateView(model.getFrameRate());
        physicsView = new PhysicsView(model.getPhysics());
        playerView = new PlayerView(model.getPlayer());
    }

    @Override
    public int getDrawPriority() {
        return DRAW_PRIORITY;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        // Draw top left stats
        Point point = model.getCameraPosition().addY(1095);
        point = frameRateView.draw(graphics, point);
        playerView.draw(graphics, point);
        //The point is changed depending on whether the information is visible or not.
        physicsView.draw(graphics);
    }
}
