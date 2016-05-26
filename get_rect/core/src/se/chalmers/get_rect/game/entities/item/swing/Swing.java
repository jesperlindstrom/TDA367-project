package se.chalmers.get_rect.game.entities.item.swing;

import se.chalmers.get_rect.game.entities.AbstractPhysicsModel;
import se.chalmers.get_rect.game.entities.ICombatModel;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.physics.CollisionData;

import java.util.ArrayList;
import java.util.List;

public class Swing extends AbstractPhysicsModel{
    private IPhysicsModel owner;
    private ISwinger weapon;
    private int swingFrames;
    private int damage;
    private int reach;
    private List<IPhysicsObject> hitEnemies;

    public Swing(int damage, int reach, int swingFrames, IRectangleFactoryAdapter rectangleFactory, IPhysicsModel owner, ISwinger weapon) {
        this(damage, reach, swingFrames, rectangleFactory, owner, weapon, false);
    }
    public Swing(int damage, int reach, int swingFrames, IRectangleFactoryAdapter rectangleFactory, IPhysicsModel owner, ISwinger weapon, boolean solid) {
        super(owner.getPosition(), new Point(), solid, false, rectangleFactory);
        this.damage = damage;
        this.owner = owner;
        this.weapon = weapon;
        this.swingFrames = swingFrames;
        this.reach = reach;
        hitEnemies = new ArrayList<>();
        setBoundingBox((int)Math.sin(Math.toRadians(weapon.getTilt()))*reach, (int)(Math.sin(Math.toRadians(weapon.getTilt()))*reach));
    }

        @Override
    public void update(double delta) {
        super.update(delta);
        setPosition(weapon.getHandPos());
        setNewHitBox();
        swingFrames--;
        if (swingFrames == 0) {
            setShouldBeRemoved();
        }
            System.out.println(getBoundingBox());
    }

    @Override
    public void onCollision(IPhysicsObject otherObject, CollisionData collisionSide, boolean isSolid) {
        if (otherObject instanceof ICombatModel && !otherObject.equals(owner) && !hitEnemies.contains(otherObject)) {
            ((ICombatModel)otherObject).takeDamage(damage);
            hitEnemies.add(otherObject);
        }
    }

    public Point getVelocity() {
        return new Point();
    }

    private void setNewHitBox() {
        setBoundingBox((int)(Math.sin(Math.toRadians(weapon.getTilt()))*reach), (int)(Math.cos(Math.toRadians(weapon.getTilt()))*reach));
    }

    protected boolean isOwner(IPhysicsObject other) {
        return owner.equals(other);
    }
}
