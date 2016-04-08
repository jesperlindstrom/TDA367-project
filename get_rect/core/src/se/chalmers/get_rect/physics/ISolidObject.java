package se.chalmers.get_rect.physics;

import se.chalmers.get_rect.adapters.IRectangleAdapter;

public interface ISolidObject {
    IRectangleAdapter getBoundingBox();
    void onCollision(ISolidObject otherObject);
}
