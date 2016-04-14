package se.chalmers.get_rect.physics;

import se.chalmers.get_rect.adapters.IRectangleAdapter;
import se.chalmers.get_rect.utilities.Point;

import java.util.ArrayList;
import java.util.List;

public class FrostbiteEngine implements IPhysicsEngine {
    private List<ISolidObject> entities;

    public FrostbiteEngine() {
        entities = new ArrayList<>();
    }

    public void add(ISolidObject entity) {
        entities.add(entity);
    }

    @Override
    public void update(double delta) {
        for (ISolidObject entity : entities) {
            handleCollision(entity);
            handleMovement(entity, delta);
            handleGravity(entity);
        }
    }

    /**
     * Handle collision check between solid objects
     * @param entity 
     */
    private void handleCollision(ISolidObject entity) {
        for(ISolidObject otherEntity : entities) {
            if (!entity.equals(otherEntity)) {
                checkCollision(entity, otherEntity);
            }
        }
    }

    /**
     * Check if an entity collides with another and tell the first entity.
     * @param entity
     * @param otherEntity
     */
    private void checkCollision(ISolidObject entity, ISolidObject otherEntity) {
        IRectangleAdapter rect1 = entity.getBoundingBox();
        IRectangleAdapter rect2 = otherEntity.getBoundingBox();
        IRectangleAdapter.IntersectionSide side = rect1.intersects(rect2);

        if (side != null) {
            // Tell the entity it has collided with something
            // and let the entity itself decide what to do.
            entity.onCollision(otherEntity, side);
        }
    }

    /**
     * Move the entity if nothing is in the way
     * @param entity
     * @param delta
     */
    private void handleMovement(ISolidObject entity, double delta) {
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
    private void handleGravity(ISolidObject entity) {

    }
}