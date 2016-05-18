package se.chalmers.get_rect.physics.frostbite;

import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.physics.IRectangleAdapter;
import se.chalmers.get_rect.utilities.CollisionData;
import se.chalmers.get_rect.utilities.Point;

import java.util.List;

public class MovementHandler {
    /**
     * Move the entity if nothing is in the way
     * @param entity
     * @param delta
     */
    public void move(IPhysicsObject entity, CollisionData collision, double delta) {
        // Get velocity
        Point velocity = entity.getVelocity();
        Point position = entity.getPosition();

        if (velocity == null) return;

        boolean isOverlapping = (collision.getOverlapList() != null);
        List<IRectangleAdapter> overlapList = collision.getOverlapList();
        IRectangleAdapter overlap = null;

        if (isOverlapping) {
            if (overlapList.size() == 1) {
                overlap = overlapList.get(0);
            } else {
                // TODO: Matte
            }
        }

        int x = velocity.getX();
        int y = velocity.getY();

        if ((x > 0 && collision.right())) {
            if (isOverlapping)
                position = position.addX((overlap.getWidth()-1));
            velocity = velocity.setX(0);
        }

        if (x < 0 && collision.left()) {
            if (isOverlapping)
                position = position.addX(overlap.getWidth()-1);
            velocity = velocity.setX(0);
        }

        if (y > 0 && collision.top()) {
            if (isOverlapping)
                position = position.addY(-(overlap.getHeight()-1));
            velocity = velocity.setY(0);
        }

        if (y < 0 && collision.bottom()) {
            if (isOverlapping) {
                position = position.addY(overlap.getHeight()-1);
            }
            velocity = velocity.setY(0);
        }

        entity.setVelocity(velocity);


        // Frame-rate dependent distance
        velocity = velocity.multiply(delta);

        // Calculate the new position
        position = position.add(velocity);

        // Set the position
        entity.setPosition(position);
    }
}
