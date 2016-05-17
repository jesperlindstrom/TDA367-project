package se.chalmers.get_rect.game.entities.item.model;

import se.chalmers.get_rect.game.entities.IEntityHolder;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.entities.item.swing.SwingFactory;
import se.chalmers.get_rect.utilities.Point;

public class MeleeWeapon extends AbstractWeapon implements IMelee {

    private final int width;
    private final int height;
    private final int damage;
    private final int frames;
    private final float swingDegrees;
    private SwingFactory swingFactory;
    private boolean usable;

    public MeleeWeapon(IPhysicsModel user, String type, SwingFactory swingFactory, int width, int height, int damage, int frames, float swingDegrees) {
        super(user, type);
        this.width = width;
        this.height = height;
        this.damage = damage;
        this.frames = frames;
        this.swingDegrees = swingDegrees;
        this.swingFactory = swingFactory;
    }

    public MeleeWeapon(IPhysicsModel user, String type, SwingFactory swingFactory, int width, int height, int damage, int frames) {
        this(user, type, swingFactory, width, height, damage, frames, 90f);
    }


    public SwingFactory getSwingFactory() {
        return swingFactory;
    }

    @Override
    public void use(Point aimDirection, IEntityHolder entityHolder) {
        if (getUsable()) {
            setUsable(false);
            setUseFrames(frames);
            entityHolder.add(getSwingFactory().make(damage, width, height, frames, getUser(), this));
        }
    }

    @Override
    public void update(double delta) {
        super.update(delta);
        if (getUsedFrames() == 0) {
            usable = true;
        }
    }

    protected void setUsable(boolean usable) {
        this.usable = usable;
    }

    protected boolean getUsable() {
        return this.usable;
    }

    @Override
    public int getSwingFrames() {
        return frames;
    }

    public float getSwingDegrees() {
        return swingDegrees;
    }
}
