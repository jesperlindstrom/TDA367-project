package se.chalmers.get_rect.game.entities.overlays.view;

import se.chalmers.get_rect.game.GameConfig;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.physics.IRectangleAdapter;
import se.chalmers.get_rect.game.entities.AbstractView;
import se.chalmers.get_rect.physics.IPhysicsEngine;
import se.chalmers.get_rect.physics.IPhysicsObject;

public class PhysicsView extends AbstractView {
    private static final String BOUNDING_BOX_IMAGE = "img/entities/physics_debug.png";
    private IPhysicsEngine engine;
    private static final int DRAW_PRIORITY = 5;

    public PhysicsView(IPhysicsEngine engine) {
        this.engine = engine;
    }

    @Override
    public int getDrawPriority() {
        return DRAW_PRIORITY;
    }

    public void draw(IGraphicsAdapter graphics) {
        if (!GameConfig.DRAW_BOUNDING_BOXES || engine == null) return;

        for (IPhysicsObject obj : engine.getEntities()) {
            IRectangleAdapter rectangle = obj.getBoundingBox();

            graphics.draw(BOUNDING_BOX_IMAGE, rectangle.getPosition(), rectangle.getWidth(), rectangle.getHeight());
        }
    }
}
