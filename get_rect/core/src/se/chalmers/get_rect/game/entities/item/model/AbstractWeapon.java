package se.chalmers.get_rect.game.entities.item.model;

import se.chalmers.get_rect.game.entities.AbstractModel;
import se.chalmers.get_rect.game.entities.item.IWeapon;
import se.chalmers.get_rect.utilities.Point;

public abstract class AbstractWeapon extends AbstractModel implements IWeapon {

    private Point direction;
    private int usedFrames;

    protected AbstractWeapon(Point position) {
        super(position);
    }

    protected void setDirection(Point direction) {
        this.direction = direction;
    }

    @Override
    public Point getDirection() {
        return direction;
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
