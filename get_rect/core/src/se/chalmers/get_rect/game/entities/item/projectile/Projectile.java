package se.chalmers.get_rect.game.entities.item.projectile;

import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.utilities.CollisionData;

public class Projectile extends AbstractPhysicsModel {
    private int width = 10;
    private int height = 10;
    private int dmg;
    private IModel owner;

    public Projectile(Point position, Point velocity, int damage, IRectangleFactoryAdapter rectangleFactory, IModel owner) {
        super(position, velocity, false, rectangleFactory);
        setBoundingBox(width, height);
        this.owner = owner;
        this.dmg = damage;
    }

    @Override
    public void onCollision(IPhysicsObject otherObject, CollisionData collisionSide, boolean isSolid) {
        if (isSolid && collisionSide.bottom()) {
            int friction = getVelocity().getX() > 0 ? -1 : 1;
            setVelocity(getVelocity().addX(friction));
        }

        if (otherObject instanceof ICombatModel && !otherObject.equals(owner)) {
            setShouldBeRemoved();
            otherObject.setPosition(otherObject.getPosition().addY(30));
            otherObject.setVelocity(getVelocity().multiply(0.1));
            ((ICombatModel) otherObject).takeDamage(dmg);
        }
    }

    @Override
    public void update(double delta) {
        if (getVelocity().getX() == 0 && getVelocity().getY() == 0) {
            setShouldBeRemoved();
        }
    }
}