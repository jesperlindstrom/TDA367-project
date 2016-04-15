package se.chalmers.get_rect.adapters;

import se.chalmers.get_rect.utilities.SideData;
import se.chalmers.get_rect.utilities.Point;

public interface IRectangleAdapter {
    float getWidth();
    float getHeight();
    float getX();
    float getY();
    Point getPosition();
    void setPosition(Point newPoint);
    SideData intersects(IRectangleAdapter rectangle, boolean isSolid);
}
