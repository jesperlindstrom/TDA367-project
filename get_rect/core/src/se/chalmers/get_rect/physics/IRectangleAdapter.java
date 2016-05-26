package se.chalmers.get_rect.physics;

import se.chalmers.get_rect.utilities.Point;

public interface IRectangleAdapter {
    int getWidth();
    int getHeight();
    int getX();
    int getY();
    Point getPosition();
    void setPosition(Point newPoint);
    CollisionData intersects(IRectangleAdapter rectangle);
    IRectangleAdapter getIntersection(IRectangleAdapter rect);
    String toString();
}
