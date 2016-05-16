package se.chalmers.get_rect.game.entities.item.model;

import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.entities.item.swing.SwingFactory;

public class OpAxe extends AbstractMeleeWeapon {

    private static final int WIDTH = 130;
    private static final int HEIGHT = 100;
    private static final int DAMAGE = 1;
    private static final int FRAMES = 30;

    public OpAxe(IPhysicsModel user, SwingFactory swingFactory) {
        super(user, swingFactory, WIDTH, HEIGHT, DAMAGE, FRAMES);
        setUsable(true);
    }

}
