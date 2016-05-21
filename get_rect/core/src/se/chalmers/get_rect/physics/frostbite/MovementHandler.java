package se.chalmers.get_rect.physics.frostbite;

import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.physics.IRectangleAdapter;
import se.chalmers.get_rect.physics.CollisionData;
import se.chalmers.get_rect.utilities.Point;

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

        IRectangleAdapter overlap = collision.getOverlap();

        int x = velocity.getX();
        int y = velocity.getY();

        boolean overlapsOnX = overlap != null && overlap.getWidth() > 0;
        boolean overlapsOnY = overlap != null && overlap.getHeight() > 0;

        if ((x > 0 && collision.right())) {
            /*if (overlapsOnX)
                position = position.addX(-overlap.getWidth() + 1);*/

            velocity = velocity.setX(0);
        }

        if (x < 0 && collision.left()) {
            if (overlapsOnX)
                position = position.addX(overlap.getWidth() - 1);

            velocity = velocity.setX(0);
        }

        if (y > 0 && collision.top()) {
            if (overlapsOnY)
                position = position.addY(-overlap.getHeight() + 1);

            velocity = velocity.setY(0);
        }


        if (y < 0 && collision.bottom()) {
            if (overlapsOnY)
                position = position.addY(overlap.getHeight()-1);

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
