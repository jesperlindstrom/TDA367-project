package se.chalmers.get_rect.game.entities.item.swing;

import se.chalmers.get_rect.game.entities.ICombatModel;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.physics.CollisionData;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.utilities.Point;

public class Uppercut extends Swing {
    private ISwinger weapon;

    public Uppercut(int damage, int reach, int swingFrames, IRectangleFactoryAdapter rectangleFactory, IPhysicsModel owner, ISwinger weapon) {
        super(damage, reach, swingFrames, rectangleFactory, owner, weapon);
        this.weapon = weapon;
    }

    @Override
    public void onCollision(IPhysicsObject otherObject, CollisionData collisionSide, boolean isSolid) {
        super.onCollision(otherObject, collisionSide, isSolid);

        if (otherObject instanceof ICombatModel && !isOwner(otherObject)){
            otherObject.setVelocity(new Point(50 * weapon.getTilt(), 50));
            ((ICombatModel)otherObject).setKnockback();
        }
    }
}
