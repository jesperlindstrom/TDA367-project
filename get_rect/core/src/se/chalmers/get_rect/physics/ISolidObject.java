package se.chalmers.get_rect.physics;

import se.chalmers.get_rect.adapters.IRectangleAdapter;
import se.chalmers.get_rect.utilities.Point;

public interface ISolidObject {
    IRectangleAdapter getBoundingBox();
    void onCollision(ISolidObject otherObject);
    void setPosition(Point position);
    Point getPosition();
    void setVelocity(Point velocity);
    Point getVelocity();
}
