package se.chalmers.get_rect.physics;

import se.chalmers.get_rect.adapters.IRectangleAdapter;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.utilities.SideData;

public interface IPhysicsObject {
    IRectangleAdapter getBoundingBox();
    void onCollision(IPhysicsObject otherObject, SideData collisionSide, boolean isSolid);
    void setPosition(Point position);
    Point getPosition();
    void setVelocity(Point velocity);
    Point getVelocity();
    boolean isSolid();
    boolean shouldBeRemoved();
    int getHealth();
    void takeDamage(int dmg);
}
