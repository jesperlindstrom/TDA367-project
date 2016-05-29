package se.chalmers.get_rect.game.entities.worldObjects.model;

import se.chalmers.get_rect.game.entities.AbstractPhysicsModel;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.physics.CollisionData;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.utilities.Point;


public class Pitfall extends AbstractPhysicsModel {
    public Pitfall(Point position, int width, int height, Point velocity, boolean solid, boolean affectedByGravity, IRectangleFactoryAdapter rectangleFactory) {
        super(position, velocity, solid, affectedByGravity, rectangleFactory);
        setBoundingBox(width, height);
    }

    @Override
    public void onCollision(IPhysicsObject otherObject, CollisionData collisionSide, boolean isSolid) {
        if (otherObject instanceof Player) {
            ((Player) otherObject).takeDamage(((Player) otherObject).getCurrentHealth());
        }
    }
}
