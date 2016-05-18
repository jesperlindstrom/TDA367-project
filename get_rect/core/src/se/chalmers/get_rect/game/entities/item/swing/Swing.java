package se.chalmers.get_rect.game.entities.item.swing;

import se.chalmers.get_rect.game.entities.AbstractPhysicsModel;
import se.chalmers.get_rect.game.entities.ICombatModel;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.utilities.CollisionData;

public class Swing extends AbstractPhysicsModel{
    private IPhysicsModel owner;
    private ISwinger weapon;
    private int originalSwingFrames;
    private int swingFrames;
    private int damage;
    private int width;
    private int height;
    private int deltaX;
    private int deltaY;

    public Swing(int damage, int width, int height, int swingFrames, IRectangleFactoryAdapter rectangleFactory, IPhysicsModel owner, ISwinger weapon) {
        super(owner.getPosition(), null, false, rectangleFactory);
        this.damage = damage;
        this.owner = owner;
        this.weapon = weapon;
        this.originalSwingFrames = swingFrames;
        this.swingFrames = swingFrames;
        this.width = width/2;
        this.height = height;
        deltaX = this.width*2/swingFrames;
        deltaY = this.height*2/swingFrames;
        setBoundingBox(20, height);
    }

    @Override
    public void update(double delta) {
        super.update(delta);
        setPosition(weapon.getHandPos().addX(weapon.getFacing() < 0 ? -width : 0));
        setNewHitBox();
        swingFrames--;
        if (swingFrames == 0) {
            setShouldBeRemoved();
        }

    }

    @Override
    public void onCollision(IPhysicsObject otherObject, CollisionData collisionSide, boolean isSolid) {
        super.onCollision(otherObject, collisionSide, isSolid);
        if (otherObject instanceof ICombatModel && !otherObject.equals(owner)) {
            ((ICombatModel)otherObject).takeDamage(damage);
        }
    }

    public Point getVelocity() {
        return null;
    }

    private void setNewHitBox() {
        if (swingFrames > originalSwingFrames/2) {
            width = width + deltaX;
            height = height - deltaY;
        } else {
            width = width - deltaX;
            height = height + deltaY;
        }
        setBoundingBox(width, height);
    }

    @Override
    protected void setBoundingBox(int width, int height) {
        super.setBoundingBox(width, height);
    }
}
