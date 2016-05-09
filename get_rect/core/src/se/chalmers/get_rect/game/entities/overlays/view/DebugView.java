package se.chalmers.get_rect.game.entities.overlays.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractView;
import se.chalmers.get_rect.game.entities.overlays.model.Debug;
import se.chalmers.get_rect.utilities.Point;

public class DebugView extends AbstractView {
    private Debug model;
    private DebugFrameRateView debugFrameRateView;
    private DebugPhysicsView debugPhysicsView;
    private DebugPlayerView debugPlayerView;
    private DebugMouseView debugMouseView;
    private static final int DRAW_PRIORITY = 15;

    public DebugView(Debug model) {
        super(model);
        this.model = model;
        debugFrameRateView = new DebugFrameRateView(model.getFrameRate());
        debugPhysicsView = new DebugPhysicsView(model.getPhysics());
        debugPlayerView = new DebugPlayerView(model.getPlayer());
        debugMouseView = new DebugMouseView(model.getInput(),model.getCamera());
    }

    @Override
    public int getDrawPriority() {
        return DRAW_PRIORITY;
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        // Draw top left stats
        Point point = model.getCameraPosition().addY(1095);
        point = debugFrameRateView.draw(graphics, point);
        point = debugPlayerView.draw(graphics, point);
        point = debugMouseView.draw(graphics, point);
        //The point is changed depending on whether the information is visible or not.
        debugPhysicsView.draw(graphics);
    }
}
