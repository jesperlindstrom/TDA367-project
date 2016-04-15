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
        Point velocity = entity.getVelocity().multiply(delta);

        // Calculate the new position
        Point newPosition = entity.getPosition().add(velocity);

        // Set the position
        entity.setPosition(newPosition);
    }
}
