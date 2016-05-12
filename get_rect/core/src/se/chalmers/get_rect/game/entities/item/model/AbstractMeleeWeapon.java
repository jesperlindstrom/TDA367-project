package se.chalmers.get_rect.game.entities.item.model;

import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.entities.item.IMelee;

public abstract class AbstractMeleeWeapon extends AbstractWeapon implements IMelee {

    protected AbstractMeleeWeapon(IPhysicsModel user) {
        super(user);
    }

}
