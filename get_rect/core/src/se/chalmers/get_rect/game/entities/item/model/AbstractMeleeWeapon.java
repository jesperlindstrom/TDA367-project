package se.chalmers.get_rect.game.entities.item.model;

import se.chalmers.get_rect.game.entities.IPhysicsModel;

public abstract class AbstractMeleeWeapon extends AbstractWeapon implements IMelee {

    protected AbstractMeleeWeapon(IPhysicsModel user) {
        super(user);
    }

}
