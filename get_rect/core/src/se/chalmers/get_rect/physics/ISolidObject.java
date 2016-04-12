package se.chalmers.get_rect.physics;

import se.chalmers.get_rect.adapters.IRectangleAdapter;
import se.chalmers.get_rect.utilities.Point;

public interface ISolidObject {
    IRectangleAdapter getBoundingBox();
    void onCollision(ISolidObject otherObject);
    Point getPosition();
    Point getVelocity();
    void setPosition(Point position);
}
