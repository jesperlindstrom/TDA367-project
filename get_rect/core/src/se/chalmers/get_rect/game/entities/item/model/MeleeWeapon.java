package se.chalmers.get_rect.game.entities.item.model;

import se.chalmers.get_rect.game.entities.IEntityHolder;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.entities.item.swing.SwingFactory;
import se.chalmers.get_rect.utilities.Point;

public class MeleeWeapon extends AbstractWeapon implements IMelee {

    private final int width;
    private final int height;
    private final int damage;
    private final int cooldown;
    private final float swingDegrees;
    private SwingFactory swingFactory;
    private boolean usable;
    private boolean solid;

    public MeleeWeapon(IPhysicsModel user, String type, SwingFactory swingFactory, int width, int height, int damage, int cooldown, float swingDegrees, boolean solid) {
        super(user, type);
        this.width = width;
        this.height = height;
        this.damage = damage;
        this.cooldown = cooldown;
        this.swingDegrees = swingDegrees == 0 ? 90f : swingDegrees;
        this.swingFactory = swingFactory;
        this.solid = solid;
    }

    public MeleeWeapon(IPhysicsModel user, String type, SwingFactory swingFactory, int width, int height, int damage, int cooldown) {
        this(user, type, swingFactory, width, height, damage, cooldown, 90f, false);
    }


    public SwingFactory getSwingFactory() {
        return swingFactory;
    }

    @Override
    public void use(Point aimDirection, IEntityHolder entityHolder) {
        if (getUsable()) {
            setUsable(false);
            setCooldownFrames(cooldown);
            entityHolder.add(getSwingFactory().make(damage, width, height, cooldown, getUser(), this, solid));
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
}
