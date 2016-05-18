package se.chalmers.get_rect.physics.frostbite;

import se.chalmers.get_rect.physics.IRectangleAdapter;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.utilities.CollisionData;

import java.util.List;

public class CollisionHandler {
    /**
     * Handle collision check between solid objects
     * @param entity
     */
    public CollisionData check(IPhysicsObject entity, List<IPhysicsObject> entities) {
        CollisionData collision = new CollisionData();

        for (IPhysicsObject otherEntity : entities) {
            if (!entity.equals(otherEntity)) {
                checkEntity(entity, otherEntity, collision);
            }
        }
        return collision;
    }

    /**
     * Check if an entity collides with another and tell the first entity.
     * @param entity
     * @param otherEntity
     */
    private void checkEntity(IPhysicsObject entity, IPhysicsObject otherEntity, CollisionData solidCollision) {
        IRectangleAdapter rect1 = entity.getBoundingBox();
        IRectangleAdapter rect2 = otherEntity.getBoundingBox();
        boolean isSolid = otherEntity.isSolid();

        CollisionData entityCollision = rect1.intersects(rect2);

        // We didn't collide with anything - do nothing.
        if (entityCollision == null) {
            return;
        }

        if (isSolid) {
            entityCollision.addSolidOverlap(rect1.getIntersection(rect2));
            solidCollision.set(entityCollision);
        }

        // Tell the entity it has collided with another
        entity.onCollision(otherEntity, entityCollision, isSolid);

    }
}
