package se.chalmers.get_rect.physics;

import se.chalmers.get_rect.utilities.Point;

public interface IPhysicsObject {
    IRectangleAdapter getBoundingBox();
    void onCollision(IPhysicsObject otherObject, CollisionData collisionSide, boolean isSolid);
    void setPosition(Point position);
    Point getPosition();
    void setVelocity(Point velocity);
    Point getVelocity();
    boolean isSolid();
    boolean shouldBeRemoved();
    boolean isAffectedByGravity();
}
