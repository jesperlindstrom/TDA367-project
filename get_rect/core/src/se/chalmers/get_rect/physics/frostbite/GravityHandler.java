package se.chalmers.get_rect.physics.frostbite;

import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.physics.SolidCollision;
import se.chalmers.get_rect.utilities.Point;

public class GravityHandler {
    private static final int GRAVITY = -1;

    /**
     * Apply gravity to the entity velocity
     * @param entity
     */
    public void apply(IPhysicsObject entity, SolidCollision collision) {
        Point velocity = entity.getVelocity();

        // Collides with ground
        if (collision.bottom()) {
            velocity = velocity.setY(0);
        } else {
            velocity = velocity.addY(GRAVITY);
        }

        entity.setVelocity(velocity);
    }
}
