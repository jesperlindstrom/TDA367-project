package se.chalmers.get_rect.game.entities.overlays.debug;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.IView;
import se.chalmers.get_rect.game.entities.overlays.debug.frameRate.FrameRateView;
import se.chalmers.get_rect.game.entities.overlays.debug.physics.PhysicsView;
import se.chalmers.get_rect.game.entities.overlays.debug.player.PlayerView;
import se.chalmers.get_rect.utilities.Point;

public class DebugView implements IView {
    private Debug model;
    private FrameRateView frameRateView;
    private PhysicsView physicsView;
    private PlayerView playerView;

    public DebugView(Debug model) {
        this.model = model;
        frameRateView = new FrameRateView(model.getFrameRate());
        physicsView = new PhysicsView(model.getPhysics());
        playerView = new PlayerView(model.getPlayer());
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        // Draw top left stats
        Point point = model.getCameraPosition().addY(1095);
        point = frameRateView.draw(graphics, point);
        point = playerView.draw(graphics, point);
        physicsView.draw(graphics);
    }
}
