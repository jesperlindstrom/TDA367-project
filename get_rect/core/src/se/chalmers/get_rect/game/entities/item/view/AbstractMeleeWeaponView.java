package se.chalmers.get_rect.game.entities.item.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractView;
import se.chalmers.get_rect.game.entities.item.model.IMelee;
import se.chalmers.get_rect.game.entities.item.model.IWeapon;

public class AbstractMeleeWeaponView extends AbstractView {

    private IMelee model;
    private float tilt;
    private int swingFrames;
    private float degreesPerFrame;
    private final float originalTilt;

    protected AbstractMeleeWeaponView(IWeapon model, float tilt) {
        super(model);
        if (model instanceof IMelee) {
            this.model = (IMelee)model;
        } else {
            throw new RuntimeException("Model was not IRanged");
        }
        this.tilt = tilt;
        this.originalTilt = tilt;
        swingFrames = this.model.getSwingFrames();
        degreesPerFrame = 180f / swingFrames ;

    }

    protected IWeapon getModel() {
        return model;
    }

    protected float getRotation() {
        return getModel().getFacing() < 0 ? tilt : -tilt;
    }

    protected float getXScale(float scale) {
        return model.getFacing() < 0 ? -scale : scale;
    }

    private void updateTilt() {
        if (model.getUsedFrames() == 0) {
            tilt = originalTilt;
            return;
        }
        if (model.getUsedFrames() < swingFrames/2) {
            tilt = tilt - degreesPerFrame;
        } else {
            tilt = tilt + degreesPerFrame;
        }
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
//        graphics.draw("img/entities/player/hand.png", getModel().getHandPos(), new Point(0, 0), 1, 1, getRotation());
        updateTilt();
    }
}
