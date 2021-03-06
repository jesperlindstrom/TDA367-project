package se.chalmers.get_rect.physics.frostbite;

import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.physics.CollisionData;
import se.chalmers.get_rect.utilities.Point;

public class GravityHandler {
    private static final int GRAVITY = -3;

    public void apply(IPhysicsObject entity, CollisionData collision) {
        Point velocity = entity.getVelocity();

        if (velocity == null) return;

        // Collides with ground
        if (collision.bottom()) {
            velocity = velocity.setY(0);
        } else {
            velocity = velocity.addY(GRAVITY);
        }

        entity.setVelocity(velocity);
    }
}
