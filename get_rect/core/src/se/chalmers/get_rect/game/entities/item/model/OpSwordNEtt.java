package se.chalmers.get_rect.game.entities.item.model;

import se.chalmers.get_rect.game.entities.AbstractModel;
import se.chalmers.get_rect.game.entities.IEntityHolder;
import se.chalmers.get_rect.game.entities.item.IMelee;
import se.chalmers.get_rect.utilities.Point;

public class OpSwordNEtt extends AbstractWeapon implements IMelee {

    protected OpSwordNEtt(Point position) {
        super(position);
    }

    @Override
    public void use(Point direction, IEntityHolder scene) {

    }

    @Override
    public void remove() {

    }
}
