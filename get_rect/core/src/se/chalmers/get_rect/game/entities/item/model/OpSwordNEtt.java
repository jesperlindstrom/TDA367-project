package se.chalmers.get_rect.game.entities.item.model;

import se.chalmers.get_rect.game.entities.IEntityHolder;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.entities.item.swing.SwingFactory;
import se.chalmers.get_rect.utilities.Point;

public class OpSwordNEtt extends AbstractMeleeWeapon implements IMelee {

    private static final int FRAMES = 30;
    private static final int WIDTH = 175;
    private static final int HEIGHT = 150;
    private static final int DAMAGE = 2;
    private SwingFactory swingFactory;
    private boolean usable;

    public OpSwordNEtt(IPhysicsModel owner, SwingFactory swingFactory) {
        super(owner);
        this.swingFactory = swingFactory;
        usable = true;
    }

    @Override
    public void use(Point aimDirection, IEntityHolder entityHolder) {
        if (usable) {
            usable = false;
            setUseFrames(FRAMES);
            entityHolder.add(swingFactory.make(DAMAGE, WIDTH, HEIGHT, FRAMES, getUser(), this));
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
        return FRAMES;
    }
}
