package se.chalmers.get_rect.game.entities.item.model;

import se.chalmers.get_rect.game.entities.AbstractModel;
import se.chalmers.get_rect.game.entities.IEntityHolder;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.utilities.Point;

public class AbstractWeapon extends AbstractModel implements IWeapon {


    private int facing;
    private int usedFrames;
    private IPhysicsModel user;

    protected AbstractWeapon(IPhysicsModel user) {
        super(user.getPosition());
        this.user = user;

    }

    @Override
    public void use(Point direction, IEntityHolder entityHolder) {

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
    public void setActive() {
        setShouldNotBeRemoved();
    }

    protected IPhysicsModel getUser() {
        return user;
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
}