package se.chalmers.get_rect.tests.physics;

import se.chalmers.get_rect.physics.IRectangleAdapter;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.utilities.Point;

public class RectangleFactoryAdapterStub implements IRectangleFactoryAdapter {
    @Override
    public IRectangleAdapter make(float x, float y, float width, float height) {
        return new RectangleAdapterStub(x, y, width, height);
    }

    @Override
    public IRectangleAdapter make(Point point, float width, float height) {
        return make(point.getX(), point.getY(), width, height);
    }
}
