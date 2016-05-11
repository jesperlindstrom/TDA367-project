package se.chalmers.get_rect.game.entities.item.model;

import se.chalmers.get_rect.game.entities.AbstractModel;
import se.chalmers.get_rect.game.entities.item.IWeapon;
import se.chalmers.get_rect.utilities.Point;

public abstract class AbstractWeapon extends AbstractModel implements IWeapon {

    protected AbstractWeapon(Point position) {
        super(position);
    }
}
