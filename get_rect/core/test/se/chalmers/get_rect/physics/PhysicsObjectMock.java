package se.chalmers.get_rect.physics;

import se.chalmers.get_rect.adapters.IRectangleAdapter;
import se.chalmers.get_rect.adapters.RectangleAdapterStub;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.utilities.SideData;

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
    public void onCollision(IPhysicsObject otherObject, SideData collisionSide, boolean isSolid) {

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

    public void setBoundingBox(IRectangleAdapter rect) {
        boundingBox = rect;
    }
}
