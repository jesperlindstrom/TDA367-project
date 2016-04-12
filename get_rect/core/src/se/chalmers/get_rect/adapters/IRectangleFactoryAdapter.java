package se.chalmers.get_rect.adapters;

import se.chalmers.get_rect.utilities.Point;

public interface IRectangleFactoryAdapter {
    IRectangleAdapter make(float x, float y, float width, float height);
    IRectangleAdapter make(Point point, float width, float height);
}