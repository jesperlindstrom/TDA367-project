package se.chalmers.get_rect.game.entities.item.view;

import se.chalmers.get_rect.game.entities.AbstractView;
import se.chalmers.get_rect.game.entities.item.model.IRanged;
import se.chalmers.get_rect.game.entities.item.model.IWeapon;
import se.chalmers.get_rect.utilities.Point;

public abstract class AbstractRangedWeaponView extends AbstractView {

    private IRanged model;

    protected AbstractRangedWeaponView(IWeapon model) {
        super(model);
        if (model instanceof IRanged) {
            this.model = (IRanged)model;
        } else {
            throw new RuntimeException("Model was not IRanged");
        }
    }

    protected IWeapon getModel() {
        return model;
    }

    protected int getRotation() {
        Point direction = model.getAimDirection();
        int tmp = (int)Math.toDegrees(Math.atan2(direction.getY(), Math.abs(direction.getX())));
        return direction.getX() < 0 ? -tmp : tmp;
    }

    protected float getXScale() {
        if (model.getAimDirection().getX() != 0) {
            return model.getAimDirection().getX();
        }
        return 1;
    }

    protected float getYScale() {
        if (model.getAimDirection().getY() != 0) {
            return model.getFacing();
        }
        return 1;

    }
}
