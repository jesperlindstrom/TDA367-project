package se.chalmers.get_rect.adapters;

import se.chalmers.get_rect.physics.IRectangleAdapter;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.utilities.CollisionData;

public class RectangleAdapterStub implements IRectangleAdapter {
    public RectangleAdapterStub(float x, float y, float width, float height) {

    }

    @Override
    public float getWidth() {
        return 0;
    }

    @Override
    public float getHeight() {
        return 0;
    }

    @Override
    public float getX() {
        return 0;
    }

    @Override
    public float getY() {
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
}
