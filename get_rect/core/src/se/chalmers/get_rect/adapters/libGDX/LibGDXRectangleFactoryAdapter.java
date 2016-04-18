package se.chalmers.get_rect.adapters.libGDX;

import se.chalmers.get_rect.adapters.IRectangleAdapter;
import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.utilities.Point;

public class LibGDXRectangleFactoryAdapter implements IRectangleFactoryAdapter {
    @Override
    public IRectangleAdapter make(float x, float y, float width, float height) {
        return new LibGDXRectangleAdapter(x, y, width, height);
    }

    @Override
    public IRectangleAdapter make(Point point, float width, float height) {
        return make(point.getX(), point.getY(), width, height);
    }
}
