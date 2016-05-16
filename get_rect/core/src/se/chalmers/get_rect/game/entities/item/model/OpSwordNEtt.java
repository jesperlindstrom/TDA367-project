package se.chalmers.get_rect.game.entities.item.model;

import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.entities.item.swing.SwingFactory;

public class OpSwordNEtt extends AbstractMeleeWeapon {


    public OpSwordNEtt(IPhysicsModel owner, SwingFactory swingFactory, int frames, int width, int height, int damage) {
        super(owner, swingFactory, frames, width, height, damage);
        setUsable(true);

    }
    public OpSwordNEtt(IPhysicsModel owner, SwingFactory swingFactory){
        this(owner, swingFactory, 30, 175, 150, 2);
    }
}
