package se.chalmers.get_rect.physics.frostbite;

import se.chalmers.get_rect.adapters.IRectangleAdapter;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.utilities.SideData;

import java.util.List;

public class CollisionHandler {
    private SideData collision;
    /**
     * Handle collision check between solid objects
     * @param entity
     */
    public SideData check(IPhysicsObject entity, List<IPhysicsObject> entities) {
        collision = new SideData();

        for (IPhysicsObject otherEntity : entities) {
            if (!entity.equals(otherEntity)) {
                checkEntity(entity, otherEntity);
            }
        }

        return collision;
    }

    /**
     * Check if an entity collides with another and tell the first entity.
     * @param entity
     * @param otherEntity
     */
    private void checkEntity(IPhysicsObject entity, IPhysicsObject otherEntity) {
        IRectangleAdapter rect1 = entity.getBoundingBox();
        IRectangleAdapter rect2 = otherEntity.getBoundingBox();
        boolean isSolid = otherEntity.isSolid();

        collision = rect1.intersects(rect2,isSolid);

        // We didn't collide with anything - do nothing.
        if (collision == null) {
            return;
        }


        // Tell the entity it has collided with another
        entity.onCollision(otherEntity, collision, isSolid);

    }
}
