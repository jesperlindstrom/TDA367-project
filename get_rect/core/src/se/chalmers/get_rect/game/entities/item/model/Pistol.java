package se.chalmers.get_rect.game.entities.item.model;

import se.chalmers.get_rect.game.entities.AbstractModel;
import se.chalmers.get_rect.game.entities.IEntityHolder;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.entities.item.IRanged;
import se.chalmers.get_rect.game.entities.projectile.ProjectileFactory;
import se.chalmers.get_rect.utilities.Point;

public class Pistol extends AbstractWeapon implements IRanged {

    private static final int SPEED = 200;
    private static final int DAMAGE = 10;
    private ProjectileFactory projectileFactory;
    private IPhysicsModel model;


    public Pistol(IPhysicsModel user, ProjectileFactory projectileFactory) {
        super(user.getPosition());
        this.projectileFactory = projectileFactory;
        this.model = user;
    }

    @Override
    public void use(Point direction, IEntityHolder entityHolder) {
        entityHolder.add(projectileFactory.make(getPosition(), direction.multiply(SPEED), DAMAGE, model));
    }

    @Override
    public void remove() {
        setShouldBeRemoved();
    }

    @Override
    public Point getPosition() {
        return new Point(model.getPosition().add(30, 50));
    }

}
