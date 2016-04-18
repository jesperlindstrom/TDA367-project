package se.chalmers.get_rect.utilities.debug;

import se.chalmers.get_rect.GameConfig;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.IRectangleAdapter;
import se.chalmers.get_rect.physics.IPhysicsEngine;
import se.chalmers.get_rect.physics.IPhysicsObject;

class PhysicsHandler {
    private static final String BOUNDING_BOX_IMAGE = "img/entities/physics_debug.png";
    private IPhysicsEngine engine;

    public void setPhysicsEngine(IPhysicsEngine engine) {
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
