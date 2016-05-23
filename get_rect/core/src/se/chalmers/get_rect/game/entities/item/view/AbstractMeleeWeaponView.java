package se.chalmers.get_rect.game.entities.item.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractView;
import se.chalmers.get_rect.game.entities.item.model.IMelee;
import se.chalmers.get_rect.game.entities.item.model.IWeapon;
import se.chalmers.get_rect.utilities.Point;

public abstract class AbstractMeleeWeaponView extends AbstractView implements IWeaponView {

    private IMelee model;
    private String iconPath;

    protected AbstractMeleeWeaponView(IWeapon model, String iconPath) {
        super(model);
        if (model instanceof IMelee) {
            this.model = (IMelee)model;
        } else {
            throw new RuntimeException("Model was not IRanged");
        }
        this.iconPath = iconPath;
    }

    protected IMelee getModel() {
        return model;
    }

    protected boolean isAttacking() {
        return getModel().getCooldownFrames() == getModel().getCooldown()-1;
    }

    protected float getXScale() {
        return model.getFacing() < 0 ? -1 : 1;
    }

    @Override
    public void drawIcon(IGraphicsAdapter graphics, Point point) {
        graphics.draw(iconPath, point);
    }
}
