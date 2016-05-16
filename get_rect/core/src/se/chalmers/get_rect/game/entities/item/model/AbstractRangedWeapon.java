package se.chalmers.get_rect.game.entities.item.model;

import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.utilities.Point;

public abstract class AbstractRangedWeapon extends AbstractWeapon implements IRanged {

    private Point aimDirection;
    private final int damage;
    private final int speed;
    protected AbstractRangedWeapon(IPhysicsModel user, String type, int damage, int speed) {
        super(user, type);
        this.damage = damage;
        this.speed = speed;
        setAimDirection(user.getVelocity().normalize());
    }

    protected void setAimDirection(Point direction) {
        this.aimDirection = direction;
    }

    @Override
    public Point getAimDirection() {
        return aimDirection;
    }


    protected Point getFireVelocity(int speed) {
        return aimDirection.multiply(speed).add(getUser().getVelocity());
    }

    protected int getSpeed() {
        return speed;
    }

    protected int getDamage() {
        return damage;
    }
}
