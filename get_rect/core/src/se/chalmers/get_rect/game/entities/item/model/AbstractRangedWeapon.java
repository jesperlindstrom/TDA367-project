package se.chalmers.get_rect.game.entities.item.model;

import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.utilities.Point;

public abstract class AbstractRangedWeapon extends AbstractWeapon implements IRanged {

    private Point aimDirection;

    protected AbstractRangedWeapon(IPhysicsModel user) {
        super(user);
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
}
