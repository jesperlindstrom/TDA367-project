package se.chalmers.get_rect.physics.frostbite;

import se.chalmers.get_rect.adapters.IRectangleAdapter;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.physics.SolidCollision;
import se.chalmers.get_rect.utilities.Side;

import java.util.List;

public class CollisionHandler {
    /**
     * Handle collision check between solid objects
     * @param entity
     */
    public SolidCollision check(IPhysicsObject entity, List<IPhysicsObject> entities) {
        SolidCollision collision = new SolidCollision();

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
    private void checkEntity(IPhysicsObject entity, IPhysicsObject otherEntity, SolidCollision collision) {
        IRectangleAdapter rect1 = entity.getBoundingBox();
        IRectangleAdapter rect2 = otherEntity.getBoundingBox();
        SolidCollision side = rect1.intersects(rect2);

        // We didn't collide with anything - do nothing.
        if (side == null)
            return;

        boolean isSolid = otherEntity.isSolid();

        // Tell the entity it has collided with another
        entity.onCollision(otherEntity, side, isSolid);

        // Register the collision if solid
        if (isSolid) {
            collision.set(side, isSolid);
        }
    }
}
