package se.chalmers.get_rect.game.entities.item.model;

import se.chalmers.get_rect.game.entities.IEntityHolder;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.entities.item.IRanged;
import se.chalmers.get_rect.game.entities.projectile.ProjectileFactory;
import se.chalmers.get_rect.utilities.Point;

public class Pistol extends AbstractWeapon implements IRanged {

    private static final int SPEED = 200;
    private static final int DAMAGE = 10;
    private ProjectileFactory projectileFactory;


    public Pistol(IPhysicsModel user, ProjectileFactory projectileFactory) {
        super(user.getPosition(), user);
        this.projectileFactory = projectileFactory;
        setAimDirection(user.getVelocity().normalize());
    }

    @Override
    public void use(Point aimDirection, IEntityHolder entityHolder) {
        setAimDirection(aimDirection);
        setUseFrames(5);
        entityHolder.add(projectileFactory.make(getSpawnPoint(), getFireVelocity(SPEED), DAMAGE, getModel()));
    }

    @Override
    public void remove() {
        setShouldBeRemoved();
    }

    public Point getSpawnPoint() {
        Point offset = getHandPos();

        if (getAimDirection().getX() != 0) {
            offset = offset.addY(20);
        }

        if (getAimDirection().getY() != 0) {
            offset = offset.addX(getAimDirection().getY() < 0 ? 25 : -25 * getFacing());
        }

        return new Point(offset.add(getAimDirection().multiply(50)));
    }

}
