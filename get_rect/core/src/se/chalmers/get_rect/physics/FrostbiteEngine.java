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
        for (ISolidObject entity1 : entities) {
            for(ISolidObject entity2 : entities) {
                handleCollision(entity1, entity2);
            }

            handleMovement(entity1, delta);
            handleGravity(entity1);
        }
    }

    /**
     *
     * @param entity
     * @param otherEntity
     */
    private void handleCollision(ISolidObject entity, ISolidObject otherEntity) {
        if (entity.equals(otherEntity)) return;

        IRectangleAdapter rect1 = entity.getBoundingBox();
        IRectangleAdapter rect2 = otherEntity.getBoundingBox();
        IRectangleAdapter.IntersectionSide side = rect1.intersects(rect2);

        if (side != null) {
            // Tell the entity it has collided with something
            // and let the entity itself decide what to do.
            entity.onCollision(otherEntity, side);
        }
    }

    private void handleMovement(ISolidObject entity, double delta) {
        // Get velocity
        Point velocity = entity.getVelocity().multiply(delta);

        // Calculate the new position
        Point newPosition = entity.getPosition().add(velocity);

        // Set the position
        entity.setPosition(newPosition);
    }

    private void handleGravity(ISolidObject entity1) {

    }

    /**
     * Method to calculate new position when entity moves
     * @param delta
     * @param position
     * @param velocity
     * @return
     */
    @Override
    public Point move(double delta, Point position, Point velocity){
        return position.add(deltaToVelocity(delta, velocity));
    }
}