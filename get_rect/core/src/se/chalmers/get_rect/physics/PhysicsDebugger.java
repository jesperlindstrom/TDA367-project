package se.chalmers.get_rect.physics;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.IRectangleAdapter;

public class PhysicsDebugger {
    private static final String BOUNDING_BOX_IMAGE = "img/entities/physics_debug.png";
    private IPhysicsEngine engine;

    public PhysicsDebugger(IPhysicsEngine engine) {
        this.engine = engine;
    }

    public void draw(IGraphicsAdapter graphics) {
        for (IPhysicsObject obj : engine.getEntities()) {
            IRectangleAdapter rectangle = obj.getBoundingBox();

            graphics.draw(BOUNDING_BOX_IMAGE, rectangle.getPosition(), rectangle.getWidth(), rectangle.getHeight());
        }
    }
}
