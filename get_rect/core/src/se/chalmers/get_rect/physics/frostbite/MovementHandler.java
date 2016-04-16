package se.chalmers.get_rect.physics.frostbite;

import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.utilities.SideData;
import se.chalmers.get_rect.utilities.Point;

public class MovementHandler {
    /**
     * Move the entity if nothing is in the way
     * @param entity
     * @param delta
     */
    public void move(IPhysicsObject entity, SideData collision, double delta) {
        // Get velocity
        Point velocity = entity.getVelocity();

        if (velocity == null) return;

        // Frame-rate dependent distance
        velocity = velocity.multiply(delta);

        int x = velocity.getX();
        int y = velocity.getY();

        if ((x > 0 && collision.right()) || (x < 0 && collision.left())) {
            velocity = velocity.setX(0);
        }

        if ((y > 0 && collision.top()) || (y < 0 && collision.bottom())) {
            velocity = velocity.setY(0);
        }

        // Calculate the new position
        Point newPosition = entity.getPosition().add(velocity);

        // Set the position
        entity.setPosition(newPosition);
    }
}
