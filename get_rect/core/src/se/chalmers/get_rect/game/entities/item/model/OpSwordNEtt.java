package se.chalmers.get_rect.game.entities.item.model;

import se.chalmers.get_rect.game.entities.IEntityHolder;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.entities.item.swing.SwingFactory;
import se.chalmers.get_rect.utilities.Point;

public class OpSwordNEtt extends AbstractMeleeWeapon implements IMelee {

    private final int frames;
    private final int width;
    private final int height;
    private final int damage;
    private SwingFactory swingFactory;
    private boolean usable;

    public OpSwordNEtt(IPhysicsModel owner, SwingFactory swingFactory, int frames, int width, int height, int damage) {
        super(owner);
        this.swingFactory = swingFactory;
        this.frames = frames;
        this.width = width;
        this.height = height;
        this.damage = damage;
        usable = true;
    }
    public OpSwordNEtt(IPhysicsModel owner, SwingFactory swingFactory){
        this(owner, swingFactory, 30, 175, 150, 2);
    }

    @Override
    public void use(Point aimDirection, IEntityHolder entityHolder) {
        if (usable) {
            usable = false;
            setUseFrames(frames);
            entityHolder.add(swingFactory.make(damage, width, height, frames, getUser(), this));
        }
    }

    @Override
    public void update(double delta) {
        super.update(delta);
        if (getUsedFrames() == 0) {
            usable = true;
        }
    }

    @Override
    public int getSwingFrames() {
        return frames;
    }
}
