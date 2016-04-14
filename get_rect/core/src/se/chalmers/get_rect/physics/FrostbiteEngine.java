package se.chalmers.get_rect.physics;

import se.chalmers.get_rect.adapters.IRectangleAdapter;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.utilities.Side;

import java.util.ArrayList;
import java.util.List;

public class FrostbiteEngine implements IPhysicsEngine {
    private List<IPhysicsObject> entities;

    public FrostbiteEngine() {
        entities = new ArrayList<>();
    }

    public void add(IPhysicsObject entity) {
        entities.add(entity);
    }

    @Override
    public void update(double delta) {
        for (IPhysicsObject entity : entities) {
            SolidCollision collision = checkCollision(entity);
            handleMovement(entity, delta);
            handleGravity(entity);
        }
    }

    /**
     * Handle collision check between solid objects
     * @param entity
     */
    private SolidCollision checkCollision(IPhysicsObject entity) {
        SolidCollision collision = new SolidCollision();

        for(IPhysicsObject otherEntity : entities) {
            if (!entity.equals(otherEntity)) {
                checkCollision(entity, otherEntity, collision);
            }
        }

        return collision;
    }

    /**
     * Check if an entity collides with another and tell the first entity.
     * @param entity
     * @param otherEntity
     */
    private void checkCollision(IPhysicsObject entity, IPhysicsObject otherEntity, SolidCollision collision) {
        IRectangleAdapter rect1 = entity.getBoundingBox();
        IRectangleAdapter rect2 = otherEntity.getBoundingBox();
        Side side = rect1.intersects(rect2);

        // We didn't collide with anything - do nothing.
        if (side == null)
            return;

        boolean isSolid = otherEntity.isSolid();

        // Tell the entity it has collided with another
        entity.onCollision(otherEntity, side, isSolid);

        // Register the collision if solid
        if (isSolid) {
            collision.set(side);
        }
    }

    /**
     * Move the entity if nothing is in the way
     * @param entity
     * @param delta
     */
    private void handleMovement(IPhysicsObject entity, double delta) {
        // Get velocity
        Point velocity = entity.getVelocity().multiply(delta);

        // Calculate the new position
        Point newPosition = entity.getPosition().add(velocity);

        // Set the position
        entity.setPosition(newPosition);
    }

    /**
     * Apply gravity to the entity velocity
     * @param entity
     */
    private void handleGravity(IPhysicsObject entity) {

    }
}