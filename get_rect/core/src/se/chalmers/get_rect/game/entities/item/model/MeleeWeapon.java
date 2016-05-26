package se.chalmers.get_rect.game.entities.item.model;

import se.chalmers.get_rect.game.entities.IEntityHolder;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.entities.item.swing.SwingFactory;
import se.chalmers.get_rect.utilities.Point;

public class MeleeWeapon extends AbstractWeapon implements IMelee {

    private final int reach;
    private final int damage;
    private final int cooldown;
    private final int startTilt;
    private final float swingDegrees;
    private SwingFactory swingFactory;
    private int tilt;
    private boolean usable;
    private boolean solid;

    public MeleeWeapon(IPhysicsModel user, String type, SwingFactory swingFactory, int reach, int damage, int cooldown, float swingDegrees, int startTilt, boolean solid) {
        super(user, type);
        this.reach = reach;
        this.damage = damage;
        this.cooldown = cooldown;
        this.swingDegrees = swingDegrees == 0 ? 90f : swingDegrees;
        this.swingFactory = swingFactory;
        this.solid = solid;
        this.startTilt = startTilt;
        this.tilt = startTilt;
        this.usable = true;
    }


    public SwingFactory getSwingFactory() {
        return swingFactory;
    }

    @Override
    public void use(Point aimDirection, IEntityHolder entityHolder) {
        if (getUsable()) {
            setUsable(false);
            setCooldownFrames(cooldown);
            entityHolder.add(getSwingFactory().make(damage, reach, cooldown, getUser(), this, solid));
        }
    }

    @Override
    public int getCooldown() {
        return cooldown;
    }

    @Override
    public void update(double delta) {
        super.update(delta);
        if (getCooldownFrames() == 0) {
            usable = true;
            tilt = startTilt*getFacing();
        } else if (swingDegrees > 350) {
            tilt = tilt + (int)swingDegrees*getFacing()/cooldown;
        } else if (getCooldownFrames() > getCooldown()/2) {
            tilt = tilt + (int)swingDegrees*getFacing()*2/cooldown;
        } else if (getCooldownFrames() < getCooldown()/2) {
            tilt = tilt - (int)swingDegrees*getFacing()*2/cooldown;
        }
    }

    protected void setUsable(boolean usable) {
        this.usable = usable;
    }

    protected boolean getUsable() {
        return this.usable;
    }

    public float getSwingDegrees() {
        return swingDegrees;
    }

    @Override
    public int getTilt() {
        return tilt;
    }
}
