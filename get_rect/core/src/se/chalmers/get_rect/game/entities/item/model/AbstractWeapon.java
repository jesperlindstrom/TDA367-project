package se.chalmers.get_rect.game.entities.item.model;

import se.chalmers.get_rect.game.entities.AbstractModel;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.entities.item.IWeapon;
import se.chalmers.get_rect.utilities.Point;

public abstract class AbstractWeapon extends AbstractModel implements IWeapon {

    private Point aimDirection;
    private int usedFrames;
    private IPhysicsModel model;
    private int facing;

    protected AbstractWeapon(Point position, IPhysicsModel model) {
        super(position);
        this.model = model;
        facing = model.getVelocity().normalize().getX();
    }

    protected void setAimDirection(Point direction) {
        this.aimDirection = direction;
    }

    @Override
    public Point getAimDirection() {
        return aimDirection;
    }

    protected void setUseFrames(int frames) {
        this.usedFrames = frames;
    }

    public int getUsedFrames() {
        if (usedFrames == 0) {
            return 0;
        }
        usedFrames = usedFrames -1;
        return usedFrames+1;
    }

    @Override
    public Point getHandPos() {
        return model.getPosition().add(getFacing() < 0 ? 30 : 40, 70);
    }

    public int getFacing() {
        if (model.getVelocity().getX() != 0) {
            facing = model.getVelocity().normalize().getX();
        }
        return facing;
    }

    protected IPhysicsModel getModel() {
        return model;
    }

    protected Point getFireVelocity(int speed) {
        return aimDirection.multiply(speed).add(model.getVelocity());
    }

    @Override
    public void remove() {
        setShouldBeRemoved();
    }

    @Override
    public void setActive() {
        setShouldNotBeRemoved();
    }
}
