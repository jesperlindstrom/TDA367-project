package se.chalmers.get_rect.adapters;

import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.utilities.Side;

public interface IRectangleAdapter {
    float getWidth();
    float getHeight();
    float getX();
    float getY();
    Point getPosition();
    void setPosition(Point newPoint);
    Side intersects(IRectangleAdapter rectangle);
}
