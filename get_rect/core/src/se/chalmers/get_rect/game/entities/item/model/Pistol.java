package se.chalmers.get_rect.game.entities.item.model;

import se.chalmers.get_rect.game.entities.AbstractModel;
import se.chalmers.get_rect.game.entities.IEntityHolder;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.entities.item.IRanged;
import se.chalmers.get_rect.game.entities.projectile.ProjectileFactory;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.utilities.Point;

public class Pistol extends AbstractModel implements IRanged {

    private static final int SPEED = 200;
    private static final int DAMAGE = 10;
    private ProjectileFactory projectileFactory;
    private IPhysicsModel model;


    public Pistol(IRectangleFactoryAdapter factoryAdapter, IPhysicsModel user) {
        super(user.getPosition());
        this.projectileFactory = new ProjectileFactory(factoryAdapter);
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
