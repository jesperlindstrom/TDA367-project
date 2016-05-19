package se.chalmers.get_rect.tests.physics;

import se.chalmers.get_rect.physics.IRectangleAdapter;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.physics.CollisionData;

public class RectangleAdapterStub implements IRectangleAdapter {
    public RectangleAdapterStub(float x, float y, float width, float height) {

    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public Point getPosition() {
        return null;
    }

    @Override
    public void setPosition(Point newPoint) {

    }

    @Override
    public CollisionData intersects(IRectangleAdapter rectangle) {
        return null;
    }

    @Override
    public IRectangleAdapter getIntersection(IRectangleAdapter rect) {
        return null;
    }
}
