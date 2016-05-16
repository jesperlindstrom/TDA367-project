package se.chalmers.get_rect.game.entities.item.model;

import se.chalmers.get_rect.game.entities.AbstractModel;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.utilities.Point;

public abstract class AbstractWeapon extends AbstractModel implements IWeapon {

    private final String type;
    private int facing;
    private int usedFrames;
    private IPhysicsModel user;

    protected AbstractWeapon(IPhysicsModel user, String type) {
        super(user.getPosition());
        this.type = type;
        this.user = user;

    }

    @Override
    public void remove() {
        setShouldBeRemoved();
    }

    @Override
    public Point getHandPos() {
        return user.getPosition().add(getFacing() < 0 ? 30 : 40, 70);
    }



    public int getFacing() {
        if (user.getVelocity().getX() != 0) {
            facing = user.getVelocity().normalize().getX();
        }
        return facing;
    }

    @Override
    public String getType() {
        return type;
    }

    protected IPhysicsModel getUser() {
        return user;
    }

    protected void setUseFrames(int frames) {
        this.usedFrames = frames;
    }

    public int getUsedFrames() {
        return usedFrames;
    }

    @Override
    public void update(double delta) {
        super.update(delta);
        if (usedFrames != 0) usedFrames--;
    }
}