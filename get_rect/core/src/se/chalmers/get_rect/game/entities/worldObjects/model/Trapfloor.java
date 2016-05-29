package se.chalmers.get_rect.game.entities.worldObjects.model;

import se.chalmers.get_rect.game.entities.AbstractPhysicsModel;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.physics.CollisionData;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.utilities.Point;

public class Trapfloor extends AbstractPhysicsModel {
    public Trapfloor(Point position, int width, int height, IRectangleFactoryAdapter rectangleFactory) {
        super(position.addY((-height)), new Point(), true, false, rectangleFactory);
        setBoundingBox(width, height);
    }

    @Override
    public void onCollision(IPhysicsObject otherObject, CollisionData collisionSide, boolean isSolid) {
        if (otherObject instanceof Player){
            setShouldBeRemoved();
        }
    }
}
