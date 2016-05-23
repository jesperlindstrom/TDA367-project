package se.chalmers.get_rect.game.entities.item.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractView;
import se.chalmers.get_rect.game.entities.item.model.IRanged;
import se.chalmers.get_rect.game.entities.item.model.IWeapon;
import se.chalmers.get_rect.utilities.Point;

public abstract class AbstractRangedWeaponView extends AbstractView implements IWeaponView {

    private IRanged model;
    private String iconPath;

    protected AbstractRangedWeaponView(IWeapon model, String iconPath) {
        super(model);
        if (model instanceof IRanged) {
            this.model = (IRanged)model;
        } else {
            throw new RuntimeException("Model was not IRanged");
        }
        this.iconPath = iconPath;
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

    @Override
    public void drawIcon(IGraphicsAdapter graphics, Point point) {
        graphics.draw(iconPath, point);
    }

    public boolean isAttacking() {
        return getModel().getCooldownFrames() == getModel().getCooldown()-1;
    }

}
