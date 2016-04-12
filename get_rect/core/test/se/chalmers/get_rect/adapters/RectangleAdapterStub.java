package se.chalmers.get_rect.adapters;

import se.chalmers.get_rect.utilities.Point;

public class RectangleAdapterStub implements IRectangleAdapter {
    public RectangleAdapterStub(float x, float y, float width, float height) {

    }

    @Override
    public boolean intersects(IRectangleAdapter otherRectangle) {
        return false;
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
}
