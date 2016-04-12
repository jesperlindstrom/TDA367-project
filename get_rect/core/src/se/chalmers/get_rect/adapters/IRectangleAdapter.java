package se.chalmers.get_rect.adapters;

import se.chalmers.get_rect.utilities.Point;

public interface IRectangleAdapter {
    boolean intersects(IRectangleAdapter otherRectangle);
    float getWidth();
    float getHeight();
    float getX();
    float getY();
    Point getPosition();
    void setPosition(Point newPoint);
}
