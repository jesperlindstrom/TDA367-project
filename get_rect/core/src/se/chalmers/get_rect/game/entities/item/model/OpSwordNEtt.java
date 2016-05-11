package se.chalmers.get_rect.game.entities.item.model;

import se.chalmers.get_rect.game.entities.IEntityHolder;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.entities.item.IMelee;
import se.chalmers.get_rect.utilities.Point;

public class OpSwordNEtt extends AbstractWeapon implements IMelee {

    protected OpSwordNEtt(Point position, IPhysicsModel model) {
        super(position, model);
    }

    @Override
    public void use(Point direction, IEntityHolder scene) {

    }

    @Override
    public void remove() {

    }

    public Point getSpawnPoint() {
        return null;
    }
}
