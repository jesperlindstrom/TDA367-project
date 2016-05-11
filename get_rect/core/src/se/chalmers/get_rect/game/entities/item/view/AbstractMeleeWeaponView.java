package se.chalmers.get_rect.game.entities.item.view;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.game.entities.AbstractView;
import se.chalmers.get_rect.game.entities.item.IMelee;
import se.chalmers.get_rect.game.entities.item.IWeapon;
import se.chalmers.get_rect.utilities.Point;

public class AbstractMeleeWeaponView extends AbstractView {

    private IWeapon model;
    private float tilt;
    private int swingFrames;
    private float degreesPerFrame;

    protected AbstractMeleeWeaponView(IWeapon model, float tilt) {
        super(model);
        this.model = model;
        this.tilt = tilt;
        swingFrames = ((IMelee)model).getSwingFrames();
        degreesPerFrame = 450 / swingFrames ;

    }

    protected IWeapon getModel() {
        return model;
    }

    protected float getRotation() {
        return getModel().getFacing() < 0 ? tilt : -tilt;
    }

    protected float getXScale(float scale) {
        return model.getAimDirection().getX() < 0 ? -scale : scale;
    }

    private void updateTilt() {
        if (model.getUsedFrames() == 0) return;
        if (model.getUsedFrames() < swingFrames/2) {
            tilt = tilt - degreesPerFrame;
        } else {
            tilt = tilt + degreesPerFrame;
        }
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        updateTilt();
        graphics.draw("img/entities/player/hand.png", getModel().getHandPos(), new Point(0, 0), 1, 1, getRotation());
    }
}