package se.chalmers.get_rect.game.entities.item.model;

import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.entities.item.swing.SwingFactory;

public class OpSwordNEtt extends AbstractMeleeWeapon {

    private static final int WIDTH = 175;
    private static final int HEIGHT = 150;
    private static final int DAMAGE = 2;
    private static final int FRAMES = 30;

    public OpSwordNEtt(IPhysicsModel owner, SwingFactory swingFactory) {
        super(owner, swingFactory, WIDTH, HEIGHT, DAMAGE, FRAMES);
        setUsable(true);
    }

}
