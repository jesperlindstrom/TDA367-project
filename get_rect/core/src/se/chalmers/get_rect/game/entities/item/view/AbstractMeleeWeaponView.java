package se.chalmers.get_rect.game.entities.item.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractView;
import se.chalmers.get_rect.game.entities.item.model.IMelee;
import se.chalmers.get_rect.game.entities.item.model.IWeapon;
import se.chalmers.get_rect.utilities.Point;

public abstract class AbstractMeleeWeaponView extends AbstractView implements IWeaponView {

    private IMelee model;
    private float tilt;
    private int swingFrames;
    private float degreesPerFrame;
    private final float originalTilt;
    private String iconPath;

    protected AbstractMeleeWeaponView(IWeapon model, float tilt, String iconPath) {
        super(model);
        if (model instanceof IMelee) {
            this.model = (IMelee)model;
        } else {
            throw new RuntimeException("Model was not IRanged");
        }
        this.tilt = tilt;
        this.originalTilt = tilt;
        swingFrames = this.model.getCooldown();
        degreesPerFrame = this.model.getSwingDegrees() * (this.model.getSwingDegrees() < 350 ? 2 : 1) / swingFrames ;
        this.iconPath = iconPath;
    }

    protected IWeapon getModel() {
        return model;
    }

    protected boolean isAttacking() {
        return getModel().getCooldownFrames() == getModel().getCooldown()-1;
    }

    protected float getRotation() {
        return getModel().getFacing() < 0 ? tilt : -tilt;
    }

    protected float getXScale(float scale) {
        return model.getFacing() < 0 ? -scale : scale;
    }

    private void updateTilt() {
        if (model.getCooldownFrames() == 0) {
            tilt = originalTilt;
            return;
        }
        if (degreesPerFrame > 11.5) {
            tilt = tilt + degreesPerFrame;
            return;
        }
        if (model.getCooldownFrames() < swingFrames/2) {
            tilt = tilt - degreesPerFrame;
        } else {
            tilt = tilt + degreesPerFrame;
        }
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        updateTilt();
    }

    @Override
    public void drawIcon(IGraphicsAdapter graphics, Point point) {
        graphics.draw(iconPath, point);
    }
}
