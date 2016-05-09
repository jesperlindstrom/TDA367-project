package se.chalmers.get_rect.game.entities.overlays.view;

import se.chalmers.get_rect.game.GameConfig;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.physics.IRectangleAdapter;
import se.chalmers.get_rect.game.entities.AbstractView;
import se.chalmers.get_rect.physics.IPhysicsEngine;
import se.chalmers.get_rect.physics.IPhysicsObject;

public class DebugPhysicsView {
    private static final String BOUNDING_BOX_IMAGE = "img/entities/physics_debug.png";
    private IPhysicsEngine engine;

    public DebugPhysicsView(IPhysicsEngine engine) {
        this.engine = engine;
    }

    public void draw(IGraphicsAdapter graphics) {
        if (!GameConfig.DRAW_BOUNDING_BOXES || engine == null) return;

        for (IPhysicsObject obj : engine.getEntities()) {
            IRectangleAdapter rectangle = obj.getBoundingBox();

            graphics.draw(BOUNDING_BOX_IMAGE, rectangle.getPosition(), rectangle.getWidth(), rectangle.getHeight());
        }
    }
}
