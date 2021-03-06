package se.chalmers.get_rect.tests.physics;

import se.chalmers.get_rect.physics.CollisionData;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.physics.IRectangleAdapter;
import se.chalmers.get_rect.utilities.Point;

public class PhysicsObjectMock implements IPhysicsObject {
    private IRectangleAdapter boundingBox;
    private Point velocity;
    private Point position;

    public PhysicsObjectMock() {
        velocity = new Point(0, 0);
        position = new Point(0, 0);
    }

    @Override
    public IRectangleAdapter getBoundingBox() {
        return boundingBox;
    }

    @Override
    public void onCollision(IPhysicsObject otherObject, CollisionData collisionSide, boolean isSolid) {

    }

    @Override
    public void setPosition(Point position) {
        this.position = position;
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public void setVelocity(Point velocity) {
        this.velocity = velocity;
    }

    @Override
    public Point getVelocity() {
        return velocity;
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public boolean shouldBeRemoved() {
        return false;
    }

    @Override
    public boolean isAffectedByGravity() {
        return true;
    }

    public void setBoundingBox(IRectangleAdapter rect) {
        boundingBox = rect;
    }
}
